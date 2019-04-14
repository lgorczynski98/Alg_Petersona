package Dekker;

public class Test {

    public static void main(String[] args)
    {
        MyThread w1 = new MyThread(0, true);
        MyThread w2 = new MyThread(1, true);
        w1.setName("Dekker-1");
        w2.setName("Dekker-2");
        w1.start();
        w2.start();
        try
        {
            w1.join();
            w2.join();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}