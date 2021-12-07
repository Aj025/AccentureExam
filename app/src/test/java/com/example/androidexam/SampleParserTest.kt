package com.example.androidexam

import org.junit.Assert.*
import org.junit.Test

class SampleParserTest {


    @Test
    fun `test sample parcer success`(){
        val parser = SampleParser()
        assertEquals(parser.convertStringToInt("3") , 3)
    }

}