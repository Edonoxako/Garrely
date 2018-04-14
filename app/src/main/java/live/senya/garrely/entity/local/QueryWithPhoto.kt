package live.senya.garrely.entity.local

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation
import live.senya.garrely.entity.Photo

// todo probably should be removed. Use direct db queries for photos instead
class QueryWithPhoto(
        
        @Embedded
        var queryToPhoto: QueryToPhoto? = null, 

        @Relation(
                parentColumn = QueryToPhoto.COLUMN_NAME_QUERY_ID,
                entityColumn = SearchQuery.COLUMN_NAME_ID
        )
        var queries: List<SearchQuery> = emptyList(),

        @Relation(
                parentColumn = QueryToPhoto.COLUMN_NAME_PHOTO_ID,
                entityColumn = Photo.COLUMN_NAME_ID
        )
        var photos: List<Photo> = emptyList()
) {
    
    val query: SearchQuery
        get() = queries.first()
}
