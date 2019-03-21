public class Main {

    public static void main(String[] args)
    {
        MyThread w1 = new MyThread(0);
        MyThread w2 = new MyThread(1);
        w1.setName("Watek - 1 ");
        w2.setName("Watek -  2");
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
