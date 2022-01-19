/*  Name: Richard Noteboom
    Course: CNT 4714 Summer 2020
    Assignment title: Project 2–Synchronized, Cooperating ThreadsUnder Locking
    Due Date: June 14, 2020
*/
package bank;

//Depositor's run method stores the values 1 to 10 in buffer.
import java.util.Random;

public class Depositor implements Runnable{
    private static Random generator = new Random();
    private Bank balance; // reference to bank account
    private String thread; // reference to thread id
    
    public Depositor(Bank shared, String id){   
        balance = shared;
        thread = id;
    } // end Depositor constructor
    
    public void run(){
        while (true){
            int value = (generator.nextInt(249) + 1);
            try {    
                Thread.sleep(generator.nextInt(15)); // sleep 15 seconds
                balance.set(value, thread); // set value in Balance

            } // end try      
            // if sleeping thread interrupted, print stack trace      
            catch (InterruptedException exception){         
                exception.printStackTrace();
            } // end catch      
            //System.out.printf("\n%s\n%s\n", thread + " done producing.","  Terminating " + thread);   
            //System.out.println("Thread " + thread + " desposits $" + value +"\n");
        }
    } // end method run
}


