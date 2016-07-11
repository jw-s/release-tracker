package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDto
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.interfaces.Adapter

import org.apache.log4j.Logger

class ArtifactToArtifactDtoAdapter : Adapter<Artifact, ArtifactDto> {

    companion object {
        private val LOGGER = Logger.getLogger(ArtifactToArtifactDtoAdapter::class.java)
    }

    override fun adapt(incoming: Artifact): ArtifactDto {
        LOGGER.info("Adapt - in: ${incoming.javaClass}")

        val out = ArtifactDto(groupId = incoming.id?.groupId!!,
                artifactId = incoming.id?.artifactId!!,
                version = incoming.id?.version!!)

        LOGGER.info("Adapt - out: ${out.javaClass}")
        return out
    }
}
