package id.ac.ubaya.informatika.ubayalibrary_160419144.util

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import java.lang.Exception

//Used to extend the loadImage method of ImageView
//Passed image URL, and progress bar view object
fun ImageView.loadImage(url: String?, progressBar: ProgressBar){
    Picasso.get()
        .load(url)
        .resize(500,400)
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object: Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
                Log.d("error", e.toString())
            }
        }) //In case it's failed to load, it uses custom drawable to replace the image
}

fun ImageView.loadArticleImage(url: String?, progressBar: ProgressBar){
    Picasso.get()
        .load(url)
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object: Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
                Log.d("error", e.toString())
            }
        }) //In case it's failed to load, it uses custom drawable to replace the image
}