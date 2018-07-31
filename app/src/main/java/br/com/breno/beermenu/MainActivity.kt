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
import br.com.breno.beermenu.Dialog.FilterBeerDialog
import br.com.breno.beermenu.Interface.GetItemsFilter


class MainActivity : AppCompatActivity(), MyResult, GetData, GetItemsFilter {

    private var intentFilter: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_main)

        //Toolbar
        toolbarMain.inflateMenu(R.menu.menu_filter)
        toolbarMain.setOnMenuItemClickListener {item: MenuItem ->
            val id = item.itemId

            when(id){
                R.id.filter_toolbar ->{
                    val filterBeerDialog = FilterBeerDialog()

//                    if (arguments != null) {
//                        val args = Bundle()
//                        val gson = Gson()
//                        args.putString(resources.getString(R.string.beer), gson.toJson(beer))
//                        filterBeerDialog.arguments = args
//                    }


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

        //Linear Layout
        val llm = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        //List
        val listBeer: ArrayList<Beer> = ArrayList()

        //Adapter
        val adapter = BeerAdapter(context ,listBeer, myResult)

        //Recycler
        myRecyclerView.setHasFixedSize(true)
        myRecyclerView.layoutManager = llm
        myRecyclerView.adapter = adapter

        val myTask = GetBeer(context, getData, myProgressBar)
        myTask.execute()

    }

    override fun myResult(result: Int, message: String) {

        val adapter = myRecyclerView.adapter as BeerAdapter
        val listBeer: ArrayList<Beer> = adapter.getList()

        when(message){
            getString(R.string.on_click) -> Toast.makeText(this, listBeer[result].name, Toast.LENGTH_LONG).show()
            getString(R.string.favorite) -> {
                adapter.markFavorite(result)
            }
        }
    }

    override fun getResultListBeer(listBeer: List<Beer>?) {
        if(listBeer != null){
            val adapter = myRecyclerView.adapter as BeerAdapter
            adapter.clearList()
            adapter.addList(listBeer)
        }
    }

    override fun getItemsFilter(requestCode: Int, resultCode: Int, data: Intent) {
        if(resultCode == Util.FILTER_BEER){
            intentFilter = data
            val listFilter = HashMap<String, String>()

            if(data.extras != null){
                if(data.extras.keySet().contains(getString(R.string.name)))
                    listFilter["beer_name"] = data.extras[getString(R.string.name)].toString()
                if(data.extras.keySet().contains(getString(R.string.yeast)))
                    listFilter["yeast"] = data.extras[getString(R.string.yeast)].toString()
                if(data.extras.keySet().contains(getString(R.string.brewed_before)))
                    listFilter["brewed_before"] = data.extras[getString(R.string.brewed_before)].toString()
                if(data.extras.keySet().contains(getString(R.string.brewed_after)))
                    listFilter["brewed_after"] = data.extras[getString(R.string.brewed_after)].toString()
            }

            val context = this
            val getData = this

            val myTask = GetBeer(context, getData, myProgressBar)
            myTask.execute(listFilter)
        }
    }
}
