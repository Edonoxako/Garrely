package live.senya.garrely.ui.main.view

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import live.senya.garrely.R
import live.senya.garrely.di.Scopes
import live.senya.garrely.entity.Photo
import live.senya.garrely.ui.common.PaddingItemDecoration
import live.senya.garrely.ui.photogrid.presenter.PhotoGridPresenter
import live.senya.garrely.ui.photogrid.view.PaginationScrollListener
import live.senya.garrely.ui.photogrid.view.PhotoAdapter
import live.senya.garrely.ui.photogrid.view.PhotoGridView
import toothpick.Toothpick

class MainActivity : MvpAppCompatActivity(), PhotoGridView {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initList()
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    @InjectPresenter
    lateinit var presenter: PhotoGridPresenter

    private val gridSpanSize: Int
        get() = when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> 3
            Configuration.ORIENTATION_PORTRAIT -> 2
            else -> 2
        }

    private val photoAdapter: PhotoAdapter by lazy {
        PhotoAdapter(presenter::openPhotoPager)
    }

    override fun showPhotos(photos: List<Photo>) {
        photoAdapter.setItems(photos)
    }

    @ProvidePresenter
    fun providePresenter(): PhotoGridPresenter {
        return Toothpick.openScope(Scopes.APP_SCOPE)
                .getInstance(PhotoGridPresenter::class.java)
    }

    private fun initList() = with(recyclerView) {
        val gridLayoutManager = GridLayoutManager(this@MainActivity, gridSpanSize)
        layoutManager = gridLayoutManager
        adapter = photoAdapter
        addOnScrollListener(PaginationScrollListener(gridLayoutManager, presenter::fetchNextPage))
        setHasFixedSize(true)
        PaddingItemDecoration(
                resources.getDimensionPixelSize(R.dimen.photo_grid_item_padding),
                gridSpanSize
        )
                .let(::addItemDecoration)
    }
}
