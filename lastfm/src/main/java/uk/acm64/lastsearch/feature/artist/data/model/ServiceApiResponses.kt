package uk.acm64.lastsearch.feature.artist.data.model

import com.squareup.moshi.Json
import uk.acm64.lastsearch.feature.artist.domain.model.Artist
import uk.acm64.lastsearch.feature.artist.domain.model.ArtistBio
import uk.acm64.lastsearch.feature.artist.domain.model.ArtistInfo

data class GetArtistsResponse(val results: ArtistSearchResult)

data class ArtistSearchResult(val artistmatches: ArtistSearchData)

data class ArtistSearchData(val artist: List<ArtistData>) {
    fun toArtists() = artist.map { Artist(it.mbid, it.name) }
}

data class ArtistData(val mbid: String, val name: String)

data class GetArtistInfoResponse(val artist: ArtistInfoData)

data class ArtistInfoData(
    @field:Json(name = "mbid") val id: String,
    val name: String,
    @field:Json(name = "image") val images: List<ArtistImageData>?,
    val bio: ArtistBioData
) {
    fun toArtistInfo(): ArtistInfo {
        val imageUrl = images?.firstOrNull { it.size == "extralarge" }?.url
        return ArtistInfo(name, imageUrl, bio.toArtistBio())
    }
}

data class ArtistImageData(
    @field:Json(name = "#text") val url: String,
    val size: String
)

data class ArtistBioData(val summary: String, val content: String) {
    fun toArtistBio() = ArtistBio(summary, content)
}


