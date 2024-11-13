package threads.runnable.synchronaized;

import interfaces.Vehicle;

public class PrintSynchronaizer {
        private Vehicle v;
        private volatile int current = 0;
        private Object lock = new Object();
        private boolean set = false;

        public PrintSynchronaizer(Vehicle v) {
            this.v = v;
        }

        public void printPrice() throws InterruptedException {
            double val;
            synchronized(lock) {
                double [] p = v.getPrices();
                if (!canPrintPrice()) throw new InterruptedException();
                while (!set)
                    lock.wait();
                val = p[current++];
                System.out.println("Print price: " + val);
                set = false;
                lock.notifyAll();
            }
        }

        public void printModel() throws InterruptedException {
            synchronized(lock) {
                String [] s = v.getNames();
                if (!canPrintModel()) throw new InterruptedException();
                while (set)
                    lock.wait();
                System.out.println("Print model: " + s[current]);
                set = true;
                lock.notifyAll();
            }
        }

        public boolean canPrintPrice() {
            return current < v.length();
        }

        public boolean canPrintModel() {
            return (!set && current < v.length()) || (set && current < v.length() - 1);
        }


}
