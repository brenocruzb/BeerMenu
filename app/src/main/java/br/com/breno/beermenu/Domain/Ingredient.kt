package br.com.breno.beermenu.Domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Ingredient: Serializable {

    @SerializedName("malt")
    var malt: List<Malt>? = null
    @SerializedName("hops")
    var hops: List<Hops>? = null
    @SerializedName("yeast")
    var yeast: String? = null
}