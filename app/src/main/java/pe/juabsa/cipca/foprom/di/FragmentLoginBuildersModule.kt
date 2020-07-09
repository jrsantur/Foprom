package pe.juabsa.cipca.foprom.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pe.juabsa.cipca.foprom.ui.login.LoginFragment

@Suppress("unused")
@Module
abstract  class FragmentLoginBuildersModule {

  @ContributesAndroidInjector
  abstract fun contributeLoginFragment(): LoginFragment
}