package live.senya.garrely.entity.local

import live.senya.garrely.entity.Photo
import live.senya.garrely.entity.local.db.Page

data class PhotoPage(
        
        val page: Page,

        val photos: List<Photo>
)