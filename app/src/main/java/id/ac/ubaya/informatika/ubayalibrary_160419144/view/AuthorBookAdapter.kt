package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Book
import id.ac.ubaya.informatika.ubayalibrary_160419144.util.loadImage
import kotlinx.android.synthetic.main.author_book_item.view.*
import kotlinx.android.synthetic.main.book_list_item.view.*

class AuthorBookAdapter(val bookList:ArrayList<Book>): RecyclerView.Adapter<AuthorBookAdapter.AuthorBookViewHolder>() {
    class AuthorBookViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorBookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.author_book_item, parent, false)

        return AuthorBookViewHolder(view)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AuthorBookViewHolder, position: Int) {
        Log.d("book", bookList.toString())
        val book = bookList[position]
        with(holder.view){
            Log.d("book", book.title.toString())
            if(book.title.toString().length > 13)
                txtAuthorBookTitle.text = "${book.title.toString().substring(0,13)}..."
            else
                txtAuthorBookTitle.text = book.title.toString()
            txtAuthorBookFullname.text = book.author!!.fullname.toString()
            btnAuthorDetail.setOnClickListener {
                val action = AuthorFragmentDirections.actionAuthorFragmentToDetailBookFragment(book.id.toString())
                Navigation.findNavController(it).navigate(action)
            }
            imgAuthorBook.loadImage(book.imgUrl, progBarAuthorBookImg, 600, 500)
        }
    }

    override fun getItemCount() = bookList.size

    fun updateBookList(newBookList: List<Book>) {
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }
}