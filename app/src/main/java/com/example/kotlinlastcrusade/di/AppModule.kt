package com.example.kotlinlastcrusade.di

import com.example.kotlinlastcrusade.data.remote.GitHubApi
import com.example.kotlinlastcrusade.data.repository.RepoRepositoryImpl
import com.example.kotlinlastcrusade.data.repository.UserRepositoryImpl
import com.example.kotlinlastcrusade.domain.repository.RepoRepository
import com.example.kotlinlastcrusade.domain.repository.UserRepository
import com.example.kotlinlastcrusade.domain.usecase.GetUserDetailsUseCase
import com.example.kotlinlastcrusade.domain.usecase.GetUserReposUseCase
import com.example.kotlinlastcrusade.domain.usecase.GetUsersUseCase
import com.example.kotlinlastcrusade.ui.viewmodel.MainViewModel
import com.example.kotlinlastcrusade.ui.viewmodel.UserViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<RepoRepository> { RepoRepositoryImpl(get()) }
    factory { GetUsersUseCase(get()) }
    factory { GetUserDetailsUseCase(get()) }
    factory { GetUserReposUseCase(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { UserViewModel(get(), get()) }
}