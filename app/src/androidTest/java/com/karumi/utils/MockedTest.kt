package com.karumi.utils

import org.junit.Before
import org.mockito.MockitoAnnotations

interface MockedTest {
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }
}
