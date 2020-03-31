package com.touchlane.android.bootstrap.userposts

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.touchlane.android.bootstrap.*
import com.touchlane.android.bootstrap.domain.userpost.UserPost
import kotlinx.android.synthetic.main.activity_user_posts.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class UserPostsActivity : MvpAppCompatActivity(R.layout.activity_user_posts), UserPostsView {

    private lateinit var adapter: UserPostAdapter

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var presenterProvider: UserPostsPresenterProvider

    @InjectPresenter
    lateinit var presenter: UserPostsPresenter

    @ProvidePresenter
    fun providerPresenter(): UserPostsPresenter = presenterProvider.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        App.get()
            .appComponent
            .userPostsComponent(UserPostsModule(this))
            .inject(this)
        super.onCreate(savedInstanceState)
        adapter = UserPostAdapter({
            router.goTo(Destination.Comments(it.id))
        })
        userPosts.layoutManager = LinearLayoutManager(this)
        userPosts.adapter = adapter
    }

    override fun showUserPosts(userPosts: List<UserPost>) {
        adapter.submitList(userPosts)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
