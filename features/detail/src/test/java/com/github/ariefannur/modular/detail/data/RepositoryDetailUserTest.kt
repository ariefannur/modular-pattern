package com.github.ariefannur.modular.detail.data

import com.github.ariefannur.modular.detail.data.repository.DetailUserRepositoryImpl
import com.github.ariefannur.modular.detail.domain.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RepositoryDetailUserTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val local: LocalDetailUser = spyk()
    private val remote: RemoteDetailUser = spyk()
    private val repository: DetailUserRepository = DetailUserRepositoryImpl(remote, local)

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
    fun `should repository getDetailUser got data DetailUser when state under test`() {
        testDispatcher.runBlockingTest {
            val name = "jhony"
            coEvery { remote.requestDetailUser(name) } returns detailUser
            coEvery { local.getDetailUser(name) } returns detailUser

            var result: DetailUser? = null
            repository.getDetailUser(name).collect {
                result = it
            }

            coVerify { remote.requestDetailUser(name) }
            coVerify { local.getDetailUser(name) }
            coVerify { local.cacheDetailUser(detailUser) }

            Assert.assertEquals(result, detailUser)
        }
    }

    @Test
    fun `should repository getRepositoryUser got data List Repository  when state under test`() {
        testDispatcher.runBlockingTest {
            val name = "jhony"
            coEvery { remote.requestRepository(name) } returns listRepo
            coEvery { local.getUserRepository(name) } returns listRepo

            var result: List<Repository> = listOf()
            repository.getUserRepository(name).collect {
                result = it
            }

            coVerify { remote.requestRepository(name) }
            coVerify { local.getUserRepository(name) }
            coVerify { local.cacheUserRepository(name, result) }

            Assert.assertEquals(result, listRepo)
        }
    }

}