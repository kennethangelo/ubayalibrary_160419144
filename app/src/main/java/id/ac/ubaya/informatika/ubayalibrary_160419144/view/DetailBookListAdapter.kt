package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Book
import id.ac.ubaya.informatika.ubayalibrary_160419144.util.loadImage
import kotlinx.android.synthetic.main.book_list_item.view.*

class DetailBookListAdapter(val bookList:ArrayList<Book>): RecyclerView.Adapter<DetailBookListAdapter.DetailBookListViewHolder>() {
    class DetailBookListViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailBookListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_list_item, parent, false)

        return DetailBookListViewHolder(view)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DetailBookListViewHolder, position: Int) {
        Log.d("book", bookList.toString())
        val book = bookList[position]
        with(holder.view){
            Log.d("book", book.title.toString())
            if(book.title.toString().length > 13)
                txtTitle.text = "${book.title.toString().substring(0,13)}..."
            else
                txtTitle.text = book.title.toString()
            txtAuthor.text = book.author!!.fullname.toString()
            btnDetail.setOnClickListener {
                val action = DetailBookListFragmentDirections.actionDetailBookListFragmentToDetailBookFragment(book.id.toString())
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