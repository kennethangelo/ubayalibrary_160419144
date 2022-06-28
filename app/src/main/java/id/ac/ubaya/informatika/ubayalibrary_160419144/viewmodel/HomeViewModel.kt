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

class HomeViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    //Live data that "emit" books data (in list form)
    val booksLD = MutableLiveData<List<Book>>()
//    val authorLD = MutableLiveData<List<Author>>()
    //Live data that "emit" error status (boolean)
    val bookLoadErrorLD = MutableLiveData<Boolean>()
    //Live data that "emit" data loading status (boolean)
    //MutableLiveData -> type of updateable LiveData
    val loadingLD = MutableLiveData<Boolean>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    //Dispatches tells you on which thread should I run this block of code (Main, IO, Default)

    //Load data from server/local and prepare livedata objects (ready to be observed)
    fun fetch() {
        loadingLD.value = true
        bookLoadErrorLD.value = true
        launch{
            val db = buildDB(getApplication())
            booksLD.value = db.bookDao().selectAllBooks()
            loadingLD.value = false
            bookLoadErrorLD.value = false
        }
    }

    fun deleteBook(book: Book){
        launch{
            val db = buildDB(getApplication())
            //Save to do changes
            db.bookDao().deleteBook(book)
            booksLD.value = db.bookDao().selectAllBooks()
        }
    }
}