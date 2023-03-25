package br.com.alura.ceep.hilt_modulos

import br.com.alura.ceep.retrofit.service.NoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
/**
 *
 * Veja o DatabaseModule para detalhes sobre as anotaçoes e logica
 * @see DetaBaseModule
 * */
class RetrofitModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(logging).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("https://your_web_api_address")
            .addConverterFactory(MoshiConverterFactory.create()).client(client).build()
    }

    @Provides
    fun provideNoteService(retrofit: Retrofit): NoteService {
        return retrofit.create(NoteService::class.java)
    }
}