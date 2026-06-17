package com.demo.app.data.repositories

class ItemRepository {

    fun getItems(): List<String> {
        // This is a stub implementation. Replace with actual data access logic.
        return listOf("Item 1", "Item 2", "Item 3")
    }

    fun searchItems(query: String): List<String> {
        // This is a stub implementation. Replace with actual search logic.
        return getItems().filter { it.contains(query, ignoreCase = true) }
    }
}
