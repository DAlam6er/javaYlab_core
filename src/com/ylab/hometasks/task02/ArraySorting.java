package com.ylab.hometasks.task02;

// Отсортируйте массив [5,6,3,2,5,1,4,9]
public class ArraySorting
{
    public static void main(String[] args)
    {
        int[] nums = new int[]{5, 6, 3, 2, 5, 1, 4, 9};

        System.out.print("Array before being sorted: ");
        printArray(nums);
        System.out.print("Array after  being sorted: ");
        sort(nums);
        printArray(nums);
    }

    /**
     * Hoare quicksort implementation within the specified range
     * @param array input array to be sorted
     * @param leftBound sort start bound (inclusive)
     * @param rightBound sort end bound (inclusive)
     */
    public static void sort(int[] array, int leftBound, int rightBound)
    {
        if (array.length < 1) return;
        assert ((leftBound > -1) && (rightBound >=leftBound) && (rightBound < array.length)):
            "array bounds are set incorrectly";
        quicksort(array, leftBound, rightBound);
    }

    /**
     * Sorting an entire array using Hoare quicksort
     * @param array array to be sorted
     */
    public static void sort(int[] array)
    {
        sort(array, 0, array.length - 1);
    }

    /**
     * Hoare quicksort implementation within the specified range
     * @param array input array to be sorted
     * @param leftBound sort start bound (inclusive)
     * @param rightBound sort end bound (inclusive)
     */
    private static void quicksort(int[] array, int leftBound, int rightBound)
    {
        int curLeft = leftBound;
        int curRight = rightBound;
        int comparand = array[(leftBound + rightBound) / 2];
        int temp;

        do {
            while ((array[curLeft] < comparand) && (curLeft < rightBound)) {
                curLeft++;
            }
            while ((comparand < array[curRight]) && (curRight > leftBound)) {
                curRight--;
            }

            if (curLeft <= curRight) {
                temp = array[curLeft];
                array[curLeft] = array[curRight];
                array[curRight] = temp;

                curLeft++;
                curRight--;
            }
        } while (curLeft <= curRight);

        if (leftBound < curRight) {
            quicksort(array, leftBound, curRight);
        }
        if (curLeft < rightBound) {
            quicksort(array, curLeft, rightBound);
        }
    }

    /**
     * Printing the contents of an array
     * @param array array to be printed
     */
    private static void printArray(int[] array)
    {
        StringBuilder sb = new StringBuilder("[");
        if (array.length == 0) {
            System.out.println();
            return;
        }
        for (int number : array) {
            sb.append(number).append(", ");
        }
        String res = sb.replace(sb.length() - 2, sb.length(), "]").toString();
        System.out.println(res);
    }
}
