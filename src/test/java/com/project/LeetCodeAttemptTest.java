package com.project;

import com.project.attempt.LeetCodeAttempt;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LeetCodeAttemptTest {

    @Test
    public void minimumOperationsToMakeBinaryArrayElementsEqualToOneITest() {

        int[] nums1 = { 0, 1, 1, 1,0, 0 };
        assertEquals(3, LeetCodeAttempt.minimumOperationsToMakeBinaryArrayElementsEqualToOneI(nums1));

        int[] nums2 = { 0, 1, 1, 1 };
        assertEquals(-1, LeetCodeAttempt.minimumOperationsToMakeBinaryArrayElementsEqualToOneI(nums2));

    }

}
