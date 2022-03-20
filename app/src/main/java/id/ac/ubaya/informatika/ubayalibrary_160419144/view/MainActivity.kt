package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
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
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
                //This code automatically detects if the user is on the top level of backstack: the drawer icon will show instead of the back button
                || super.onNavigateUp()
    }
}