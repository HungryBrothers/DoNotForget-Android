package kr.hungrybrothres.donotforget

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import dagger.hilt.android.HiltAndroidApp
import org.json.JSONObject

@HiltAndroidApp
class DoNotForgetApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(true)
            .methodCount(5)
            .methodOffset(5)
            .tag("leeam")
            .build()

        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })

    }
}