package com.project.attempt;

public class LeetCodeAttempt {

    public static void main(String[] args) {

        int[] nums1 = { 0, 1, 1, 1,0, 0 };
        System.out.println(minimumOperationsToMakeBinaryArrayElementsEqualToOneI(nums1));

        int[] nums2 = { 0, 1, 1, 1 };
        System.out.println(minimumOperationsToMakeBinaryArrayElementsEqualToOneI(nums2));

    }

    public static int minimumOperationsToMakeBinaryArrayElementsEqualToOneI(int[] nums) {

        int[] currentMinOperations = { -1 };

        return flipOperation(nums, 0, 0, currentMinOperations);

    }

    private static int flipOperation(int[] nums, int numberOfFlips, int previousIndex, int[] currentMinOperations) {

        if (currentMinOperations[0] != -1 && currentMinOperations[0] < numberOfFlips) {
            return -1;
        } else if (numberOfFlips > nums.length * nums.length) {
            return -1;
        }

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == 0) { break; }

            if (i == nums.length - 1) {
                currentMinOperations[0] = numberOfFlips;
                return numberOfFlips;
            }

        }

        int minOperations = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length - 2; i++) {

            if (i == previousIndex) {
                continue;
            }

            int[] newNums = new int[nums.length];
            System.arraycopy(nums, 0, newNums, 0, nums.length);

            flip(newNums, i, i + 2);

            int flips = flipOperation(newNums, numberOfFlips + 1, i, currentMinOperations);

            if (flips != -1 && flips < minOperations) {
                minOperations = flips;
            }

        }

        if (minOperations == Integer.MAX_VALUE) {
            minOperations = -1;
        }

        return minOperations;

    }

    private static int[] flip(int[] nums, int startIndex, int endIndex) {

        for (int i = startIndex; i <= endIndex; i++) {

            if (nums[i] == 1) { nums[i] = 0; }
            else if (nums[i] == 0) { nums[i] = 1; }

        }

        return nums;

    }

}
