package pe.juabsa.cipca.foprom.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pe.juabsa.cipca.foprom.LoginActivity

@Suppress("unused")
@Module
abstract class LoginActivityModule {
    @ContributesAndroidInjector(modules = [FragmentLoginBuildersModule::class])
    abstract fun contributeLoginActivity() : LoginActivity
}