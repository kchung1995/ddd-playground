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

    @Test
    fun `배송 시작한 상품은 주소를 변경할 수 없다`() {

    }

    @Test
    fun `배송 전의 상품은 주소를 변경할 수 있다`() {

    }

    @Test
    fun `출고 전에 주문을 취소할 수 있다`() {

    }

    @Test
    fun `출고 후에는 주문을 취소할 수 없다`() {

    }

    @Test
    fun `고객이 결제를 완료하기 전에는 상품을 준비하지 않는다`() {

    }
}