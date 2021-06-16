package com.karumi.utils

import com.karumi.core.ui.ViewModel
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.mockito.MockitoAnnotations

interface MockedTest {
    @Before
    fun setUp() {
        ViewModel.dispatcher = Dispatchers.Unconfined
        MockitoAnnotations.initMocks(this)
    }
}
