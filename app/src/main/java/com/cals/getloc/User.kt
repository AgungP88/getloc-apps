package com.cals.getloc

data class User(
    var userID: String = "",
    var profilePictureURL: String = "",
    var active: Boolean = false,
    var email: String = "",
    var userName: String = ""
)