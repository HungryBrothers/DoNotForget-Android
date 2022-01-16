package kr.hungrybrothres.donotforget.features.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import dagger.hilt.android.AndroidEntryPoint
import kr.hungrybrothres.donotforget.R
import kr.hungrybrothres.donotforget.databinding.ActivitySplashBinding
import kr.hungrybrothres.donotforget.features.base.BaseActivity
import kr.hungrybrothres.donotforget.features.extensions.openActivity
import kr.hungrybrothres.donotforget.features.ui.auth.login.LoginActivity

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash){
    override fun initView(savedInstanceState: Bundle?) {
        Handler(Looper.getMainLooper()).postDelayed({
            openActivity(LoginActivity::class.java)
            finish()
        }, 2000)
    }

    override fun onProcess() {

    }
}