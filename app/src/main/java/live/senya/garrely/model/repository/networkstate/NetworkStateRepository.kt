package live.senya.garrely.model.repository.networkstate

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
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
}