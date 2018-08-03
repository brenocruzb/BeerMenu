package br.com.breno.beermenu.View

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import br.com.breno.beermenu.View.Adapter.BeerAdapter
import br.com.breno.beermenu.Presenter.Domain.Beer
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import br.com.breno.beermenu.Presenter.Dialog.FilterBeerDialog
import br.com.breno.beermenu.MVP
import br.com.breno.beermenu.Presenter.Presenter
import br.com.breno.beermenu.R
import com.google.gson.Gson


class MainActivity : AppCompatActivity(), MVP.ViewInter {

    companion object {
        private var presenter: MVP.PresenterInter? = null
    }

    /**Adapter da aplicação**/
    private lateinit var adapter: BeerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_main)

        if(presenter == null)
            presenter = Presenter(this)

        presenter?.setView(this)
        presenter?.retrieveListFilter(savedInstanceState)

        //Toolbar
        toolbarMain.inflateMenu(R.menu.menu_filter)
        toolbarMain.title = getString(R.string.toolbar_title)
        toolbarMain.setOnMenuItemClickListener(presenter?.onMenuItemClickListener())

        //Support
        val context: Context = this
        val myResult: MVP.ViewInter = this

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
        myRecyclerView.addOnScrollListener(presenter?.onScrollListener())

        presenter?.executeTask()
    }

    /**Salva o filtro para o usuário não perder a lista caso rotacione o celular**/
    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(Util.STATE_ACTIVITY, Gson().toJson(presenter?.getListFilter()))
        super.onSaveInstanceState(outState)
    }

    override fun showBeer(position: Int) {
        val listBeer: ArrayList<Beer> = adapter.getList()
        val intent = Intent(this@MainActivity, BeerActivity::class.java)
        intent.putExtra(getString(R.string.beer), listBeer[position])
        startActivity(intent)
    }

    override fun updateIsFavorite(position: Int) {
        val idBeer = adapter.getList()[position].id
        presenter?.updateIsFavorite(idBeer)
        adapter.markFavorite(position)
    }

    override fun getResultListBeer(listBeer: List<Beer>) {
        adapter.addList(listBeer)
    }

    override fun showToast(message: Int) {
        Toast.makeText(this, getString(message), Toast.LENGTH_LONG).show()
    }

    override fun getItemsFilter(data: Intent) {
        adapter.clearList()
        presenter?.getItemsFilter(data)
    }

    override fun showProgressBar(visible: Int) {
        myProgressBar.visibility = visible
    }

    override fun showFilterDialog(listFilter: HashMap<String, String>) {
        //Instancia do DialogFragment.
        val filterBeerDialog = FilterBeerDialog()
        val args = Bundle()
        args.putString(getString(R.string.filter), Gson().toJson(listFilter))
        filterBeerDialog.arguments = args
        filterBeerDialog.show(supportFragmentManager, "missiles")
    }
}
