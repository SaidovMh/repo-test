package TestPac;

public class Main {
    private static Object monitor = new Object();
    private static final int repetitionsQuantity = 5;
    private static volatile char lastLetter = 'C';

    public static void main(String[] args) {
        LetterPrinterThread threadFirst  = new LetterPrinterThread('C', 'A');
        LetterPrinterThread threadSecond  = new LetterPrinterThread('A', 'B');
        LetterPrinterThread threadThird = new LetterPrinterThread('B', 'C');

        threadFirst.start();
        threadSecond.start();
        threadThird.start();
    }

    private static class LetterPrinterThread extends Thread {
        private char before;
        private char after;

        public LetterPrinterThread(char before, char after) {
            this.before = before;
            this.after = after;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < repetitionsQuantity; i++) {
                    synchronized (monitor) {
                        while (lastLetter != before) {
                            monitor.wait();
                        }
                        System.out.print(after);
                        lastLetter = after;
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
