package uk.acm64.proto

import javax.inject.Inject

class ProtoFeatureUiMapper @Inject constructor() {
   fun toListView(features: List<ProtoFeature>): List<ProtoFeatureUi>  = features.map {ProtoFeatureUi(it.name)}

}
