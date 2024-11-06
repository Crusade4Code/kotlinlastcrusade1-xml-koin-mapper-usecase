package com.example.kotlinlastcrusade1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.kotlinlastcrusade1.databinding.FragmentUserDetailsBinding
import com.example.kotlinlastcrusade1.domain.model.User
import com.example.kotlinlastcrusade1.ui.adapter.RepoAdapter
import com.example.kotlinlastcrusade1.ui.base.BaseFragment
import com.example.kotlinlastcrusade1.ui.fragments.MainFragment.Companion.LOGIN
import com.example.kotlinlastcrusade1.ui.viewmodel.UserViewModel
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

            observerUserDetails()

            startInitializationsAndCalls()
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
        setupWait()
        setupRecyclerView()
    }

    private fun startInitializationsAndCalls() {
        props.login?.let {
            viewModel.getAllData(it)
        }
    }

    private fun FragmentUserDetailsBinding.setupWait() {
        val blinkAnimation = AlphaAnimation(0.0f, 1.0f)
        blinkAnimation.apply {
            duration = 500
            startOffset = 20
            repeatMode = Animation.REVERSE
            repeatCount = Animation.INFINITE
        }
        txtWait.startAnimation(blinkAnimation)
    }

    private fun FragmentUserDetailsBinding.setupRecyclerView() {
        repoAdapter = RepoAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = repoAdapter
        }
    }

    private fun FragmentUserDetailsBinding.observerUserDetails() {
        viewModel.allDataReceived.observe(viewLifecycleOwner) { data ->
            showUserDetails(data.first)
            repoAdapter.submitList(data.second)
            showScreen()
        }
    }

    private fun FragmentUserDetailsBinding.showUserDetails(user: User) {
        user.run {
            txtName.text = login
            txtBio.text = bio
            imgAvatar.load(user.avatarUrl)
        }
    }

    private fun FragmentUserDetailsBinding.showScreen() {
        txtWait.clearAnimation()
        txtWait.visibility = View.GONE
        blockView.visibility = View.GONE
    }

    class Props {
        var login: String? = null
    }
}