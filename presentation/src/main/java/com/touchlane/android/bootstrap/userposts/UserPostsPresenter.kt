package com.touchlane.android.bootstrap.userposts

import com.touchlane.android.bootstrap.SchedulersModule
import com.touchlane.android.bootstrap.domain.userpost.UserPostsInteractor
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class UserPostsPresenter(
    private val userPostsInteractor: UserPostsInteractor,
    @SchedulersModule.Main
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : MvpPresenter<UserPostsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        userPostsInteractor.userPosts()
            .observeOn(scheduler)
            .subscribe(
                {
                    viewState.showUserPosts(it)
                },
                {
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