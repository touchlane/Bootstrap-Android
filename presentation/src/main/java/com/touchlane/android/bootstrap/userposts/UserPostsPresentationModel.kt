package com.touchlane.android.bootstrap.userposts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import com.touchlane.android.bootstrap.domain.Result
import com.touchlane.android.bootstrap.domain.userposts.UserPost
import com.touchlane.android.bootstrap.domain.userposts.UserPostsInteractor
import kotlinx.coroutines.launch

class UserPostsPresentationModel(
    private val userPostsInteractor: UserPostsInteractor,
    val userPosts: MutableLiveData<List<UserPost>> = MutableLiveData(),
    val loading: MutableLiveData<Loading> = MutableLiveData(),
    val errorMessage: LiveEvent<String> = LiveEvent()
) : ViewModel() {

    init {
        viewModelScope.launch {
            loading.value = Loading.YES
            when(val result = userPostsInteractor.userPosts()) {
                is Result.Success -> userPosts.value = result.value
                is Result.Error -> errorMessage.value = result.throwable.message
            }
            loading.value = Loading.NO
        }
    }
}