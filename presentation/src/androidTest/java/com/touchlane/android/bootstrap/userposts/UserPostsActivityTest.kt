package com.touchlane.android.bootstrap.userposts

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.touchlane.android.bootstrap.*
import com.touchlane.android.bootstrap.domain.userpost.UserPost
import com.touchlane.android.bootstrap.domain.userpost.UserPostsInteractor
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@LargeTest
@RunWith(AndroidJUnit4::class)
class UserPostsActivityTest {

    private val testUserPost1 = UserPost(
        1,
        1,
        "title1",
        "body1",
        "username1",
        "email1"
    )
    private val testUserPosts = listOf(testUserPost1)

    private val mockUserPostsInteractor = mock(UserPostsInteractor::class.java)
    private val mockRouter = mock(Router::class.java)
    private val mockAppComponent = mock(AppComponent::class.java)

    private lateinit var mockUserPostsComponent: UserPostsComponent

    @get:Rule
    var activityRule: ActivityTestRule<UserPostsActivity> =
        object : ActivityTestRule<UserPostsActivity>(
            UserPostsActivity::class.java,
            true,
            false
        ) {

            override fun beforeActivityLaunched() {
                super.beforeActivityLaunched()
                App.get().appComponent = mockAppComponent
            }
        }

    @Before
    fun setup() {
        mockUserPostsComponent = MockUserPostsComponent(
            mockRouter,
            UserPostsPresenterProvider(
                UserPostsPresenter(
                    mockUserPostsInteractor,
                    Schedulers.trampoline()
                )
            )
        )
        `when`(mockAppComponent.userPostsComponent(any())).thenReturn(mockUserPostsComponent)
        `when`(mockUserPostsInteractor.userPosts()).thenReturn(Single.just(testUserPosts))
    }

    @Test
    fun testPostsShown() {
        activityRule.launchActivity(Intent())

        onView(withId(R.id.userPosts)).check(
            matches(
                atPosition(
                    0,
                    allOf(
                        hasDescendant(
                            allOf(
                                withId(R.id.title),
                                withText("title1")
                            )
                        ),
                        hasDescendant(
                            allOf(
                                withId(R.id.body),
                                withText("body1")
                            )
                        ),
                        hasDescendant(
                            allOf(
                                withId(R.id.username),
                                withText("username1")
                            )
                        )
                    )
                )
            )
        )
    }

    @Test
    fun testClickOnPost() {
        activityRule.launchActivity(Intent())

        onView(
            allOf(
                withId(R.id.title),
                withText("title1")
            )
        ).perform(click())

        verify(mockRouter).goTo(Destination.Comments(1))
    }
}