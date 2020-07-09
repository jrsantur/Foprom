package pe.juabsa.cipca.foprom.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pe.juabsa.cipca.foprom.ui.home.commerce.detail.*

@Suppress("unused")
@Module
abstract class FragmentCommerceDetailBuildersModule {

  @ContributesAndroidInjector
  abstract fun contributeGeneralBusinessDataFragment() : GeneralBusinessDataFragment

  @ContributesAndroidInjector
  abstract fun contributeBusinessShoppingFragment(): BusinessShoppingFragment

  @ContributesAndroidInjector
  abstract fun contributeBusinessInventoryFragment(): BusinessInventoryFragment

  @ContributesAndroidInjector
  abstract fun contributeBusinessSalesFragment(): BusinessSalesFragment

  @ContributesAndroidInjector
  abstract fun contributeBalanceSheetFragment(): BalanceSheetFragment

}