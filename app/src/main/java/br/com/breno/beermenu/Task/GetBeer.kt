package br.com.breno.beermenu.Task

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.view.View
import android.widget.ProgressBar
import br.com.breno.beermenu.Domain.Beer
import br.com.breno.beermenu.Interface.GetData
import br.com.breno.beermenu.Network.BeerNetwork

@SuppressLint("StaticFieldLeak")
class GetBeer constructor(private val getData: GetData?, private val progressBar: ProgressBar) : AsyncTask<HashMap<String, String>, Void, List<Beer>?>() {

    /**Antes de solicitar os dados da chamada REST, o progressBar fica visível**/
    override fun onPreExecute() {
        super.onPreExecute()
        progressBar.visibility = View.VISIBLE
    }

    /**Consumo da API. Se não houver problema com a conexão, o método retorna uma lista com as bebidas,
     * caso contrário retorna null**/
    override fun doInBackground(vararg values: HashMap<String, String>): List<Beer>? {
        val listFilter: HashMap<String, String> = if(values.isNotEmpty()) values[0] else HashMap()

        val beerNetwork = BeerNetwork()
        return beerNetwork.get(listFilter) ?: return null
    }

    /**Ao fim da chamada, a interface GetData é acionada e o progressBar fica oculto novamente**/
    override fun onPostExecute(result: List<Beer>?) {
        super.onPostExecute(result)
        progressBar.visibility = View.GONE
        getData?.getResultListBeer(result)
    }
}