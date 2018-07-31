package br.com.breno.beermenu

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import br.com.breno.beermenu.Adapter.BeerAdapter
import br.com.breno.beermenu.Domain.Beer
import br.com.breno.beermenu.Interface.GetData
import br.com.breno.beermenu.Interface.MyResult
import br.com.breno.beermenu.Task.GetBeer
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MyResult, GetData {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_main)

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
            adapter.addList(listBeer)
        }
    }
}
