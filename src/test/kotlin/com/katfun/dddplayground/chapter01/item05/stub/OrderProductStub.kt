package com.katfun.dddplayground.chapter01.item05.stub

import com.katfun.dddplayground.chapter01.item05.OrderProduct
import com.katfun.dddplayground.chapter01.item05.Product
import java.math.BigDecimal

object OrderProductStub {
    private val PRODUCT_01 = Product(
        name = "Apple Mac Studio M2 Max",
        price = BigDecimal.valueOf(3100000)
    )

    private val PRODUCT_02 = Product(
        name = "무농약부추",
        price = BigDecimal.valueOf(1820)
    )

    private val PRODUCT_03 = Product(
        name = "소라사키 히나 한정판 피규어",
        price = BigDecimal.valueOf(318000)
    )

    val ORDER_PRODUCT_01 = OrderProduct(PRODUCT_01, 1)

    val ORDER_PRODUCT_02 = OrderProduct(PRODUCT_02, 2)

    val ORDER_PRODUCT_03 = OrderProduct(PRODUCT_03, 5)
}