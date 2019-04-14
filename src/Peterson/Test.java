package Peterson;

public class Test {

    public static void main(String[] args)
    {
        MyThread w1 = new MyThread(0, true);
        MyThread w2 = new MyThread(1, true);
        w1.setName("Peterson-1");
        w2.setName("Peterson-2");
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
