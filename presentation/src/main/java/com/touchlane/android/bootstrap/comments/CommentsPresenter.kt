package com.touchlane.android.bootstrap.comments

import com.touchlane.android.bootstrap.SchedulersModule
import com.touchlane.android.bootstrap.domain.comments.CommentsInteractor
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class CommentsPresenter(
    private val commentsInteractor: CommentsInteractor,
    private val postId: Int,
    @SchedulersModule.Main
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : MvpPresenter<CommentsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        commentsInteractor.commentsToPost(postId)
            .observeOn(scheduler)
            .subscribe(
                {
                    viewState.showComments(it)
                }, {
                    viewState.showError(it.message ?: "Error")
                }
            )
            .let { compositeDisposable.add(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}