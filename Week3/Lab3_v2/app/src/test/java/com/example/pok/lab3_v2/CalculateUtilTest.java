package com.example.pok.lab3_v2;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculateUtilTest {

    @Test
    public void sumTwoNumber() {

        assertEquals(3,CalculateUtil.sumTwoNumber(1,2));

    }

    @Test
    public void divideTwoNumber() {

        assertNotEquals(1, CalculateUtil.divideTwoNumber(1, 0));

    }
}