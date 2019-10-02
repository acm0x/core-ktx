package uk.acm64.proto

import androidx.lifecycle.MutableLiveData
import uk.acm64.core.BaseViewModel
import uk.acm64.core.Failure
import androidx.lifecycle.viewModelScope
import uk.acm64.core.UseCase
import javax.inject.Inject

data class ProtoModelState(val features: List<ProtoFeatureUi>)


class ProtoViewModel @Inject constructor(val getProtoFeatures: GetProtoFeatures, val mapper: ProtoFeatureUiMapper) : BaseViewModel() {
    var protoFeatureState: MutableLiveData<ProtoModelState> = MutableLiveData()

    init {
        loadFeatures()
    }

    private fun loadFeatures() = viewModelScope
            getProtoFeatures(UseCase.None()) { it.either(::handleDetailsFailure, ::handleDetails) }

    private fun handleDetailsFailure(failure: Failure) {
        return handleFailure(failure)
    }

    private fun handleDetails(features: List<ProtoFeature>) {
        protoFeatureState.postValue(ProtoModelState(mapper.toListView(features)))
    }
}
