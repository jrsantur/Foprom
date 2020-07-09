package pe.juabsa.cipca.foprom.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pe.juabsa.cipca.foprom.CommerceDetailActivity

@Suppress("unused")
@Module
abstract class CommerceDetailActivityModule {
     @ContributesAndroidInjector(modules = [FragmentCommerceDetailBuildersModule::class])
     abstract fun contributeMainActivity(): CommerceDetailActivity
}