package com.touchlane.android.bootstrap.userposts

import com.touchlane.android.bootstrap.Router
import com.touchlane.android.bootstrap.UserPostsComponent
import com.touchlane.android.bootstrap.UserPostsPresenterProvider

class MockUserPostsComponent(
    private val mockRouter: Router,
    private val presenterProvider: UserPostsPresenterProvider
) : UserPostsComponent {

    override fun inject(userPostsActivity: UserPostsActivity) {
        userPostsActivity.router = mockRouter
        userPostsActivity.presenterProvider = presenterProvider
    }
}