public class MinMax {
    public static int comparisons = 0;
    public static int k = 0;
    /*
     * Determine the min and max values of the input array a in the range ub to ub (inclusive)
     * using a Floor(n/2) and Ceiling (n/2)split.
     *
     * Input:
     * lb – index of lower bound in array a
     * ub – index of upper bound in array a
     * a  - array of values to analyze
     *
     * Output:
     * A Pair object containing the min value in its alpha variable and the max value in its omega variable.
     */
    public static Pair mmA(int lb, int ub, int[] a) {
        int n = ub - lb;
        if( n == 0){
            return new Pair(a[lb], a[lb]);
        }
        if( n == 1){
            return new Pair(Math.min(a[lb], a[ub]), Math.max(a[lb], a[ub]));
        }
        int middle = (ub + lb)/2;
        Pair left = mmA(lb, middle, a);
        Pair right = mmA(middle +1, ub, a);
        return new Pair(Math.min(left.alpha, right.alpha), Math.max(left.omega, right.omega));

    }

    /*
     * Determine the min and max values of the input array a in the range ub to ub (inclusive)
     * using a better split in order to achieve exactly Ceiling(3n/2 –2) comparisons.
     *
     * Input:
     * lb – index of lower bound in array a
     * ub – index of upper bound in array a
     * a  - array of values to analize
     *
     * Output:
     * A Pair object containing the min value in its alpha variable and the max value in its omega variable.
     */
    public static Pair mmB(int lb, int ub, int[] a) {
        int n = ub - lb + 1;
        //System.out.format("lb: %d, ub: %d, n = %d highest 2 = %d\n", lb, ub, n,  Integer.highestOneBit(n-1));
        if(n == 1 ) return new Pair(a[lb], a[ub]);
        if((n & (n - 1)) == 0 ) return mmB2Power(lb, ub, a);

        //if (k++ > 20) return null;

        Pair left = mmB2Power(lb, lb + Integer.highestOneBit(n-1) - 1, a);
        Pair right = mmB(lb + Integer.highestOneBit(n-1), ub, a);
        comparisons +=2;
        System.out.format("comparisons %d \n", comparisons);
        return new Pair(Math.min(left.alpha, right.alpha), Math.max(left.omega, right.omega));
    }

    public static Pair mmB2Power(int lb, int ub, int[] a){
        int n = ub - lb + 1;
        //System.out.format("lb: %d, ub: %d, n = %d \n", lb, ub, n);

        //if (k++ > 20) return null;
        if( n == 2){
            comparisons += 1;
            System.out.format("comparisons %d\n", comparisons);
            return a[lb] < a[ub] ? new Pair(a[lb], a[ub]) : new Pair(a[ub], a[lb]);
        }
        int middle = (ub + lb)/2;
        Pair left = mmB2Power(lb, middle, a);
        Pair right = mmB2Power(middle +1, ub, a);
        comparisons +=2;
        System.out.format("comparisons %d \n", comparisons);
        return new Pair(Math.min(left.alpha, right.alpha), Math.max(left.omega, right.omega));
    }

    public static void main(String[] args) {
        int[] array = new int[]{4, 7, 2}; Pair expected = new Pair(2, 7);
        //int[] array = new int[]{4, 7, 2, 10, 345, 28, 1, 5, 0, -10, -4098, 2, 34, 7, 2, 10, 345, 28, 1, 5, 0, -10, -4098, 23}; Pair expected = new Pair(-4098, 345);
        //int[] array = new int[]{4, 7, 2, 10, 345, 28, 1, 5}; Pair expected = new Pair(1, 345);
        //int[] array = new int[]{4, 7, 2, 10}; Pair expected = new Pair(2, 10);
        //int[] array = new int[]{4, 7, 2, 10, 345, 28, 1, 5, 0, -10, -4098, 23, 0}; Pair expected = new Pair(-4098, 345);
        //int[] array = new int[]{4, 7, 2, 10, 345, 28, 1, 5, 200, 20, 13, 10, 1}; Pair expected = new Pair(1, 345);

        Pair actual = MinMax.mmB(0, array.length-1, array);
        if (actual.alpha != expected.alpha) {
            System.out.println("Min value incorrect.  Expected: "+expected.alpha+" but got: "+actual.alpha);
        } else if (actual.omega != expected.omega) {
            System.out.println("Max value incorrect.  Expected: "+expected.omega+" but got: "+actual.omega);
        } else {
            System.out.println("Test Passed");
        }
    }

}
