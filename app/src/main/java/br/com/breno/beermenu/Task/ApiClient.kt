package br.com.breno.beermenu.Task

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private var retrofit: Retrofit? = null

    private var timeOut = 10

    fun getClient(context: Context): Retrofit? {
        if (retrofit == null) {

            val urlBase = "https://api.punkapi.com/v2/"

            val okHttpClient = OkHttpClient.Builder()
                    .readTimeout(timeOut.toLong(), TimeUnit.SECONDS)
                    .connectTimeout(timeOut.toLong(), TimeUnit.SECONDS)
//                    .addInterceptor(GzipRequestInterceptor())
                    .build()

//            val gson = GsonBuilder()
//                    .registerTypeHierarchyAdapter(ByteArray::class.java, ByteArrayToBase64TypeAdapter())
//                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'-0300'")
//                    .create()

            retrofit = Retrofit.Builder()
                    .baseUrl(urlBase)
                    .client(okHttpClient)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        return retrofit
    }
}