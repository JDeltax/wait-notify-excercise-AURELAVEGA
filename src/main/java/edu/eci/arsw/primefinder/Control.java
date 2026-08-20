/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.primefinder;
/**
 *
 */
import java.util.Timer;
import java.util.TimerTask; //TIMER PRINCIPAL
import java.util.Scanner;


public class Control extends Thread {

    private final static int NTHREADS = 3;
    private final static int MAXVALUE = 30000000;
    private final static int TMILISECONDS = 3000;

    private final int NDATA = MAXVALUE / NTHREADS;

    private final Scanner scanner;
    private final Timer timer;
    private PrimeFinderThread pft[];
    private boolean paused;
    private int primesFound;

    private Control() {
        super();
        this.scanner = new Scanner(System.in);
        this.timer = new Timer();
        this.paused = false;
        this.primesFound = 0;
        this.pft = new  PrimeFinderThread[NTHREADS];

        int i;
        for (i = 0; i < NTHREADS - 1; i++) {
            PrimeFinderThread elem = new PrimeFinderThread(i * NDATA, (i + 1) * NDATA, this);
            pft[i] = elem;
        }
        pft[i] = new PrimeFinderThread(i * NDATA, MAXVALUE + 1, this);
    }

    public static Control newControl() {
        return new Control();
    }

    public synchronized void awaitIfPaused() throws InterruptedException {
        while (paused) {
            wait();
        }
    }

    public synchronized void primeFound() {
        primesFound++;
    }

    private void pauseWorkers() {
        synchronized (this) {
            paused = true;
            System.out.println("Primos encontrados hasta ahora: " + primesFound);
        }

        scanner.nextLine();

        synchronized (this) {
            paused = false;
            notifyAll();
        }
    }

    @Override
    public void run() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                pauseWorkers();
            }
        }, TMILISECONDS, TMILISECONDS);

        for (int i = 0; i < NTHREADS; i++) {
            pft[i].start();
        }
    }
}