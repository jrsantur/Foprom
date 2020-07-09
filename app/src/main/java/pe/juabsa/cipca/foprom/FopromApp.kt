
package pe.juabsa.cipca.foprom

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import pe.juabsa.cipca.foprom.di.AppInjector
import pe.juabsa.cipca.foprom.util.SharedPrefsHelper
import timber.log.Timber
import javax.inject.Inject


class FopromApp : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        AppInjector.init(this)
        SharedPrefsHelper.with(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}
