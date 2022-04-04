package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Article
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Book
import id.ac.ubaya.informatika.ubayalibrary_160419144.util.loadImage
import kotlinx.android.synthetic.main.article_list_item.view.*
import kotlinx.android.synthetic.main.book_list_item.view.*
import kotlinx.android.synthetic.main.booklist_list_item.view.*

class ArticleAdapter(val articleList:ArrayList<Article>): RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    class ArticleViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.article_list_item, parent, false)

        return ArticleViewHolder(view)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articleList[position]
        with(holder.view){
            txtArticleTitle.text = article.title.toString()
            if(article.content.toString().length > 60)
                txtArticleBriefDesc.text = "${article.content.toString().substring(0,57)}..."
            else
                txtArticleBriefDesc.text = article.content.toString()
            btnArticleDetail.setOnClickListener {
                val action = ArticleFragmentDirections.actionArticleToDetailArticleFragment(article.id.toString())
                Navigation.findNavController(it).navigate(action)
            }
            imgArticle.loadImage(article.imgUrl, progBarArticleImg, null, null)
        }
    }

    override fun getItemCount() = articleList.size

    fun updateArticleList(newArticleList: List<Article>) {
        articleList.clear()
        articleList.addAll(newArticleList)
        notifyDataSetChanged()
    }
}