package com.kotlin.sphtech.network

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

class RetroRepositoryTest{
    lateinit var serviceInterface: ServiceInterface

    @Before
    fun setUp() {
        serviceInterface = mock()

    }

    @Test
    fun makeAPICall() {
        val query = "a807b7ab-6cad-4aa6-87d0-e283a7353a0f"
        val call = serviceInterface.getDataFromAPI(query)
        assertEquals(true,call.execute().isSuccessful)

    }


}