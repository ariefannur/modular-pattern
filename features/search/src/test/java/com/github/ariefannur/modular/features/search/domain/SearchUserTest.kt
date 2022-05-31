package com.github.ariefannur.modular.features.search.domain

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

class SearchUserTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)
    private val repository: SearchRepository = mockk()
    private val searchUser: SearchUser = SearchUser(repository)

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
    fun `Should usecase SearchUser got data list user when state under test`() {
        testScope.launch {
            val name = "wang"
            coEvery { repository.searchUsers(name) } returns flowOf(listUsers)

            var listResult: List<User> = listOf()
            searchUser(name) {
                when(it) {
                    is DataState.Success -> listResult = it.result
                }
            }

            coVerify { repository.searchUsers(name) }
            Assert.assertEquals(listResult, listUsers)
        }
    }

}