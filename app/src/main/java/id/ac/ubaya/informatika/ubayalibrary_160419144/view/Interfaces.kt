package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.view.View
import android.widget.CompoundButton
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Article
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Book

interface ButtonCreateNotificationClickListener{
    fun onButtonCreateNotificationClick(v: View, a:Article)
}

interface DetailArticleClick{
    fun onArticleDetailClick(v: View)
}

interface DetailBookClick{
    fun onDetailBookClick(v: View)
}

interface AddArticleClick{
    fun onAddArticleClick(v: View)
}

interface EditArticleClick{
    fun onEditArticleClick(v: View, a: Article)
}

interface AddBookClick{
    fun onAddBookClick(v: View)
}

interface EditBookClick{
    fun onEditBookClick(v: View, b: Book)
}

interface DeleteArticleClick{
    fun onDeleteArticleClick(obj: Article)
}

interface DeleteBookClick{
    fun onDeleteBookClick(obj: Book)
}

