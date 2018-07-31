package br.com.breno.beermenu.Interface

import br.com.breno.beermenu.Domain.Beer

interface GetData {
    fun getResultListBeer(listBeer: List<Beer>?)
}