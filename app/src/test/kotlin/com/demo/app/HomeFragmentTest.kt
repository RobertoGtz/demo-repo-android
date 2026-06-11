package com.demo.app

import kotlin.test.Test
import kotlin.test.assertEquals

class HomeFragmentTest {
    @Test
    fun `home fragment has correct title`() {
        val fragment = HomeFragment()
        assertEquals("Home", fragment.title)
    }

    @Test
    fun `onViewCreated does not crash`() {
        val fragment = HomeFragment()
        fragment.onViewCreated()
    }
}
