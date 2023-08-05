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

    fun changeOrderState(orderState: OrderState): Order {
        return Order(
            this.products,
            this.shippingInfo,
            orderState
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
    val addressLine01: String,
    val addressLine02: String,
    val addressExtra: String? = null,
    val postalCode: String
)

enum class OrderState {
    BEFORE_PAY,
    PAID,
    SHIPPING,
    SHIPPED,
    CANCELLED;

    fun isCancellable() = this == BEFORE_PAY || this == PAID

    fun isPreparable() = this == PAID || this == SHIPPING || this == SHIPPED

    fun isShipping() = this == SHIPPING || this == SHIPPED
}