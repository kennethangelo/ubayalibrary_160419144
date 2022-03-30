package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.util.loadImage
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.AuthorViewModel
import kotlinx.android.synthetic.main.fragment_author.*

class AuthorFragment : Fragment() {
    private lateinit var authorViewModel: AuthorViewModel
    private val bookAdapter  = AuthorBookAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_author, container, false)
    }
    //Set actions for observer about how to handle the emitted data
    fun observeViewModel() {
        //Any program under the Observer function will be executed if needed (i.e. activity state changed, configuration changed)
        //Students LiveData (observable) attached to this fragment (observer)
        //Everytime observer changes state -> observable emit the data
        //Used to "observe" Article data
        authorViewModel.bookAuthorLD.observe(viewLifecycleOwner) {
            bookAdapter.updateBookList(it)
        }

        authorViewModel.authorLD.observe(viewLifecycleOwner) {
            txtAuthorName.text = it.fullname
            txtAuthorBio.text = it.bio
            txtAuthorDOB.text = it.dob
            imgAuthor.loadImage(it.imgUrl, pbAuthorImage, 300, 300)
        }

        authorViewModel.authorLoadErrorLD.observe(viewLifecycleOwner) {
            if (it == true) {
                txtAuthorError.visibility = View.VISIBLE
            } else {
                txtAuthorError.visibility = View.GONE
            }
        }

        authorViewModel.bookLoadErrorLD.observe(viewLifecycleOwner) {
            if (it == true) {
                txtAuthorBookError.visibility = View.VISIBLE
            } else {
                txtAuthorBookError.visibility = View.GONE
            }
        }

        authorViewModel.loadingLD.observe(viewLifecycleOwner) {
            if (it == true) {
                consLayoutAuthor.visibility = View.GONE
                pbAuthor.visibility = View.VISIBLE
            } else {
                consLayoutAuthor.visibility = View.VISIBLE
                pbAuthor.visibility = View.GONE
            }
        }

        authorViewModel.loadingBookLD.observe(viewLifecycleOwner) {
            if (it == true) {
                recViewAuthorBooks.visibility = View.GONE
                pbAuthorBook.visibility = View.VISIBLE
            } else {
                recViewAuthorBooks.visibility = View.VISIBLE
                pbAuthorBook.visibility = View.GONE
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val authorID = AuthorFragmentArgs.fromBundle(requireArguments()).authorID
            authorViewModel = ViewModelProvider(this).get(AuthorViewModel::class.java)
            authorViewModel.refresh(authorID)

            val gm = GridLayoutManager(activity,2) // 2 = jumlah kolom
            recViewAuthorBooks.layoutManager = gm
            recViewAuthorBooks.adapter = bookAdapter

            observeViewModel()
        }


    }
}