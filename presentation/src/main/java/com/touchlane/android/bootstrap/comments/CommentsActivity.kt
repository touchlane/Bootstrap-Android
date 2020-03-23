package com.touchlane.android.bootstrap.comments

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.touchlane.android.bootstrap.R
import com.touchlane.android.bootstrap.Router
import com.touchlane.android.bootstrap.extra
import kotlinx.android.synthetic.main.activity_comments.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CommentsActivity : AppCompatActivity(R.layout.activity_comments) {

    private val postId: Int by extra(EXTRA_POST_ID)
    private val presentationModel: CommentsPresentationModel by viewModel {
        parametersOf(postId)
    }
    private val router: Router by inject()
    private lateinit var adapter: CommentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Comments:"
        }

        adapter = CommentsAdapter()
        comments.layoutManager = LinearLayoutManager(this)
        comments.adapter = adapter

        presentationModel.comments.observe(this, Observer {
            adapter.submitList(it)
        })
        presentationModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {
        const val EXTRA_POST_ID = "extra_post_id"
    }
}