package com.cals.getloc.model

import java.io.Serializable

class HomeModel : Serializable {
    var category: String? = null
    var city: String? = null
    var namePlace: String? = null
    var price: String? = null
    var place_id: String? = null
    var spend_time: Int? = null
    var aggregateRating = 0.0
}