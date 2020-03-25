package com.touchlane.android.bootstrap

import android.content.Context
import com.touchlane.android.bootstrap.comments.CommentsActivity
import com.touchlane.android.bootstrap.comments.CommentsPresentationModel
import com.touchlane.android.bootstrap.data.Mapper
import com.touchlane.android.bootstrap.data.Strings
import com.touchlane.android.bootstrap.data.StringsImpl
import com.touchlane.android.bootstrap.data.comments.*
import com.touchlane.android.bootstrap.data.posts.*
import com.touchlane.android.bootstrap.data.users.*
import com.touchlane.android.bootstrap.domain.comments.Comment
import com.touchlane.android.bootstrap.domain.comments.CommentsInteractor
import com.touchlane.android.bootstrap.domain.comments.CommentsInteractorImpl
import com.touchlane.android.bootstrap.domain.comments.CommentsRepo
import com.touchlane.android.bootstrap.domain.posts.Post
import com.touchlane.android.bootstrap.domain.posts.PostsRepo
import com.touchlane.android.bootstrap.domain.userposts.UserPostsInteractor
import com.touchlane.android.bootstrap.domain.userposts.UserPostsInteractorImpl
import com.touchlane.android.bootstrap.domain.users.User
import com.touchlane.android.bootstrap.domain.users.UsersRepo
import com.touchlane.android.bootstrap.userposts.UserPostsActivity
import com.touchlane.android.bootstrap.userposts.UserPostsPresentationModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.JsonSerializer
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

@JvmField
val appModule = module {

    single<Strings> {
        StringsImpl(get())
    }
}

@JvmField
val networkModule = module {

    single<JsonSerializer> {
        GsonSerializer()
    }

    single {
        OkHttp.create {
            config {
                connectTimeout(30, TimeUnit.SECONDS)
            }
        }
    }

    single {
        HttpClient(get()) {
            install(JsonFeature) {
                serializer = get()
            }
        }
    }
}

private const val QUALIFIER_POSTS_MAPPER = "POSTS_MAPPER"
private const val POSTS_URL = "https://jsonplaceholder.typicode.com/posts"

@JvmField
val postsModule = module {

    single<PostsDataSource> {
        ApiPostsDataSource(get(), POSTS_URL)
    }

    single<Mapper<List<PostDto>, List<Post>>>(named(QUALIFIER_POSTS_MAPPER)) {
        PostsMapper()
    }

    single<PostsRepo> {
        ApiPostsRepo(get(), get(named(QUALIFIER_POSTS_MAPPER)))
    }
}

private const val QUALIFIER_USERS_MAPPER = "USERS_MAPPER"
private const val USERS_URL = "https://jsonplaceholder.typicode.com/users"

@JvmField
val usersModule = module {

    single<UsersDataSource> { ApiUsersDataSource(get(), USERS_URL) }

    single<Mapper<List<UserDto>, List<User>>>(named(QUALIFIER_USERS_MAPPER)) {
        UsersMapper()
    }

    single<UsersRepo> {
        ApiUsersRepo(get(), get(named(QUALIFIER_USERS_MAPPER)))
    }
}

@JvmField
val userPostsModule = module {

    single<UserPostsInteractor> {
        UserPostsInteractorImpl(get(), get())
    }

    scope<UserPostsActivity> {

        scoped<Router> { (context: Context) ->
            SimpleRouter(context)
        }
    }

    viewModel {
        UserPostsPresentationModel(get())
    }
}

private const val QUALIFIER_COMMENTS_MAPPER = "COMMENTS_MAPPER"
private const val COMMENTS_TO_POST_URL = "https://jsonplaceholder.typicode.com/comments?postId="

@JvmField
val commentsModule = module {

    single<CommentsDataSource> {
        ApiCommentsDataSource(get(), COMMENTS_TO_POST_URL)
    }

    single<Mapper<List<CommentDto>, List<Comment>>>(named(QUALIFIER_COMMENTS_MAPPER)) {
        CommentsMapper()
    }

    single<CommentsRepo> {
        ApiCommentsRepo(get(), get(named(QUALIFIER_COMMENTS_MAPPER)))
    }

    single<CommentsInteractor> {
        CommentsInteractorImpl(get())
    }

    viewModel { (id: Int) ->
        CommentsPresentationModel(get(), id)
    }

    scope<CommentsActivity> {

        scoped<Router> { (context: Context) ->
            SimpleRouter(context)
        }
    }
}