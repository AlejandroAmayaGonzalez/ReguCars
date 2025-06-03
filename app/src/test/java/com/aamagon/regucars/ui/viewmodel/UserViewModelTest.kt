package com.aamagon.regucars.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aamagon.regucars.domain.GetUserUseCase
import com.aamagon.regucars.domain.model.User
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {
    @RelaxedMockK
    private lateinit var getUserUseCase: GetUserUseCase

    private lateinit var userViewModel: UserViewModel
    private val dispatcher = StandardTestDispatcher()

    // Create a rule to update livedata immediately while the test is running
    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `create viewmodel, get data and set it`() = runBlocking {
        // Given
        val user = User("foto1","man21","manolo@example.com","manolo","21/06/2000","España")
        coEvery { getUserUseCase() } returns user

        // When
        userViewModel = UserViewModel(getUserUseCase)
        // Keep running the coroutine
        dispatcher.scheduler.advanceUntilIdle()

        // Then
        assert(userViewModel.user.value == user)
    }
}