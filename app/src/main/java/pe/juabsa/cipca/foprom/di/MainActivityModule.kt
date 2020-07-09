package pe.juabsa.cipca.foprom.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pe.juabsa.cipca.foprom.MainActivity

@Suppress("unused")
@Module
abstract class MainActivityModule {
  @ContributesAndroidInjector(modules = [FragmentMainBuildersModule::class])
  abstract fun contributeMainActivity(): MainActivity
}
