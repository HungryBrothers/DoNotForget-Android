package kr.hungrybrothres.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hungrybrothres.data.remote.auth.AuthService
import kr.hungrybrothres.data.remote.subscribe.SubscribeService
import kr.hungrybrothres.data.repository.AuthRepositoryImpl
import kr.hungrybrothres.data.repository.SubscribeRepositoryImpl
import kr.hungrybrothres.domain.repository.AuthRepository
import kr.hungrybrothres.domain.repository.SubscribeRepository
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(authService: AuthService): AuthRepository {
        return AuthRepositoryImpl(authService)
    }

//    @Provides
//    @Singleton
//    fun provideSubscribeRepository(subscribeService: SubscribeService): SubscribeRepository {
//        return SubscribeRepositoryImpl(subscribeService)
//    }


}
//@Module
//@InstallIn(SingletonComponent::class)
//abstract class RepositoryModule {
//
//    @Binds
//    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl) : AuthRepository
//
//    @Binds
//    abstract fun bindSubscribeRepository(subscribeRepositoryImpl: SubscribeRepositoryImpl) : SubscribeRepository
//}