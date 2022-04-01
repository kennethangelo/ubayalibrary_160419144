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
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Book
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Booklist
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Category

class DetailBookListViewModel (application: Application): AndroidViewModel(application) {
    val categoryLD = MutableLiveData<Category>()
    val booklistInfoLD = MutableLiveData<Booklist>()
    val loadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val booklistLD = MutableLiveData<List<Book>>()
    val booklistLoadErrorLD = MutableLiveData<Boolean>()    //Live data that "emit" data loading status (boolean)
    val booklistLoadingLD = MutableLiveData<Boolean>()



    //TAG variable useful on volley request cancellation & delete inside refresh method
    val TAG = "volleyTag"
    private var queue: RequestQueue?= null

    //Load data from server/local and prepare livedata objects (ready to be observed)
    fun fetch(type: String?, id:String?) {
        booklistLoadErrorLD.value = false
        booklistLoadingLD.value = true

        loadErrorLD.value = false
        loadingLD.value = true

        //Initialize volley
        queue = Volley.newRequestQueue(getApplication())

        var url: String
        var url2 = ""
        var stringRequest: StringRequest? = null

        if(type.equals("Category")){
             url = "http://10.0.2.2/ubayalibrary/category.php?categoryID=$id"
//          url = "http://192.168.0.8/ubayalibrary/category.php?categoryID=id"

            stringRequest = StringRequest(
                Request.Method.GET, url,
                {
                    //TypeToken -> retrieve the obj type of list of booklist
                    val sType = object: TypeToken<Category>() {}.type
                    //fromJson -> convert JSON string to list of booklist
                    val result = Gson().fromJson<Category>(it, sType)
                    //Update the student LD which is being observed by Booklist List Fragment
                    categoryLD.value = result
                    loadingLD.value = false
                    Log.d("showvolley", result.toString())
                },
                {
                    Log.d("showerror", it.toString())
                    loadErrorLD.value = true
                    loadingLD.value = true
                }
            )
            url2 = "http://10.0.2.2/ubayalibrary/book.php?categoryID=$id"
//          url = "http://192.168.0.8/ubayalibrary/book.php?categoryID=id"


        } else if (type.equals("Booklist")){
            url = "http://10.0.2.2/ubayalibrary/booklist.php?booklistID=$id"
//        url = "http://192.168.0.8/ubayalibrary/booklist.php?booklistID=id"

            stringRequest = StringRequest(
                Request.Method.GET, url,
                {
                    //TypeToken -> retrieve the obj type of list of booklist
                    val sType = object: TypeToken<Booklist>() {}.type
                    //fromJson -> convert JSON string to list of booklist
                    val result = Gson().fromJson<Booklist>(it, sType)
                    //Update the student LD which is being observed by Booklist List Fragment
                    booklistInfoLD.value = result
                    loadingLD.value = false
                    Log.d("showvolley", result.toString())
                },
                {
                    Log.d("showerror", it.toString())
                    loadErrorLD.value = true
                    loadingLD.value = true
                }
            )

            url2 = "http://10.0.2.2/ubayalibrary/detailbooklist.php?booklistID=$id"
//          url = "http://192.168.0.8/ubayalibrary/detailbooklist.php?booklistID=$id"
        }


        val stringRequest2 = StringRequest(
            Request.Method.GET, url2,
            {
                //TypeToken -> retrieve the obj type of list of booklist
                val sType = object: TypeToken<List<Book>>() {}.type
                //fromJson -> convert JSON string to list of booklist
                val result = Gson().fromJson<List<Book>>(it, sType)
                //Update the student LD which is being observed by Booklist List Fragment
                booklistLD.value = result
                booklistLoadingLD.value = false
                Log.d("showvolley", result.toString())
            },
            {
                Log.d("showerror", it.toString())
                booklistLoadErrorLD.value = true
                booklistLoadingLD.value = true
            }
        )

        //Start the volley queue request and set the TAG
        stringRequest?.tag = TAG
        stringRequest2.tag = TAG
        queue?.add(stringRequest)
        queue?.add(stringRequest2)
    }

    //To clean up code (volley resource) to prevent memory leak problems.
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}