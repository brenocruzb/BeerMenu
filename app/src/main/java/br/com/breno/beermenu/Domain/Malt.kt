package br.com.breno.beermenu.Domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Malt: Serializable {
    @SerializedName("name")
    var name: String? = null
    @SerializedName("amount")
    var amount: Amount? = null
}