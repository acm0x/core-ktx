package uk.acm64.lastsearch.feature.artist.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import uk.acm64.test.utils.CoroutinesTestRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.stub
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.internal.assertSame
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.fail
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import uk.acm64.lastsearch.feature.artist.domain.model.ArtistInfo
import uk.acm64.lastsearch.feature.artist.domain.repository.LastFmRepository

@RunWith(MockitoJUnitRunner::class)
internal class GetArtistInfoUseCaseTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var lastFmRepository: LastFmRepository

    @Mock
    lateinit var artistInfo: ArtistInfo

    lateinit var cut: GetArtistInfoUseCase

    @Before
    fun setUp() {
        cut = GetArtistInfoUseCase(lastFmRepository)
    }

    @Test
    fun `run Get Artist Info Use Case`() = runBlockingTest {
        lastFmRepository.stub {
            onBlocking { getArtistInfo(any()) } doReturn artistInfo
        }

        val result = cut.run(GetArtistInfoUseCase.Params("ID"))

        assertTrue(result.isRight)
        result.either({ fail("Should be right") },
            {
                assertSame(artistInfo, it)
            })
    }

    @Test
    fun `run Get Artist Infor Use Case with failure`() = runBlockingTest {
        lastFmRepository.stub {
            onBlocking { getArtistInfo(any()) } doThrow IllegalStateException()
        }

        val result = cut.run(GetArtistInfoUseCase.Params("ID"))

        assertTrue(result.isLeft)
        result.either(
            {
                assertSame(GetArtistInfoUseCase.GetArtistInfoFailure, it)
            }, { fail("Should be left") })
    }

    @Test
    fun `run Get Artist Infor Use Case with empty`() = runBlockingTest {
        lastFmRepository.stub {
            onBlocking { getArtistInfo(any()) } doReturn null
        }

        val result = cut.run(GetArtistInfoUseCase.Params("ID"))

        assertTrue(result.isLeft)
        result.either(
            {
                assertSame(GetArtistInfoUseCase.GetArtistInfoNxFailure, it)
            }, { fail("Should be left") })
    }
}