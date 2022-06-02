package com.github.ariefannur.modular.features.search.presentation

import com.github.ariefannur.modular.core.base.DataState
import com.github.ariefannur.modular.features.search.domain.SearchUser
import com.github.ariefannur.modular.features.search.domain.User
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SearchViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private val searchUser: SearchUser = mockk()
    private val viewModel: SearchViewModel by lazy {  SearchViewModel(searchUser) }

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
    fun `doSearchUsers should get list of user when state under test`() = runBlockingTest {
        val callbackSlot = slot<DataState<List<User>>.() -> Unit>()
        val username = "name"
        coEvery {
            searchUser(username, callback = capture(callbackSlot))
        } answers  {
            val fakeData = DataState.Success(
                listUsers
            )
            callbackSlot.captured(fakeData)
        }

        viewModel.doSearchUsers(username)
        val result = mutableListOf<SearchState>()
        val job = launch {
            viewModel.listUser.toList(result)
        }
        coVerify { searchUser(username, callback = capture(callbackSlot)) }
        Assert.assertEquals(result.first(), SearchState.Success(listUsers))
        job.cancel()
    }
}