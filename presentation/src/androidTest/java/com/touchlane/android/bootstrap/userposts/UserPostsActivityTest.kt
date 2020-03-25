package com.touchlane.android.bootstrap.userposts

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.touchlane.android.bootstrap.Destination
import com.touchlane.android.bootstrap.R
import com.touchlane.android.bootstrap.Router
import com.touchlane.android.bootstrap.atPosition
import com.touchlane.android.bootstrap.domain.Result
import com.touchlane.android.bootstrap.domain.userposts.UserPost
import com.touchlane.android.bootstrap.domain.userposts.UserPostsInteractor
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.mockito.Mockito.*

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

    @get:Rule
    var activityRule: ActivityTestRule<UserPostsActivity> =
        object : ActivityTestRule<UserPostsActivity>(
            UserPostsActivity::class.java,
            true,
            false
        ) {

            override fun beforeActivityLaunched() {
                super.beforeActivityLaunched()
                loadKoinModules(module {
                    single<UserPostsInteractor>(override = true) {
                        mockUserPostsInteractor
                    }

                    scope<UserPostsActivity> {
                        scoped<Router>(override = true) {
                            mockRouter
                        }
                    }
                })
            }
        }

    @Before
    fun setup() = runBlocking {
        `when`(mockUserPostsInteractor.userPosts()).thenReturn(Result.Success(testUserPosts))
        Unit
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