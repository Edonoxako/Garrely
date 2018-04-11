package live.senya.garrely.ui.common

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class PaddingItemDecoration(
        private val mSizeGridSpacingPx: Int, 
        private val mGridSize: Int
) : RecyclerView.ItemDecoration() {

    private var leftSpacingIsRequired = false

    override fun getItemOffsets(
            outRect: Rect, 
            view: View, 
            parent: RecyclerView, 
            state: RecyclerView.State?
    ) {
        val frameWidth = ((parent.width - mSizeGridSpacingPx.toFloat() * (mGridSize - 1)) / mGridSize).toInt()
        val padding = parent.width / mGridSize - frameWidth
        val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewAdapterPosition
        
        if (itemPosition < mGridSize) {
            outRect.top = 0
        } else {
            outRect.top = mSizeGridSpacingPx
        }

        when {
            itemPosition % mGridSize == 0 -> {
                outRect.left = 0
                outRect.right = padding
                leftSpacingIsRequired = true
            }
            (itemPosition + 1) % mGridSize == 0 -> {
                leftSpacingIsRequired = false
                outRect.right = 0
                outRect.left = padding
            }
            leftSpacingIsRequired -> {
                leftSpacingIsRequired = false
                outRect.left = mSizeGridSpacingPx - padding
                if ((itemPosition + 2) % mGridSize == 0) {
                    outRect.right = mSizeGridSpacingPx - padding
                } else {
                    outRect.right = mSizeGridSpacingPx / 2
                }
            }
            (itemPosition + 2) % mGridSize == 0 -> {
                leftSpacingIsRequired = false
                outRect.left = mSizeGridSpacingPx / 2
                outRect.right = mSizeGridSpacingPx - padding
            }
            else -> {
                leftSpacingIsRequired = false
                outRect.left = mSizeGridSpacingPx / 2
                outRect.right = mSizeGridSpacingPx / 2
            }
        }
        
        outRect.bottom = 0
    }
}