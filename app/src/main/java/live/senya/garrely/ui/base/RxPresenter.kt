package live.senya.garrely.ui.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class RxPresenter<T : MvpView> : MvpPresenter<T>() {

    private val subscriptions = CompositeDisposable()

    override fun onDestroy() {
        subscriptions.clear()
    }

    protected fun Disposable.store() {
        subscriptions.add(this)
    }
    
}