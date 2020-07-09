/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pe.juabsa.cipca.foprom.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pe.juabsa.cipca.foprom.ui.home.client.detail.ClientViewModel
import pe.juabsa.cipca.foprom.ui.home.HomeViewModel
import pe.juabsa.cipca.foprom.ui.home.commerce.detail.CommerceViewModel
import pe.juabsa.cipca.foprom.ui.login.LoginViewModel
import pe.juabsa.cipca.foprom.ui.user.UserViewModel
import pe.juabsa.cipca.foprom.viewmodel.FopromViewModelFactory

@Suppress("unused")
@Module
abstract class ViewModelModule {
  @Binds
  @IntoMap
  @ViewModelKey(UserViewModel::class)
  abstract fun bindUserViewModel(userViewModel: UserViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(LoginViewModel::class)
  abstract fun bindLoginViewModel(loginViewModel: LoginViewModel) :ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(HomeViewModel::class)
  abstract fun bindingHomeViewModel(homeViewModel: HomeViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(ClientViewModel::class)
  abstract fun bindingClientViewModel(clientViewModel: ClientViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(CommerceViewModel::class)
  abstract fun bindingCommerceViewModel(commerceViewModel: CommerceViewModel) : ViewModel

  @Binds
  abstract fun bindViewModelFactory(factory: FopromViewModelFactory): ViewModelProvider.Factory
}
