package com.example.patronuschellange.di

import com.example.patronuschellange.users.data.repository.FakeUserHolderRepositoryImpl
import com.example.patronuschellange.users.di.DataModule
import com.example.patronuschellange.users.domain.repository.UserHolderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class]
)
object TestAppModule {

    /**
     * to provide the repository
     * implementation for testing
     * whenever the repository interface is required
     */
    @Provides
    @Singleton
    fun provideRepository(): UserHolderRepository =
        FakeUserHolderRepositoryImpl()

    /**
     * injected it to use the fake
     */
    @Provides
    @Singleton
    fun provideFakeRepositoryImpl(): FakeUserHolderRepositoryImpl =
        FakeUserHolderRepositoryImpl()

}