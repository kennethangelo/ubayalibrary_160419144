package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Article

class ProfileArticleAdapter(val articleList:ArrayList<Article>): RecyclerView.Adapter<ProfileArticleAdapter.ProfileBookListViewHolder>() {
    class ProfileBookListViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileBookListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.article_list_item, parent, false)

        return ProfileBookListViewHolder(view)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProfileBookListViewHolder, position: Int) {
//        val booklist = booklistList[position]
//        with(holder.view){
//            txtBooklistTitle.text = booklist.name
//            txtMadeByUsername.text = booklist.username
//            btnBookListDetails.setOnClickListener {
//                val action =
//                    ProfileFragmentDirections.actionProfileFragmentToDetailBookListFragment("Booklist", booklist.id.toString())
//                Navigation.findNavController(it).navigate(action)
//            }
//        }
    }

    override fun getItemCount() = articleList.size

    fun updateProfileBooklistList(newBooklistList: List<Article>) {
        articleList.clear()
        articleList.addAll(newBooklistList)
        notifyDataSetChanged()
    }
}