package kr.hungrybrothres.donotforget.features.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import kr.hungrybrothres.donotforget.R
import kr.hungrybrothres.donotforget.databinding.ActivityMainBinding
import kr.hungrybrothres.donotforget.features.base.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun initView(savedInstanceState: Bundle?) {
//        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
    }

    override fun onProcess() {
        setUpNavigation()
    }

    private fun setUpNavigation() {
//        binding.apply {
//            val navController = Navigation.findNavController(this@MainActivity, R.id.navHostFragment)
//            bottomNavigation.setupWithNavController(navController)
//
//            lifecycleScope.launchWhenResumed {
//                navController.addOnDestinationChangedListener { _, destination, _ ->
//                    when(destination.id) {
//                        R.id.page_subscribe -> Logger.d("sub")
//                        R.id.page_settings -> Logger.d("settings")
//                    }
//                }
//            }
//        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.navController)

        navHostFragment.navController.addOnDestinationChangedListener {_, destination, _ ->
            Logger.d("des :: ${destination.id}")
            when(destination.id) {
                R.id.subscribe -> Logger.d("sub")
                R.id.settings -> Logger.d("settings")
            }

        }

    }

}