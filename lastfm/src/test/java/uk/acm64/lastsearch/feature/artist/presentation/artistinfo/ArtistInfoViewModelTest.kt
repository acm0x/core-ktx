package uk.acm64.lastsearch.feature.artist.presentation.artistinfo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.igorwojda.showcase.library.testutils.CoroutinesTestRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.amshove.kluent.`should be`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import uk.acm64.lastsearch.feature.artist.domain.usecase.GetArtistInfoUseCase

@RunWith(MockitoJUnitRunner::class)
class ArtistInfoViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getArtistInfoUseCase: GetArtistInfoUseCase

    lateinit var cut: ArtistInfoViewModel

    @Before
    fun setUp() {
        cut = ArtistInfoViewModel(getArtistInfoUseCase)
    }

    @Test
    fun `perform init`() {
        cut.loadData("ARTIST_ID")

        cut.state.value `should be` ArtistInfoViewState.Loading
        verify(getArtistInfoUseCase).invoke(any(), any(), any())
    }

}