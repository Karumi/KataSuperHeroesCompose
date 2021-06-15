package com.karumi.core.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class ViewModel<C, E> : androidx.lifecycle.ViewModel(), LifecycleObserver {

    companion object {
        var dispatcher: CoroutineDispatcher = Dispatchers.Default
    }

    val state: Flow<ViewModelState<C, E>> get() = _state
    private var _state: MutableStateFlow<ViewModelState<C, E>> = MutableStateFlow(
        ViewModelState.Loading()
    )

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
    }

    fun currentState(): ViewModelState<C, E> = _state.value
    protected fun updateState(newState: ViewModelState<C, E>) {
        _state.value = newState
    }

    protected inline fun async(crossinline block: suspend () -> Unit) =
        viewModelScope.launch {
            block()
        }

    protected suspend inline fun <T> await(crossinline block: suspend () -> T): T =
        withContext(dispatcher) { block() }

    protected inline fun asyncFlow(crossinline block: suspend () -> Unit) = async {
        withContext(dispatcher) {
            block()
        }
    }
}

sealed class ViewModelState<C, out E> {
    companion object {
        val initialState: ViewModelState<Nothing, Nothing> = Loading()
    }

    data class Loading<C, E>(val refreshing: Boolean = false) : ViewModelState<C, E>()
    data class Error<C, E>(val error: E) : ViewModelState<C, E>()
    data class Loaded<C, E>(
        val content: C,
        val error: E? = null,
        val refreshing: Boolean = false
    ) : ViewModelState<C, E>()

    fun loading(): Boolean = this is Loading

    fun refreshingContent(): Boolean = when (this) {
        is Loading -> this.refreshing
        is Loaded<*, *> -> this.refreshing
        else -> false
    }

    fun containsAnyError(): Boolean = error() != null

    fun content(): C? = when (this) {
        is Loaded -> this.content
        else -> null
    }

    fun withContentIfLoaded(newContent: (C) -> C): ViewModelState<C, E> = when (this) {
        is Loaded -> Loaded(content = newContent(this.content), error = this.error, refreshing = this.refreshing)
        is Loading -> this
        is Error -> this
    }

    fun error(): E? = when (this) {
        is Error -> error
        is Loaded -> error
        is Loading -> null
    }

    fun hasContent(): Boolean = content() != null

    fun markAsRefreshing(refreshing: Boolean = true): ViewModelState<C, E> = when (this) {
        is Loading -> Loading(refreshing)
        is Error -> if (refreshing) Loading(refreshing) else this
        is Loaded -> copy(refreshing = refreshing)
    }

    fun <E> withError(error: E): ViewModelState<C, E> = when (this) {
        is Loading -> Error(error)
        is Error -> Error(error)
        is Loaded -> Loaded(error = error, content = this.content, refreshing = this.refreshing)
    }

    fun removeError(): ViewModelState<C, E> = when (this) {
        is Loading -> this
        is Error -> Loading()
        is Loaded -> Loaded(content = this.content, refreshing = this.refreshing, error = null)
    }
}
