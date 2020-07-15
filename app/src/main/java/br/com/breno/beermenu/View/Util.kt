package br.com.breno.beermenu.View

object Util {
    /**Constante utilizada para salvar o estado da activity**/
    val STATE_ACTIVITY: String = "state-activity"

    /**Constantes utilizadas nos filtros de busca disponibilizados pela API**/
    object Filter{
        val ABV_GT: String = "abv_gt"
        val ABV_LT: String = "abv_lt"
        val IBU_GT: String = "ibu_gt"
        val IBU_LT: String = "ibu_lt"
        val EBC_GT: String = "ebc_gt"
        val EBC_LT: String = "ebc_lt"
        val BEER_NAME: String = "beer_name"
        val YEAST: String = "yeast"
        val BREWED_BEFORE: String = "brewed_before"
        val BREWED_AFTER: String = "brewed_after"
        val HOPS: String = "hops"
        val MALT: String = "malt"
        val FOOD: String = "food"
        val IDS: String = "ids"

        val PAGE: String = "page"
    }
}