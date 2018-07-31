package br.com.breno.beermenu.Task

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.view.View
import android.widget.ProgressBar
import br.com.breno.beermenu.Domain.Beer
import br.com.breno.beermenu.Interface.GetData
import br.com.breno.beermenu.Network.BeerNetwork

@SuppressLint("StaticFieldLeak")
class GetBeer constructor(private val context: Context, private val getData: GetData?, private val progressBar: ProgressBar) : AsyncTask<String?, Void, List<Beer>?>() {

    override fun onPreExecute() {
        super.onPreExecute()
        progressBar.visibility = View.VISIBLE
    }

    override fun doInBackground(vararg values: String?): List<Beer>? {
        val beerNetwork = BeerNetwork(context)

        return beerNetwork.get() ?: return null
    }

    override fun onPostExecute(result: List<Beer>?) {
        super.onPostExecute(result)
        progressBar.visibility = View.GONE
        getData?.getResultListBeer(result)
    }
}