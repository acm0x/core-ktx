package uk.acm64.lastsearch.feature.artist.presentation.artistinfo

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_artist_info.*
import uk.acm64.core.extension.*
import uk.acm64.lastsearch.R
import uk.acm64.lastsearch.contract.LastSearchBaseFragment
import uk.acm64.lastsearch.feature.artist.domain.usecase.GetArtistInfoUseCase

class ArtistInfoFragment : LastSearchBaseFragment() {

    companion object {
        const val ARTIST_ID = "ArtistInfoFragment_key_ARTIST_ID"
    }

    override fun layoutId() = R.layout.fragment_artist_info

    private lateinit var artistInfoViewModel: ArtistInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        artistInfoViewModel = viewModel(viewModelFactory) {
            observe(state, ::renderInfo)
            failure(failure, ::handleFailure)
        }
    }

    private fun renderInfo(artistInfoViewState: ArtistInfoViewState?) {
        when (artistInfoViewState) {
            is ArtistInfoViewState.Loading -> showLoading()
            is ArtistInfoViewState.Available -> showInfo(artistInfoViewState.artistInfoUi)
        }
    }

    private fun showInfo(artistInfoUi: ArtistInfoUi) {
        artist_info_progress.invisible()
        artistInfoFragment_title.text = artistInfoUi.name
        artistInfoFragment_summary.text =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                Html.fromHtml(artistInfoUi.summary, Html.FROM_HTML_MODE_LEGACY);
            } else {
                Html.fromHtml(artistInfoUi.summary);
            }
        artistInfoFragment_summary.movementMethod = LinkMovementMethod.getInstance()
        if (artistInfoUi.imageUrl != null) {
            Glide.with(context)
                .load(artistInfoUi.imageUrl)
                .into(artistInfoFragment_image)
        }
    }

    private fun showLoading() {
        artist_info_progress.visible()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val artistId = arguments?.getString(ARTIST_ID)
        if (artistId != null) {
            artistInfoViewModel.loadData(artistId)
        } else {
            handleFailure(GetArtistInfoUseCase.GetArtistInfoNxFailure)
        }
    }
}
