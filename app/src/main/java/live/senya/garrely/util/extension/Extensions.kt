package live.senya.garrely.util.extension

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import toothpick.Scope
import toothpick.config.Module

fun (() -> Any).toCompletable(): Completable = Completable.fromCallable(this)

fun Disposable.addTo(compositeDisposable: CompositeDisposable): Disposable {
    return apply { compositeDisposable.add(this) }
}

fun <T : Fragment> T.withArguments(action: Bundle.() -> Unit): T {
    arguments = Bundle().apply(action)
    return this
}

fun Fragment.toast(message: String?) {
    activity?.let {
        Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
    }
}

fun Fragment.toast(message: Int) {
    activity?.let {
        Toast.makeText(it, it.getString(message), Toast.LENGTH_SHORT).show()
    }
}

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

val Context.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(this)

fun Scope.installBindings(bindings: Module.() -> Unit): Scope {

    val module = object : Module() {
        init {
            bindings.invoke(this)
        }
    }

    installModules(module)

    return this
}

fun <E> MutableCollection<E>.replaceWith(collection: Collection<E>) {
    clear()
    addAll(collection)
}