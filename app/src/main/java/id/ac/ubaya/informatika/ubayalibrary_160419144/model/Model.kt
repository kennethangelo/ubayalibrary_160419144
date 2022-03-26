package id.ac.ubaya.informatika.ubayalibrary_160419144.model

import com.google.gson.annotations.SerializedName

data class Book(
    val id:String?,
    val title:String?,
    val imgUrl:String?,
    //To fix mismatch name with the JSON field name
    @SerializedName("description")
    val desc:String?,
    @SerializedName("total_pages")
    val pages:Int?,
    val author:String?,
    val genre:String?,
    val publisher: String?
)