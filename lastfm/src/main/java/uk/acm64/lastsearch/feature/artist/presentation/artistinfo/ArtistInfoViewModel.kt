package uk.acm64.lastsearch.feature.artist.presentation.artistinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import uk.acm64.core.BaseViewModel
import uk.acm64.lastsearch.feature.artist.domain.model.ArtistInfo
import uk.acm64.lastsearch.feature.artist.domain.usecase.GetArtistInfoUseCase
import javax.inject.Inject

class ArtistInfoViewModel  @Inject constructor(val getArtistInfoUseCase: GetArtistInfoUseCase): BaseViewModel() {

    var state: MutableLiveData<ArtistInfoViewState> = MutableLiveData()

    fun loadData(artistId: String) {
        state.value = ArtistInfoViewState.Loading
        getArtistInfoUseCase(viewModelScope, GetArtistInfoUseCase.Params(artistId)) {
            it.either(::handleFailure, ::handleSuccess)
            }
        }

    private fun handleSuccess(artistInfo: ArtistInfo) {
        state.value = ArtistInfoViewState.Available(mapToPresentation(artistInfo))
    }

    private fun mapToPresentation(artistInfo: ArtistInfo): ArtistInfoUi = ArtistInfoUi(artistInfo.name, artistInfo.imageUrl, artistInfo.bio.summary)

}

sealed class ArtistInfoViewState() {
    object Loading: ArtistInfoViewState()
    data class Available(val artistInfoUi: ArtistInfoUi): ArtistInfoViewState()
}
