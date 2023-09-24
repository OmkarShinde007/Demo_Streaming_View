package com.demo.demostramingapp.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.demo.demostramingapp.model.MyApplication
import com.demo.demostramingapp.viewmodel.MyViewModel
import com.demo.demostramingapp.R
import com.google.android.material.appbar.MaterialToolbar
import javax.inject.Inject

/** Activity used to launch the [HomeMovieListFragment] and handle the toolbar events. */
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MyViewModel

    private lateinit var homeFragment: HomeMovieListFragment
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MyApplication).appComponent.inject(this)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Set click listener for the left icon
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        if (savedInstanceState == null) {
            homeFragment = HomeMovieListFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, homeFragment)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu?.findItem(R.id.search)
        searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_view_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle search query submission
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle search query text change
                queryChange(newText)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun queryChange(newText: String?) {
        homeFragment.updateRecyclerView(newText)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                item.expandActionView()
                true
            }

            else -> false
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Re-expand the search view if it was expanded before the orientation change
        if (searchView.isIconified) {
            searchView.isIconified = false
        }
    }
}