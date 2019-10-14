package uk.acm64.lastsearch.feature.artist.data.model

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be`
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import uk.acm64.lastsearch.feature.artist.domain.model.ArtistBio

const val NAME = "ARTIST"
const val SUMMARY = "SUMMARY"
const val CONTENTS = "CONTENT"
const val ID = "ID"
const val IMAGE_URL = "IMAGE_URL"

@RunWith(MockitoJUnitRunner::class)
class ServiceApiResponsesTest {

    @Mock
    lateinit var artistBioData: ArtistBioData

    @Mock
    lateinit var artistBio: ArtistBio

    @Test
    fun toArtists() {
        val artistSearchData = ArtistSearchData(listOf(ArtistData(ID, NAME)))

        val artists = artistSearchData.toArtists()

        artists.size `should be equal to`  1
        artists[0].name `should be` NAME
        artists[0].id `should be` ID
    }

    @Test
    fun toArtistInfo() {
        val artistInfoData = ArtistInfoData(ID, NAME, listOf(), artistBioData)
        artistBioData.stub {
            on {toArtistBio()} doReturn artistBio
        }

        val artistInfo = artistInfoData.toArtistInfo()

        artistInfo.name `should be equal to` NAME
        artistInfo.bio `should be` artistBio
    }

    @Test
    fun toArtistBio() {
        val artistBioData = ArtistBioData(SUMMARY, CONTENTS)

        val artistBio = artistBioData.toArtistBio()

        artistBio.content `should be equal to` CONTENTS
        artistBio.summary `should be equal to` SUMMARY
    }
}