package com.katfun.dddplayground.chapter01.item05

import java.math.BigDecimal

data class Order(
    val products: List<OrderProduct>,
    val shippingInfo: ShippingInfo,
    val orderState: OrderState
) {
    init {
        require(products.size > 1) { "A new order should contain at least one product." }
    }

    val orderAmountSum: BigDecimal = products.sumOf { it.orderAmountSum }

    private fun changeOrderState(newOrderState: OrderState): Order {
        return Order(
            this.products,
            this.shippingInfo,
            newOrderState
        )
    }

    fun changeShippingInfo(shippingInfo: ShippingInfo): Order {
        require(this.orderState.isShipping().not()) { "Cannot change shipping address if already started shipping." }
        return Order(
            this.products,
            shippingInfo,
            this.orderState
        )
    }

    fun cancel(): Order {
        require(this.orderState.isCancellable()) { "An order cannot be cancelled if already started shipping." }
        return changeOrderState(OrderState.CANCELLED)
    }
}

data class OrderProduct(
    val product: Product,
    val quantity: Int,
) {
    val orderAmountSum: BigDecimal = product.price.multiply(BigDecimal.valueOf(quantity.toLong()))
}

data class Product(
    val name: String,
    val price: BigDecimal
)

data class ShippingInfo(
    val receiverName: String,
    val phoneNumber: String,
    val address: Address
)

data class Address(
    val line01: String,
    val line02: String,
    val addressExtra: String? = null,
    val postalCode: String
)

enum class OrderState {
    PAYMENT_WAITING,
    PREPARING,
    SHIPPED,
    DELIVERING,
    DELIVERY_COMPLETED,
    CANCELLED;

    fun isCancellable() = this == PAYMENT_WAITING || this == PREPARING

    fun isShipping() = this == SHIPPED || this == DELIVERING || this == DELIVERY_COMPLETED
}