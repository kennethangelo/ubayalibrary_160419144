package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.FragmentProfileBinding
import id.ac.ubaya.informatika.ubayalibrary_160419144.util.loadImage
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.ArticleViewModel
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var articleViewModel: ArticleViewModel
    private val articleAdapter =
        ArticleAdapter(arrayListOf(), { item -> articleViewModel.delete(item) })
    private lateinit var dataBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    //Set actions for observer about how to handle the emitted data
    fun observeViewModel() {
        //Any program under the Observer function will be executed if needed (i.e. activity state changed, configuration changed)
        //Students LiveData (observable) attached to this fragment (observer)
        //Everytime observer changes state -> observable emit the data
        //Used to "observe" Article data
        viewModel.profileArticleLD.observe(viewLifecycleOwner) {
            articleAdapter.updateArticleList(it)
        }

        viewModel.profileLD.observe(viewLifecycleOwner) {
            dataBinding.user = it
        }

//        viewModel.profileLoadErrorLD.observe(viewLifecycleOwner) {
//            if (it == true) {
//                txtProfileError.visibility = View.VISIBLE
//            } else {
//                txtProfileError.visibility = View.GONE
//            }
//        }
//
//        viewModel.booklistLoadErrorLD.observe(viewLifecycleOwner) {
//            if (it == true) {
//                txtProfileBooklistError.visibility = View.VISIBLE
//            } else {
//                txtProfileBooklistError.visibility = View.GONE
//            }
//        }
//
//        viewModel.loadingLD.observe(viewLifecycleOwner) {
//            if (it == true) {
//                consProfile.visibility = View.GONE
//                pbProfile.visibility = View.VISIBLE
//            } else {
//                consProfile.visibility = View.VISIBLE
//                pbProfile.visibility = View.GONE
//            }
//        }
//
//        viewModel.loadingBooklistLD.observe(viewLifecycleOwner) {
//            if (it == true) {
//                recProfileArticle.visibility = View.GONE
//                pbProfileBooklist.visibility = View.VISIBLE
//            } else {
//                recProfileArticle.visibility = View.VISIBLE
//                pbProfileBooklist.visibility = View.GONE
//            }
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.fetch("kennethangelo")

        val lm = LinearLayoutManager(context) // 2 = jumlah kolom
        recProfileArticle.layoutManager = lm
        recProfileArticle.adapter = articleAdapter

        observeViewModel()
    }
}