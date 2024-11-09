package com.example.kotlinlastcrusade1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.kotlinlastcrusade1.databinding.FragmentUserDetailsBinding
import com.example.kotlinlastcrusade1.domain.model.User
import com.example.kotlinlastcrusade1.ui.adapter.RepoAdapter
import com.example.kotlinlastcrusade1.ui.base.BaseFragment
import com.example.kotlinlastcrusade1.ui.fragments.MainFragment.Companion.LOGIN
import com.example.kotlinlastcrusade1.ui.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailFragment : BaseFragment<FragmentUserDetailsBinding>() {

    private val viewModel: UserViewModel by viewModel()
    private lateinit var repoAdapter: RepoAdapter
    private val props = Props()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        viewBinding.apply {
            initializeScreenWithParameters()

            configureComponents()

            startInitializationsAndCalls()

            collectUserDetails()
        }

        return view
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentUserDetailsBinding.inflate(inflater, container, false)

    private fun initializeScreenWithParameters() {
        props.login = arguments?.getString(LOGIN)
    }

    private fun FragmentUserDetailsBinding.configureComponents() {
        hideScreen()
        setupRecyclerView()
    }

    private fun startInitializationsAndCalls() {
        props.login?.let {
            viewModel.observeUserData(it)
        }
    }

    private fun FragmentUserDetailsBinding.setupRecyclerView() {
        repoAdapter = RepoAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = repoAdapter
        }
    }

    private fun FragmentUserDetailsBinding.collectUserDetails() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userDataState.collect { dataState ->
                if (dataState.isLoading) {
                    // Show loading indicator (e.g., a progress bar)
                    showLoadingIndicator()
                } else {
                    // Hide loading indicator
                    hideLoadingIndicator()

                    showScreen()

                    // Update the UI with the user and repos data
                    dataState.user?.let { user ->
                        // Update UI with user details
                        showUserDetails(user)
                    }

                    dataState.repos?.let { repos ->
                        // Update UI with the list of repositories
                        repoAdapter.submitList(repos)
                    }
                }
            }
        }
    }

    private fun FragmentUserDetailsBinding.showUserDetails(user: User) {
        user.run {
            txtName.text = login
            txtBio.text = bio
            imgAvatar.load(user.avatarUrl)
        }
    }

    private fun FragmentUserDetailsBinding.showLoadingIndicator() {
        pgsLoading.visibility = View.VISIBLE
    }

    private fun FragmentUserDetailsBinding.hideLoadingIndicator() {
        pgsLoading.visibility = View.GONE
    }

    private fun FragmentUserDetailsBinding.showScreen() {
        blockView.visibility = View.GONE
    }

    private fun FragmentUserDetailsBinding.hideScreen() {
        blockView.visibility = View.VISIBLE
    }

    class Props {
        var login: String? = null
    }
}