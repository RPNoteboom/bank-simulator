/*  Name: Richard Noteboom
    Course: CNT 4714 Summer 2020
    Assignment title: Project 2–Synchronized, Cooperating ThreadsUnder Locking
    Due Date: June 14, 2020
*/
package bank;

//Withdrawler's run method loops ten times reading a value from buffer.
import java.util.Random;

public class Withdrawler implements Runnable{
    private static Random generator = new Random();
    private Bank balance; // reference to shared object
    private String thread; // reference to thread id
    
    // constructor
    public Withdrawler(Bank shared, String id){   
        balance = shared;
        thread = id;
    } // end Consumer constructor
    // read sharedLocation's value four times and sum the values
    public void run(){
        while (true){
            int value = (generator.nextInt(49) + 1);            
                try{
                    Thread.sleep(generator.nextInt(5));
                    balance.get(value, thread);
                } // end try      
                // if sleeping thread interrupted, print stack trace      
                catch (InterruptedException exception){         
                    exception.printStackTrace();
                } // end catch    
            //System.out.printf("\n%s %d.\n%s\n", thread + " withdraws $", value, "  Terminating " + thread);
        }
    } // end method run
}
