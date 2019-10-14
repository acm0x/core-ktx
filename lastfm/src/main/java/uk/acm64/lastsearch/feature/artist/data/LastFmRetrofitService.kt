package uk.acm64.lastsearch.feature.artist.data

import retrofit2.http.POST
import retrofit2.http.Query
import uk.acm64.lastsearch.feature.artist.data.model.GetArtistInfoResponse
import uk.acm64.lastsearch.feature.artist.data.model.GetArtistsResponse

interface LastFmRetrofitService {
    @POST("./?method=artist.search")
    suspend fun getArtists(
        @Query("artist") phrase: String,
        @Query("limit") limit: Int = 60
    ): GetArtistsResponse

    @POST("./?method=artist.getInfo")
    suspend fun getArtistInfo(@Query("mbid") artistId: String): GetArtistInfoResponse?

}
