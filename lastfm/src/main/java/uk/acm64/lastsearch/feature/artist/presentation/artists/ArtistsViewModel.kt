package uk.acm64.lastsearch.feature.artist.presentation.artists

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import uk.acm64.core.BaseViewModel
import uk.acm64.lastsearch.feature.artist.domain.model.Artist
import uk.acm64.lastsearch.feature.artist.domain.usecase.GetArtistsUseCase
import javax.inject.Inject

class ArtistsViewModel @Inject constructor(val getArtistsUseCase: GetArtistsUseCase) :
    BaseViewModel() {

    var state: MutableLiveData<ArtistsViewState> = MutableLiveData()

    fun search(query: String?) {
        if (query != null && query.isNotBlank()) {
            state.value = ArtistsViewState.Loading
            getArtistsUseCase(viewModelScope, GetArtistsUseCase.Params(query)) {
                it.either(::handleFailure, ::handleSuccess)
            }
        } else {
            // Not exploring the edge case when query is null instead of ""
            state.value = ArtistsViewState.Empty
        }
    }

    private fun handleSuccess(list: List<Artist>) {
        when {
            list.isEmpty() -> state.value = ArtistsViewState.Empty
            else -> state.value = ArtistsViewState.Available(mapToPresentation(list))
        }
    }

    private fun mapToPresentation(list: List<Artist>): List<ArtistListRowUi> = list.map {
        ArtistListRowUi(it.id, it.name)
    }

}

sealed class ArtistsViewState(searchResults: List<ArtistListRowUi> = listOf()) {
    object Loading : ArtistsViewState()
    object Empty : ArtistsViewState()
    data class Available(val results: List<ArtistListRowUi>) : ArtistsViewState(results)
}
