package br.com.breno.beermenu.Network

import br.com.breno.beermenu.Domain.Beer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface AppService {

    /**Consulta utilizando filtros dinâmicos**/
    @GET("beers")
    fun listBeersFiltered(@QueryMap params: Map<String, String>): Call<List<Beer>>
}