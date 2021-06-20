package com.nayandhabarde.wingo.retrofit

import com.nayandhabarde.wingo.constants.WingoHeaders
import com.nayandhabarde.wingo.model.PageResponse
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import retrofit2.*
import java.lang.Exception
import java.lang.IllegalStateException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class PageResponseCallAdapterFactory private  constructor(): CallAdapter.Factory() {

    companion object {
        @JvmStatic @JvmName("create")
        operator fun invoke() = PageResponseCallAdapterFactory()
    }

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if(Deferred::class.java != getRawType(returnType)) {
            return null
        }

        if(returnType !is ParameterizedType) {
            throw IllegalStateException(
                "Deferred return type must be parameterized as Deferred<Foo> or Deferred<out Foo>"
            )
        }
        val responseType = getParameterUpperBound(0, returnType)


        if(PageResponse::class.java != getRawType(responseType)) {
            return null
        }

        if(responseType !is ParameterizedType) {
            throw IllegalStateException(
                "PageResponse return type must be parameterized as PageResponse<Foo> or PageResponse<out Foo>"
            )
        }

        val rawPageResponseType = getRawType(responseType)
        return BodyCallAdapter<Any>(rawPageResponseType)
    }

    private class BodyCallAdapter<T>(
        private val responseType: Type
    ) : CallAdapter<T, Deferred<PageResponse<T>>> {

        override fun responseType() = responseType

        override fun adapt(call: Call<T>): Deferred<PageResponse<T>> {
            val deferred = CompletableDeferred<PageResponse<T>>()

            deferred.invokeOnCompletion {
                if (deferred.isCancelled) {
                    call.cancel()
                }
            }

            call.enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    deferred.completeExceptionally(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.isSuccessful) {
                        val total: Long? = response.headers()[WingoHeaders.HEADER_X_TOTAL.value]?.toLong()
                        val pageResponse: PageResponse<T> = PageResponse(response.body()!!, total!!)
                        deferred.complete(pageResponse)
                    } else {
                        deferred.completeExceptionally(HttpException(response))
                    }
                }
            })

            return deferred
        }
    }
}

