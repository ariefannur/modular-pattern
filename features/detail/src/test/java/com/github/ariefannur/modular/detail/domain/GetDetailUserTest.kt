package com.github.ariefannur.modular.detail.domain

import com.github.ariefannur.modular.core.base.DataState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetDetailUserTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)
    private val repository: DetailUserRepository = mockk()
    private val getDetailUser: GetDetailUser = GetDetailUser(repository)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val detailUser = DetailUser(
        "jhony",
        "jhonyjj",
        "",
        "",
        "sleman",
        "jhony@gmail.com",
        1,
        12
    )

    @Test
    fun `should UseCase GetDetailUser got data DetailUser when state under test`() {
        testScope.launch {
            val name = "jhony"
            coEvery { repository.getDetailUser(name) } returns flowOf(detailUser)

            var result: DetailUser? = null
            getDetailUser(name) {
                when(it) {
                    is DataState.Success -> result = it.result
                }
            }

            coVerify { repository.getDetailUser(name) }

            Assert.assertEquals(result, detailUser)
        }
    }

}