package com.touchlane.android.bootstrap.comments

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.touchlane.android.bootstrap.*
import com.touchlane.android.bootstrap.domain.comments.Comment
import kotlinx.android.synthetic.main.activity_comments.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class CommentsActivity : MvpAppCompatActivity(R.layout.activity_comments), CommentsView {

    private val postId: Int by extra(EXTRA_POST_ID)

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var presenterProvider: CommentsPresenterProvider

    @InjectPresenter
    lateinit var presenter: CommentsPresenter

    @ProvidePresenter
    fun providePresenter() = presenterProvider.get()

    private lateinit var adapter: CommentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.get()
            .appComponent
            .commentsComponent(
                CommentsModule(
                    this,
                    postId
                )
            )
            .inject(this)
        super.onCreate(savedInstanceState)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Comments:"
        }
        adapter = CommentsAdapter()
        comments.layoutManager = LinearLayoutManager(this)
        comments.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun showComments(comments: List<Comment>) {
        adapter.submitList(comments)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val EXTRA_POST_ID = "extra_post_id"
    }
}