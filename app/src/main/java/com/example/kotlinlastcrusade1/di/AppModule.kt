package com.example.kotlinlastcrusade1.di

import com.example.kotlinlastcrusade1.data.remote.GitHubApi
import com.example.kotlinlastcrusade1.data.repository.RepoRepositoryImpl
import com.example.kotlinlastcrusade1.data.repository.UserRepositoryImpl
import com.example.kotlinlastcrusade1.domain.repository.RepoRepository
import com.example.kotlinlastcrusade1.domain.repository.UserRepository
import com.example.kotlinlastcrusade1.domain.usecase.GetUserDetailsUseCase
import com.example.kotlinlastcrusade1.domain.usecase.GetUserReposUseCase
import com.example.kotlinlastcrusade1.domain.usecase.GetUsersUseCase
import com.example.kotlinlastcrusade1.ui.viewmodel.MainViewModel
import com.example.kotlinlastcrusade1.ui.viewmodel.UserViewModel
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