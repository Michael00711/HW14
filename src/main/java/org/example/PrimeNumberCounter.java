package org.example;

public class PrimeNumberCounter {
    public static void main(String[] args) {
        int[] numbers = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11}; // Здесь задается массив чисел

        int middleIndex = numbers.length / 2; // Индекс, разделяющий массив пополам

        PrimeCounterThread thread1 = new PrimeCounterThread(numbers, 0, middleIndex);
        PrimeCounterThread thread2 = new PrimeCounterThread(numbers, middleIndex, numbers.length);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int totalCount = thread1.getPrimeCount() + thread2.getPrimeCount();

        System.out.println("Total count of prime numbers: " + totalCount);
    }

    static class PrimeCounterThread extends Thread {
        private int[] numbers;
        private int startIndex;
        private int endIndex;
        private int primeCount; // Количество простых чисел

        public PrimeCounterThread(int[] numbers, int startIndex, int endIndex) {
            this.numbers = numbers;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        public int getPrimeCount() {
            return primeCount;
        }

        @Override
        public void run() {
            for (int i = startIndex; i < endIndex; i++) {
                if (isPrime(numbers[i])) {
                    primeCount++;
                }
            }
        }

        private boolean isPrime(int number) {
            if (number <= 1) {
                return false;
            }
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
