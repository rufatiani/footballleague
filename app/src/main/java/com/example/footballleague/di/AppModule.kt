package com.example.footballleague.di

import com.example.footballleague.api.ApiConfiguration
import com.example.footballleague.api.ApiInterface
import com.example.footballleague.repository.*
import com.example.footballleague.viewmodel.ClassementViewModel
import com.example.footballleague.viewmodel.EventViewModel
import com.example.footballleague.viewmodel.LeagueViewModel
import com.example.footballleague.viewmodel.TeamViewModel
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModules = module {
    single {
        createWebService<ApiInterface>(
            okHttpClient = createHttpClient(),
            factory = RxJava2CallAdapterFactory.create(),
            baseUrl = ApiConfiguration.BASE_URL
        )
    }

    factory<LeagueRepository> { LeagueRepositoryImpl(apiInterface = get()) }
    viewModel { LeagueViewModel(leagueRepository = get()) }

    factory<EventRepository> { EventRepositoryImpl(apiInterface = get(), context = get()) }
    viewModel { EventViewModel(eventRepository = get()) }

    factory<TeamRepository> { TeamRepositoryImpl(apiInterface = get(), context = get()) }
    viewModel { TeamViewModel(teamRepository = get()) }

    factory<ClassementRepository> { ClassementRepositoryImpl(apiInterface = get(), context = get()) }
    viewModel { ClassementViewModel(classementRepository = get()) }
}

fun createHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.readTimeout(5 * 60, TimeUnit.SECONDS)
    return client.addInterceptor {
        val originalRequest = it.request()
        val requestBuilder = originalRequest.newBuilder()
        requestBuilder.header("Content-Type", "application/json")
        val request =
            requestBuilder.method(originalRequest.method(), originalRequest.body()).build()
        return@addInterceptor it.proceed(request)
    }.build()
}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    factory: CallAdapter.Factory, baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addCallAdapterFactory(factory)
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}