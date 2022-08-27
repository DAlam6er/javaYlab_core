package com.ylab.hometasks.task01;

/*
    Заполните массив случайными числами
    и выведите максимальное, минимальное и среднее значение
 */
public class RandomNumbers
{
    public final static int LENGTH = 10;
    private final int[][] numbers = new int[LENGTH][LENGTH];

    public static void main(String[] args)
    {
        RandomNumbers randomNumbers = new RandomNumbers();
        randomNumbers.populate();
        // uncomment to print array content
        System.out.println(randomNumbers);
        System.out.println("max = " + randomNumbers.getMax());
        System.out.println("min = " + randomNumbers.getMin());
        System.out.printf("avg = %.3f\n", randomNumbers.getAverage());
    }

    /**
     * Populates an array with random numbers
     */
    public void populate()
    {
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                numbers[i][j] = MWCRandom.nextInt();
            }
        }
    }

    /**
     * Multiply-with-carry pseudorandom number generator class.
     * In practice, the most convenient application of MWC is to use 64-bit arithmetic,
     * and make the "base" (b) be half of that size, or 2<sup>32</sup>.
     * <pre> This then gives us the following properties:
     *      1) when we multiply by "a", the carry will end up in the top 32 bits,
     *         and the "new value of x" will end up in the lower 32 bits —
     *         hence, we don't need two separate variables;
     *      2) to divide by base (to get the carry), we simply shift 32 bits to the right;
     *      3) to perform mod b, we simply AND with 2<sup>32</sup>-1 (or cast to an int) to get the bottom 32 bits.
     * </pre>
     * A value of "a" is chosen so that both (ab - 1) and (ab - 1) / 2 are prime;
     * the period is then (ab - 1) / 2.
     */
    static class MWCRandom
    {
        private final static long a = 0xffffda61L;
        private static long x = System.nanoTime() & 0xffffffffL;

        /**
         * Multiply-with-carry pseudorandom number generator.
         * @return integer with a period of around 2^63
         */
        public static int nextInt()
        {
            x = (a * (x & 0xffffffffL)) + (x >>> 32);
            return (int) x;
        }
    }

    /**
     * Returns the maximum value of an array
     */
    public int getMax()
    {
        int max = numbers[0][0];
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if (max < numbers[i][j]) {
                    max = numbers[i][j];
                }
            }
        }
        return max;
    }

    /**
     * Returns the minimum value of an array
     */
    public int getMin()
    {
        int min = numbers[0][0];
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if (min > numbers[i][j]) {
                    min = numbers[i][j];
                }
            }
        }
        return min;
    }

    /**
     * Returns the average value of an array
     */
    public double getAverage()
    {
        long sum = 0;
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                sum += numbers[i][j];
            }
        }
        return (double) sum / (2 * LENGTH);
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < LENGTH; i++) {
            if (i > 0) sb.append(" ");
            sb.append("[");
            for (int j = 0; j < LENGTH; j++) {
                sb.append(String.format("%11d", numbers[i][j])).append(", ");
            }
            sb.replace(sb.length() - 2, sb.length(), "];\n");
        }
        sb.replace(sb.length() - 2, sb.length(), "]");
        return sb.toString();
    }
}
