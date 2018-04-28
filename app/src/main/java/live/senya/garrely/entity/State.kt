package live.senya.garrely.entity

import live.senya.garrely.entity.local.db.Page
import java.util.*

data class State(
        var data: SortedMap<Page, List<Photo>> = TreeMap(),
        var isLoading: Boolean = false,
        var error: Exception? = null
)