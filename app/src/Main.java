public class Main {
    public static void main(String[] args) {
     readgrades read = new readgrades();
     updategrades update = new updategrades();

        Thread t1 = new Thread(read);
        t1.setName("student1");
        Thread t2 = new Thread(read);
        t2.setName("DR");
        Thread t3 = new Thread(update);
        t3.setName("TA1");
        Thread t4 = new Thread(read);
        t4.setName("student2");
        Thread t5 = new Thread(update);
        t5.setName("TA2");
        Thread t6 = new Thread(update);
        t6.setName("TA3");
        Thread t7 = new Thread(read);
        t7.setName("student3");


        t1.start();
        t3.start();
        t2.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();

    }

}