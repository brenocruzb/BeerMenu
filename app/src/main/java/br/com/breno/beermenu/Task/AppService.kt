package br.com.breno.beermenu.Task

import br.com.breno.beermenu.Domain.Beer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface AppService {
    @GET("beers")
    fun listBeers(): Call<List<Beer>>

    @GET("beers")
    fun listBeersFiltered(@QueryMap params: Map<String, String>): Call<List<Beer>>
}