package br.com.breno.beermenu.Presenter.Bo

import br.com.breno.beermenu.MVP
import br.com.breno.beermenu.Model.Dao.BeerDao

/**Classe responsável por comunicar a activity com o Dao da aplicação**/
class BeerBo constructor(presenter: MVP.PresenterInter) {
    private val beerDao = BeerDao(presenter)

    fun insert(idBeer: Int): Long{
        return beerDao.insert(idBeer)
    }

    fun delete(idBeer: Int): Boolean {
        return beerDao.delete(idBeer)
    }

    fun getAll(): ArrayList<Int>{
        val sql = "SELECT * FROM beer"
        return beerDao.getList(sql)
    }

    fun exists(idBeer: Int): Boolean{
        val sql = "SELECT * FROM beer WHERE id_beer = $idBeer"
        return beerDao.getList(sql).size > 0
    }
}