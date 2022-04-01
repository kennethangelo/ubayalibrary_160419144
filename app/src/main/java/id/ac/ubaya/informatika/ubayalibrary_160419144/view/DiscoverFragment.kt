package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.BookListViewModel
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.fragment_discover.*

class DiscoverFragment : Fragment() {
    private lateinit var bookListViewModel: BookListViewModel
    private lateinit var categoryViewModel: CategoryViewModel
    private val bookListAdapter  = DiscoverBookListAdapter(arrayListOf())
    private val categoryAdapter  = DiscoverCategoryAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    //Set actions for observer about how to handle the emitted data
    fun observeViewModel() {
        //Any program under the Observer function will be executed if needed (i.e. activity state changed, configuration changed)
        //Students LiveData (observable) attached to this fragment (observer)
        //Everytime observer changes state -> observable emit the data
        //Used to "observe" Articles data
        bookListViewModel.bookListLD.observe(viewLifecycleOwner) {
            bookListAdapter.updateBooklistList(it)
        }

        categoryViewModel.categoriesLD.observe(viewLifecycleOwner) {
            categoryAdapter.updateCategoryList(it)
        }

        bookListViewModel.booklistLoadErrorLD.observe(viewLifecycleOwner) {
            if (it == true) {
                txtBooklistError.visibility = View.VISIBLE
            } else {
                txtBooklistError.visibility = View.GONE
            }
        }

        categoryViewModel.categoryLoadErrorLD.observe(viewLifecycleOwner) {
            if (it == true) {
                txtCategoryError.visibility = View.VISIBLE
            } else {
                txtCategoryError.visibility = View.GONE
            }
        }

        bookListViewModel.loadingLD.observe(viewLifecycleOwner) {
            if (it == true) {
                recViewBooklists.visibility = View.GONE
                progressBookList.visibility = View.VISIBLE
            } else {
                recViewBooklists.visibility = View.VISIBLE
                progressBookList.visibility = View.GONE
            }
        }

        categoryViewModel.loadingLD.observe(viewLifecycleOwner) {
            if (it == true) {
                recViewCategories.visibility = View.GONE
                progressCategory.visibility = View.VISIBLE
            } else {
                recViewCategories.visibility = View.VISIBLE
                progressCategory.visibility = View.GONE
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookListViewModel = ViewModelProvider(this).get(BookListViewModel::class.java)
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        bookListViewModel.refresh()
        categoryViewModel.refresh()

        recViewCategories.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recViewCategories.adapter = categoryAdapter

        recViewBooklists.layoutManager = LinearLayoutManager(context)
        recViewBooklists.adapter = bookListAdapter

        observeViewModel()

        //Triggered when user do vertical swipe on the layout
        refreshSearchLayout.setOnRefreshListener {
            recViewCategories.visibility = View.GONE
            recViewBooklists.visibility = View.GONE

            txtCategoryError.visibility = View.GONE
            txtBooklistError.visibility = View.GONE

            progressCategory.visibility = View.VISIBLE
            progressBookList.visibility = View.VISIBLE

            //Loads up the viewmodel to retrieve latest data from endpoint API
            bookListViewModel.refresh()
            categoryViewModel.refresh()

            //Hide the loading progress icon
            refreshSearchLayout.isRefreshing = false
        }
    }
}