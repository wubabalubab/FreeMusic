package com.example.freemusic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        int a=0b11; int b=0b01;
        System.out.println(a&b);
        assertEquals(4, 2 + 2);
    }
}