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

class DetailBookViewModel(application: Application): AndroidViewModel(application), CoroutineScope{
    val bookLD = MutableLiveData<Book>()
    val bookLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    //Dispatches tells you on which thread should I run this block of code (Main, IO, Default)

    fun addBook(list: List<Book>){
        launch{
            val db = buildDB(getApplication())
//            *list => convert individual element of list into its individual obj (Todo)
//            and set it as separated param
            db.bookDao().insertAllBook(*list.toTypedArray())
        }
    }

    //Load data from server/local and prepare livedata objects (ready to be observed)
    fun fetch(uuid: Int) {
        loadingLD.value = true
        bookLoadErrorLD.value = false
        launch{
            val db = buildDB(getApplication())
            bookLD.value = db.bookDao().selectBook(uuid)
            Log.d("bookLD", bookLD.value.toString())
        }
    }

    fun update(title:String, imgUrl:String, desc:String, pages:String, author:String, category:String, publisher:String, uuid:Int){
        launch{
            val db = buildDB(getApplication())
            //Save to do changes
            db.bookDao().update(title, imgUrl, desc, pages, author, category, publisher, uuid)
        }
    }
}