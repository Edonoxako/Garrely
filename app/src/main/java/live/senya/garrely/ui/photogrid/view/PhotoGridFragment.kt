package live.senya.garrely.ui.photogrid.view

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_photo_grid.*
import live.senya.garrely.R
import live.senya.garrely.di.Scopes
import live.senya.garrely.entity.Photo
import live.senya.garrely.ui.base.BaseFragment
import live.senya.garrely.ui.common.PaddingItemDecoration
import live.senya.garrely.ui.photogrid.presenter.PhotoGridPresenter
import live.senya.garrely.util.extension.withArguments
import toothpick.Toothpick

class PhotoGridFragment : BaseFragment(), PhotoGridView {

    override val layoutRes = R.layout.fragment_photo_grid

    @InjectPresenter
    lateinit var presenter: PhotoGridPresenter
    
    private val gridSpanSize = arguments?.getInt(GRID_SPAN_SIZE_KEY) ?: DEFAULT_GRID_SPAN_SIZE

    private val photoAdapter: PhotoAdapter by lazy {
        PhotoAdapter(presenter::openPhotoPager)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initList()
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
        layoutManager = GridLayoutManager(activity, gridSpanSize)
        adapter = photoAdapter
        setHasFixedSize(true)
        PaddingItemDecoration(
                resources.getDimensionPixelSize(R.dimen.photo_grid_item_padding),
                gridSpanSize
        )
                .let(::addItemDecoration)
    }

    companion object {
        private const val DEFAULT_GRID_SPAN_SIZE = 2
        private const val GRID_SPAN_SIZE_KEY = "gridSpanSize"

        fun getInstance(gridSpanSize: Int): PhotoGridFragment {
            return PhotoGridFragment().withArguments {
                putInt(GRID_SPAN_SIZE_KEY, gridSpanSize)
            }
        }
    }

}