package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.FragmentAddArticleBinding
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.FragmentAddArticleBindingImpl
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Article
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Global
import id.ac.ubaya.informatika.ubayalibrary_160419144.util.LibraryWorker
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.ArticleViewModel
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.DetailArticleViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

class AddArticleFragment : Fragment(), AddArticleClick {
    private lateinit var viewModel: DetailArticleViewModel
    private lateinit var dataBinding: FragmentAddArticleBinding
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
        dataBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_article, container, false
        )
        return dataBinding.root
        // return inflater.inflate(R.layout.fragment_create_to_do, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formatted = now.format(formatter)
        dataBinding.article = Article("",formatted,"", Global.username,"")
        viewModel = ViewModelProvider(this).get(DetailArticleViewModel::class.java)
        dataBinding.addArticlelistener = this
    }

    override fun onAddArticleClick(v: View) {
        val c = Calendar.getInstance()
        c.set(year, month, day, hour, minute, 0)
        val today = Calendar.getInstance()
        val diff = (c.timeInMillis/1000L) - (today.timeInMillis/1000L)

        dataBinding.article?.let{
            val list = listOf(it)
            viewModel.addArticle(list)
//            Toast.makeText(v.context, "Data added", Toast.LENGTH_LONG).show()
            val myWorkRequest = OneTimeWorkRequestBuilder<LibraryWorker>()
                //Notif will show 30 seconds later after work queued
                .setInitialDelay(diff, TimeUnit.SECONDS)
                //This is an InputData object that constructed from the key and value pair.
                //Left -> key, right->value
                .setInputData(
                    workDataOf(
                        "title" to "Article ${it.title} Created",
                        "message" to "A new article has been created! Stay creative!"
                    )
                )
                .build()
            //Work request is being queued
            WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)
            Navigation.findNavController(v).popBackStack()
        }
    }
}