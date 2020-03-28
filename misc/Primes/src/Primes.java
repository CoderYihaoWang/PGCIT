public class Primes {
    public static void main(String[] args) {
        primes(Integer.parseInt(args[0]));
    }
    private static void primes(int n) {
        try {
            divide(n + "\0".repeat(n - 1).charAt(0), n - 1);
            primes(n - 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("The prime numbers are:");
        } catch (ArithmeticException e) {
            primes(n - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println(e);
            n = (new int[n])[n];
        }
    }
    private static void divide(int a, int b) {
        try {
            divide(a + (new int[b - 1])[0], b - 1 + 0 / (a % b));
        } catch (ArrayIndexOutOfBoundsException e) {}
    }
}