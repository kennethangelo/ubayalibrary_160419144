package id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel

import android.app.Application
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Article
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Book
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Global.isLogin
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.User
import id.ac.ubaya.informatika.ubayalibrary_160419144.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    //Live data that emit profile data
    val profileLD = MutableLiveData<User>()
    val profileArticleLD = MutableLiveData<List<Article>>()

    val profileLoadErrorLD = MutableLiveData<Boolean>()
    val articleLoadErrorLD = MutableLiveData<Boolean>()

    val loadingLD = MutableLiveData<Boolean>()
    val loadingArticleLD = MutableLiveData<Boolean>()

    val resultLD = MutableLiveData<User>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    //Dispatches tells you on which thread should I run this block of code (Main, IO, Default)

    //Load data from server/local and prepare livedata objects (ready to be observed)
    fun fetch(username: String) {
        profileLoadErrorLD.value = false
        loadingLD.value = true

        articleLoadErrorLD.value = false
        loadingArticleLD.value = true
        launch {
            val db = buildDB(getApplication())
            profileLD.value = db.userDao().selectUser(username)
        }
    }

    fun checkLogin(username: String, password: String) {
        launch {
            val db = buildDB(getApplication())
            resultLD.value = db.userDao().checkLogin(username, password)
            isLogin = resultLD.value != null
        }
    }

    fun register(list: List<User>){
        launch{
            val db = buildDB(getApplication())
//            *list => convert individual element of list into its individual obj (Todo)
//            and set it as separated param
            db.userDao().insertAllUser(*list.toTypedArray())
        }
    }
}