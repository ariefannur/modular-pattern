package com.github.ariefannur.modular.detail.domain

import com.github.ariefannur.modular.core.base.DataState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetUserReposTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)
    private val repository: DetailUserRepository = mockk()
    private val getUserRepo: GetUserRepo = GetUserRepo(repository)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val listRepo = listOf(
        Repository(
            1,
            "Android repo",
            "",
            12,
            "1 month ago"
        )
    )

    @Test
    fun `should UseCase GetUserRepo got data List Repository when state under test`() {
        testScope.launch {
            val name = "jhony"
            coEvery { repository.getUserRepository(name) } returns flowOf(listRepo)

            var result: List<Repository> = listOf()
            getUserRepo(name) {
                when(it) {
                    is DataState.Success -> result = it.result
                }
            }

            coVerify { repository.getUserRepository(name) }

            Assert.assertEquals(result, listRepo)
        }
    }
}