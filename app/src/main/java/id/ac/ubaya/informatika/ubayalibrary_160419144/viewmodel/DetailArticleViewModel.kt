package id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Article
import id.ac.ubaya.informatika.ubayalibrary_160419144.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailArticleViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val articleLD = MutableLiveData<Article>()
    //Live data that "emit" error status (boolean)
    val articleLoadErrorLD = MutableLiveData<Boolean>()
    //Live data that "emit" data loading status (boolean)
    //MutableLiveData -> type of updateable LiveData
    val loadingLD = MutableLiveData<Boolean>()

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun addArticle(list: List<Article>){
        launch{
            val db = buildDB(getApplication())
//            *list => convert individual element of list into its individual obj (Todo)
//            and set it as separated param
            db.articleDao().insertArticle(*list.toTypedArray())
        }
    }

    fun fetch(uuid: Int) {
        articleLoadErrorLD.value = false
        loadingLD.value = true
        launch{
            val db = buildDB(getApplication())
            articleLD.value = db.articleDao().selectArticle(uuid)
        }
    }

    fun update(title:String, content:String, imgUrl:String, uuid:Int){
        launch{
            val db = buildDB(getApplication())
            //Save to do changes
            db.articleDao().updateArticle(title, content, imgUrl, uuid)
        }
    }
}