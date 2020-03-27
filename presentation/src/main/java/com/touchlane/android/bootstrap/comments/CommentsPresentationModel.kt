package com.touchlane.android.bootstrap.comments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import com.touchlane.android.bootstrap.domain.Result
import com.touchlane.android.bootstrap.domain.comments.Comment
import com.touchlane.android.bootstrap.domain.comments.CommentsInteractor
import kotlinx.coroutines.launch

class CommentsPresentationModel(
    private val commentsInteractor: CommentsInteractor,
    private val postId: Int,
    val comments: MutableLiveData<List<Comment>> = MutableLiveData(),
    val errorMessage: LiveEvent<String> = LiveEvent()
) : ViewModel() {

    init {
        viewModelScope.launch {
            when (val result = commentsInteractor.commentsToPost(postId)) {
                is Result.Success -> comments.value = result.value
                is Result.Error -> errorMessage.value = result.throwable.message
            }
        }
    }
}