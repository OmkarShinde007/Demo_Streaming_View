package com.demo.demostramingapp.view

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.demostramingapp.model.MyApplication
import com.demo.demostramingapp.viewmodel.MyViewModel
import com.demo.demostramingapp.R
import javax.inject.Inject

/** Fragment used to shows the Recyclerview and handle its actions. */
class HomeMovieListFragment : Fragment() {

    private lateinit var movieListRecyclerview: RecyclerView
    private lateinit var recyclerViewAdapter: MovieListAdapter

    @Inject
    lateinit var viewModel: MyViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_movie_list, container, false)
        // Inject dependencies using Dagger
        (requireActivity().application as MyApplication).appComponent.inject(this)

        movieListRecyclerview = view.findViewById(R.id.movie_list_recyclerview)
        // Configuration changes the Gridview span
        val configuration = resources.configuration
        var spanCount = INITIAL_SPAN
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Change the column count for landscape mode
            spanCount = SEVEN_SPAN // Set the desired column count
        } else if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Change the column count for portrait mode
            spanCount = THREE_SPAN // Set the desired column count
        }

        movieListRecyclerview.layoutManager = GridLayoutManager(context, spanCount)
        recyclerViewAdapter = MovieListAdapter(requireContext())

        movieListRecyclerview.adapter = recyclerViewAdapter

        // Fetch data from the ViewModel and update the adapter
        viewModel.combinedData.observe(viewLifecycleOwner) { data ->
            if (data.size == 3) {
                recyclerViewAdapter.updateList(data[0].page.`content-items`.content)
                recyclerViewAdapter.updateList(data[1].page.`content-items`.content)
                recyclerViewAdapter.updateList(data[2].page.`content-items`.content)
            }
        }

        // Load data from all three datasets
        viewModel.loadData()

        return view
    }

    /** After scrolling the Recyclerview items to the last then next dataset should load but this functionality is not working right now.*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set a scroll listener to detect when the user reaches the end of the dataset
        movieListRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount

                if (lastVisibleItemPosition == totalItemCount - 1) {
                    // User has reached the end of the current dataset, load the next one
                    //viewModel.loadNextDataSet()
                }
            }
        })
    }

    /** Method used to provide the search query to adapter. */
    fun updateRecyclerView(page: String?) {
        recyclerViewAdapter.updateFilterList(page)

    }

    /** Method used to handle the configuration changes. */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Check the orientation and update the span count
        var spanCount = if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            SEVEN_SPAN
        } else {
            THREE_SPAN
        }

        movieListRecyclerview.layoutManager = GridLayoutManager(context, spanCount)
    }

    companion object {
        const val INITIAL_SPAN = 0
        const val THREE_SPAN = 3
        const val SEVEN_SPAN = 7
    }
}