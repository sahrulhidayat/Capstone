package com.sahrulhidayat.core.di

import androidx.room.Room
import com.sahrulhidayat.core.BuildConfig
import com.sahrulhidayat.core.data.preference.PreferenceDataStore
import com.sahrulhidayat.core.data.source.GameRepository
import com.sahrulhidayat.core.data.source.local.LocalDataSource
import com.sahrulhidayat.core.data.source.local.room.GameDatabase
import com.sahrulhidayat.core.data.source.remote.RemoteDataSource
import com.sahrulhidayat.core.data.source.remote.network.ApiService
import com.sahrulhidayat.core.domain.interfaces.IGameRepository
import com.sahrulhidayat.core.domain.interfaces.IPreferenceDataStore
import com.sahrulhidayat.core.domain.usecase.GameInteractor
import com.sahrulhidayat.core.domain.usecase.GameUseCase
import com.sahrulhidayat.core.domain.usecase.PreferenceInteractor
import com.sahrulhidayat.core.domain.usecase.PreferenceUseCase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("sahrulhidayat".toCharArray())
        val factory = SupportFactory(passphrase)

        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java, "game_entities"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val dataStoreModule = module {
    single<IPreferenceDataStore> {
        PreferenceDataStore(androidContext())
    }
}

val networkModule = module {
    single {
        val hostname = "api.rawg.io"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/RI9CUmPUOpUk2vdVMSZDWj+wtoQO5k9MSCSM9w4grmU=")
            .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .add(hostname, "sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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
    single<IGameRepository> {
        GameRepository(get(), get())
    }
}

val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
    factory<PreferenceUseCase> { PreferenceInteractor(get()) }
}