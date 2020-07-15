package br.com.breno.beermenu.Model.Network

import br.com.breno.beermenu.Presenter.Domain.Beer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface AppService {

    /**Consulta utilizando filtros dinâmicos**/
    @GET("beers")
    fun listBeersFiltered(@QueryMap params: Map<String, String>): Call<List<Beer>>
}