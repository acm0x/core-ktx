package uk.acm64.lastsearch.feature.artist.data.repository

import uk.acm64.lastsearch.feature.artist.data.LastFmRetrofitService
import uk.acm64.lastsearch.feature.artist.domain.model.Artist
import uk.acm64.lastsearch.feature.artist.domain.model.ArtistInfo
import uk.acm64.lastsearch.feature.artist.domain.repository.LastFmRepository
import javax.inject.Inject

class LastFmRepositoryImpl @Inject constructor(private val lastFmRetrofitService: LastFmRetrofitService) :
    LastFmRepository {
    override suspend fun getArtists(query: String): List<Artist> =
        lastFmRetrofitService.getArtists(query)
            .results
            .artistmatches
            .toArtists()

    override suspend fun getArtistInfo(artistId: String): ArtistInfo? =
        lastFmRetrofitService.getArtistInfo(artistId)
            ?.artist
            ?.toArtistInfo()

}