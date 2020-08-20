package com.example.covid19

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Picture
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.covid19.activities.LoginActivity
import com.example.covid19.data.model.CovidMenu
import com.example.covid19.data.model.CovidNotification
import com.example.covid19.data.repository.CovidNotificationRepository
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var ref: FirebaseFirestore?= null
    private var covidNotificationRepository: CovidNotificationRepository? = null
    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder
    private val channelId: String = "com.example.covid19"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar1 as Toolbar)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        mAuth = FirebaseAuth.getInstance()
        ref = FirebaseFirestore.getInstance()
        covidNotificationRepository = CovidNotificationRepository()
        if(mAuth?.currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val navHostFragment = nav_host_fragment as NavHostFragment
        NavigationUI.setupWithNavController(bottomNavigation1, navHostFragment.navController)

        ref?.collection("foods")?.addSnapshotListener {values, error ->
            run {
                if(values!!.metadata.isFromCache) return@run
                for(item in values.documentChanges){
                    if(item.type == DocumentChange.Type.ADDED){
                        val noti = CovidNotification()
                        noti.title = "New Menu: " + item.document.data["name"].toString()
                        noti.date = Date().toString()
                        covidNotificationRepository?.insert(noti)
                        createNotification(noti.title, noti.date)
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.main_menu_logout){
            mAuth?.signOut()
            goToLogin()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun createNotification(title: String, date: String){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationChannel = NotificationChannel(channelId, "Covid19 Notifications", NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.CYAN
            notificationChannel.enableVibration(true)

            builder = Notification.Builder(this, channelId)
                .setContentTitle("New Menu: $title")
                .setContentText(date)
        }

        builder = Notification.Builder(this)
            .setContentTitle("New Menu: $title")
            .setContentText(date)
            .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
            .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.common_google_signin_btn_icon_dark_normal))

        notificationManager.notify(1234, builder.build())
    }
}