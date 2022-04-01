package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Booklist
import kotlinx.android.synthetic.main.booklist_list_item.view.*

class DiscoverBookListAdapter(val booklistList:ArrayList<Booklist>): RecyclerView.Adapter<DiscoverBookListAdapter.BookListViewHolder>() {
    class BookListViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.booklist_list_item, parent, false)

        return BookListViewHolder(view)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        val booklist = booklistList[position]
        with(holder.view){
            txtBooklistTitle.text = booklist.name
            txtMadeByUsername.text = booklist.username
            btnBookListDetails.setOnClickListener {
                val action =
                    DiscoverFragmentDirections.actionDiscoverToDetailBookListFragment("Booklist", booklist.id.toString())
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount() = booklistList.size

    fun updateBooklistList(newBooklistList: List<Booklist>) {
        booklistList.clear()
        booklistList.addAll(newBooklistList)
        notifyDataSetChanged()
    }
}