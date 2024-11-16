package com.example.cryptotracker.core.data.networking

import com.example.cryptotracker.core.domain.util.NetworkError
import com.example.cryptotracker.core.domain.util.Result
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.SerializationException
import java.nio.channels.UnresolvedAddressException

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
) : Result<T, NetworkError> {
    val response = try{
      execute()
    }catch (e: UnresolvedAddressException){
       return  Result.Error(NetworkError.NO_INTERNET)
    }catch (e: SerializationException){
       return Result.Error(NetworkError.SERIALIZATION)
    }catch (e: Exception){
       return Result.Error(NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}