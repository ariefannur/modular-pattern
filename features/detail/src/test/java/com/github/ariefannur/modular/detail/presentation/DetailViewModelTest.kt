package com.github.ariefannur.modular.detail.presentation

import com.github.ariefannur.modular.core.base.DataState
import com.github.ariefannur.modular.detail.domain.DetailUser
import com.github.ariefannur.modular.detail.domain.GetDetailUser
import com.github.ariefannur.modular.detail.domain.GetUserRepo
import com.github.ariefannur.modular.detail.domain.Repository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val getUserDetail: GetDetailUser = mockk()
    private val getUserRepo:  GetUserRepo = mockk()
    private val viewModel: DetailUserViewModel by lazy {  DetailUserViewModel(getUserDetail, getUserRepo) }

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
    fun `getDetailUser should return data detail user and list repository when state under test`() = runBlockingTest {
        val username = "name"
        val callbackSlot = slot<DataState<DetailUser>.() -> Unit>()
        val callbackSlotRepo = slot<DataState<List<Repository>>.() -> Unit>()
        coEvery {
            getUserDetail(username, callback = capture(callbackSlot))
        } answers  {
            val fakeData = DataState.Success(
                detailUser
            )
            callbackSlot.captured(fakeData)
        }

        coEvery {
            getUserRepo(username, callback = capture(callbackSlotRepo))
        } answers  {
            val fakeData = DataState.Success(
               listRepo
            )
            callbackSlotRepo.captured(fakeData)
        }

        viewModel.getDetailUser(username)
        val result = mutableListOf<UserDetailState>()
        val resultRepo = mutableListOf<UserDetailRepoState>()

        val job = async {
            viewModel.detailUser.toList(result)
        }

        val job2 = async {
            viewModel.listRepo.toList(resultRepo)
        }

        coVerify { getUserDetail(username, callback = callbackSlot.captured) }
        coVerify { getUserRepo(username, callback = callbackSlotRepo.captured) }
        Assert.assertEquals(result.first(), UserDetailState.Success(detailUser))
        Assert.assertEquals(resultRepo.first(), UserDetailRepoState.Success(listRepo))

        job.cancel()
        job2.cancel()
    }
}