package com.touchlane.android.bootstrap.comments

import com.touchlane.android.bootstrap.CommentsComponent
import com.touchlane.android.bootstrap.CommentsPresenterProvider
import com.touchlane.android.bootstrap.Router

class MockCommentsComponent(
    private val mockRouter: Router,
    private val presenterProvider: CommentsPresenterProvider
) : CommentsComponent {

    override fun inject(commentsActivity: CommentsActivity) {
        commentsActivity.router = mockRouter
        commentsActivity.presenterProvider = presenterProvider
    }
}