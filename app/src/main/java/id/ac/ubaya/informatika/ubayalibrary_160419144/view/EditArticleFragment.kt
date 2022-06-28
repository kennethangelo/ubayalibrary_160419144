package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.os.Bundle
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
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.FragmentEditArticleBinding
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.FragmentEditBookBinding
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Article
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.DetailArticleViewModel

class EditArticleFragment : Fragment(), EditArticleClick {
    private lateinit var viewModel: DetailArticleViewModel
    private lateinit var dataBinding: FragmentEditArticleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentEditArticleBinding>(inflater, R.layout.fragment_edit_article, container, false)
        return dataBinding.root
    }

    fun observeViewModel(){
        viewModel.articleLD.observe(viewLifecycleOwner, Observer{
            dataBinding.article = it
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailArticleViewModel::class.java)
        val uuid = EditArticleFragmentArgs.fromBundle(requireArguments()).articleID
        //Fetch from LiveModel with UUID supplied as parameter
        viewModel.fetch(uuid)
        dataBinding.editArticlelistener = this
        //Observe the LiveData
        observeViewModel()
    }

    override fun onEditArticleClick(v: View, a: Article) {
        viewModel.update(a.title, a.content, a.imgUrl, a.uuid)
        Toast.makeText(v.context, "Todo updated", Toast.LENGTH_SHORT).show()
        val action = EditArticleFragmentDirections.actionEditArticleFragmentToItemArticle()
        Navigation.findNavController(v).navigate(action)
    }
}