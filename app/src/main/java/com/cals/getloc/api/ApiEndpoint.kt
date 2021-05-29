package com.cals.getloc.api

object ApiEndpoint {

    @JvmField
    var BASEURL = "https://getloc-314510.et.r.appspot.com"
    @JvmField
    var getAllList = "/wisata"
    @JvmField
    var WisataByPlaceId = "/wisata/{id}"
    @JvmField
    var search_by_name = "/wisata/search/name?nama={nama}"
    @JvmField
    var DetailRestaurant = "restaurant?res_id="
    @JvmField
    var ReviewRestaurant = "reviews?res_id="
    @JvmField
    var CariResto = "search?q="
}