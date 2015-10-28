/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutexthreads;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author James
 */
public class MutexThreadedArrayList {
    
    private Random r = new Random();
    
    private Object lockA = new Object();
    private Object lockB = new Object();
    
    private ArrayList<Integer> listA = new ArrayList<>();
    private ArrayList<Integer> listB = new ArrayList<>();
    
    public void addToListA() {
        synchronized (lockA){
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
            }
            listA.add(r.nextInt(100));
        }
    }
    
    public synchronized void addToListB() {
        synchronized (lockB){
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
            }
            listB.add(r.nextInt(100));
        }
    }

    public ArrayList<Integer> getListA() {
        return listA;
    }

    public ArrayList<Integer> getListB() {
        return listB;
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException{
        // TODO code application logic here
        MutexThreadedArrayList tal = new MutexThreadedArrayList();
        ExecutorService executor = Executors.newFixedThreadPool(1000);
        for(int i = 0;i < 6;i++){
            executor.submit(new MutexWorker(tal,i));
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);
        MutexVerifier v = new MutexVerifier(tal);
        long start = System.currentTimeMillis();
        v.start();
        v.join();
        long end = System.currentTimeMillis();
        long totalTime = end - start;
        System.out.println(totalTime);
    }
    
}
