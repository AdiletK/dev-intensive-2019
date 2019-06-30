package ru.skillbranch.devintensive.models

class UserView (
    val id: String,
    val fullName:String,
    val nickName:String,
    val avatar: String?,
    var status: String?,
    val initials: String?
){

}