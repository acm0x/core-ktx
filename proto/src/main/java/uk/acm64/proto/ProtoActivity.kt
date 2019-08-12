package uk.acm64.proto

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
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
//            observe(mortgageTableState, ::renderMortgageTable)
//            failure(failure, ::handleFailure)
        }
    }


    inline fun <reified T : ViewModel> FragmentActivity.viewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit): T {
        val vm = ViewModelProviders.of(this, factory)[T::class.java]
        vm.body()
        return vm
    }


}
