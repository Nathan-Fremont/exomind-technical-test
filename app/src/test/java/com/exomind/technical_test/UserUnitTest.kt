package com.exomind.technical_test

import android.os.Build
import com.exomind.technical_test.domain.Repository
import com.exomind.technical_test.domain.di.koinDomainModules
import com.exomind.technical_test.repository.di.koinRepositoryModules
import com.exomind.technical_test.repository.local.room.RoomDataSource
import com.exomind.technical_test.ui.di.koinUiModules
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.koin.core.context.loadKoinModules
import org.koin.java.KoinJavaComponent.inject
import org.koin.test.AutoCloseKoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class UserUnitTest : AutoCloseKoinTest() {

    private val localDataSource: RoomDataSource by inject(RoomDataSource::class.java)
    private val repository: Repository by inject(Repository::class.java)

    @Before
    fun setup() {
        loadKoinModules(koinDomainModules)
        loadKoinModules(koinRepositoryModules)
        loadKoinModules(koinUiModules)

        println("SETUP")
    }

    @Test
    fun step1_noUsersInDatabase() {
        println("step1_noUsersInDatabase")

        val localUsers = localDataSource.userDao().getAllUsers()
            .subscribeOn(Schedulers.io())
            .blockingGet()

        println("step1_noUsersInDatabase = ${localUsers.size}")
        assert(localUsers.isEmpty())
    }

    @Test
    fun step2_getUsers() {
        println("step2_getUsers")
        val users = repository.getUsersList()
            .subscribeOn(Schedulers.io())
            .blockingGet()

        println("step2_getUsers = ${users.size}")
        assert(users.isNotEmpty())
    }

    @Test
    fun step3_gotUsersInDatabase() {
        println("step3_gotUsersInDatabase")
        val localUsers = localDataSource.userDao().getAllUsers()
            .subscribeOn(Schedulers.io())
            .blockingGet()

        println("step3_gotUsersInDatabase = ${localUsers.size}")
        assert(localUsers.isNotEmpty())
    }
}