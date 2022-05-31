package com.github.ariefannur.modular.features.search.data

import com.github.ariefannur.modular.features.search.data.repository.SearchRepositoryImpl
import com.github.ariefannur.modular.features.search.domain.LocalSearchUsers
import com.github.ariefannur.modular.features.search.domain.RemoteSearchUsers
import com.github.ariefannur.modular.features.search.domain.SearchRepository
import com.github.ariefannur.modular.features.search.domain.User
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

class RepositorySearchUserTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val local: LocalSearchUsers = spyk()
    private val remote: RemoteSearchUsers = spyk()
    private val repository: SearchRepository = SearchRepositoryImpl(remote, local)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val listUsers = listOf(
        User(name="Zhiwu Wang", username="wangzhiwubigdata", avatar="https://avatars.githubusercontent.com/u/10925085?v=4", description="A developer,bigdataer", address="Mars", email=""),
        User(name="WangBin", username="wang-bin", avatar="https://avatars.githubusercontent.com/u/785206?v=4", description="", address="Ningbo->Shanghai, China", email="wbsecg1@gmail.com"),
        User("jhony", "jhonyjj", "https://avatars.githubusercontent.com/u/785206?v=4", "", "sleman", "jhonyj@gmail.com")
    )

    @Test
    fun `should repository get list user when success response`() {
        testDispatcher.runBlockingTest {
            val name = "wang"
            coEvery { remote.requestSearchUsers(name) } returns listUsers
            coEvery { local.getCacheUsers(name) } returns listOf()

            var listResult: List<User> = listOf()
            repository.searchUsers(name).collect {
                listResult = it
            }

            coVerify { local.getCacheUsers(name) }
            coVerify { remote.requestSearchUsers(name) }
            coVerify { local.saveCacheUsers(listUsers) }

            Assert.assertEquals(listResult, listUsers)
        }
    }

}