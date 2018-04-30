package live.senya.garrely.entity.mapper

import live.senya.garrely.entity.local.PhotoPage
import live.senya.garrely.entity.local.db.Page
import live.senya.garrely.entity.remote.ApiResponse
import javax.inject.Inject

class PhotoPageMapper @Inject constructor() : Mapper2<Page, ApiResponse, PhotoPage> {

    override fun map(item1: Page, item2: ApiResponse): PhotoPage {
        return PhotoPage(item1, item2.photos)
    }
}