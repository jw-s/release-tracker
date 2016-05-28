package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDTO;
import com.github.joelws.release.tracker.dto.release.ReleaseDTO;
import com.github.joelws.release.tracker.entity.release.Release;
import com.github.joelws.release.tracker.interfaces.Adapter;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReleaseAdapter implements Adapter<ReleaseDTO, Release> {
    private static final Logger LOGGER = Logger.getLogger(ReleaseAdapter.class);

    @Override
    public Release adapt(ReleaseDTO releaseDTO) {
        LOGGER.info("Adapt - in: " + releaseDTO.getClass());

        ArtifactAdapter artifactAdapter = new ArtifactAdapter();
        HotfixAdapter hotfixAdapter = new HotfixAdapter();

        List<ArtifactDTO> inArtifactList = releaseDTO.getArtifacts();
        Set<ReleaseDTO> inHotfixSet = releaseDTO.getHotfixes();

        Release out = new Release();

        out.setName(releaseDTO.getName());

        out.setArtifacts(inArtifactList
                .stream()
                .map(artifactAdapter::adapt)
                .collect(Collectors.toList()));


        out.setHotfixes(inHotfixSet
                .stream()
                .map(hotfixAdapter::adapt)
                .collect(Collectors.toSet()));

        LOGGER.info("Adapt - out: " + out.getClass());

        return out;

    }
}

