package com.github.ariefannur.modular.detail

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.github.ariefannur.modular.core.infra.Service
import com.github.ariefannur.modular.detail.presentation.DetailUserActivity
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailUserActivityTest {

    private val mockWebServer = MockWebServer()

    @get:Rule
    val activityRule = ActivityTestRule(DetailUserActivity::class.java, true, false)

    @Before
    fun setup() {
        mockWebServer.start(8080)
        IdlingRegistry.getInstance().register(
            OkHttp3IdlingResource.create(
                "okhttp",
                Service().createClient()
            ))
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testSuccessfulResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(
                        MockUserResponse.userDetailResponse
                    )
            }
        }

        activityRule.launchActivity(null)
    }

}