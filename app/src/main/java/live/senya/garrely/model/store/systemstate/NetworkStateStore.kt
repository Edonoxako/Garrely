package live.senya.garrely.model.store.systemstate

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.github.karczews.rxbroadcastreceiver.RxBroadcastReceivers
import io.reactivex.Observable
import live.senya.garrely.entity.NetworkState
import javax.inject.Inject

class NetworkStateStore @Inject constructor(
        private val context: Context
) {

    private val currentNetworkState: NetworkState
        get() {
            val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val networkInfo = connectivityManager.activeNetworkInfo
                    ?: return NetworkState.DISCONNECTED

            return if (networkInfo.state == NetworkInfo.State.CONNECTED) {
                NetworkState.CONNECTED
            } else {
                NetworkState.DISCONNECTED
            }
        }

    fun observeNetworkState(): Observable<NetworkState> {
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)

        return RxBroadcastReceivers
                .fromIntentFilter(context, intentFilter)
                .map { currentNetworkState }
                .startWith(currentNetworkState)
                .distinctUntilChanged()
    }
    
}