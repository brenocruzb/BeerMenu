package br.com.breno.beermenu.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.breno.beermenu.Interface.MyResult
import br.com.breno.beermenu.Domain.Beer
import br.com.breno.beermenu.R
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.common.util.UriUtil
import android.net.Uri


class BeerAdapter constructor(private val context: Context, private val listBeer: ArrayList<Beer>, private val myResult: MyResult?) : RecyclerView.Adapter<BeerAdapter.MyViewHolder>(){
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

        //local_db
//        val uri = Uri.parse("res:///" + (if(beer.favoriteStatus) R.drawable.selected_favorite else R.drawable.unselected_favorite))

        val uri = Uri.Builder()
                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME) // "res"
                .path((if(beer.favoriteStatus) R.drawable.selected_favorite else R.drawable.unselected_favorite).toString())
                .build()

        holder.favoriteImage.setImageURI(uri)
    }

    override fun getItemCount(): Int {
        return listBeer.size
    }

    fun getList(): ArrayList<Beer>{
        return listBeer
    }

    fun addList(listBeer: List<Beer>){
        this.listBeer.addAll(listBeer)
        try {
            notifyDataSetChanged()
        }catch (e: Exception){
            Log.e("MyError", if (e.message != null) e.message else e.toString())
        }
    }

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
        val favoriteImage = itemView.findViewById<ImageView>(R.id.favorite)!!
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