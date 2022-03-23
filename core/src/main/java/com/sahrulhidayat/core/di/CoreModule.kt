package com.sahrulhidayat.core.di

import androidx.room.Room
import com.sahrulhidayat.core.BuildConfig
import com.sahrulhidayat.core.data.preference.SettingsPreference
import com.sahrulhidayat.core.data.source.GameRepository
import com.sahrulhidayat.core.data.source.local.LocalDataSource
import com.sahrulhidayat.core.data.source.local.room.GameDatabase
import com.sahrulhidayat.core.data.source.remote.RemoteDataSource
import com.sahrulhidayat.core.data.source.remote.network.ApiService
import com.sahrulhidayat.core.domain.`interface`.IGameRepository
import com.sahrulhidayat.core.domain.`interface`.ISettingsPreference
import com.sahrulhidayat.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<GameDatabase>().gameDao() }

    single {
        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java, "game_entities"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single { AppExecutors() }
    single<IGameRepository> {
        GameRepository(get(), get(), get())
    }
    single<ISettingsPreference> {
        SettingsPreference(get())
    }
}