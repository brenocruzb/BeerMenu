package br.com.breno.beermenu.Presenter.Domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Amount: Serializable {

    @SerializedName("value")
    var value: Double? = null
    @SerializedName("unit")
    var unit: String? = null
}