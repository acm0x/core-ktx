package uk.acm64.lastsearch.feature.artist.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import uk.acm64.test.utils.CoroutinesTestRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.stub
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.fail
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import uk.acm64.lastsearch.feature.artist.domain.repository.LastFmRepository

@RunWith(MockitoJUnitRunner::class)
internal class GetArtistsUseCaseTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var lastFmRepository: LastFmRepository

    lateinit var cut: GetArtistsUseCase

    @Before
    fun setUp() {
        cut = GetArtistsUseCase(lastFmRepository)
    }

    @Test
    fun `run Get Artists Use Case`() = runBlockingTest {
        lastFmRepository.stub {
            onBlocking { getArtists(any()) } doReturn listOf()
        }

        val result = cut.run(GetArtistsUseCase.Params("a"))

        assertTrue(result.isRight)
        result.either({ fail("Should be right") },
            {
                assertTrue(it.isEmpty())
            })
    }

    @Test
    fun `run Get Artists Use Case with failure`() = runBlockingTest {
        lastFmRepository.stub {
            onBlocking { getArtists(any()) } doThrow IllegalStateException()
        }

        val result = cut.run(GetArtistsUseCase.Params("a"))

        assertTrue(result.isLeft)
    }
}