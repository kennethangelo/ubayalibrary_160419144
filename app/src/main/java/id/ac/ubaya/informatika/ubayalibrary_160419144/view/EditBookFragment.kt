package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.FragmentDetailBookBinding
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.FragmentEditArticleBinding
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.FragmentEditBookBinding
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Article
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Book
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.DetailArticleViewModel
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.DetailBookViewModel

class EditBookFragment : Fragment(), EditBookClick {
    private lateinit var viewModel: DetailBookViewModel
    private lateinit var dataBinding: FragmentEditBookBinding
    var globalUUID: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentEditBookBinding>(inflater, R.layout.fragment_edit_book, container, false)
        return dataBinding.root
    }

    fun observeViewModel(){
        viewModel.bookLD.observe(viewLifecycleOwner, Observer{
            dataBinding.book = it
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailBookViewModel::class.java)
        val globalUUID = EditBookFragmentArgs.fromBundle(requireArguments()).bookID
        //Fetch from LiveModel with UUID supplied as parameter
        viewModel.fetch(globalUUID)
        dataBinding.editBooklistener = this
        //Observe the LiveData
        observeViewModel()
    }

    override fun onEditBookClick(v: View, b: Book) {
        Log.d("book", b.toString())
        viewModel.update(b.title, b.imgUrl, b.desc, b.pages, b.author, b.category, b.publisher, globalUUID)
        Toast.makeText(v.context, "Book updated", Toast.LENGTH_SHORT).show()
        val action = EditBookFragmentDirections.actionEditBookFragmentToItemHome()
        Navigation.findNavController(v).navigate(action)

    }
}