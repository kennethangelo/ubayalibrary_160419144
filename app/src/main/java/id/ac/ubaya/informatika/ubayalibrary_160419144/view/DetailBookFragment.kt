package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.os.Bundle
import android.util.Log
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
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.FragmentDetailBookBinding
import id.ac.ubaya.informatika.ubayalibrary_160419144.util.loadImage
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.DetailBookViewModel
import kotlinx.android.synthetic.main.fragment_detail_article.*
import kotlinx.android.synthetic.main.fragment_detail_book.*

class DetailBookFragment : Fragment() {
    private lateinit var detailBookViewModel: DetailBookViewModel
    private lateinit var dataBinding: FragmentDetailBookBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentDetailBookBinding>(inflater, R.layout.fragment_detail_book, container, false)
        return dataBinding.root
    }

    //Set actions for observer about how to handle the emitted data
    fun observeViewModel() {
        detailBookViewModel.bookLD.observe(viewLifecycleOwner, Observer {
            Log.d("it", it.toString())
            dataBinding.book = it
        })
//        //Any program under the Observer function will be executed if needed (i.e. activity state changed, configuration changed)
//        //Students LiveData (observable) attached to this fragment (observer)
//        //Everytime observer changes state -> observable emit the data
//        //Used to "observe" Article data
//        detailBookViewModel.bookLD.observe(viewLifecycleOwner) {
//            val author = it.author
//            val book = it
//            txtDetailBookTitle.text = it.title
//            txtDetailBookAuthor.text = "by ${it.author!!.fullname}"
//            txtDetailBookCategory.text = it.category!!.name
//            txtDetailBookPublisher.text = it.publisher!!.name
//            txtDetailBookPages.text = it.pages.toString()
//            txtDetailBookDescription.text = it.desc
//            imgDetailBookTitle.loadImage(it.imgUrl, pbDetailBookImg, 900, 600)
//            txtDetailBookAuthor.setOnClickListener {
//                val action = DetailBookFragmentDirections.actionDetailBookFragmentToAuthorFragment(author!!.id.toString())
//                Navigation.findNavController(it).navigate(action)
//            }
//
//            btnDetailBookRating.setOnClickListener{
//                val action = DetailBookFragmentDirections.actionDetailBookFragmentToReviewFragment(book!!.id.toString())
//                Navigation.findNavController(it).navigate(action)
//            }
//        }
//
//        detailBookViewModel.bookLoadErrorLD.observe(viewLifecycleOwner) {
//            if (it == true) {
//                txtDetailBookError.visibility = View.VISIBLE
//            } else {
//                txtDetailBookError.visibility = View.GONE
//            }
//        }
//
//        detailBookViewModel.loadingLD.observe(viewLifecycleOwner) {
//            if (it == true) {
//                scrollDetailBook.visibility = View.GONE
//                pbDetailBook.visibility = View.VISIBLE
//            } else {
//                scrollDetailBook.visibility = View.VISIBLE
//                pbDetailBook.visibility = View.GONE
//            }
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val bookID = DetailBookFragmentArgs.fromBundle(requireArguments()).bookID
            detailBookViewModel = ViewModelProvider(this).get(DetailBookViewModel::class.java)
            detailBookViewModel.fetch(bookID)
            observeViewModel()

            fabEditBook.setOnClickListener {
                val action = DetailBookFragmentDirections.actionDetailBookFragmentToEditBookFragment(bookID)
                Navigation.findNavController(it).navigate(action)
            }
        }


    }
}