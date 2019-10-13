package com.ginzo.remote

import arrow.core.Either
import io.reactivex.SingleTransformer
import retrofit2.Response

fun <T, S> responseToEither(map: (T) -> S): SingleTransformer<Response<T>, Either<Throwable, S>> {
  return SingleTransformer { single ->
    single
      .map { response ->
        try {
          Either.cond(
            response.isSuccessful,
            { map(response.body()!!) },
            { Throwable() })
        } catch (e: Exception) {
          Either.left(Throwable(e))
        }
      }
      .onErrorReturn {
        Either.left(it)
      }
  }
}