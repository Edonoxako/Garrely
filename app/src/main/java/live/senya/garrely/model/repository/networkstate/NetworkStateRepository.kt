package live.senya.garrely.model.repository.networkstate

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import live.senya.garrely.entity.NetworkState
import live.senya.garrely.model.store.systemstate.NetworkStateStore
import javax.inject.Inject

class NetworkStateRepository @Inject constructor(
        private val store: NetworkStateStore
) {

    fun observeNetworkState(): Observable<NetworkState> {
        return store.observeNetworkState()
                .subscribeOn(AndroidSchedulers.mainThread())
    }

    fun currentNetworkState(): Single<NetworkState> {
        return store.currentNetworkState()
                .subscribeOn(Schedulers.io())
    }
}