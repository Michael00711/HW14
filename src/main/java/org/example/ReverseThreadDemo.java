import java.util.Stack;

public class ReverseThreadDemo {
    public static void main(String[] args) {
        Stack<Thread> threadStack = new Stack<>();

        for (int i = 0; i < 50; i++) {
            Thread thread = new Thread(new MyRunnable(i));
            threadStack.push(thread);
        }

        while (!threadStack.isEmpty()) {
            Thread thread = threadStack.pop();
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All threads have finished executing.");
    }

    static class MyRunnable implements Runnable {
        private int threadNumber;

        public MyRunnable(int threadNumber) {
            this.threadNumber = threadNumber;
        }

        @Override
        public void run() {
            System.out.println("Hello from thread " + threadNumber);
        }
    }
}
