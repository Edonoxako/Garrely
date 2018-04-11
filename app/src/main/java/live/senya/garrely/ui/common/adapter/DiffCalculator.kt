package live.senya.garrely.ui.common.adapter

import android.support.v7.util.DiffUtil

class DiffCalculator(
        private val oldList: List<DiffCalculable>,
        private val newList: List<DiffCalculable>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].isTheSame(newList[newItemPosition])
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].isContentTheSame(newList[newItemPosition])
    }
    
    fun calculateDiff(): DiffUtil.DiffResult = DiffUtil.calculateDiff(this) 
}