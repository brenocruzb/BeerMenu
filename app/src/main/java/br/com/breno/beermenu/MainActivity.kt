package br.com.breno.beermenu

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.widget.Toast
import br.com.breno.beermenu.Adapter.BeerAdapter
import br.com.breno.beermenu.Domain.Beer
import br.com.breno.beermenu.Interface.GetData
import br.com.breno.beermenu.Interface.MyResult
import br.com.breno.beermenu.Task.GetBeer
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.support.v7.widget.RecyclerView
import br.com.breno.beermenu.Bo.BeerBo
import br.com.breno.beermenu.Dialog.FilterBeerDialog
import br.com.breno.beermenu.Interface.GetItemsFilter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity(), MyResult, GetData, GetItemsFilter {

    private lateinit var adapter: BeerAdapter

    private val listFilter = HashMap<String, String>()
    private lateinit var beerBo: BeerBo

    private inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_main)

        //Bo
        beerBo = BeerBo(this)

        //Toolbar
        toolbarMain.inflateMenu(R.menu.menu_filter)
        toolbarMain.title = getString(R.string.toolbar_title)
        toolbarMain.setOnMenuItemClickListener {item: MenuItem ->
            val id = item.itemId

            when(id){
                R.id.filter_toolbar ->{
                    val filterBeerDialog = FilterBeerDialog()

                    val args = Bundle()
                    args.putString(resources.getString(R.string.filter), Gson().toJson(listFilter))
                    filterBeerDialog.arguments = args

//                    filterBeerDialog.setTargetFragment(Fragment(), Util.FILTER_BEER)
                    filterBeerDialog.show(supportFragmentManager, "missiles")
                }
            }
            true
        }

        //Support
        val context: Context = this
        val myResult: MyResult = this
        val getData: GetData = this
        var loading = true

        //Linear Layout
        val llm = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        //Adapter
        adapter = BeerAdapter(context, myResult)

        //Recycler
        myRecyclerView.setHasFixedSize(true)
        myRecyclerView.layoutManager = llm
        myRecyclerView.adapter = adapter
        myRecyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if(dy > 0){
                    val lastItemPosition: Int = llm.findLastVisibleItemPosition()
                    val isMultiple: Boolean = (lastItemPosition + 1) % 25 == 0

                    if(isMultiple && loading){
                        if(lastItemPosition + llm.childCount >= llm.itemCount){
                            loading = false

                            listFilter[Util.Filter.PAGE] = ((lastItemPosition + 1) / 25 + 1).toString()

                            val myTask = GetBeer(context, getData, myProgressBar)
                            myTask.execute(listFilter)
                        }
                    }else if(!isMultiple && !loading){
                        loading = true
                    }
                }
            }
        })

        if(savedInstanceState != null && savedInstanceState.containsKey(Util.STATE_ACTIVITY)){
            val list: HashMap<String, String> = Gson().fromJson(savedInstanceState.getString(Util.STATE_ACTIVITY))
            listFilter.clear()
            listFilter.putAll(list)
        }

        val myTask = GetBeer(context, getData, myProgressBar)
        myTask.execute(listFilter)
    }

    //Salva o filtro para o usuário não perder a lista caso rotacione o celular
    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(Util.STATE_ACTIVITY, Gson().toJson(listFilter))

        super.onSaveInstanceState(outState)
    }

    //Retorno do Adapter
    override fun myResult(result: Int, message: String) {
        val listBeer: ArrayList<Beer> = adapter.getList()

        when(message){
            getString(R.string.on_click) -> {
                val intent = Intent(this@MainActivity, BeerActivity::class.java)
                intent.putExtra(getString(R.string.beer), listBeer[result])
                startActivity(intent)
            }
            getString(R.string.favorite) -> {

                val idBeer = listBeer[result].id

                if(beerBo.exists(idBeer))
                    beerBo.delete(idBeer)
                else
                    beerBo.insert(idBeer)

                adapter.markFavorite(result)
            }
        }
    }

    //Retorno do REST
    override fun getResultListBeer(listBeer: List<Beer>?) {
        if(listBeer != null){
            listBeer.forEach {
                item: Beer -> item.favoriteStatus = beerBo.exists(item.id)
            }

            adapter.addList(listBeer)
        }else{
            Toast.makeText(this, getString(R.string.error_conection), Toast.LENGTH_LONG).show()
        }
    }

    //Rotorno dos Filtros
    override fun getItemsFilter(requestCode: Int, resultCode: Int, data: Intent) {
        if(resultCode == Util.FILTER_BEER){
            adapter.clearList()
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


                //É necessário o procedimento diferenciado na chave "ID's" já que "favoritos" também consome dela

                var filterId = ""

                if(data.extras.keySet().contains(getString(R.string.favorite))){
                    val listId = beerBo.getAll()
                    filterId = listId[0].toString()

                    for(i in 1 until listId.size)
                        filterId += "|${listId[i]}"

                    listFilter[getString(R.string.favorite)] = filterId

                    listFilter[Util.Filter.IDS] = filterId
                }else
                    listFilter.remove(getString(R.string.favorite))

                if(data.extras.keySet().contains(Util.Filter.IDS)) {
                    if(filterId.isNotEmpty()){
                        filterId += "|${data.extras[Util.Filter.IDS]}"
                    }else{
                        filterId = data.extras[Util.Filter.IDS].toString()
                    }

                    listFilter[Util.Filter.IDS] = filterId
                }

                if( !data.extras.keySet().contains(getString(R.string.favorite)) &&
                    !data.extras.keySet().contains(Util.Filter.IDS)){
                    listFilter.remove(Util.Filter.IDS)
                }

            }else{
                listFilter.clear()
            }

            val context: Context = this
            val getData: GetData = this

            val myTask = GetBeer(context, getData, myProgressBar)
            myTask.execute(listFilter)
        }
    }
}
