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
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.DetailBookViewModel
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.HomeViewModel
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.ReviewViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_review.*

class ReviewFragment : Fragment() {
    private lateinit var viewModel: ReviewViewModel
    private val reviewAdapter  = ReviewAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    //Set actions for observer about how to handle the emitted data
    fun observeViewModel() {
        //Any program under the Observer function will be executed if needed (i.e. activity state changed, configuration changed)
        //Students LiveData (observable) attached to this fragment (observer)
        //Everytime observer changes state -> observable emit the data
        //Used to "observe" Students data
        viewModel.reviewLD.observe(viewLifecycleOwner) {
            reviewAdapter.updateReviewList(it)
        }

        viewModel.reviewLoadErrorLD.observe(viewLifecycleOwner) {
            if (it == true) {
                txtErrorReview.visibility = View.VISIBLE
            } else {
                txtErrorReview.visibility = View.GONE
            }
        }

        viewModel.loadingLD.observe(viewLifecycleOwner) {
            if (it == true) {
                recViewReview.visibility = View.GONE
                pbFragmentReview.visibility = View.VISIBLE
            } else {
                recViewReview.visibility = View.VISIBLE
                pbFragmentReview.visibility = View.GONE
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val bookID = ReviewFragmentArgs.fromBundle(requireArguments()).bookID

            viewModel = ViewModelProvider(this).get(ReviewViewModel::class.java)
            viewModel.refresh(bookID)

            val lm = LinearLayoutManager(context)
            recViewReview.layoutManager = lm
            recViewReview.adapter = reviewAdapter

            observeViewModel()
        }
    }
}