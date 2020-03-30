package com.touchlane.android.bootstrap

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.touchlane.android.bootstrap.comments.CommentsActivity
import com.touchlane.android.bootstrap.comments.CommentsPresenter
import com.touchlane.android.bootstrap.data.Strings
import com.touchlane.android.bootstrap.data.StringsImpl
import com.touchlane.android.bootstrap.data.api.WebApi
import com.touchlane.android.bootstrap.data.comments.ApiCommentsDataSource
import com.touchlane.android.bootstrap.data.comments.ApiCommentsRepo
import com.touchlane.android.bootstrap.data.comments.CommentsDataSource
import com.touchlane.android.bootstrap.data.comments.CommentsMapper
import com.touchlane.android.bootstrap.data.posts.ApiPostsDataSource
import com.touchlane.android.bootstrap.data.posts.ApiPostsRepo
import com.touchlane.android.bootstrap.data.posts.PostsDataSource
import com.touchlane.android.bootstrap.data.posts.PostsMapper
import com.touchlane.android.bootstrap.data.users.ApiUsersDataSource
import com.touchlane.android.bootstrap.data.users.ApiUsersRepo
import com.touchlane.android.bootstrap.data.users.UsersDataSource
import com.touchlane.android.bootstrap.data.users.UsersMapper
import com.touchlane.android.bootstrap.domain.comments.CommentsInteractor
import com.touchlane.android.bootstrap.domain.comments.CommentsInteractorImpl
import com.touchlane.android.bootstrap.domain.comments.CommentsRepo
import com.touchlane.android.bootstrap.domain.posts.PostsRepo
import com.touchlane.android.bootstrap.domain.userpost.UserPostsInteractor
import com.touchlane.android.bootstrap.domain.userpost.UserPostsInteractorImpl
import com.touchlane.android.bootstrap.domain.users.UsersRepo
import com.touchlane.android.bootstrap.userposts.UserPostsActivity
import com.touchlane.android.bootstrap.userposts.UserPostsPresenter
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Provider
import javax.inject.Qualifier
import javax.inject.Scope
import javax.inject.Singleton

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewScope

@Singleton
@Component(modules = [AppModule::class, SchedulersModule::class, NetworkModule::class])
interface AppComponent {

    fun userPostsComponent(userPostsModule: UserPostsModule): UserPostsComponent

    fun commentsComponent(commentsModule: CommentsModule): CommentsComponent
}

@Module
class AppModule(
    private val context: Context
) {

    @Provides
    @Singleton
    fun context(): Context = context

    @Provides
    @Singleton
    fun strings(context: Context): Strings = StringsImpl(context)
}

@Module
class SchedulersModule {

    @Provides
    @IO
    @Singleton
    fun io(): Scheduler = Schedulers.io()

    @Provides
    @Main
    @Singleton
    fun main(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Computation
    @Singleton
    fun computation(): Scheduler = Schedulers.computation()

    @Qualifier
    annotation class IO

    @Qualifier
    annotation class Computation

    @Qualifier
    annotation class Main
}

@Module
class NetworkModule() {

    @Provides
    @Singleton
    fun callAdapterFactory(@SchedulersModule.IO scheduler: Scheduler): RxJava3CallAdapterFactory =
        RxJava3CallAdapterFactory.createWithScheduler(scheduler)

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder()
        .create()

    @Provides
    @Singleton
    fun converterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    @BaseUrl
    @Singleton
    fun baseUrl(): String = "https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun retrofit(
        callAdapterFactory: RxJava3CallAdapterFactory,
        converterFactory: GsonConverterFactory,
        @BaseUrl baseUrl: String
    ): Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(callAdapterFactory)
        .addConverterFactory(converterFactory)
        .baseUrl(baseUrl)
        .build()

    @Provides
    @Singleton
    fun webApi(retrofit: Retrofit): WebApi = retrofit.create(WebApi::class.java)

    @Provides
    @Singleton
    fun postsDataSource(webApi: WebApi): PostsDataSource = ApiPostsDataSource(webApi)

    @Provides
    @Singleton
    fun postsMapper(): PostsMapper = PostsMapper()

    @Provides
    @Singleton
    fun postsRepo(postsDataSource: PostsDataSource, postsMapper: PostsMapper): PostsRepo =
        ApiPostsRepo(
            postsDataSource,
            postsMapper
        )

    @Provides
    @Singleton
    fun usersDataSource(webApi: WebApi): UsersDataSource = ApiUsersDataSource(webApi)

    @Provides
    @Singleton
    fun usersMapper(): UsersMapper = UsersMapper()

    @Provides
    @Singleton
    fun usersRepo(usersDataSource: UsersDataSource, usersMapper: UsersMapper): UsersRepo =
        ApiUsersRepo(
            usersDataSource,
            usersMapper
        )

    @Provides
    @Singleton
    fun commentsDataSource(webApi: WebApi): CommentsDataSource = ApiCommentsDataSource(webApi)

    @Provides
    @Singleton
    fun commentsMapper(): CommentsMapper = CommentsMapper()

    @Provides
    @Singleton
    fun commentsRepo(
        commentsDataSource: CommentsDataSource,
        commentsMapper: CommentsMapper
    ): CommentsRepo =
        ApiCommentsRepo(
            commentsDataSource,
            commentsMapper
        )

    @Qualifier
    annotation class BaseUrl
}

@ViewScope
@Subcomponent(modules = [UserPostsModule::class])
interface UserPostsComponent {

    fun inject(userPostsActivity: UserPostsActivity)
}

@Module
class UserPostsModule(
    private val activityContext: Context
) {

    @Provides
    @ViewScope
    fun router(): Router = SimpleRouter(activityContext)

    @Provides
    @ViewScope
    fun userPostsInteractor(postsRepo: PostsRepo, usersRepo: UsersRepo): UserPostsInteractor =
        UserPostsInteractorImpl(
            postsRepo,
            usersRepo
        )

    @Provides
    @ViewScope
    fun userPostsPresenterProvider(
        userPostsInteractor: UserPostsInteractor,
        @SchedulersModule.Main scheduler: Scheduler
    ): UserPostsPresenterProvider =
        UserPostsPresenterProvider(UserPostsPresenter(userPostsInteractor, scheduler))
}

class UserPostsPresenterProvider(
    private val presenter: UserPostsPresenter
) : Provider<UserPostsPresenter> {

    override fun get(): UserPostsPresenter = presenter
}

@ViewScope
@Subcomponent(modules = [CommentsModule::class])
interface CommentsComponent {

    fun inject(commentsActivity: CommentsActivity)
}

@Module
class CommentsModule(
    private val activityContext: Context,
    private val postId: Int
) {

    @Provides
    @ViewScope
    fun router(): Router = SimpleRouter(activityContext)

    @Provides
    @ViewScope
    fun commentsInteractor(commentsRepo: CommentsRepo): CommentsInteractor =
        CommentsInteractorImpl(commentsRepo)

    @Provides
    @ViewScope
    fun commentsPresenterProvider(
        commentsInteractor: CommentsInteractor,
        @SchedulersModule.Main scheduler: Scheduler
    ): CommentsPresenterProvider =
        CommentsPresenterProvider(CommentsPresenter(commentsInteractor, postId, scheduler))
}

class CommentsPresenterProvider(
    private val commentsPresenter: CommentsPresenter
) : Provider<CommentsPresenter> {

    override fun get(): CommentsPresenter = commentsPresenter
}