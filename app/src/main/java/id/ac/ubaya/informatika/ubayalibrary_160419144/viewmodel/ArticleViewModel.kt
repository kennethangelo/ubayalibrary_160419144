package id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Article
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Book

class ArticleViewModel(application: Application): AndroidViewModel(application) {
    //Live data that "emit" articles data (in list form)
    val articlesLD = MutableLiveData<List<Article>>()
    //Live data that "emit" error status (boolean)
    val articleLoadErrorLD = MutableLiveData<Boolean>()
    //Live data that "emit" data loading status (boolean)
    //MutableLiveData -> type of updateable LiveData
    val loadingLD = MutableLiveData<Boolean>()

    //TAG variable useful on volley request cancellation & delete inside refresh method
    val TAG = "volleyTag"
    private var queue: RequestQueue?= null

    //Load data from server/local and prepare livedata objects (ready to be observed)
    fun refresh() {
        articleLoadErrorLD.value = false
        loadingLD.value = true

        //Initialize volley
        queue = Volley.newRequestQueue(getApplication());
        val url = "http://10.0.2.2/ubayalibrary/articles.php";

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                //TypeToken -> retrieve the obj type of list of article
                val sType = object: TypeToken<List<Article>>() {}.type
                //fromJson -> convert JSON string to list of article
                val result = Gson().fromJson<List<Article>>(it, sType)
                //Update the student LD which is being observed by Article List Fragment
                articlesLD.value = result
                loadingLD.value = false
                Log.d("showvolley", result.toString())
            },
            {
                Log.d("showerror", it.toString())
                articleLoadErrorLD.value = true
                loadingLD.value = true
            }
        )

        //Start the volley queue request and set the TAG
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    //To clean up code (volley resource) to prevent memory leak problems.
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}