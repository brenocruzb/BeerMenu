package br.com.breno.beermenu.Domain

import com.google.gson.annotations.SerializedName

class Beer {

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

    @Transient
    var favoriteStatus: Boolean = false


//    "id": 192,
//    "name": "Punk IPA 2007 - 2010",
//    "tagline": "Post Modern Classic. Spiky. Tropical. Hoppy.",
//    "first_brewed": "04/2007",
//    "description": "Our flagship beer that kick started the craft beer revolution. This is James and Martin's original take on an American IPA, subverted with punchy New Zealand hops. Layered with new world hops to create an all-out riot of grapefruit, pineapple and lychee before a spiky, mouth-puckering bitter finish.",
//    "image_url": "https://images.punkapi.com/v2/192.png"
//    "food_pairing":
//    [
//    "Spicy carne asada with a pico de gallo sauce",
//    "Shredded chicken tacos with a mango chilli lime salsa",
//    "Cheesecake with a passion fruit swirl sauce"
//    ]

}