package uk.acm64.proto.listdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*
import uk.acm64.proto.R

class ListAdapter : RecyclerView.Adapter<ListViewHolder>() {

    var listUi: List<ListRowUi> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = listUi.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUi[position])
    }

}

class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(listRowUi: ListRowUi) {
        itemView.apply {
            list_item_text.text = listRowUi.name
        }
    }
}
