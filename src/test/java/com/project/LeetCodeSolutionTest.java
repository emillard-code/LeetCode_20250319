package com.project;

import com.project.solution.LeetCodeSolution;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LeetCodeSolutionTest {

    @Test
    public void minOperationsTest() {

        int[] nums1 = { 0, 1, 1, 1,0, 0 };
        assertEquals(3, LeetCodeSolution.minOperations(nums1));

        int[] nums2 = { 0, 1, 1, 1 };
        assertEquals(-1, LeetCodeSolution.minOperations(nums2));

    }

}
