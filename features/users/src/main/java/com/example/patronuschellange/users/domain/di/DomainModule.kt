package com.example.patronuschellange.users.domain.di

import com.example.patronuschellange.users.domain.mappers.UserDeviceHolderDetailsMapper
import com.example.patronuschellange.users.domain.mappers.UserDeviceHolderMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Singleton
    @Provides
    fun provideUserDeviceHolderMapper() = UserDeviceHolderMapper()

    @Singleton
    @Provides
    fun provideUserDeviceHolderDetailsMapper() = UserDeviceHolderDetailsMapper()
}