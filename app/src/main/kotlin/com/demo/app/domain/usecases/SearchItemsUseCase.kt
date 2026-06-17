package com.demo.app.domain.usecases

class SearchItemsUseCase {

    fun execute(query: String, predicate: (String, String) -> Boolean): List<String> {
        val items = listOf("apple", "banana", "grape", "orange", "watermelon")
        if (query.isBlank()) return items
        return items.filter { predicate(it, query) }
    }
}
