package com.touchlane.android.bootstrap.userposts

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.touchlane.android.bootstrap.Destination
import com.touchlane.android.bootstrap.R
import com.touchlane.android.bootstrap.Router
import kotlinx.android.synthetic.main.activity_user_posts.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class UserPostsActivity : AppCompatActivity(R.layout.activity_user_posts) {

    private lateinit var adapter: UserPostAdapter
    private val router: Router by inject()
    private val presentationModel: UserPostsPresentationModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = UserPostAdapter({
            router.goTo(this, Destination.Comments(it.id))
        })
        userPosts.layoutManager = LinearLayoutManager(this)
        userPosts.adapter = adapter

        presentationModel.loading.observe(this, Observer {
            progressBar.visibility = if (it == Loading.YES) View.VISIBLE else View.GONE
        })
        presentationModel.userPosts.observe(this, Observer {
            adapter.submitList(it)
        })
        presentationModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}
