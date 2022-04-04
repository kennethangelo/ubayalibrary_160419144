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
import kotlinx.android.synthetic.main.book_list_item.view.*

class BookAdapter(val bookList:ArrayList<Book>): RecyclerView.Adapter<BookAdapter.HomeViewHolder>() {
    class HomeViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_list_item, parent, false)

        return HomeViewHolder(view)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        Log.d("book", bookList.toString())
        val book = bookList[position]
        with(holder.view){
            Log.d("book", book.title.toString())
            if(book.title.toString().length > 15)
                txtTitle.text = "${book.title.toString().substring(0,15)}..."
            else
                txtTitle.text = book.title.toString()
            txtAuthor.text = book.author!!.fullname.toString()
            btnDetail.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeToDetailFragment(book.id.toString())
                Navigation.findNavController(it).navigate(action)
            }
            imgBook.loadImage(book.imgUrl, progBarBookImg, 600, 500)
        }
    }

    override fun getItemCount() = bookList.size

    fun updateBookList(newBookList: List<Book>) {
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }
}