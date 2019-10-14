package uk.acm64.lastsearch.feature.artist.domain.model

data class ArtistInfo(val name: String, val imageUrl: String?, val bio: ArtistBio)

data class ArtistBio(val summary: String, val content: String)
