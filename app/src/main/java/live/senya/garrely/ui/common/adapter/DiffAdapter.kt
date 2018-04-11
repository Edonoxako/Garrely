package live.senya.garrely.ui.common.adapter

import android.support.v7.widget.RecyclerView
import live.senya.garrely.util.extension.replaceWith

abstract class DiffAdapter<E : DiffCalculable, VH : RecyclerView.ViewHolder>
    : RecyclerView.Adapter<VH>() {

    protected val items = arrayListOf<E>()

    override fun getItemCount() = items.size

    fun setItems(newItems: List<E>) {
        val diffResult = DiffCalculator(items, newItems).calculateDiff()
        items.replaceWith(newItems)
        diffResult.dispatchUpdatesTo(this)
    }
}