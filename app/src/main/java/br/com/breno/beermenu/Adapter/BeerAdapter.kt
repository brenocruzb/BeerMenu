package br.com.breno.beermenu.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.breno.beermenu.Interface.MyResult
import br.com.breno.beermenu.Domain.Beer
import br.com.breno.beermenu.R
import com.facebook.drawee.view.SimpleDraweeView


class BeerAdapter constructor(private val context: Context, private val myResult: MyResult?) : RecyclerView.Adapter<BeerAdapter.MyViewHolder>(){

    /**Lista de bebidas. A lista será preenchida de acordo com os filtros dispostos na aplicação.**/
    private val listBeer: ArrayList<Beer> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_beer, parent, false)

        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val beer: Beer = listBeer[position]

        holder.tvNameBeer.text = beer.name
        holder.tvDescription.text = "${context.getString(R.string.breweded_in)} ${beer.firstBrewed}"
        holder.beerImage.setImageURI(beer.imageUrl)

        //Verifica se a bebida é marcada como favorita
        val resourceId = (if(beer.favoriteStatus) R.drawable.selected_favorite else R.drawable.unselected_favorite)

        //local_db
        holder.favoriteImage.setImageURI("res:///$resourceId")
    }

    override fun getItemCount(): Int {
        return listBeer.size
    }

    /**Retorna a lista de bebidas**/
    fun getList(): ArrayList<Beer>{
        return listBeer
    }

    /**Limpa a lista de bebidas**/
    fun clearList(){
        listBeer.clear()
        try {
            notifyDataSetChanged()
        }catch (e: Exception){
            Log.e("MyError", if (e.message != null) e.message else e.toString())
        }
    }

    /**Adisiona itens a lista de bebidas**/
    fun addList(listBeer: List<Beer>){
        this.listBeer.addAll(listBeer)
        try {
            notifyDataSetChanged()
        }catch (e: Exception){
            Log.e("MyError", if (e.message != null) e.message else e.toString())
        }
    }

    /**Marca uma bebida como favorita**/
    fun markFavorite(position: Int){
        listBeer[position].favoriteStatus = !listBeer[position].favoriteStatus
        try {
            notifyItemChanged(position)
        }catch (e: Exception){
            Log.e("MyError", if (e.message != null) e.message else e.toString())
        }
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{

        val beerImage = itemView.findViewById<SimpleDraweeView>(R.id.imageBeer)!!
        val favoriteImage = itemView.findViewById<SimpleDraweeView>(R.id.favorite)!!
        val tvNameBeer = itemView.findViewById<TextView>(R.id.tvNameBeer)!!
        val tvDescription = itemView.findViewById<TextView>(R.id.tvDescriptionBeer)!!

        init {
            itemView.setOnClickListener(this)
            favoriteImage.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            when(view?.id){
                R.id.favorite-> myResult?.myResult(adapterPosition, context.getString(R.string.favorite))
                else ->         myResult?.myResult(adapterPosition, context.getString(R.string.on_click))
            }
        }
    }
}