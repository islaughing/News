package com.example.news;

import android.util.Log;

import com.example.news.laughing.utils.AnalysisSign;

import org.junit.Test;

import java.lang.System;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        System.out.print(AnalysisSign.getsign(1,3,006513e01bd344fca03610d1fd0145f0));
    }
}