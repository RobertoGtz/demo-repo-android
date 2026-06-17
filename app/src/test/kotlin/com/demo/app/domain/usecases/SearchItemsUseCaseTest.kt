package com.demo.app.domain.usecases

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SearchItemsUseCaseTest {

    private val items = listOf("apple", "banana", "grape", "orange", "watermelon")
    private val searchItemsUseCase = SearchItemsUseCase()

    @Test
    fun `test search with matching results`() {
        val query = "ap"
        val result = searchItemsUseCase.execute(query) { item, q -> item.contains(q, ignoreCase = true) }
        assertTrue(result.isNotEmpty(), "Result should not be empty")
        assertEquals(listOf("apple", "grape"), result, "Result should contain 'apple' and 'grape'")
    }

    @Test
    fun `test search with no matching results`() {
        val query = "xyz"
        val result = searchItemsUseCase.execute(query) { item, q -> item.contains(q, ignoreCase = true) }
        assertTrue(result.isEmpty(), "Result should be empty")
    }

    @Test
    fun `test search with empty query`() {
        val query = ""
        val result = searchItemsUseCase.execute(query) { item, q -> item.contains(q, ignoreCase = true) }
        assertEquals(items, result, "Result should contain all items when query is empty")
    }

    @Test
    fun `test search with case insensitivity`() {
        val query = "Ap"
        val result = searchItemsUseCase.execute(query) { item, q -> item.contains(q, ignoreCase = true) }
        assertEquals(listOf("apple", "grape"), result, "Result should contain 'apple' and 'grape' ignoring case")
    }
}
