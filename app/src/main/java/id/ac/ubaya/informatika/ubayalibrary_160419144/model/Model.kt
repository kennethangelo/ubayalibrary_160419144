package id.ac.ubaya.informatika.ubayalibrary_160419144.model

import androidx.room.*

@Entity
data class Article(
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "date_added")
    var date_added: String,
    @ColumnInfo(name = "content")
    var content: String,
    @ColumnInfo(name = "username")
    var username: String,
    @ColumnInfo(name = "imgUrl")
    var imgUrl: String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}

@Entity
data class Book(
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "imgUrl")
    var imgUrl: String,
    @ColumnInfo(name = "desc")
    var desc: String,
    @ColumnInfo(name = "pages")
    var pages: String,
    @ColumnInfo(name = "author")
    var author: String,
    @ColumnInfo(name = "category")
    var category: String,
    @ColumnInfo(name = "publisher")
    var publisher: String,
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}

@Entity
data class User(
    @PrimaryKey
    var username: String,
    @ColumnInfo(name = "password")
    var password: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "dob")
    var dob: String,
    @ColumnInfo(name = "joinDate")
    var joinDate: String,
    @ColumnInfo(name = "bio")
    var bio: String,
    @ColumnInfo(name = "imgUrl")
    var imgUrl: String,
)

