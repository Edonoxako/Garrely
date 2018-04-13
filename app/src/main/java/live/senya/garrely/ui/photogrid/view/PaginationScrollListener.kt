package live.senya.garrely.ui.photogrid.view

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class PaginationScrollListener(
        private val layoutManager: LinearLayoutManager,
        private val requestNextItems: () -> Unit
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) = with(layoutManager) {
        if (dy <= 0) return

        val scrolledOffItems = findFirstVisibleItemPosition()
        val visibleItems = childCount
        val itemsTotal = itemCount

        if (visibleItems + scrolledOffItems + VISIBILITY_THRESHOLD >= itemsTotal) {
            requestNextItems.invoke()
        }    
    }

    companion object {
        private const val VISIBILITY_THRESHOLD = 2
    }
}