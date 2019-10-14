package uk.acm64.lastsearch.contract

import androidx.core.content.ContextCompat
import uk.acm64.core.BaseFragment
import uk.acm64.core.extension.appContext
import uk.acm64.lastsearch.AndroidApplication
import uk.acm64.lastsearch.R
import uk.acm64.lastsearch.contract.di.ApplicationComponent

abstract class LastSearchBaseFragment : BaseFragment() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as AndroidApplication).appComponent
    }

    override fun snackbarTextColor() = ContextCompat.getColor(appContext, R.color.secondaryTextColor)

    override fun snackbarBackgroudColor() = ContextCompat.getColor(appContext, R.color.secondaryLightColor)

}