package Dekker;

import java.util.Random;

public class MyThread extends Thread
{
    private volatile static boolean chce[] = {true, true};
    private volatile static char znaki[] = {'+', '-'};
    private volatile static int czyja_kolej = 0;
    private static Random r = new Random();
    private int nr;
    private boolean czySynchr;

    public MyThread(int nr, boolean czySynchr)
    {
        this.nr = nr;
        this.czySynchr = czySynchr;
    }

    private void sekcja_krytyczna(int powtorzenie)
    {
        System.out.print("Sekcja krytyczna watku: " + Thread.currentThread().getName() + ", nr powt.= " + powtorzenie + " -> ");
        for(int i = 0; i < 100; i++)
        {
            System.out.print(znaki[nr]);
        }
        System.out.println();
    }

    public void run()
    {
        if(czySynchr)
            dzialanieSynchr();
        else
            dzialanieNiesynch();
    }

    private void dzialanieSynchr()
    {
        for (int i = 0; i < 100; i++)
        {
            chce[nr] = true;
            int drugi = (nr+1)%2;

            while(chce[drugi])
            {
                if(czyja_kolej == drugi)
                {
                    chce[nr] = false;
                    while(czyja_kolej == drugi)
                    {
                        try
                        {
                            sleep(r.nextInt(10) + 1);
                        }
                        catch(Exception e)
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    chce[nr] = true;
                }
            }
            sekcja_krytyczna(i);
            czyja_kolej = drugi;
            chce[nr] = false;
        }

    }

    private void dzialanieNiesynch()
    {
        for(int j = 0; j < 5; j++)
        {
            try
            {
                sleep(r.nextInt(11));
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            sekcja_krytyczna(j);
        }
    }
}
