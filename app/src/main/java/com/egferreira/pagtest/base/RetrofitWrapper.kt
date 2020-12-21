package com.egferreira.pagtest.base

import com.egferreira.pagtest.base.request.Result
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import com.egferreira.pagtest.base.response.Error
import com.egferreira.pagtest.base.response.ErrorResponse

suspend fun <T> retrofitWrapper(call: suspend () -> T): Result<T> {
    return try {
        Result.success(call())
    } catch (ioException: IOException) {
        Result.failure(getInternalServerError(ioException))
    } catch (httpException: HttpException) {
        val errorBody = httpException.response()?.errorBody()

        when (httpException.code()) {
            HttpURLConnection.HTTP_BAD_REQUEST -> {
                Result.failure(getBadRequestError(errorBody, httpException))
            }
            else -> {
                Result.failure(getGenericError(httpException))
            }
        }
    }
}

private fun getInternalServerError(ioException: IOException) = Error(
    HttpURLConnection.HTTP_INTERNAL_ERROR,
    title = Constants.TITULO_MSG_ERRO_CONEXAO,
    message = Constants.MSG_ERRO_CONEXAO,
    cause = ioException
)

private fun getGenericError(httpException: HttpException) =
    com.egferreira.pagtest.base.response.Error(
        errorCode = httpException.code(),
        title = Constants.TITULO_MSG_ERRO_PADRAO,
        message = "${Constants.DEFAULT_ERROR_MESSAGE_WITH_CODE}${httpException.code()}",
        cause = httpException
    )

private fun getBadRequestError(
    errorBody: ResponseBody?,
    httpException: HttpException
): Error {
    return try {
        val error = Gson().fromJson(errorBody?.charStream(), ErrorResponse::class.java)
        Error(
            errorCode = httpException.code(),
            title = error.title,
            message = error.detail,
            altMessage = error.message,
            cause = httpException
        )
    } catch (e: Exception) {
        getGenericError(httpException)
    }
}