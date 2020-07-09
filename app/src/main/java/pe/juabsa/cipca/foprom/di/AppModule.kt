/*
 * Copyright (C) 2017 The Android Open Source Project
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

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import pe.juabsa.cipca.foprom.api.BasicAuthInterceptor
import pe.juabsa.cipca.foprom.api.FopromService
import pe.juabsa.cipca.foprom.db.*
import pe.juabsa.cipca.foprom.util.LiveDataCallAdapterFactory
import pe.juabsa.cipca.foprom.util.SharedPrefsHelper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideGithubService(): FopromService {
        return Retrofit.Builder()
                .baseUrl("http://192.168.1.3:8080/apirest/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .client(OkHttpClient.Builder().addInterceptor(BasicAuthInterceptor("admin", "admin")).build())
                .build()
                .create(FopromService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): FopromDb {
        return Room
                .databaseBuilder(app, FopromDb::class.java, "foprom.db")
                .createFromAsset("init.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: FopromDb): UserDao {
        return db.userDao()
    }

    @Singleton
    @Provides
    fun provideRepoDao(db: FopromDb): RepoDao {
        return db.repoDao()
    }

    @Singleton
    @Provides
    fun provideClientDao(db: FopromDb): ClientDao {
        return db.clientDao()
    }

    @Singleton
    @Provides
    fun provideCommerceDao(db: FopromDb): CommerceDao{
        return db.commerceDao()
    }

    @Singleton
    @Provides
    fun provideparameters(db: FopromDb): ParameterDao{
        return db.parameterDao()
    }


}
