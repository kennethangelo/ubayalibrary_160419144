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
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.ArticleListItemBinding
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Article
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Book
import id.ac.ubaya.informatika.ubayalibrary_160419144.util.loadImage
import kotlinx.android.synthetic.main.article_list_item.view.*

class ArticleAdapter(val articleList:ArrayList<Article>, val adapterOnClick: (Article) -> Unit): RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>(), DetailArticleClick, DeleteArticleClick {
    class ArticleViewHolder(var view: ArticleListItemBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapter.ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //Replace the original inflate layout with DataBindingUtil inflation method
        //Therefore the view obj now can access any variable defined in layout
        val view = DataBindingUtil.inflate<ArticleListItemBinding>(inflater, R.layout.article_list_item, parent, false)
        return ArticleViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.view.article = articleList[position]
        holder.view.articleDetailListener = this
        holder.view.deleteArticleListener = this
    }

    override fun onArticleDetailClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = ArticleFragmentDirections.actionArticleToDetailArticleFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }

    fun updateArticleList(newArticleList: List<Article>) {
        articleList.clear()
        articleList.addAll(newArticleList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = articleList.size

    override fun onDeleteArticleClick(obj: Article) {
        adapterOnClick(obj)
    }
}