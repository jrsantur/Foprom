package pe.juabsa.cipca.foprom.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pe.juabsa.cipca.foprom.HomeActivity

@Suppress("unused")
@Module
abstract class HomeActivityModule {
  @ContributesAndroidInjector(modules = [FragmentHomeBuildersModule::class])
  abstract fun contributeHomeActivity() : HomeActivity
}