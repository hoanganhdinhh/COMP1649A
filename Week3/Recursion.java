package Labs.Week3;

public class Recursion {
    public static long factorial(int n){
        n = 5;
        if (n <= 0){
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
}