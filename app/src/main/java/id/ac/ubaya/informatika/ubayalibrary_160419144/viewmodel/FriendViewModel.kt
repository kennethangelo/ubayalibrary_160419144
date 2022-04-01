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
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.User

class FriendViewModel(application: Application): AndroidViewModel(application) {
    //Live data that emit profile data
    val friendLD = MutableLiveData<List<User>>()
    val friendLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    //Load data from server/local and prepare livedata objects (ready to be observed)
    fun refresh(username: String?) {
        friendLoadErrorLD.value = false
        loadingLD.value = true

        //Initialize volley
        queue = Volley.newRequestQueue(getApplication());
        val url = "http://10.0.2.2/ubayalibrary/friend.php?username=$username";
//        val url = "http://192.168.0.8/ubayalibrary/friend.php?username=$username";

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                //TypeToken -> retrieve the obj type of list of booklist
                val sType = object: TypeToken<List<User>>() {}.type
                //fromJson -> convert JSON string to list of booklist
                val result = Gson().fromJson<List<User>>(it, sType)
                //Update the student LD which is being observed by Profile Fragment
                friendLD.value = result
                loadingLD.value = false
                Log.d("showvolley", result.toString())
            },
            {
                Log.d("showerror", it.toString())
                friendLoadErrorLD.value = true
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