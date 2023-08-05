package com.katfun.dddplayground.chapter01.item05

import com.katfun.dddplayground.chapter01.item05.stub.AddressStub.ADDRESS_01
import com.katfun.dddplayground.chapter01.item05.stub.OrderProductStub.ORDER_PRODUCT_01
import com.katfun.dddplayground.chapter01.item05.stub.OrderProductStub.ORDER_PRODUCT_02
import com.katfun.dddplayground.chapter01.item05.stub.OrderProductStub.ORDER_PRODUCT_03
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class OrderTest {
    private val shippingInfo = ShippingInfo("정규호", "010-1234-5678", ADDRESS_01)

    @Test
    fun `상품 목록이 하나도 없다면 IllegalArgumentException이 발생한다`() {
        // given
        val products = emptyList<OrderProduct>()
        val shippingInfo = shippingInfo
        val orderState = OrderState.PAYMENT_WAITING

        // when

        // then
        assertThrows(IllegalArgumentException::class.java) { Order(products, shippingInfo, orderState) }
    }

    @Test
    fun `배송 시작한 상품은 주소를 변경할 수 없다`() {
        // given
        val products = listOf(ORDER_PRODUCT_01, ORDER_PRODUCT_02, ORDER_PRODUCT_03)
        val shippingInfo = shippingInfo
        val orderState = OrderState.DELIVERING

        val order = Order(products, shippingInfo, orderState)

        // when
        val newShippingInfo = ShippingInfo("정카펀", "010-1234-5678", ADDRESS_01)

        // then
        assertThrows(IllegalArgumentException::class.java) { order.changeShippingInfo(newShippingInfo) }
    }

    @Test
    fun `배송 전의 상품은 주소를 변경할 수 있다`() {
        // given
        val products = listOf(ORDER_PRODUCT_01, ORDER_PRODUCT_02, ORDER_PRODUCT_03)
        val shippingInfo = shippingInfo
        val orderState = OrderState.PAYMENT_WAITING

        val order = Order(products, shippingInfo, orderState)

        // when
        val newShippingInfo = ShippingInfo("정카펀", "010-1234-5678", ADDRESS_01)
        val newOrder = order.changeShippingInfo(newShippingInfo)

        // then
        assertThat(newOrder.shippingInfo).isEqualTo(newOrder.shippingInfo)
    }

    @Test
    fun `출고 전에 주문을 취소할 수 있다`() {
        // given
        val products = listOf(ORDER_PRODUCT_01, ORDER_PRODUCT_02, ORDER_PRODUCT_03)
        val shippingInfo = shippingInfo
        val orderState = OrderState.PAYMENT_WAITING

        val order = Order(products, shippingInfo, orderState)

        // when
        val cancelledOrder = order.cancel()

        // then
        assertThat(cancelledOrder.orderState).isEqualTo(OrderState.CANCELLED)
    }

    @Test
    fun `출고 후에는 주문을 취소할 수 없다`() {
        // given
        val products = listOf(ORDER_PRODUCT_01, ORDER_PRODUCT_02, ORDER_PRODUCT_03)
        val shippingInfo = shippingInfo
        val orderState = OrderState.DELIVERING

        val order = Order(products, shippingInfo, orderState)

        // when

        // then
        assertThrows(IllegalArgumentException::class.java) { order.cancel() }
    }

    @Test
    fun `고객이 결제를 완료하기 전에는 상품을 준비하지 않는다`() {

    }
}