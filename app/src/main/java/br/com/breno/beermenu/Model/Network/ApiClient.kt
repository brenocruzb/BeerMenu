package br.com.breno.beermenu.Model.Network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private var retrofit: Retrofit? = null

    private var timeOut = 10

    /**Método de configuração para comunicação com a API**/
    fun getClient(): Retrofit? {
        if (retrofit == null) {

            val urlBase = "https://api.punkapi.com/v2/"

            val okHttpClient = OkHttpClient.Builder()
                    .readTimeout(timeOut.toLong(), TimeUnit.SECONDS)
                    .connectTimeout(timeOut.toLong(), TimeUnit.SECONDS)
                    .build()

            retrofit = Retrofit.Builder()
                    .baseUrl(urlBase)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        return retrofit
    }
}