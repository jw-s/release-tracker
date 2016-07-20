package com.github.joelws.release.tracker.service.artifact.operation

import com.github.joelws.release.tracker.conversion.ArtifactDtoToArtifactAdapter
import com.github.joelws.release.tracker.dto.artifact.ArtifactDto
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.response.RestResponse.Success
import com.github.joelws.release.tracker.response.build
import com.github.joelws.release.tracker.service.ServiceExecution
import com.github.joelws.release.tracker.service.ServiceHelper
import com.github.joelws.release.tracker.service.ServiceOperation
import org.apache.log4j.Logger
import javax.ws.rs.core.Response

open class DeleteArtifactServiceOperation(val helper: ServiceHelper,
                                          val deleteArtifactServiceExecution: ServiceExecution<Artifact, Unit>) : ServiceOperation<String> {

    companion object {
        private val LOGGER = Logger.getLogger(DeleteArtifactServiceOperation::class.java)
    }

    override fun delegate(param: String?): Response {

        LOGGER.info("Starting DeleteArtifactServiceOperation, In: $param")

        val artifactDtoToArtifactAdapter = helper.factory.getImpl(ArtifactDtoToArtifactAdapter::class.java)

        val artifactInQuestion = artifactDtoToArtifactAdapter.adapt(helper.jsonAdapter.getObjectFromJson(param, ArtifactDto::class.java))

        deleteArtifactServiceExecution.execute(artifactInQuestion)
        return Success().build()

    }


}
