package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.FragmentEditArticleBinding
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.FragmentEditBookBinding
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Article
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Book
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.DetailArticleViewModel
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.DetailBookViewModel

class EditBookFragment : Fragment(), EditBookClick {
    private lateinit var viewModel: DetailBookViewModel
    private lateinit var dataBinding: FragmentEditBookBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_book, container, false)
    }

    fun observeViewModel(){
        viewModel.bookLD.observe(viewLifecycleOwner, Observer{
            dataBinding.book = it
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailBookViewModel::class.java)
        val uuid = EditBookFragmentArgs.fromBundle(requireArguments()).bookID
        //Fetch from LiveModel with UUID supplied as parameter
        viewModel.fetch(uuid)
        //Observe the LiveData
        observeViewModel()
    }

    override fun onEditBookClick(v: View, b: Book) {
        viewModel.update(b.title, b.imgUrl, b.desc, b.pages, b.author, b.category, b.publisher, b.uuid)
        Toast.makeText(v.context, "Todo updated", Toast.LENGTH_SHORT).show()
    }
}