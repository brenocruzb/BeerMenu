package br.com.breno.beermenu.Domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Beer: Serializable {

    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name: String? = null
    @SerializedName("tagline")
    var tagline: String? = null
    @SerializedName("first_brewed")
    var firstBrewed: String? = null
    @SerializedName("image_url")
    var imageUrl: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("food_pairing")
    var foodPairing: List<String>? = null
    @SerializedName("ingredients")
    var ingredients: Ingredient? = null

    @Transient
    var favoriteStatus: Boolean = false
}