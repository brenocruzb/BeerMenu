package br.com.breno.beermenu.Domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Hops: Serializable {
    @SerializedName("name")
    var name: String? = null
    @SerializedName("amount")
    var amount: Amount? = null
    @SerializedName("add")
    var add: String? = null
    @SerializedName("attribute")
    var attribute: String? = null
}