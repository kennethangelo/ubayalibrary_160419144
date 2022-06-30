package id.ac.ubaya.informatika.ubayalibrary_160419144.util

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class LibraryWorker(val context: Context, val params: WorkerParameters)
    : Worker(context, params){
    //This class have a async function that trigger the creation of notification
    override fun doWork(): Result {
        NotificationHelper(context)
            //Input data is a key-value object that contain info to be processed within the doWork()
            .createNotification(inputData.getString("title").toString(),
                inputData.getString("message").toString())
        return Result.success()
    }
}