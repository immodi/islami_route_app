package com.client.routeapplication

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.client.routeapplication.hadeth.HadethFragment
import com.client.routeapplication.quran.QuranFragment
import com.client.routeapplication.radio.RadioFragment
import com.client.routeapplication.sepha.SebhaFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private var currentFragment: Fragment? = null
    private var isBackPress = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //----------------------------
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.nav_item4


        bottomNavigationView.setOnItemSelectedListener { item ->
            if (bottomNavigationView.selectedItemId == item.itemId) {
                return@setOnItemSelectedListener false
            } else {
                changeMenuItem(item)
                return@setOnItemSelectedListener true
            }
        }

        // Initialize the current fragment
        if (savedInstanceState == null) {
            currentFragment = QuranFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, currentFragment!!)
                .addToBackStack(R.id.nav_item4.toString())
                .commit()
        }

        //---------------------------
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                try {
                    isBackPress = true

                    if (supportFragmentManager.backStackEntryCount > 1) {
                        val lastEntryId = supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 2).name?.toInt()

                        // Pop the top entry from the back stack
                        supportFragmentManager.popBackStackImmediate()

                        if (lastEntryId != null) {
                            bottomNavigationView.selectedItemId = lastEntryId
                        } else {
                            Log.e("null backstack ", "null")
                        }

                        isBackPress = false

                    } else {
                        // If there's only one or no entries left, finish the activity
                        isBackPress = false
                        finish()
                    }
                } catch (e: Exception) {
                    isBackPress = false
                    Log.e("BackPress", "Error handling back press", e)
                    finish()
                }
            }
        }


        onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun replaceFragment(fragment: Fragment, itemId: Int) {
        Log.e("hello from rF", fragment.toString())
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, fragment)

        if (!isBackPress) transaction.addToBackStack(itemId.toString())

        transaction.commit()

        currentFragment = fragment
    }

    private fun changeMenuItem(item: MenuItem) {
        when (item.itemId) {
            R.id.nav_item1 -> {
                Log.e("change menu item", "Radio")
                replaceFragment(RadioFragment(), R.id.nav_item1)
            }

            R.id.nav_item2 -> {
                Log.e("change menu item", "Sebha")
                replaceFragment(SebhaFragment(), R.id.nav_item2)
            }

            R.id.nav_item3 -> {
                Log.e("change menu item", "Hadeth")
                replaceFragment(HadethFragment(), R.id.nav_item3)
            }

            R.id.nav_item4 -> {
                Log.e("change menu item", "Quran")
                replaceFragment(QuranFragment(), R.id.nav_item4)
            }

            else -> Log.e("no change menu item", "no change menu item")
        }

    }


}
