public class pucekrad {


    public static long factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n-1);
    }

    public static long factorialI(int n) {
        int ret = 1;
        for (int i = 2; i <= n; i++) {
            ret*=i;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(pucekrad.factorial(5));
        System.out.println(pucekrad.factorialI(5));
    }
}
