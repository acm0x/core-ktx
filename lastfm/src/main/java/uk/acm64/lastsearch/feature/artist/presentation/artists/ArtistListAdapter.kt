package uk.acm64.lastsearch.feature.artist.presentation.artists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*
import uk.acm64.lastsearch.R
import kotlin.properties.Delegates

class ArtistListAdapter : RecyclerView.Adapter<ArtistListAdapter.ListViewHolder>() {

    var itemClickListener: ((artist: ArtistListRowUi) -> Unit)? = null
    var artistListUi: List<ArtistListRowUi> by Delegates.observable(listOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = artistListUi.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(artistListUi[position])
    }

    inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(artistListRowUi: ArtistListRowUi) {
            itemView.apply {
                list_item_text.text = artistListRowUi.name
                setOnClickListener {
                    itemClickListener?.invoke(artistListRowUi)
                }
            }
        }
    }
}
