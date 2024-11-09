package com.example.kotlinlastcrusade1.core.coroutines.dispatchers

import com.example.kotlinlastcrusade1.core.coroutines.dispatchers.base.DispatchersProvider
import kotlinx.coroutines.CoroutineDispatcher

class TestDispatchersProvider(
    testDispatcher: CoroutineDispatcher
) : DispatchersProvider {
    override val default: CoroutineDispatcher = testDispatcher
    override val io: CoroutineDispatcher = testDispatcher
    override val main: CoroutineDispatcher = testDispatcher
}