package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.BookListItemBinding
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Article
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Book
import id.ac.ubaya.informatika.ubayalibrary_160419144.util.loadImage
import kotlinx.android.synthetic.main.book_list_item.view.*

class BookAdapter(val bookList:ArrayList<Book>, val adapterOnClick: (Book) -> Unit): RecyclerView.Adapter<BookAdapter.BookViewHolder>(), DetailBookClick, DeleteBookClick {
    class BookViewHolder(var view: BookListItemBinding): RecyclerView.ViewHolder(view.root)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookAdapter.BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.todo_item_layout, parent,false)
        //Replace the original inflate layout with DataBindingUtil inflation method
        //Therefore the view obj now can access any variable defined in layout
        val view = DataBindingUtil.inflate<BookListItemBinding>(inflater, R.layout.book_list_item, parent, false)
        return BookViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        //Access particular to do obj based on position and set it into the variable to do in view
        holder.view.book = bookList[position]
        //Important to instanstiate the listener
        //Refer to the adapter obj => alteady implement the interface for itself
        holder.view.detailBookListener = this
        holder.view.deleteBookListener = this
    }

    override fun getItemCount() = bookList.size

    fun updateBookList(newBookList: List<Book>) {
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }

    override fun onDetailBookClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = HomeFragmentDirections.actionHomeToDetailFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }

    override fun onDeleteBookClick(obj: Book) {
        adapterOnClick(obj)
    }
}