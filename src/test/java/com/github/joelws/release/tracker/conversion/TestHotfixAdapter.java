package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDTO;
import com.github.joelws.release.tracker.dto.release.ReleaseDTO;
import com.github.joelws.release.tracker.entity.release.Release;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class) public class TestHotfixAdapter
{
    @Mock private ReleaseDTO from;

    @Mock private ArtifactDTO mockArtifactDTO;

    private HotfixAdapter adapter;

    @Before public void setUp() throws Exception
    {
        adapter = new HotfixAdapter();

    }

    @Test public void testAdapter() throws Exception
    {
        List<ArtifactDTO> artifactList = new ArrayList<>();
        artifactList.add(mockArtifactDTO);

        Mockito.when(from.getArtifacts()).thenReturn(artifactList);
        Mockito.when(from.getName()).thenReturn("R1-HF1");

        Release result = adapter.adapt(from);

        Mockito.verify(from, Mockito.times(1)).getArtifacts();
        Mockito.verify(from, Mockito.never()).getHotfixes();
        Mockito.verify(from, Mockito.times(1)).getName();

        Assert.assertEquals(result.getName(), "R1-HF1");

        Assert.assertTrue(result.getArtifacts().size() == 1);

        Assert.assertTrue(result.getHotfixes().size() == 0);
    }
}