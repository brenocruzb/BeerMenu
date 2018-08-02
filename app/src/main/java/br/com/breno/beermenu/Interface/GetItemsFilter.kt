package br.com.breno.beermenu.Interface

import android.content.Intent

/**Interface responsável por ligar o dialog de filtros com
 * a mainActivity**/
interface GetItemsFilter {
    fun getItemsFilter(requestCode: Int, resultCode: Int, data: Intent)
}