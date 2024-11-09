package com.example.kotlinlastcrusade1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinlastcrusade1.R
import com.example.kotlinlastcrusade1.databinding.FragmentMainBinding
import com.example.kotlinlastcrusade1.ui.adapter.UserAdapter
import com.example.kotlinlastcrusade1.ui.base.BaseFragment
import com.example.kotlinlastcrusade1.ui.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        viewBinding.apply {
            configureComponents()

            startInitializationsAndCalls()

            collectUsers()
        }

        return view
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentMainBinding.inflate(inflater, container, false)

    private fun FragmentMainBinding.configureComponents() {
        setupRecyclerView()
    }

    private fun startInitializationsAndCalls() {
        viewModel.getUsers()
    }

    private fun collectUsers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Collect user list
                viewModel.users.collect { userList ->
                    userAdapter.submitList(userList)
                }
            }
        }
    }

    private fun FragmentMainBinding.setupRecyclerView() {
        userAdapter = UserAdapter { selectedUser ->
            navigateToUserDetails(selectedUser.login)
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }
    }

    private fun navigateToUserDetails(username: String) {
        findNavController().navigate(
            R.id.action_mainFragment_to_userDetailFragment,
            bundleOf(LOGIN to username)
        )
    }

    companion object {
        const val LOGIN = "username"
    }
}