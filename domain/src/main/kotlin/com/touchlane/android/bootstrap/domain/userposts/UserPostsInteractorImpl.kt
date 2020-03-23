package com.touchlane.android.bootstrap.domain.userposts

import com.touchlane.android.bootstrap.domain.ResourceNotFoundException
import com.touchlane.android.bootstrap.domain.Result
import com.touchlane.android.bootstrap.domain.posts.Post
import com.touchlane.android.bootstrap.domain.posts.PostsRepo
import com.touchlane.android.bootstrap.domain.users.UsersRepo

class UserPostsInteractorImpl(
    private val postsRepo: PostsRepo,
    private val usersRepo: UsersRepo
) : UserPostsInteractor {

    override suspend fun userPosts(): Result<List<UserPost>> = try {
        postsRepo
            .posts()
            .let { posts ->
                val users = usersRepo.users()
                posts.map { post: Post ->
                    val user = users.find { it.id == post.userId }
                        ?: return Result.Error(ResourceNotFoundException("User with id:${post.userId} not found"))
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
            .let { Result.Success(it) }
    } catch (e: Throwable) {
        Result.Error(e)
    }
}