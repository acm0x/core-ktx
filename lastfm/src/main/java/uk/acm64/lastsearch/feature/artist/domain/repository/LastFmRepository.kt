package uk.acm64.lastsearch.feature.artist.domain.repository

import uk.acm64.lastsearch.feature.artist.domain.model.Artist
import uk.acm64.lastsearch.feature.artist.domain.model.ArtistInfo

interface LastFmRepository {
    suspend fun getArtists(query: String): List<Artist>
    suspend fun getArtistInfo(artistId: String): ArtistInfo?
}