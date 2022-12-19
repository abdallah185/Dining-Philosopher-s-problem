import java.util.concurrent.Semaphore;
public class readgrades implements Runnable{
    static volatile int readerCount = 0;
    static Semaphore mutex = new Semaphore(1);
    @Override
    public void run() {
        try {
            //
            mutex.acquire(); // wait
            synchronized (this)
            {
                readerCount++;
            }

            if (readerCount == 1) updategrades.rw_mutex.acquire();
            // هنا بياخد مفتاح الدخول من الwriter ومش بيسمحله الدخول مع ال reader و يبقي كده مفيش عندنا deadlock
            mutex.release();// signal

            System.out.println("Thread "+Thread.currentThread().getName() + " is READING");
            Thread.sleep(1500);
            System.out.println("Thread "+Thread.currentThread().getName() + " has FINISHED READING");

            mutex.acquire();
            synchronized (this){
                readerCount--;
            }
            // هنا بسيب مفتاح الدخول لل writer و بمنع  اي reader من انه يخش معاه وبرضوا كده الdeadlock مش موجوده
            if (readerCount == 0) updategrades.rw_mutex.release();
            mutex.release();

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }


}
