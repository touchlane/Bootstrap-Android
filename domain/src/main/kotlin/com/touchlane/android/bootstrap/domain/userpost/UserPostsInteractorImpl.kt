package com.touchlane.android.bootstrap.domain.userpost

import com.touchlane.android.bootstrap.domain.ResourceNotFoundException
import com.touchlane.android.bootstrap.domain.posts.PostsRepo
import com.touchlane.android.bootstrap.domain.users.UsersRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction

class UserPostsInteractorImpl(
    private val postsRepo: PostsRepo,
    private val usersRepo: UsersRepo
) : UserPostsInteractor {

    override fun userPosts(): Single<List<UserPost>> = postsRepo.posts()
        .zipWith(
            usersRepo.users(),
            BiFunction { posts, users ->
                posts.map { post ->
                    val user = users.find {
                        it.id == post.userId
                    } ?: throw ResourceNotFoundException("User with id:${post.userId} not found")
                    UserPost(
                        post.id,
                        post.userId,
                        post.title,
                        post.body,
                        user.username,
                        user.email
                    )
                }
            }
        )
}