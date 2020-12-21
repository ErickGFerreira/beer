package com.egferreira.pagtest.base

object Constants {
    const val ISO_8601_24H_FULL_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
    const val BASE_URL = "https://api.punkapi.com/v2/"
    const val CONNECTION_TIME_OUT: Long = 60

    const val TITULO_MSG_ERRO_CONEXAO = "Ops, falha na conexão. :/"
    const val MSG_ERRO_CONEXAO =
        "Verifique se o seu dispositivo está conectado e tente realizar esta operação novamente."

    const val TITULO_MSG_ERRO_PADRAO = "Algo deu errado :("
    const val MSG_ERRO_PADRAO =
        "Não foi possivel completar a operação. \nTente novamente em alguns instantes."
    val DEFAULT_ERROR_MESSAGE_WITH_CODE: String =
        MSG_ERRO_PADRAO + " Código de erro: "

}

