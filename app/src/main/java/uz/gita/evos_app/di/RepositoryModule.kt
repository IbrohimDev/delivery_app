package uz.gita.evos_app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.evos_app.domain.repository.AppRepository
import uz.gita.evos_app.domain.repository.AuthRepository
import uz.gita.evos_app.domain.repository.impl.AppRepositoryImpl
import uz.gita.evos_app.domain.repository.impl.AuthRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun getAppRepository(impl:AppRepositoryImpl):AppRepository

    @Binds
    @Singleton
    fun getAuthRepository(impl:AuthRepositoryImpl):AuthRepository

}