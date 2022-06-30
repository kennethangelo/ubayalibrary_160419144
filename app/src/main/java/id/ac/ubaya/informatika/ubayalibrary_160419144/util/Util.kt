package id.ac.ubaya.informatika.ubayalibrary_160419144.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.LibraryDatabase
import java.lang.Exception

//Used to extend the loadImage method of ImageView
//Passed image URL, and progress bar view object
fun ImageView.loadImage(url: String?, progressBar: ProgressBar, height: Int?, width: Int?) {
    if (height != null && width != null) {
        Picasso.get()
            .load(url)
            .resize(width, height)
            .error(R.drawable.ic_baseline_error_24)
            .into(this, object : Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    Log.d("error", e.toString())
                }
            }) //In case it's failed to load, it uses custom drawable to replace the image
    } else {
        Picasso.get()
            .load(url)
            .error(R.drawable.ic_baseline_error_24)
            .into(this, object : Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    Log.d("error", e.toString())
                }
            }) //In case it's failed to load, it uses custom drawable to replace the image
    }
}

@BindingAdapter("android:imageUrl", "android:progressBar")
fun loadPhotoURL(view: ImageView, url: String?, pb: ProgressBar) {
    view.loadImage(url, pb, null, null)
}

fun createNotificationChannel(
    context: Context,
    importance: Int,
    showBadge: Boolean,
    name: String,
    description: String
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "${context.packageName}-$name"
        val channel = NotificationChannel(channelId, name, importance)
        channel.description = description
        channel.setShowBadge(showBadge)

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}

val DB_NAME = "librarydb"


fun buildDB(context: Context): LibraryDatabase {
    val db = Room.databaseBuilder(
        context, LibraryDatabase::class.java, DB_NAME
    )
        .build()
    return db
}
