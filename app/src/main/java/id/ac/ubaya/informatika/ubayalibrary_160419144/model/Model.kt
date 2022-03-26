package id.ac.ubaya.informatika.ubayalibrary_160419144.model

import com.google.gson.annotations.SerializedName

data class Author(
    val id:String?,
    val fullname:String?,
    val bio:String?,
    @SerializedName("date_of_birth")
    val dob:String?,
    @SerializedName("photo_url")
    val imgUrl:String?,
)

data class Article(
    val id:String?,
    val title:String?,
    @SerializedName("date_added")
    val content:String?,
    val username:String?,
    @SerializedName("contentImg")
    val imgUrl: String?
)

data class Booklist(
    val id:String?,
    val name:String?,
    val username:String?,
    @SerializedName("date_added")
    val date:String?,
)

data class Book(
    val id:String?,
    val title:String?,
    val imgUrl:String?,
    //To fix mismatch name with the JSON field name
    @SerializedName("description")
    val desc:String?,
    @SerializedName("total_pages")
    val pages:Int?,
//    val author:Author?,
//    val genre:Genre?,
//    val publisher: Publisher?,
//    val reviews: Review?,
)

data class Genre(
    val id:String?,
    val name:String?,
    @SerializedName("description")
    val desc:String?,
)

data class Publisher(
    val id:String?,
    val title:String?,
    val bio:String?,
    @SerializedName("logoUrl")
    val imgUrl:String?,
)

data class Review(
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

data class User(
    val username:String?,
    val password:String?,
    val name:String?,
    //To fix mismatch name with the JSON field name
    @SerializedName("date_of_birth")
    val dob:String?,
    @SerializedName("created_at")
    val joinDate:String?,
    val bio:String?,
    @SerializedName("photo_url")
    val imgUrl:String?,
)

