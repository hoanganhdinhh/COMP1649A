package Week4;

public class GetNumberOfStep {
    public static int getNumberOfSteps(int n) {
        int steps = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                steps++;
            }
        }
        return steps;
    }
}
