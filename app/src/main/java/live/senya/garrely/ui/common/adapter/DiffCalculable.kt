package live.senya.garrely.ui.common.adapter

interface DiffCalculable {

    fun isTheSame(other: DiffCalculable): Boolean

    fun isContentTheSame(other: DiffCalculable): Boolean
}