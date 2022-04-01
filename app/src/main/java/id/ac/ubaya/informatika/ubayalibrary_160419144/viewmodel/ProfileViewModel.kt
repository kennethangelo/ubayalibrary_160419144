package id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel

import android.app.Application
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Booklist
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.User

class ProfileViewModel(application: Application): AndroidViewModel(application) {
    //Live data that emit profile data
    val profileLD = MutableLiveData<User>()
    val profileBooklistLD = MutableLiveData<List<Booklist>>()

    val profileLoadErrorLD = MutableLiveData<Boolean>()
    val booklistLoadErrorLD = MutableLiveData<Boolean>()

    val loadingLD = MutableLiveData<Boolean>()
    val loadingBooklistLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    //Load data from server/local and prepare livedata objects (ready to be observed)
    fun refresh(username: String?) {
        profileLoadErrorLD.value = false
        loadingLD.value = true

        booklistLoadErrorLD.value = false
        loadingBooklistLD.value = true

        //Initialize volley
        queue = Volley.newRequestQueue(getApplication());
        val url = "http://10.0.2.2/ubayalibrary/user.php?username=$username";
//        val url = "http://192.168.0.2/ubayalibrary/user.php?username=$username";

        val url2 = "http://10.0.2.2/ubayalibrary/booklist.php?username=$username";
//        val url = "http://192.168.0.8/ubayalibrary/booklist.php?username=$username";

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                //TypeToken -> retrieve the obj type of list of booklist
                val sType = object: TypeToken<User>() {}.type
                //fromJson -> convert JSON string to list of booklist
                val result = Gson().fromJson<User>(it, sType)
                //Update the student LD which is being observed by Profile Fragment
                profileLD.value = result
                loadingLD.value = false
                Log.d("showvolley", result.toString())
            },
            {
                Log.d("showerror", it.toString())
                profileLoadErrorLD.value = true
                loadingLD.value = true
            }
        )

        val stringRequest2 = StringRequest(
            Request.Method.GET, url2,
            {
                //TypeToken -> retrieve the obj type of list of booklist
                val sType = object: TypeToken<List<Booklist>>() {}.type
                //fromJson -> convert JSON string to list of booklist
                val result = Gson().fromJson<List<Booklist>>(it, sType)
                //Update the student LD which is being observed by Profile Fragment
                profileBooklistLD.value = result
                loadingBooklistLD.value = false
                Log.d("showvolley", result.toString())
            },
            {
                Log.d("showerror", it.toString())
                booklistLoadErrorLD.value = true
                loadingBooklistLD.value = true
            }
        )

        //Start the volley queue request and set the TAG
        stringRequest.tag = TAG
        queue?.add(stringRequest)
        queue?.add(stringRequest2)

    }

    //To clean up code (volley resource) to prevent memory leak problems.
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}