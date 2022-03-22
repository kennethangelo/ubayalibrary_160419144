package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Book

class HomeAdapter(val bookList:ArrayList<Book>): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    class HomeViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_list_item, parent, false)

        return HomeViewHolder(view)

    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val book = bookList[position]
        with(holder.view){
//            txtID.text = student.id
//            txtName.text = student.name
//            btnDetail.setOnClickListener {
//                val action = StudentListFragmentDirections.actionStudentDetail(student.id.toString())
//                Navigation.findNavController(it).navigate(action)
//            }
//            imageView.loadImage(student.photoUrl, progressBar)
        }
    }

    override fun getItemCount() = bookList.size

    fun updateBookList(newBookList: List<Book>) {
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }
}