package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.FragmentDetailArticleBinding
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.FragmentEditBookBinding
import id.ac.ubaya.informatika.ubayalibrary_160419144.util.loadImage
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.DetailArticleViewModel
import kotlinx.android.synthetic.main.fragment_detail_article.*

class DetailArticleFragment : Fragment() {
    private lateinit var detailArticleViewModel: DetailArticleViewModel
    private lateinit var dataBinding: FragmentDetailArticleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentDetailArticleBinding>(inflater, R.layout.fragment_detail_article, container, false)
        return dataBinding.root
    }
    //Set actions for observer about how to handle the emitted data
    fun observeViewModel() {
        detailArticleViewModel.articleLD.observe(viewLifecycleOwner, Observer {
            dataBinding.article = it
        })
        //Any program under the Observer function will be executed if needed (i.e. activity state changed, configuration changed)
        //Students LiveData (observable) attached to this fragment (observer)
        //Everytime observer changes state -> observable emit the data
        //Used to "observe" Article data
//        detailArticleViewModel.articleLD.observe(viewLifecycleOwner) {
//            txtDetailArticleTitle.setText(it.title)
//            txtDetailArticleUsername.setText(it.username)
//            txtDetailArticleContent.setText(it.content)
//            txtDetailArticleDate.setText(it.date_added)
//            imgDetailArticle.loadImage(it.imgUrl, progressImgDetailArticle, null, null)
//        }

//        detailArticleViewModel.articleLoadErrorLD.observe(viewLifecycleOwner) {
//            if (it == true) {
//                txtDetailArticleError.visibility = View.VISIBLE
//            } else {
//                txtDetailArticleError.visibility = View.GONE
//            }
//        }
//
//        detailArticleViewModel.loadingLD.observe(viewLifecycleOwner) {
//            if (it == true) {
//                scrollDetail.visibility = View.GONE
//                pbDetailArticle.visibility = View.VISIBLE
//            } else {
//                scrollDetail.visibility = View.VISIBLE
//                pbDetailArticle.visibility = View.GONE
//            }
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val articleID = DetailArticleFragmentArgs.fromBundle(requireArguments()).idArticle
            detailArticleViewModel = ViewModelProvider(this).get(DetailArticleViewModel::class.java)
            detailArticleViewModel.fetch(articleID)
            observeViewModel()

            fabEditArticle.setOnClickListener {
                val action = DetailArticleFragmentDirections.actionDetailArticleFragmentToEditArticleFragment(articleID)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}