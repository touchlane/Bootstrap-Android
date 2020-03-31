package com.touchlane.android.bootstrap.userposts

import com.touchlane.android.bootstrap.domain.userpost.UserPost
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface UserPostsView : MvpView {

    @AddToEndSingle
    fun showUserPosts(userPosts: List<UserPost>)

    @OneExecution
    fun showError(message: String)
}