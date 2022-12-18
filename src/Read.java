import java.util.concurrent.Semaphore;
//هنا عملت import علشان نشتغل بيها اسهل
public class Read implements Runnable {
    // هنا عملت المتغير ده علشان نعرف عدد الي هيخشوا يشفوا الداتا
    static volatile int readerCount = 0;
/*
هنا volatile علشان هـread و write في main memory
وكمان  الـ  threads اتحطت queue لما تكون ممنوع ليها الدخول و ده هنا بيمنع اي starvation
 */
    static Semaphore mutex = new Semaphore(1);
    // هنا عمل semaphore علشان اتحكم ف ال  readers الي داخلين
    @Override
    public void run() {
        try {
            //
            mutex.acquire(); // wait
            synchronized (this)
            {
            readerCount++;
            }

            if (readerCount == 1) Write.rw_mutex.acquire();
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
            if (readerCount == 0) Write.rw_mutex.release();
            mutex.release();

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}