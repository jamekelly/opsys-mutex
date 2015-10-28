/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutexthreads;


/**
 *
 * @author James
 */
public class MutexWorker extends Thread {
    
    private MutexThreadedArrayList threaded;
    private int id;
    
    public MutexWorker(MutexThreadedArrayList threaded, int id) {
        this.threaded = threaded;
        this.id = id;
    }
    
    @Override
    public void run() {
        System.out.println("Starting number " + this.id);
        for(int i = 0; i<5000;i++){
            threaded.addToListA();
            threaded.addToListB();
            //System.out.println(this.id + " added shit");
        }
        System.out.println("Finishing number " + this.id);
    }
    
}
