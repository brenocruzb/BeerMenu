package br.com.breno.beermenu.Network

import android.content.Context
import android.util.Log
import br.com.breno.beermenu.Domain.Beer
import br.com.breno.beermenu.Task.ApiClient
import br.com.breno.beermenu.Task.AppService

class BeerNetwork constructor(context: Context) {
    private val mAppService = ApiClient.getClient(context)?.create(AppService::class.java)
    private val nameNetwork = "BeerNetwork"

    fun get(): List<Beer>?{
        val call = mAppService?.listBeers()

        try{
            val response = call?.execute()
            if(response?.isSuccessful!!){
                return response.body()
            }

        }catch (e: Exception){
            Log.e("MyError", "Get $nameNetwork " + if (e.message != null) e.message else e.toString())
        }
        return null
    }
}