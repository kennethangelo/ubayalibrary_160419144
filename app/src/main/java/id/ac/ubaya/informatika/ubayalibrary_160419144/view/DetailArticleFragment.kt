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
            txtDetailArticleDate.setText(it.dob)
            imgDetailArticle.loadImage(it.imgUrl, progressImgDetailArticle, null, null)
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