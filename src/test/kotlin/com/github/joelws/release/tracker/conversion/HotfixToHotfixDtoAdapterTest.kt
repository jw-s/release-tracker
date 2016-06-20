package com.github.joelws.release.tracker.conversion

import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.entity.release.Release
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*

class HotfixToHotfixDtoAdapterTest {

    private val from = Release()

    private val mockArtifact = Artifact()

    private val adapter = HotfixToHotfixDtoAdapter()

    @Test
    fun testAdapt() {

        val artifactList = ArrayList<Artifact>().apply {
            add(mockArtifact)
        }

        from.apply {
            name = "R1-HF1"
            artifacts = artifactList
        }

        val result = adapter.adapt(from)

        assertEquals("R1-HF1", result.name)

        assertTrue(result.artifacts.size == 1)

        assertTrue(result.hotfixes.isEmpty())
    }
}