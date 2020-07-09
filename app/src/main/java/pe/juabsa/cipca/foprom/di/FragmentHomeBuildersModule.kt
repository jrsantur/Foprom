package pe.juabsa.cipca.foprom.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pe.juabsa.cipca.foprom.ui.home.client.ClientFragment
import pe.juabsa.cipca.foprom.ui.home.solicitude.SolicitudeFragment
import pe.juabsa.cipca.foprom.ui.home.commerce.CommerceFragment

@Suppress("unused")
@Module
abstract class FragmentHomeBuildersModule {

  @ContributesAndroidInjector
  abstract fun contributeClientFragment() : ClientFragment

  @ContributesAndroidInjector
  abstract fun contributeHomeFragment(): SolicitudeFragment

  @ContributesAndroidInjector
  abstract fun contributeCommercehow() : CommerceFragment

}