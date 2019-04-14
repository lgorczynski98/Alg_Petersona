package Lamport;

public class Test {

    public static void main(String[] args)
    {
        MyThread w1 = new MyThread(0, true);
        MyThread w2 = new MyThread(1, true);
        MyThread w3 = new MyThread(2, true);
        MyThread w4 = new MyThread(3, true);
        MyThread w5 = new MyThread(4, true);
        w1.setName("Lamport-1");
        w2.setName("Lamport-2");
        w3.setName("Lamport-3");
        w4.setName("Lamport-4");
        w5.setName("Lamport-5");
        w1.start();
        w2.start();
        w3.start();
        w4.start();
        w5.start();
        try
        {
            w1.join();
            w2.join();
            w3.join();
            w4.join();
            w5.join();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
