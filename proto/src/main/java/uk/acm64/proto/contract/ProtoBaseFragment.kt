package uk.acm64.contract

import androidx.core.content.ContextCompat
import uk.acm64.core.BaseFragment
import uk.acm64.core.extension.appContext
import uk.acm64.proto.AndroidApplication
import uk.acm64.proto.R
import uk.acm64.proto.contract.di.ApplicationComponent

abstract class ProtoBaseFragment : BaseFragment() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as AndroidApplication).appComponent
    }

    override fun snackbarTextColor() = ContextCompat.getColor(appContext, R.color.secondaryTextColor)

    override fun snackbarBackgroudColor() = ContextCompat.getColor(appContext, R.color.secondaryLightColor)

}