package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.util.createNotificationChannel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    init{
        //a workaround solution to get access of context object within activity
        instance = this
    }

    companion object{
        private var instance: MainActivity ?= null

        fun showNotification(title:String, content:String, icon:Int){
            val channelId = "${instance?.packageName}-${ instance?.getString(R.string.app_name)}"

            val notificationBuilder = NotificationCompat.Builder(instance!!.applicationContext, channelId).apply {
                setSmallIcon(icon)
                setContentTitle(title)
                setContentText(content)
                setStyle(NotificationCompat.BigTextStyle())
                priority = NotificationCompat.PRIORITY_DEFAULT
                setAutoCancel(true)
            }

            val notificationManager = NotificationManagerCompat.from(instance!!.applicationContext.applicationContext!!)
            notificationManager.notify(1001, notificationBuilder.build())
        }
    }
    //lateinit -> Late Initialization
    //When a non-null initializer can't be supplied in the constructor.
    //But the dev certain that the variable won't be null when accessing it -> avoiding null checks when referencing it later.
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Below is used to initialize the Navigation Controller and associate it with NavHost Object
        navController = (supportFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        //Menghubungkan antara bottom nav dengan navcontroller
        NavigationUI.setupWithNavController(navView, navController)
        bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, target, _ ->
            if(target.id == R.id.loginFragment || target.id == R.id.signUpFragment){
                bottomNav.visibility = View.GONE
                navView.visibility = View.GONE
            } else {
                bottomNav.visibility = View.VISIBLE
                navView.visibility = View.VISIBLE
            }
        }

        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false, getString(R.string.app_name), "App notification channel.")
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
                //This code automatically detects if the user is on the top level of backstack: the drawer icon will show instead of the back button
                || super.onNavigateUp()
    }
}