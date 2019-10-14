package uk.acm64.lastsearch.feature.artist.presentation.artists

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_artists.*
import uk.acm64.core.extension.*
import uk.acm64.lastsearch.R
import uk.acm64.lastsearch.contract.LastSearchBaseFragment
import uk.acm64.lastsearch.feature.artist.presentation.artistinfo.ArtistInfoFragment

class ArtistsFragment : LastSearchBaseFragment() {
    override fun layoutId() = R.layout.fragment_artists

    private lateinit var artistListAdapter: ArtistListAdapter
    private lateinit var artistsViewModel: ArtistsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        appComponent.inject(this)
        artistsViewModel = viewModel(viewModelFactory) {
            observe(state, ::renderSearchResults)
            failure(failure, ::handleFailure)
        }
        artistListAdapter = ArtistListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        artistListAdapter.itemClickListener = { artist ->
            Bundle().apply {
                putString(ArtistInfoFragment.ARTIST_ID, artist.artistId)
            }.also {
                findNavController().navigate(
                    R.id.action_lastSearchFragment_to_artistInfoFragment,
                    it
                )
            }
        }
        list_view.adapter = artistListAdapter
        val value = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                artistsViewModel.search(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        }
        artist_search_widget.setOnQueryTextListener(value);
    }

    private fun renderSearchResults(artistsViewState: ArtistsViewState?) {
        when (artistsViewState) {
            is ArtistsViewState.Empty -> showEmpty()
            is ArtistsViewState.Loading -> showLoading()
            is ArtistsViewState.Available -> showList(artistsViewState.results)
        }
    }

    private fun showLoading() {
        artists_progress.visible()
        artists_empty.invisible()
        list_view.invisible()

    }

    private fun showList(results: List<ArtistListRowUi>) {
        artistListAdapter.artistListUi = results
        artists_progress.invisible()
        artists_empty.invisible()
        list_view.visible()
    }

    private fun showEmpty() {
        artistListAdapter.artistListUi = listOf()
        artists_progress.invisible()
        artists_empty.visible()
        list_view.invisible()
    }

}