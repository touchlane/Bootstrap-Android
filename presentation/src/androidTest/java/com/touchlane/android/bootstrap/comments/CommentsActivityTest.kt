package com.touchlane.android.bootstrap.comments

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.touchlane.android.bootstrap.R
import com.touchlane.android.bootstrap.Router
import com.touchlane.android.bootstrap.atPosition
import com.touchlane.android.bootstrap.domain.Result
import com.touchlane.android.bootstrap.domain.comments.Comment
import com.touchlane.android.bootstrap.domain.comments.CommentsInteractor
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@LargeTest
@RunWith(AndroidJUnit4::class)
class CommentsActivityTest {

    private val testComment1 = Comment(
        1,
        1,
        "name1",
        "body1"
    )
    private val testComments = listOf(testComment1)

    private val testCommentsInteractor = mock(CommentsInteractor::class.java)
    private val mockRouter = mock(Router::class.java)

    @get:Rule
    var activityRule: ActivityTestRule<CommentsActivity> =
        object : ActivityTestRule<CommentsActivity>(CommentsActivity::class.java, true, false) {

            override fun beforeActivityLaunched() {
                super.beforeActivityLaunched()
                loadKoinModules(module {

                    single<CommentsInteractor>(override = true) {
                        testCommentsInteractor
                    }

                    scope<CommentsActivity> {
                        scoped<Router>(override = true) {
                            mockRouter
                        }
                    }
                })
            }
        }

    @Before
    fun setup() = runBlocking {
        `when`(testCommentsInteractor.commentsToPost(1)).thenReturn(Result.Success(testComments))
        Unit
    }

    @Test
    fun testCommentsShown() {
        activityRule.launchActivity(
            Intent().apply {
                putExtra(CommentsActivity.EXTRA_POST_ID, 1)
            }
        )

        onView(withId(R.id.comments)).check(
            ViewAssertions.matches(
                atPosition(
                    0,
                    Matchers.allOf(
                        hasDescendant(
                            Matchers.allOf(
                                withId(R.id.name),
                                withText("name1")
                            )
                        ),
                        hasDescendant(
                            Matchers.allOf(
                                withId(R.id.body),
                                withText("body1")
                            )
                        )
                    )
                )
            )
        )
    }
}