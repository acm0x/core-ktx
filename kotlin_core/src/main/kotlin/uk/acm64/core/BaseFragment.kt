package uk.acm64.core

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.SnackbarContentLayout
import kotlinx.android.synthetic.main.toolbar.*
import uk.acm64.core.kotlin.R
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    abstract fun layoutId(): Int

    abstract fun snackbarTextColor(): Int

    abstract fun snackbarBackgroudColor(): Int

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(layoutId(), container, false)

    open fun onBackPressed() {}

    internal fun firstTimeCreated(savedInstanceState: Bundle?) = savedInstanceState == null

    internal fun showProgress() = progressStatus(View.VISIBLE)

    internal fun hideProgress() = progressStatus(View.GONE)

    private fun progressStatus(viewStatus: Int) =
            with(activity) { if (this is BaseActivity) this.progress.visibility = viewStatus }

    open fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
        }
    }

    @SuppressLint("RestrictedApi")
    private fun snackbar(message: Int, duration: Int): Snackbar {
        val snackBar = Snackbar.make(view!!, message, duration)
        snackBar.view.setBackgroundColor(snackbarBackgroudColor())
        val contentLayout = (snackBar.view as ViewGroup).getChildAt(0) as SnackbarContentLayout
        contentLayout.messageView.setTextColor(snackbarTextColor())
        return snackBar
    }

    protected fun notify(@StringRes message: Int) {
        val snackBar = snackbar(message, Snackbar.LENGTH_SHORT)
        snackBar.show()
    }

    internal fun notifyWithAction(@StringRes message: Int, @StringRes actionText: Int, action: () -> Any) {
        val snackBar = snackbar(message, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction(actionText) { action.invoke() }
        snackBar.setActionTextColor(snackbarTextColor())
        snackBar.show()
    }

    fun renderFailure(@StringRes message: Int) {
        notify(message)
    }
}
