package com.project.solution;

import java.util.ArrayDeque;
import java.util.Deque;

public class LeetCodeSolution {

    public static void main(String[] args) {

        int[] nums1 = { 0, 1, 1, 1,0, 0 };
        System.out.println(minOperations(nums1));

        int[] nums2 = { 0, 1, 1, 1 };
        System.out.println(minOperations(nums2));

    }

    public static int minOperations(int[] nums) {

        Deque<Integer> flipQueue = new ArrayDeque<>();
        int count = 0;

        for (int i = 0; i < nums.length; i++) {

            while (!flipQueue.isEmpty() && i > flipQueue.peekFirst() + 2) {
                flipQueue.pollFirst();
            }

            if ((nums[i] + flipQueue.size()) % 2 == 0) {

                if (i + 2 >= nums.length) {
                    return -1;
                }

                count++;
                flipQueue.offerLast(i);

            }

        }

        return count;

    }

}
