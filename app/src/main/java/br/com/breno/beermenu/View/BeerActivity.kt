package br.com.breno.beermenu.View

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.breno.beermenu.Presenter.Domain.Beer
import br.com.breno.beermenu.R
import kotlinx.android.synthetic.main.activity_beer.*

class BeerActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer)

        setSupportActionBar(toolbarTop)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val beer: Beer = intent.getSerializableExtra(getString(R.string.beer)) as Beer

        toolbarTop.title = beer.name
        imageBeer.setImageURI(beer.imageUrl)
        textViewDescription.text = beer.description

        if(beer.ingredients?.malt != null){
            var txtMalt = ""

            beer.ingredients?.malt?.forEach {
                malt -> txtMalt += "${getString(R.string.name)} ${malt.name}\n${getString(R.string.amount)} ${malt.amount?.value} ${malt.amount?.unit}\n\n"
            }

            textViewMaltList.text = txtMalt
        }

        if(beer.ingredients?.hops != null){

            var txtHops = ""

            beer.ingredients?.hops?.forEach {
                hops -> txtHops += "${getString(R.string.name)} ${hops.name}\n${getString(R.string.amount)} ${hops.amount?.value} ${hops.amount?.unit}\n${getString(R.string.add)} ${hops.add}\n${getString(R.string.attribute)} ${hops.attribute}\n\n"
            }

            textViewHopsList.text = txtHops
        }

        if(beer.ingredients?.yeast != null){
            textViewYestName.text = beer.ingredients?.yeast
        }
    }

    /**Evento chamado ao clicar no cursor Voltar do toolbar**/
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}