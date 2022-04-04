package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.util.loadImage
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.BookListViewModel
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.DetailArticleViewModel
import kotlinx.android.synthetic.main.fragment_detail_article.*
import kotlinx.android.synthetic.main.fragment_profile.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import java.util.concurrent.TimeUnit

class DetailArticleFragment : Fragment() {
    private lateinit var detailArticleViewModel: DetailArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_article, container, false)
    }
    //Set actions for observer about how to handle the emitted data
    fun observeViewModel() {
        //Any program under the Observer function will be executed if needed (i.e. activity state changed, configuration changed)
        //Students LiveData (observable) attached to this fragment (observer)
        //Everytime observer changes state -> observable emit the data
        //Used to "observe" Article data
        detailArticleViewModel.articleLD.observe(viewLifecycleOwner) {
            txtDetailArticleTitle.setText(it.title)
            txtDetailArticleUsername.setText(it.username)
            txtDetailArticleContent.setText(it.content)
            txtDetailArticleDate.setText(it.date_added)
            imgDetailArticle.loadImage(it.imgUrl, progressImgDetailArticle, null, null)
        }

        detailArticleViewModel.articleLoadErrorLD.observe(viewLifecycleOwner) {
            if (it == true) {
                txtDetailArticleError.visibility = View.VISIBLE
            } else {
                txtDetailArticleError.visibility = View.GONE
            }
        }

        detailArticleViewModel.loadingLD.observe(viewLifecycleOwner) {
            if (it == true) {
                scrollDetail.visibility = View.GONE
                pbDetailArticle.visibility = View.VISIBLE
            } else {
                scrollDetail.visibility = View.VISIBLE
                pbDetailArticle.visibility = View.GONE
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val articleID = DetailArticleFragmentArgs.fromBundle(requireArguments()).idArticle
            detailArticleViewModel = ViewModelProvider(this).get(DetailArticleViewModel::class.java)
            detailArticleViewModel.fetch(articleID)
            observeViewModel()
        }
    }
}