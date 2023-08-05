package com.katfun.dddplayground.chapter01.item05

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class OrderTest {
    @Test
    fun `상품 목록이 하나도 없다면 IllegalArgumentException이 발생한다`() {
        // given
        val products = emptyList<OrderProduct>()
        val address = Address("대한민국", "서울", null, "12345")
        val shippingInfo = ShippingInfo("정규호", "010-1234-5678", address)
        val orderState = OrderState.BEFORE_PAY

        // when

        // then
        assertThrows(IllegalArgumentException::class.java) { Order(products, shippingInfo, orderState) }
    }
}