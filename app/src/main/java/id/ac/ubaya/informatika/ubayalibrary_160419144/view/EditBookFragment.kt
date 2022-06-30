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
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.FragmentDetailBookBinding
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.FragmentEditArticleBinding
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.FragmentEditBookBinding
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Article
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Book
import id.ac.ubaya.informatika.ubayalibrary_160419144.util.LibraryWorker
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.DetailArticleViewModel
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.DetailBookViewModel
import java.util.*
import java.util.concurrent.TimeUnit

class EditBookFragment : Fragment(), EditBookClick {
    private lateinit var viewModel: DetailBookViewModel
    private lateinit var dataBinding: FragmentEditBookBinding
    var year = 0
    var month = 0
    var day = 0
    var hour = 0
    var minute = 0

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
        val uuid = EditBookFragmentArgs.fromBundle(requireArguments()).bookID
        //Fetch from LiveModel with UUID supplied as parameter
        viewModel.fetch(uuid)
        dataBinding.editBooklistener = this
        //Observe the LiveData
        observeViewModel()
    }

    override fun onEditBookClick(v: View, b: Book) {
        val c = Calendar.getInstance()
        c.set(year, month, day, hour, minute, 0)
        val today = Calendar.getInstance()
        val diff = (c.timeInMillis/1000L) - (today.timeInMillis/1000L)

        Log.d("book", b.toString())
        viewModel.update(b.title, b.imgUrl, b.desc, b.pages, b.author, b.category, b.publisher, b.uuid)
        val myWorkRequest = OneTimeWorkRequestBuilder<LibraryWorker>()
            //Notif will show 30 seconds later after work queued
            .setInitialDelay(diff, TimeUnit.SECONDS)
            //This is an InputData object that constructed from the key and value pair.
            //Left -> key, right->value
            .setInputData(
                workDataOf(
                    "title" to "Book ${b.title} Updated",
                    "message" to "A book has been updated!"
                )
            )
            .build()
        //Work request is being queued
        WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)
        Navigation.findNavController(v).popBackStack()
    }
}