package id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Article
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Book
import id.ac.ubaya.informatika.ubayalibrary_160419144.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ArticleViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    //Live data that "emit" articles data (in list form)
    val articlesLD = MutableLiveData<List<Article>>()
    //Live data that "emit" error status (boolean)
    val articleLoadErrorLD = MutableLiveData<Boolean>()
    //Live data that "emit" data loading status (boolean)
    //MutableLiveData -> type of updateable LiveData
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    //Dispatches tells you on which thread should I run this block of code (Main, IO, Default)

    //Load data from server/local and prepare livedata objects (ready to be observed)
    fun refresh() {
        loadingLD.value = true
        articleLoadErrorLD.value = false
        launch{
            val db = buildDB(getApplication())
            articlesLD.value = db.articleDao().selectAllArticle()
        }
    }

    fun delete(article: Article){
        launch{
            val db = buildDB(getApplication())
            //Save to do changes
            db.articleDao().deleteArticle(article)
            articlesLD.value = db.articleDao().selectAllArticle()
        }
    }
}