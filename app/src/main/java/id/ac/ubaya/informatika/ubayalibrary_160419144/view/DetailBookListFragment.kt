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
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.DetailBookListViewModel
import kotlinx.android.synthetic.main.fragment_author.*
import kotlinx.android.synthetic.main.fragment_detail_book_list.*

class DetailBookListFragment : Fragment() {
    private lateinit var viewModel: DetailBookListViewModel
    private val bookAdapter  = DetailBookListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_book_list, container, false)
    }
    //Set actions for observer about how to handle the emitted data
    //Set actions for observer about how to handle the emitted data
    fun observeViewModel(type: String?) {
        //Any program under the Observer function will be executed if needed (i.e. activity state changed, configuration changed)
        //Students LiveData (observable) attached to this fragment (observer)
        //Everytime observer changes state -> observable emit the data
        //Used to "observe" Article data
        viewModel.booklistLD.observe(viewLifecycleOwner) {
            bookAdapter.updateBookList(it)
        }

        if(type.equals("Category")){
            viewModel.categoryLD.observe(viewLifecycleOwner) {
                txtDetailListName.text = it.name
                txtDetailListDesc.text = it.desc
                imgDetailList.loadImage("https://static.vecteezy.com/system/resources/previews/004/493/331/large_2x/brown-and-black-gradient-background-free-photo.jpg", pbDetailListBG, null, null)
            }

        } else if(type.equals("Booklist")){
            viewModel.booklistInfoLD.observe(viewLifecycleOwner) {
                txtDetailListName.text = it.name
                txtDetailListDesc.text = it.desc
                imgDetailList.loadImage("https://static.vecteezy.com/system/resources/previews/004/493/331/large_2x/brown-and-black-gradient-background-free-photo.jpg", pbDetailListBG, null, null)
            }
        }

        viewModel.loadErrorLD.observe(viewLifecycleOwner) {
            if (it == true) {
                txtDetailBookListError.visibility = View.VISIBLE
            } else {
                txtDetailBookListError.visibility = View.GONE
            }
        }

        viewModel.loadingLD.observe(viewLifecycleOwner) {
            if (it == true) {
                consDetailList.visibility = View.GONE
                pbDetailBookList.visibility = View.VISIBLE
            } else {
                consDetailList.visibility = View.VISIBLE
                pbDetailBookList.visibility = View.GONE
            }
        }

        viewModel.booklistLoadingLD.observe(viewLifecycleOwner) {
            if (it == true) {
                recViewDetailList.visibility = View.GONE
                pbDetailListRecView.visibility = View.VISIBLE
            } else {
                recViewDetailList.visibility = View.VISIBLE
                pbDetailListRecView.visibility = View.GONE
            }
        }

        viewModel.loadErrorLD.observe(viewLifecycleOwner) {
            if (it == true) {
                txtDetailBookListError.visibility = View.VISIBLE
            } else {
                txtDetailBookListError.visibility = View.GONE
            }
        }

        viewModel.booklistLoadErrorLD.observe(viewLifecycleOwner) {
            if (it == true) {
                txtDetailListRecError.visibility = View.VISIBLE
            } else {
                txtDetailListRecError.visibility = View.GONE
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val id = DetailBookListFragmentArgs.fromBundle(requireArguments()).id
            val type = DetailBookListFragmentArgs.fromBundle(requireArguments()).type
            viewModel = ViewModelProvider(this).get(DetailBookListViewModel::class.java)
            viewModel.fetch(type, id)

            val gm = GridLayoutManager(activity,2) // 2 = jumlah kolom
            recViewDetailList.layoutManager = gm
            recViewDetailList.adapter = bookAdapter

            observeViewModel(type)
        }
    }
}