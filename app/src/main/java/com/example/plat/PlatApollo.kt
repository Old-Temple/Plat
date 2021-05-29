package com.example.plat

import android.content.Context
import android.os.Looper
import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.subscription.WebSocketSubscriptionTransport
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response


/*
서버 관련한 것들
엥간해서는 건들지 말자
 */
private var instance: ApolloClient? = null

class PlatApollo{
    val apolloCient =  ApolloClient.builder()
        .serverUrl("https://plat-backend.herokuapp.com")
        .build()
}

fun apolloClient(context: Context): ApolloClient {
    check(Looper.myLooper() == Looper.getMainLooper()) {
        "Only the main thread can get the apolloClient instance"
    }

    if (instance != null) {
        return instance!!
    }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthorizationInterceptor(context))
        .build()

    instance =  ApolloClient.builder()
        .serverUrl("https://plat-backend.herokuapp.com")
        .subscriptionTransportFactory(WebSocketSubscriptionTransport.Factory("wss://plat-backend.herokuapp.com", okHttpClient))
        .okHttpClient(okHttpClient)
        .build()


    return instance!!
}

private class AuthorizationInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("token", PlatPrefs.prefs.getValue("token",""))
            .build()

        return chain.proceed(request)
    }
}