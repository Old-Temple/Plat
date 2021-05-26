package com.example.plat

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlatApollo{
    val apolloCient =  ApolloClient.builder()
        .serverUrl("https://plat-backend.herokuapp.com")
        .build()
}