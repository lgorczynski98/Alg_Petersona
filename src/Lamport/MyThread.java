package Lamport;

import java.util.Random;

public class MyThread extends Thread
{
    private volatile static boolean wybieranie[] = {false, false, false, false, false};
    private volatile static char znaki[] = {'+', '-', ')', '(', '*'};
    private volatile static int numerek[] = {0, 0, 0, 0, 0};
    private static Random r = new Random();
    private int nr;
    private boolean czySynchr;

    public MyThread(int i, boolean czySynchr)
    {
        this.nr = i;
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

    public int max()
    {
        int max = numerek[0];
        for (int i = 0; i < numerek.length; i++)
            if (numerek[i] > max) max = numerek[i];
        return max;
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
            wybieranie[nr] = true;
            numerek[nr] = 1 + max();
            wybieranie[nr] = false;
            for (int j = 0; j < 5; j++)
            {
                try
                {
                    sleep(r.nextInt(10) + 1);
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }

                while(wybieranie[j])
                { }
                while((numerek[j] != 0) && (numerek[j] < numerek[nr]))
                { }
            }
            sekcja_krytyczna(i);
            numerek[nr] = 0;
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
