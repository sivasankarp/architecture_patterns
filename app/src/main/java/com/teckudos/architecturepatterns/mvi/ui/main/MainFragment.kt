package com.teckudos.architecturepatterns.mvi.ui.main

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.teckudos.architecturepatterns.R
import com.teckudos.architecturepatterns.mvi.model.BlogPost
import com.teckudos.architecturepatterns.mvi.model.User
import com.teckudos.architecturepatterns.mvi.ui.DataStateListener
import com.teckudos.architecturepatterns.mvi.ui.main.state.MainStateEvent
import com.teckudos.architecturepatterns.mvi.util.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.Exception

class MainFragment : Fragment(), MainRecyclerAdapter.Interaction {

    private lateinit var viewModel: MainViewModel

    lateinit var dataStateHandler: DataStateListener

    lateinit var mainRecyclerAdapter: MainRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid activity")

        subscribeObservers()
        initRecyclerView()
    }

    private fun subscribeObservers() {
        /*
        * viewLifecycleOwner or this in fragment -
        *
        * viewLifecycleOwner is tied to when the fragment has (and loses) its UI
        * (onCreateView(), onDestroyView())
        * this is tied to the fragment's overall lifecycle (onCreate(), onDestroy()), which
        * may be substantially longer
        *
        * */
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            println("DEBUG: DataState: ${dataState}")

            // Handle Loading and Message
            dataStateHandler.onDataStateChange(dataState)

            // Handle Data<T>
            dataState.data?.let { mainViewState ->

                mainViewState.getContentIfNotHandled()?.let { event ->
                    println("DEBUG: INSIDE DataState: ${event}")
                    event.blogPosts?.let { blogPosts ->
                        // set blogPost data
                        viewModel.setBlogListData(blogPosts)
                    }

                    event.user?.let { user ->
                        // set user data
                        viewModel.setUser(user)
                    }
                }

            }

        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            viewState.blogPosts?.let {
                println("DEBUG : set blog post to recyclerview : ${it}")
                mainRecyclerAdapter.submitList(it)
            }
            viewState.user?.let {
                println("DEBUG: set user data : ${it}")
                setUserProperties(it)
            }
        })
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            mainRecyclerAdapter = MainRecyclerAdapter(this@MainFragment)
            adapter = mainRecyclerAdapter
        }
    }

    private fun setUserProperties(user: User) {
        email.text = user.email
        username.text = user.username

        view?.let {
            Glide.with(it.context)
                .load(user.image)
                .into(image)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    private fun triggerGetUserEvent() {
        viewModel.setStateEvent(MainStateEvent.GetUserEvent(userId = "1"))
    }

    private fun triggerGetBlogsEvent() {
        viewModel.setStateEvent(MainStateEvent.GetBlogPostsEvent())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_get_user -> triggerGetUserEvent()
            R.id.action_get_blogs -> triggerGetBlogsEvent()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            dataStateHandler = context as DataStateListener
        } catch (e: ClassCastException) {
            println("$context must implement DataStateListener")
        }
    }

    override fun onItemSelected(position: Int, item: BlogPost) {
        println("DEBUG: CLICKED ${position}")
        println("DEBUG: CLICKED ${item}")
    }
}