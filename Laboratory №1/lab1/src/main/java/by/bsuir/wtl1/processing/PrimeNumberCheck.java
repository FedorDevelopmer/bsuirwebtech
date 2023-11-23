package by.bsuir.wtl1.processing;


public class PrimeNumberCheck {
    public static boolean isPrime(long number){
        if(number < 2){
            return false;
        }
        double numberRootDouble = Math.sqrt(number);
        long numberRoot = Math.round(numberRootDouble);
         for (long i = 2; i <= numberRoot; i++) {
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }
}
