package pe.juabsa.cipca.foprom.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pe.juabsa.cipca.foprom.ui.home.client.detail.*

@Suppress("unused")
@Module
abstract class FragmentClientDetaillBuildersModule {

  @ContributesAndroidInjector
  abstract fun contributeGeneralDataFragment() : GeneralDataFragment

  @ContributesAndroidInjector
  abstract fun contributeHousingDataFragment() : HousingDataFragment

  @ContributesAndroidInjector
  abstract fun contributeSpouseInformationFragment() : SpouseInformationFragment

  @ContributesAndroidInjector
  abstract fun contributeInventoryInformationFragment() : InventoryInformationFragment

  @ContributesAndroidInjector
  abstract fun contributeFinancialDebtsFragment() : FinancialDebtsFragment

  @ContributesAndroidInjector
  abstract fun contributeLaborDataFragment() : LaborDataFragment


}