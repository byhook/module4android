package com.handy.module.network.ext

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.handy.module.network.response.ResultException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


fun <T> Flow<T>.catchResult(action: suspend (cause: ResultException) -> Unit): Flow<T> {
    return this.catch { e ->
        if (e is ResultException) {
            action.invoke(e)
        }
    }
}

/**
 * 默认在主线程
 */
@MainThread
inline fun <T> Flow<T>.collectResult(
        owner: LifecycleOwner,
        minActiveState: Lifecycle.State = Lifecycle.State.CREATED,  //STARTED
        crossinline action: suspend (value: T) -> Unit,
): Job = owner.lifecycleScope.launch {
    owner.repeatOnLifecycle(state = minActiveState) {
        collect { action(it) }
    }
}

@MainThread
inline fun <T> Flow<T>.collectResult(
        crossinline action: suspend (value: T) -> Unit,
): Job = GlobalScope.launch(Dispatchers.Main) {
    collect { action(it) }
}

@Suppress("unused")
inline fun <T> Flow<T>.collectByViewLifecycle(
        fragment: Fragment,
        minActiveState: Lifecycle.State = Lifecycle.State.CREATED, //STARTED
        crossinline action: suspend (value: T) -> Unit,
): Job = collectResult(
    owner = fragment.viewLifecycleOwner,
    minActiveState = minActiveState,
    action = action,
)
