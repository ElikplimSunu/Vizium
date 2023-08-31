package com.sunueric.prototype1.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sunueric.prototype1.data.AuthRepository
import com.sunueric.prototype1.data.AuthRepositoryImpl
import com.sunueric.prototype1.data.DataRepository
import com.sunueric.prototype1.data.FirestoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun provideDataRepository(firestore: FirebaseFirestore): DataRepository = FirestoreRepository(firestore)


    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl
}