package br.com.breno.beermenu.Interface

import br.com.breno.beermenu.Domain.Beer

/**Interface responsável por ligar a chamada assíncrona que consome a API
 * com a mainActivity**/
interface GetData {
    fun getResultListBeer(listBeer: List<Beer>?)
}