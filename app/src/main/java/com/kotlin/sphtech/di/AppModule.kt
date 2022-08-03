package com.kotlin.sphtech.di


import android.content.Context
import com.kotlin.sphtech.db.AppDao
import com.kotlin.sphtech.db.AppDatabase
import com.kotlin.sphtech.network.ServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val BASE_URL = "https://data.gov.sg/api/action/"

    @Provides
    @Singleton
    fun getAppDatabase(@ApplicationContext context: Context): AppDatabase{
        return AppDatabase.getDbInstance(context)
    }

    @Provides
    @Singleton
    fun getAppDao(appDatabase: AppDatabase):AppDao{
        return appDatabase.getAppDao()
    }


    @Provides
    @Singleton
    fun getRetroInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getRetroServiceInstance(retrofit: Retrofit): ServiceInterface{
        return retrofit.create(ServiceInterface::class.java)
    }
}