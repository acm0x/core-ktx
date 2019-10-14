package uk.acm64.lastsearch.feature.artist.data.repository

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Answers
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import uk.acm64.lastsearch.feature.artist.data.LastFmRetrofitService
import uk.acm64.lastsearch.feature.artist.data.model.*
import uk.acm64.lastsearch.feature.artist.domain.model.Artist
import uk.acm64.lastsearch.feature.artist.domain.model.ArtistInfo
import kotlin.test.assertNotNull

@RunWith(MockitoJUnitRunner::class)
class LastFmRepositoryImplTest {

    @Mock
    lateinit var lastFmRetrofitService: LastFmRetrofitService

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    lateinit var getArtistsResponse: GetArtistsResponse

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    lateinit var getArtistInfoResponse: GetArtistInfoResponse

    @Mock
    lateinit var artistInfo: ArtistInfo

    lateinit var cut: LastFmRepositoryImpl

    @Before
    fun setUp() {
        cut = LastFmRepositoryImpl(lastFmRetrofitService)
    }

    @Test
    fun `retrieve Artist`() = runBlockingTest {
        lastFmRetrofitService.stub {
            onBlocking { getArtists(any(), any()) } doReturn getArtistsResponse
        }

        val element: ArtistData = mock { on { name } doReturn "a" }

        val art: Artist = mock()
        val artistSearchData: ArtistSearchData = mock {
            on { artist } doReturn listOf(element)
            on { toArtists() } doReturn listOf(art)
        }
        val artistSearchResult: ArtistSearchResult =
            mock { on { artistmatches } doReturn artistSearchData }
        getArtistsResponse.stub { on { results } doReturn artistSearchResult }

        val artists = cut.getArtists("a")

        artists.size `should be equal to` 1

    }

    @Test
    fun `retrieve Artist details`() = runBlockingTest {
        lastFmRetrofitService.stub {
            onBlocking { getArtistInfo(any()) } doReturn getArtistInfoResponse
        }
        val artistResult: ArtistInfoData = mock {
            on { name } doReturn NAME
            on { toArtistInfo() } doReturn artistInfo
        }
        getArtistInfoResponse.stub { on { artist } doReturn artistResult }

        val artist = cut.getArtistInfo("a")

        assertNotNull(artist)
        artist `should be` artistInfo

    }
}