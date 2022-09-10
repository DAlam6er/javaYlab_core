package com.ylab.hometasks.task03;

import java.util.*;

/*
Task2

    [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
 */
public class ArrayPair
{
    public static void main(String[] args)
    {
        int[] nums = new int[]{3, 4, 2, 7};
        int sumToFind = 10;
        int[] pair = findFirstPair(nums, sumToFind).orElse(null);
        if (pair != null) {
            System.out.println(Arrays.toString(pair));
        } else {
            System.out.println("Pair not found");
        }
    }

    /**
     * Find a pair with the given sum in an array
     * @param nums an array of integers to search for the pair
     * @param sumToFind the desired amount for the pair
     * @return an Optional, describing the first pair of int numbers found
     */
    private static Optional<int[]> findFirstPair(int[] nums, int sumToFind)
    {
        if (nums == null) return Optional.empty();

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int desiredPair = sumToFind - nums[i];

            if (map.containsKey(desiredPair)) {
                int[] res = new int[] {nums[map.get(desiredPair)], nums[i]};
                return Optional.of(res);
            }
            map.put(nums[i], i);
        }

        return Optional.empty();
    }
}
