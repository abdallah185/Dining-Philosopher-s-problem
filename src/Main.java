public class Main {
    public static void main(String[] args) {
        //Shared message object between Reader and Writer threads.
        Read read = new Read();
        Write write = new Write();

        Thread t1 = new Thread(read);
        t1.setName("thread1");
        Thread t2 = new Thread(read);
        t2.setName("thread2");
        Thread t3 = new Thread(write);
        t3.setName("thread3");
        Thread t4 = new Thread(read);
        t4.setName("thread4");
        Thread t5 = new Thread(write);
        t5.setName("thread5");
        Thread t6 = new Thread(write);
        t6.setName("thread6");
        Thread t7 = new Thread(read);
        t7.setName("thread7");


        t1.start();
        t3.start();
        t2.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();

    }
}