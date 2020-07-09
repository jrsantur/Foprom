package pe.juabsa.cipca.foprom.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pe.juabsa.cipca.foprom.ClientDetailActivity

@Suppress("unused")
@Module
abstract class ClientDetailActivityModule {
  @ContributesAndroidInjector(modules = [FragmentClientDetaillBuildersModule::class])
  abstract fun contributeClientDetailActivity(): ClientDetailActivity
}