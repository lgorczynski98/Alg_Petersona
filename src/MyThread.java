public class MyThread extends Thread
{
    public static Integer K[] = {0,0};
    public static Integer czyja_kolej = 0;
    public Integer i;

    public MyThread(Integer i)
    {
        this.i = i;
    }


    public void sekcja_krytyczna()
    {
        System.out.println(Thread.currentThread().getName() + " komunikat 1");
        System.out.println(Thread.currentThread().getName() + " komunikat  2");
        System.out.println(Thread.currentThread().getName() + " komunikat   3");
    }

    public void run()
    {
        for(int j = 0; j < 5; j++)
        {
            K[i] = 0;
            int drugi = (i+1)%2;
            czyja_kolej = drugi;
            while(K[drugi] == 0 && czyja_kolej == drugi)
            {
                try
                {
                    sleep(5);
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
            sekcja_krytyczna();
            K[i] = 1;
        }
    }

    /*public void run()
    {
        for(int j = 0; j < 5; j++)
        {
            sekcja_krytyczna();
        }
    }*/
}
