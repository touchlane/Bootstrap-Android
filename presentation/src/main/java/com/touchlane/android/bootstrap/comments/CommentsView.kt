package com.touchlane.android.bootstrap.comments

import com.touchlane.android.bootstrap.domain.comments.Comment
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface CommentsView : MvpView {

    @AddToEndSingle
    fun showComments(comments: List<Comment>)

    @OneExecution
    fun showError(message: String)
}