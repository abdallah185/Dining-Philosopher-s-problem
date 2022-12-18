import java.util.concurrent.Semaphore;

public class updategrades implements Runnable {
    static Semaphore rw_mutex = new Semaphore(1);
    @Override
    public void run() {
        try {
            rw_mutex.acquire();
            System.out.println("Thread "+Thread.currentThread().getName() + " is WRITING");
            Thread.sleep(2500);
            System.out.println("Thread "+Thread.currentThread().getName() + " has finished WRITING");
            rw_mutex.release();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
