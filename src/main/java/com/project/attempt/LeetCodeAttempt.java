package com.project.attempt;

public class LeetCodeAttempt {

    public static void main(String[] args) {

        int[] nums1 = { 0, 1, 1, 1,0, 0 };
        System.out.println(minimumOperationsToMakeBinaryArrayElementsEqualToOneI(nums1));

        int[] nums2 = { 0, 1, 1, 1 };
        System.out.println(minimumOperationsToMakeBinaryArrayElementsEqualToOneI(nums2));

    }

    // This method returns the minimum number of operations needed to turn an array of binary
    // elements into a contiguous block of '1'. If this can't be done, returns -1 instead.
    public static int minimumOperationsToMakeBinaryArrayElementsEqualToOneI(int[] nums) {

        // We use this array to keep track of what the current record is for the minimum
        // number of operations that fulfills the challenge specifications. We use an array
        // as a pseudo global variable whose value will be consistent across all recursive
        // calls of our helper method. We use -1 as a default value when no record has been
        // achieved yet.
        int[] currentMinOperations = { -1 };

        // We call a recursive helper method that will test flipping the array in various
        // ways to see if there is any combination that leads to all elements becoming '1'.
        return flipOperation(nums, 0, 0, currentMinOperations);

    }

    // A recursive helper method that tests all possible combinations of flip operations.
    // int numberOfFlips keeps track of the number of flips performed so far, while
    // int previousIndex keeps track of the starting index of the previous flip, mostly
    // so that we avoid flipping the exact same positions as the previous flip.
    private static int flipOperation(int[] nums, int numberOfFlips, int previousIndex, int[] currentMinOperations) {

        // If the current number of flips surpasses the current record for minimum operations
        // needed to turn the array into all '1's, then we immediately return -1 as any further logic
        // is pointless as we would only want values that are lower than the current minimum operations.
        // We also return -1 if the number of flips surpasses the square of the length of int[] nums.
        // This is to avoid stack overflow as the flip combinations could theoretically go endlessly,
        // and to put a hard cap to how much recursion can be done. If we have tested all possible flip
        // combinations up to that amount, it is reasonable to assume that we would have tried every
        // non-repeating combination of flips and that any further flips would just result in the repeat
        // of some pattern of flips that have already been done. If we have not found a sequence that
        // leads to all elements becoming '1' despite testing all combination of flips up to the square
        // of the length of int[] nums, then it is reasonable to assume that there is no combination of
        // flips that will satisfy the challenge specifications at that point.
        if (currentMinOperations[0] != -1 && currentMinOperations[0] < numberOfFlips) {
            return -1;
        } else if (numberOfFlips > nums.length * nums.length) {
            return -1;
        }

        // Here, we test if the current int[] nums has achieved all of its elements
        // becoming '1' yet or not. If it has, we immediately return the number of
        // flips that it took to reach this point, and update the current record for
        // the minimum number of operations needed.
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == 0) { break; }

            if (i == nums.length - 1) {

                if (currentMinOperations[0] == -1 || numberOfFlips < currentMinOperations[0]) {
                    currentMinOperations[0] = numberOfFlips;
                }

                return numberOfFlips;

            }

        }

        // We will use int minOperations in our recursive logic to compare various combinations
        // of flips and see which of them leads to the lowest number of operations needed to turn
        // all the array's elements into a '1' value. Since we are comparing minimum values, we set
        // int minOperations' default value to a large enough value that won't interfere with our logic.
        int minOperations = Integer.MAX_VALUE;

        // We perform a for-loop that will test every possible flip.
        for (int i = 0; i < nums.length - 2; i++) {

            // If int i is the same value as the previous index that we flipped from, we skip ahead
            // as performing a flip at this spot would simply undo our previous flip and accomplish
            // nothing in particular.
            if (i == previousIndex) {
                continue;
            }

            // Otherwise, we create a copy of int[] nums in order to avoid any issues arising
            // from the recursive calls of our method and the modifications we perform.
            int[] newNums = new int[nums.length];
            System.arraycopy(nums, 0, newNums, 0, nums.length);

            // We call a helper method to perform a flip operation at this index. It will flip
            // the element at the current index, as well as the elements in the next two indexes,
            // as the challenge specification clearly states that we absolutely must flip three
            // elements in a row during any given flip operation.
            flip(newNums, i, i + 2);

            // We then perform a recursive call with the flipped array, to see if it will eventually
            // return a valid value that leads to all elements being flipped to a '1' value.
            // If such a value doesn't exist, int flips will inevitably return -1, but if such a value
            // does exist, then it will return a positive value larger than 0.
            // During each recursive call, we make sure to increase the number of flips by 1.
            int flips = flipOperation(newNums, numberOfFlips + 1, i, currentMinOperations);

            // If a valid value does exist, and the number of flips needed is less than the current
            // record stored in int minOperations, we update int minOperations with that value.
            if (flips != -1 && flips < minOperations) {
                minOperations = flips;
            }

        }

        // If we never end up finding any valid values in all the recursions performed, then
        // the value of int minOperations should still remain as the default value we initially
        // set it as. In this case, we will then manually set it to -1 to indicate there is no
        // combination of flips that can satisfy the challenge specifications.
        if (minOperations == Integer.MAX_VALUE) {
            minOperations = -1;
        }

        // We then return int minOperations as it is.
        return minOperations;

    }

    // A helper method that flips every element from index startIndex to index endIndex (inclusive).
    private static int[] flip(int[] nums, int startIndex, int endIndex) {

        for (int i = startIndex; i <= endIndex; i++) {

            if (nums[i] == 1) { nums[i] = 0; }
            else if (nums[i] == 0) { nums[i] = 1; }

        }

        return nums;

    }

}
