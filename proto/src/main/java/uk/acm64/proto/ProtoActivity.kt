package uk.acm64.proto

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import uk.acm64.core.Failure
import uk.acm64.core.extension.failure
import uk.acm64.core.extension.observe
import uk.acm64.core.kotlin.R
import uk.acm64.proto.contract.di.ApplicationComponent
import javax.inject.Inject

class ProtoActivity : FragmentActivity() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as AndroidApplication).appComponent
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var protoViewModel: ProtoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_core_layout)
        appComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()
        protoViewModel = viewModel(viewModelFactory) {
            observe(protoFeatureState, ::renderFeaturesList)
            failure(failure, ::handleFailure)
        }
    }

    fun handleFailure(failure: Failure?) {
    }

    private fun renderFeaturesList(protoModelState: ProtoModelState?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    inline fun <reified T : ViewModel> FragmentActivity.viewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit): T {
        val vm = ViewModelProviders.of(this, factory)[T::class.java]
        vm.body()
        return vm
    }


}
