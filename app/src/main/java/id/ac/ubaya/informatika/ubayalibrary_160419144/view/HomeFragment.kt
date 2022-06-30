package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Global.isLogin
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private val homeAdapter = BookAdapter(arrayListOf(), { item -> viewModel.deleteBook(item) })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    //Set actions for observer about how to handle the emitted data
    fun observeViewModel() {
        //Any program under the Observer function will be executed if needed (i.e. activity state changed, configuration changed)
        //Students LiveData (observable) attached to this fragment (observer)
        //Everytime observer changes state -> observable emit the data
        //Used to "observe" Students data
        viewModel.booksLD.observe(viewLifecycleOwner) {
            homeAdapter.updateBookList(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(isLogin == false){
            val action = HomeFragmentDirections.actionItemHomeToLoginFragment()
            Navigation.findNavController(view).navigate(action)
        } else {
            viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
            viewModel.fetch()

            val gm = GridLayoutManager(activity, 2) // 2 = jumlah kolom
            recView.layoutManager = gm
            recView.adapter = homeAdapter

            fabAddBook.setOnClickListener {
                val action = HomeFragmentDirections.actionItemHomeToAddBookFragment()
                Navigation.findNavController(it).navigate(action)
            }

            observeViewModel()
        }


    }
}