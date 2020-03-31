package com.touchlane.android.bootstrap.data

import android.content.Context
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class StringsImplTest {

    private lateinit var strings: Strings
    private val mockContext = mock<Context>()

    @Before
    fun setup() {
        strings = StringsImpl(mockContext)
    }

    @Test
    fun testGetString() {
        whenever(mockContext.getString(TEST_STRING_ID)).thenReturn(TEST_STRING)

        assertEquals(TEST_STRING, strings[TEST_STRING_ID])
    }

    @Test
    fun testGetStringFormatted() {
        whenever(mockContext.getString(TEST_STRING_ID, TEST_FORMAT_ARG)).thenReturn(TEST_STRING)

        assertEquals(TEST_STRING, strings[TEST_STRING_ID, TEST_FORMAT_ARG])
    }

    companion object {
        const val TEST_STRING_ID = 1
        const val TEST_STRING = "TEST_STRING"
        const val TEST_FORMAT_ARG = 1
    }
}