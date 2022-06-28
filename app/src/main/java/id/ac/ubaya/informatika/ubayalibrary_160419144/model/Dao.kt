package id.ac.ubaya.informatika.ubayalibrary_160419144.model

import androidx.room.*

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(vararg article:Article)

    @Query("SELECT * FROM article")
    suspend fun selectAllArticle():List<Article>

    @Query("SELECT * FROM article WHERE uuid = :id")
    suspend fun selectArticle(id:Int):Article

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("UPDATE article SET title= :title, content = :content, imgUrl = :imgUrl WHERE uuid = :uuid")
    suspend fun updateArticle(title:String, content:String, imgUrl:String, uuid:Int)

    @Query("SELECT a.* FROM article a INNER JOIN user u ON a.username = u.username WHERE a.username = :username")
    suspend fun selectArticleBasedOnUsername(username:String):List<Article>
}

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBook(vararg book:Book)

    @Query("SELECT * FROM book")
    suspend fun selectAllBooks():List<Book>

    @Query("SELECT * FROM book WHERE uuid = :id")
    suspend fun selectBook(id:Int):Book

    @Delete
    suspend fun deleteBook(book: Book)

    @Query("UPDATE book SET title= :title, `desc` = :desc, imgUrl = :imgUrl, pages = :pages, author = :author, category = :category, publisher = :publisher WHERE uuid = :uuid")
    suspend fun update(title:String, imgUrl:String, desc:String, pages:String, author:String, category:String, publisher: String, uuid:Int)
}

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUser(vararg user:User)

    @Query("SELECT * FROM user WHERE username = :username")
    suspend fun selectUser(username:String):User

    @Query("UPDATE user SET password= :password, name = :name, dob = :dob, joinDate = :joinDate, bio = :bio, imgUrl = :imgUrl WHERE username = :username")
    suspend fun updateUser(password:String, name:String, dob:String, joinDate:String, bio:String, imgUrl:String, username: String)
}



