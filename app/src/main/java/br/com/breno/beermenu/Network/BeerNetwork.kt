package br.com.breno.beermenu.Network

import android.util.Log
import br.com.breno.beermenu.Domain.Beer

class BeerNetwork {
    /**Variável que cria a comunicação com a API**/
    private val appService = ApiClient.getClient()?.create(AppService::class.java)

    /**Constante utilizada para erros da classe**/
    private val nameNetwork = "BeerNetwork"

    /**Método que consome a API. Retorna uma lista de bebidas de acordo com os filtros passados no Hash**/
    fun get(listFilter: HashMap<String, String>): List<Beer>?{
        val call = appService?.listBeersFiltered(listFilter)

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