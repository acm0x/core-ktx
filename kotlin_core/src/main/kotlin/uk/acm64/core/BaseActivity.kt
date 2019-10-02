package uk.acm64.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.toolbar.*
import uk.acm64.core.kotlin.R

abstract class BaseActivity : AppCompatActivity() {

    abstract fun navigationId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_core_layout)
        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.nav_host_fragment)
        navController.setGraph(navigationId())
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()
}
