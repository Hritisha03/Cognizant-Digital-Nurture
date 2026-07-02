public class ExSeven {

    // Recursive method to calculate future value
    static double futureValue(double currentValue, double growthRate, int years) {

        // Base Case
        if (years == 0)
            return currentValue;

        // Recursive Call
        return futureValue(currentValue * (1 + growthRate), growthRate, years - 1);
    }

    public static void main(String[] args) {

        double currentValue = 10000;
        double growthRate = 0.10;   // 10%
        int years = 5;

        double predictedValue = futureValue(currentValue, growthRate, years);

        System.out.println("Current Value : ₹" + currentValue);
        System.out.println("Growth Rate   : " + (growthRate * 100) + "%");
        System.out.println("Years         : " + years);
        System.out.println("Future Value  : ₹" + predictedValue);
    }
}