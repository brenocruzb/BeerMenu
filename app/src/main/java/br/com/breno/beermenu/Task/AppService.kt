package br.com.breno.beermenu.Task

import br.com.breno.beermenu.Domain.Beer
import retrofit2.Call
import retrofit2.http.GET

interface AppService {
    @GET("beers")
    fun listBeers(): Call<List<Beer>>
}