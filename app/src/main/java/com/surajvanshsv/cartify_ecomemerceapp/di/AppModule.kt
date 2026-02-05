package com.surajvanshsv.cartify_ecomemerceapp.di

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.surajvanshsv.cartify_ecomemerceapp.repositories.CartRepository
import com.surajvanshsv.cartify_ecomemerceapp.room.AppDatabase
import com.surajvanshsv.cartify_ecomemerceapp.room.CartDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideCartDao(appDatabase: AppDatabase) : CartDao {
        return appDatabase.cartDao()
    }

    @Provides
    fun provideCartRepository(cartDao : CartDao) : CartRepository {
        return CartRepository(cartDao)
    }

}
