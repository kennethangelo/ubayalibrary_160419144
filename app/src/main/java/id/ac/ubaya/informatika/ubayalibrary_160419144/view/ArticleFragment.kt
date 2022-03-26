package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.fragment_article.*
import kotlinx.android.synthetic.main.fragment_home.*

class ArticleFragment : Fragment() {
    private lateinit var viewModel: ArticleViewModel
    private val articleAdapter  = ArticleAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    //Set actions for observer about how to handle the emitted data
    fun observeViewModel() {
        //Any program under the Observer function will be executed if needed (i.e. activity state changed, configuration changed)
        //Students LiveData (observable) attached to this fragment (observer)
        //Everytime observer changes state -> observable emit the data
        //Used to "observe" Articles data
        viewModel.articlesLD.observe(viewLifecycleOwner) {
            articleAdapter.updateArticleList(it)
        }

        viewModel.articleLoadErrorLD.observe(viewLifecycleOwner) {
            if (it == true) {
                txtArticleError.visibility = View.VISIBLE
            } else {
                txtArticleError.visibility = View.GONE
            }
        }

        viewModel.loadingLD.observe(viewLifecycleOwner) {
            if (it == true) {
                recViewArticle.visibility = View.GONE
                progressArticle.visibility = View.VISIBLE
            } else {
                recViewArticle.visibility = View.VISIBLE
                progressArticle.visibility = View.GONE
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        viewModel.refresh()

        recViewArticle.layoutManager = LinearLayoutManager(context)
        recViewArticle.adapter = articleAdapter

        observeViewModel()

        //Triggered when user do vertical swipe on the layout
        refreshArticleLayout.setOnRefreshListener {
            recViewArticle.visibility = View.GONE
            txtArticleError.visibility = View.GONE
            progressArticle.visibility = View.VISIBLE
            //Loads up the viewmodel to retrieve latest data from endpoint API
            viewModel.refresh()
            //Hide the loading progress icon
            refreshArticleLayout.isRefreshing = false
        }
    }
}