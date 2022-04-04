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

class DetailArticleViewModel(application: Application): AndroidViewModel(application) {
    val articleLD = MutableLiveData<Article>()
    //Live data that "emit" error status (boolean)
    val articleLoadErrorLD = MutableLiveData<Boolean>()
    //Live data that "emit" data loading status (boolean)
    //MutableLiveData -> type of updateable LiveData
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue?= null


    fun fetch(articleID: String) {
        val id = articleID

        articleLoadErrorLD.value = false
        loadingLD.value = true

        //Initialize volley
        queue = Volley.newRequestQueue(getApplication());
        val url = "http://10.0.2.2/ubayalibrary/article.php?id=$id"
//        val url = "http://192.168.0.8/ubayalibrary/article.php?id=$id"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                Log.d("it", it.toString())
                //TypeToken -> retrieve the obj type of student
                val sType = object: TypeToken<Article>() {}.type
                //fromJson -> convert JSON string to list of student
                val result = Gson().fromJson<Article>(it, sType)
                //Update the student LD which is being observed by Student List Fragment
                articleLD.value = result
                loadingLD.value = false
                Log.d("showvolley", it)
            },
            {
                articleLoadErrorLD.value = true
                loadingLD.value = true
                Log.d("showvolley", it.toString())
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