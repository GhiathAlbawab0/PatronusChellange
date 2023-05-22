package com.example.patronuschellange.users.di

import com.example.patronuschellange.common.Constants
import com.example.patronuschellange.users.data.datasource.api.remote.UserHolderRemoteDataSource
import com.example.patronuschellange.users.data.datasource.api.remote.UserHolderRemoteDataSourceImpl
import com.example.patronuschellange.users.data.datasource.api.service.PatronusApi
import com.example.patronuschellange.users.data.repository.UserHolderRepositoryImpl
import com.example.patronuschellange.users.domain.mappers.UserDeviceHolderDetailsMapper
import com.example.patronuschellange.users.domain.mappers.UserDeviceHolderMapper
import com.example.patronuschellange.users.domain.repository.UserHolderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideApi(builder: Retrofit.Builder): PatronusApi {
        return builder.baseUrl(Constants.API_BASE_URL).build().create(PatronusApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        api: PatronusApi,
        userDeviceHolderMapper: UserDeviceHolderMapper,
        userDeviceHolderDetailsMapper: UserDeviceHolderDetailsMapper
    ): UserHolderRemoteDataSource =
        UserHolderRemoteDataSourceImpl(api, userDeviceHolderMapper, userDeviceHolderDetailsMapper)

    @Singleton
    @Provides
    fun providesPropertyRepository(remoteDataSource: UserHolderRemoteDataSource): UserHolderRepository =
        UserHolderRepositoryImpl(remoteDataSource)
}