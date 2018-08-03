package br.com.breno.beermenu.Presenter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View

import br.com.breno.beermenu.MVP
import br.com.breno.beermenu.Presenter.Bo.BeerBo
import br.com.breno.beermenu.Presenter.Domain.Beer
import br.com.breno.beermenu.Model.Task.GetBeer
import br.com.breno.beermenu.R
import br.com.breno.beermenu.View.Util
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Presenter(private var view: MVP.ViewInter) : MVP.PresenterInter {

    private var loading = true

    /**Lista de filtros que o app utiliza para consumir a API**/
    private val listFilter = HashMap<String, String>()

    /**Método utilizado para converter uma String em objeto utilizando a API Gson**/
    private inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

    /**Banco da aplicação. É utilizado para armazenar as bebidas favoritas**/
    /**Objeto que se conecta com o banco na camada de modelo**/
    private val beerBo = BeerBo(this)

    override fun setView(view: MVP.ViewInter) {
        this.view = view
    }

    override fun getContext(): Context {
        return view as Context
    }

    override fun retrieveListFilter(savedInstanceState: Bundle?) {
        //Recuperação do estado da activity
        if(savedInstanceState != null && savedInstanceState.containsKey(Util.STATE_ACTIVITY)){
            val list: HashMap<String, String> = Gson().fromJson(savedInstanceState.getString(Util.STATE_ACTIVITY))
            listFilter.clear()
            listFilter.putAll(list)
        }
    }

    override fun onScrollListener(): RecyclerView.OnScrollListener {
        return object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                //Gerenciamento da paginação do app
                if(dy > 0){

                    val llm = recyclerView?.layoutManager as LinearLayoutManager

                    val lastItemPosition: Int = llm.findLastVisibleItemPosition()
                    val isMultiple: Boolean = (lastItemPosition + 1) % 25 == 0

                    if(isMultiple && loading){
                        if(lastItemPosition + llm.childCount >= llm.itemCount){
                            loading = false

                            listFilter[Util.Filter.PAGE] = ((lastItemPosition + 1) / 25 + 1).toString()

                            executeTask()
                        }
                    }else if(!isMultiple && !loading){
                        loading = true
                    }
                }
            }
        }
    }

    override fun onMenuItemClickListener(): Toolbar.OnMenuItemClickListener {
        return Toolbar.OnMenuItemClickListener { item: MenuItem ->
            val id = item.itemId

            when(id){
                R.id.filter_toolbar ->{
                    view.showFilterDialog(listFilter)
                }
            }
            true }
    }

    override fun executeTask() {
        //Execução assíncrona para o consumo da API.
        val myTask = GetBeer(this)
        myTask.execute(listFilter)
    }

    override fun getListFilter(): HashMap<String, String> {
        return listFilter
    }

    override fun updateIsFavorite(idBeer: Int) {
        if(beerBo.exists(idBeer))
            beerBo.delete(idBeer)
        else
            beerBo.insert(idBeer)
    }

    override fun getResultListBeer(listBeer: List<Beer>?) {
        if(listBeer != null){
            listBeer.forEach {
                item: Beer -> item.favoriteStatus = beerBo.exists(item.id)
            }
            view.getResultListBeer(listBeer)

        }else{
            view.showToast(R.string.error_conection)
        }
    }

    override fun showProgressBar(status: Boolean) {
        view.showProgressBar(if(status) View.VISIBLE else View.GONE)
    }

    override fun getItemsFilter(data: Intent) {
        listFilter.remove(Util.Filter.PAGE)

        if(data.extras != null){

            if(data.extras.keySet().contains(Util.Filter.ABV_GT))
                listFilter[Util.Filter.ABV_GT] = data.extras[Util.Filter.ABV_GT].toString()
            else
                listFilter.remove(Util.Filter.ABV_GT)

            if(data.extras.keySet().contains(Util.Filter.ABV_LT))
                listFilter[Util.Filter.ABV_LT] = data.extras[Util.Filter.ABV_LT].toString()
            else
                listFilter.remove(Util.Filter.ABV_LT)

            if(data.extras.keySet().contains(Util.Filter.IBU_GT))
                listFilter[Util.Filter.IBU_GT] = data.extras[Util.Filter.IBU_GT].toString()
            else
                listFilter.remove(Util.Filter.IBU_GT)

            if(data.extras.keySet().contains(Util.Filter.IBU_LT))
                listFilter[Util.Filter.IBU_LT] = data.extras[Util.Filter.IBU_LT].toString()
            else
                listFilter.remove(Util.Filter.IBU_LT)

            if(data.extras.keySet().contains(Util.Filter.EBC_GT))
                listFilter[Util.Filter.EBC_GT] = data.extras[Util.Filter.EBC_GT].toString()
            else
                listFilter.remove(Util.Filter.EBC_GT)

            if(data.extras.keySet().contains(Util.Filter.EBC_LT))
                listFilter[Util.Filter.EBC_LT] = data.extras[Util.Filter.EBC_LT].toString()
            else
                listFilter.remove(Util.Filter.EBC_LT)

            if(data.extras.keySet().contains(Util.Filter.BEER_NAME))
                listFilter[Util.Filter.BEER_NAME] = data.extras[Util.Filter.BEER_NAME].toString()
            else
                listFilter.remove(Util.Filter.BEER_NAME)

            if(data.extras.keySet().contains(Util.Filter.YEAST))
                listFilter[Util.Filter.YEAST] = data.extras[Util.Filter.YEAST].toString()
            else
                listFilter.remove(Util.Filter.YEAST)

            if(data.extras.keySet().contains(Util.Filter.BREWED_BEFORE))
                listFilter[Util.Filter.BREWED_BEFORE] = data.extras[Util.Filter.BREWED_BEFORE].toString()
            else
                listFilter.remove(Util.Filter.BREWED_BEFORE)

            if(data.extras.keySet().contains(Util.Filter.BREWED_AFTER))
                listFilter[Util.Filter.BREWED_AFTER] = data.extras[Util.Filter.BREWED_AFTER].toString()
            else
                listFilter.remove(Util.Filter.BREWED_AFTER)

            if(data.extras.keySet().contains(Util.Filter.HOPS))
                listFilter[Util.Filter.HOPS] = data.extras[Util.Filter.HOPS].toString()
            else
                listFilter.remove(Util.Filter.HOPS)

            if(data.extras.keySet().contains(Util.Filter.MALT))
                listFilter[Util.Filter.MALT] = data.extras[Util.Filter.MALT].toString()
            else
                listFilter.remove(Util.Filter.MALT)

            if(data.extras.keySet().contains(Util.Filter.FOOD))
                listFilter[Util.Filter.FOOD] = data.extras[Util.Filter.FOOD].toString()
            else
                listFilter.remove(Util.Filter.FOOD)


            //É necessário o procedimento diferenciado no filtro "ID's" já que "favoritos" também consome dela

            var filterId = ""

            val context = view as Context

            if(data.extras.keySet().contains(context.getString(R.string.favorite))){
                val listId = beerBo.getAll()
                filterId = listId[0].toString()

                for(i in 1 until listId.size)
                    filterId += "|${listId[i]}"

                listFilter[context.getString(R.string.favorite)] = filterId

                listFilter[Util.Filter.IDS] = filterId
            }else
                listFilter.remove(context.getString(R.string.favorite))

            if(data.extras.keySet().contains(Util.Filter.IDS)) {
                if(filterId.isNotEmpty()){
                    filterId += "|${data.extras[Util.Filter.IDS]}"
                }else{
                    filterId = data.extras[Util.Filter.IDS].toString()
                }

                listFilter[Util.Filter.IDS] = filterId
            }

            if( !data.extras.keySet().contains(context.getString(R.string.favorite)) &&
                    !data.extras.keySet().contains(Util.Filter.IDS)){
                listFilter.remove(Util.Filter.IDS)
            }

        }else{
            listFilter.clear()
        }

        val myTask = GetBeer(this)
        myTask.execute(listFilter)
    }
}