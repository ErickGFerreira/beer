package com.egferreira.pagtest.base.response

data class Error(
    val errorCode: Int = 0, val title: String = "", override val message: String = "",
    val altMessage: String = "", val tipo: String = "", override val cause: Throwable
) : Throwable()