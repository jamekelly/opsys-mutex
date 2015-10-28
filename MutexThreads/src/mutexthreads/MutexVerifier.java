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
public class MutexVerifier extends Thread{
    
    private MutexThreadedArrayList tal;
    
    public MutexVerifier(MutexThreadedArrayList tal) {
        this.tal = tal;
    }
    
    public void verify() {
        if(this.tal.getListA().size() == 30000 && this.tal.getListB().size() == 30000) {
            System.out.println("Mutex Success!");
        } else {
            System.out.println("Failed");
            System.out.println("A: " + tal.getListA().size());
            System.out.println("B: " + tal.getListB().size());
        }
    }
    
    @Override
    public void run() {
        this.verify();
    }
    
}
