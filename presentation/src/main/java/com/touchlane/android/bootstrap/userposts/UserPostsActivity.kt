package com.touchlane.android.bootstrap.userposts

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.touchlane.android.bootstrap.Destination
import com.touchlane.android.bootstrap.R
import com.touchlane.android.bootstrap.Router
import kotlinx.android.synthetic.main.activity_user_posts.*
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UserPostsActivity : AppCompatActivity(R.layout.activity_user_posts) {

    private lateinit var adapter: UserPostAdapter
    private val router: Router by lifecycleScope.inject {
        parametersOf(this)
    }
    private val presentationModel: UserPostsPresentationModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = UserPostAdapter({
            router.goTo(Destination.Comments(it.id))
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
