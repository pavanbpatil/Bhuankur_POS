package com.alphaone.pos.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.alphaone.pos.R
import com.alphaone.pos.fragment.*
import com.alphaone.pos.modelclass.ServiceResponse
import com.alphaone.pos.networkmanager.LoginManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*


class Home : AppCompatActivity(){

    // private var mDrawer: DrawerLayout? = null
    // private val toolbar: Toolbar? = null
    // private val nvDrawer: NavigationView? = null

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private var drawerToggle: ActionBarDrawerToggle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolBar);

        // This will display an Up icon (<-), we will replace it with hamburger later
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(nvView)


        drawerToggle = setupDrawerToggle()
        drawerToggle?.isDrawerIndicatorEnabled()
        // Setup toggle to display hamburger icon with nice animation
        // drawerToogle.setDrawerIndicatorEnabled(true);
        drawerToggle?.syncState()


        // Tie DrawerLayout events to the ActionBarToggle
        drawerToggle?.let { drawer_layout.addDrawerListener(it) }
        loadFragment(Dashboard::class.java)

    }

    private fun setupDrawerToggle(): ActionBarDrawerToggle? {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return ActionBarDrawerToggle(this, drawer_layout, toolBar, R.string.drawer_open, R.string.drawer_close)
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            selectDrawerItem(menuItem)
            true
        }
    }

    fun selectDrawerItem(menuItem: MenuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked

        val fragmentClass: Class<*>
        fragmentClass = when (menuItem.itemId) {
            R.id.nav_dashboard -> Dashboard::class.java
            R.id.nav_pending_cart -> PendingCart::class.java
            R.id.nav_update_crates -> UpdateCrates::class.java
            R.id.nav_stock -> Stock::class.java
            R.id.nav_sale -> Sale::class.java
            R.id.nav_settings -> Settings::class.java
            R.id.nav_user_guide -> UserGuide::class.java
            R.id.nav_helpline -> Helpline::class.java
            else -> Dashboard::class.java
        }

        loadFragment(fragmentClass)

        // Highlight the selected item has been done by NavigationView
        menuItem.isChecked = true
        // Set action bar title
        title = menuItem.title
        // Close the navigation drawer
        drawer_layout!!.closeDrawers()
    }

    fun loadFragment(fragmentClass: Class<*>) {
        var fragment: Fragment? = null
        try {
            fragment = fragmentClass.newInstance() as Fragment
        } catch (e: Exception) {
            e.printStackTrace()
        }
        // Insert the fragment by replacing any existing fragment
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment!!).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (drawerToggle!!.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }


}
