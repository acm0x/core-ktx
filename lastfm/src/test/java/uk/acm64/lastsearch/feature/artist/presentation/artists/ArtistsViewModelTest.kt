package uk.acm64.lastsearch.feature.artist.presentation.artists

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
import uk.acm64.lastsearch.feature.artist.CoroutinesTestRule
import uk.acm64.lastsearch.feature.artist.domain.usecase.GetArtistsUseCase

@RunWith(MockitoJUnitRunner::class)
class ArtistsViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getArtistsUseCase: GetArtistsUseCase

    lateinit var cut: ArtistsViewModel

    @Before
    fun setUp() {
        cut = ArtistsViewModel(getArtistsUseCase)
    }

    @Test
    fun `perform search`() {
        cut.search("A")

        cut.state.value `should be` ArtistsViewState.Loading
        verify(getArtistsUseCase).invoke(any(), any(), any())
    }

}