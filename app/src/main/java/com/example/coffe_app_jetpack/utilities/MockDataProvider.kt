package com.example.coffe_app_jetpack.utilities

import com.example.coffe_app_jetpack.models.Product

class MockDataProvider {
    companion object {
        fun listsProducts(): List<Product> = listOf(
            Product(
                0,
                "Café de México",
                "El mejor café Mexicano",
                "Mensaje de descripción del producto a mostrar",
                34.5,
                "MXN",
                "MXN"
            ),
            Product(
                1,
                "Café de Costa Rica",
                "El mejor café del sur",
                "Mensaje de descripción del producto a mostrar",
                83.5,
                "CRI",
                "CRI"
            ),
            Product(
                2,
                "Café de Etiopía",
                "Café de exportación",
                "Mensaje de descripción del producto a mostrar",
                10.2,
                "ETP",
                "ETP"
            ),
            Product(
                3,
                "Café de Java",
                "Café único en el mundo",
                "Mensaje de descripción del producto a mostrar",
                4.2,
                "JAV",
                "JAV"
            )
        )

        fun productById(id: Int): Product? {
            val list = MockDataProvider.listsProducts()
            val product = list.find { it.id == id }
            return product
        }

        fun listOfCities(): List<String> = listOf(
                "Ciudad de México, México",
                "Costa Rica, Costa Rica",
                "Etiopía, Etiopía",
                "Java, Java"
        )
    }
}