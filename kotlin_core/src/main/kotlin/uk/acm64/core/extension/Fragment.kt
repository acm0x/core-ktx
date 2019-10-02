package uk.acm64.core.extension

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import uk.acm64.core.BaseFragment

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
        beginTransaction().func().commit()

inline fun <reified T : ViewModel> Fragment.viewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}

inline fun <reified T : ViewModel> FragmentActivity.viewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}

fun BaseFragment.close() = fragmentManager?.popBackStack()

val BaseFragment.appContext: Context get() = activity?.applicationContext!!

fun Fragment.snackbar(text: CharSequence, duration: Int = Snackbar.LENGTH_LONG, init: Snackbar.() -> Unit = {}): Snackbar {
    return view!!.snackbar(text, duration, init)
}

fun Fragment.snackbar(@StringRes text: Int, duration: Int = Snackbar.LENGTH_LONG, init: Snackbar.() -> Unit = {}): Snackbar {
    return view!!.snackbar(text, duration, init)
}

fun Activity.hideKeyboard() {
    hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
}

fun Fragment.hideKeyboard() {
    if (context != null && view != null) {
        context!!.hideKeyboard(view!!)
    }
}