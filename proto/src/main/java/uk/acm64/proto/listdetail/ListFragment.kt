package uk.acm64.proto.listdetail

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.list_fragment.*
import uk.acm64.contract.ProtoBaseFragment
import uk.acm64.proto.R

class ListFragment: ProtoBaseFragment() {
    override fun layoutId() = R.layout.list_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list_view.adapter = ListAdapter().apply {
            listUi = listOf("A", "B", "C", "D").map { ListRowUi(it) }
        }
    }
}
