package com.github.joelws.release.tracker.service.release.operation;

import com.github.joelws.release.tracker.conversion.ReleaseDtoToReleaseAdapter
import com.github.joelws.release.tracker.conversion.ReleaseToReleaseDtoAdapter
import com.github.joelws.release.tracker.dto.release.ReleaseDto
import com.github.joelws.release.tracker.response.RestResponse.NotFound
import com.github.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.github.joelws.release.tracker.response.build
import com.github.joelws.release.tracker.service.ServiceHelper
import com.github.joelws.release.tracker.service.ServiceOperation
import com.github.joelws.release.tracker.service.release.execution.UpdateReleaseServiceExecution
import javax.ws.rs.core.Response

open class UpdateReleaseServiceOperation(private val helper: ServiceHelper,
                                         private val updateReleaseServiceExecution: UpdateReleaseServiceExecution) : ServiceOperation<String> {
    override fun delegate(param: String?): Response {
        val adapter = helper.factory.getImpl(ReleaseDtoToReleaseAdapter::class.java)

        val result = updateReleaseServiceExecution
                .execute(adapter.adapt(helper.jsonAdapter.getObjectFromJson(param, ReleaseDto::class.java)))

        return if (result != null) {
            val adapterForResponse = helper.factory.getImpl(ReleaseToReleaseDtoAdapter::class.java)
            val adaptedResult = adapterForResponse.adapt(result)
            SuccessWithEntity(adaptedResult).build()
        } else {
            NotFound("Release doesn't exist").build()
        }
    }
}