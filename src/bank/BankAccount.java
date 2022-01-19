/*  Name: Richard Noteboom
    Course: CNT 4714 Summer 2020
    Assignment title: Project 2–Synchronized, Cooperating ThreadsUnder Locking
    Due Date: June 14, 2020
*/
package bank;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class BankAccount implements Bank{
    // Lock to control mutually exclusive access to the buffer   
    private Lock accessLock = new ReentrantLock();
    
    // conditions to control reading and writing - synchronizing access between the threads
    private Condition canWrite = accessLock.newCondition();  //used by consumer to notify producer
    private Condition canRead = accessLock.newCondition();   //used by producer to notify consumer
    
    private int balance = 0; // shared by depositor and withdrawler threads - (-1) means invalid
    //private boolean occupied = false; // whether buffer is occupied - contents same as last access
    
    // setter (mutator) method for producer to write into buffer
    public void set(int value, String id){   
        accessLock.lock();// lock this object   
        // output thread information and buffer information, then wait   
        try{       
            // while buffer is occupied, place thread in waiting state
            //if (occupied){         
                //System.out.println(id + " tries to write.");         
            //    System.out.printf("\nIn use." + id + " waits.");
            //    canWrite.await();// wait until buffer is empty      
            //} // end while
            // not occupied
            //else if (!occupied){
            balance += value; // set new buffer value
                //occupied = true;      
            System.out.printf("\nThread " + id + " desposits " + value + "\t\t\t\t\t\t (+) Balance is $" + balance);      
                // signal thread waiting to read from buffer      
            canRead.signal();
            //}
            //occupied = false;
        } // end try   
        //catch (InterruptedException exception){      
            //exception.printStackTrace();
        //} // end catch   
        finally{     
            accessLock.unlock();// unlock this object   
        } // end finally
    } // end method set
    
    // getter (accessor) method for threads to retrieve value from buffer
    public int get(int value, String id){
        int readValue = 0; // initialize value read from buffer
        accessLock.lock(); // lock this object
        // output thread information and buffer information, then wait
        try{
            // if occupied, place thread in waiting state
            //if (occupied){
                //System.out.println(id + " tries to read.");
            //    System.out.printf("\nIn use. " + id + " waits.");
		//canRead.await(); // wait
            //} // end while
            //if (!occupied){
                // withdrawl money from Balance 
                //occupied = true;
                readValue = balance; // retrieve value from buffer
                if (balance < value)
                {
                    System.out.printf("\n\t\t\t\tThread " + id + " withdrawls " + value + "\t\t (***) Withdrawl - Blocked - Insufficient Funds!!!");
                    canRead.await(); // wait
                }
                else{
                    balance -=value;
                    //displayState(id + " reads " + readValue);
                    System.out.printf("\n\t\t\t\tThread " + id + " withdrawls " + value + "\t\t (-) Balance is $" + balance);
                }
                // signal thread waiting for buffer to be empty
                
                //occupied = false;
            //}
            canWrite.signal();
            
        } // end try
        // if waiting thread interrupted, print stack trace
        catch (InterruptedException exception){
            exception.printStackTrace();
        } // end catch
        finally{
            accessLock.unlock(); // unlock this object
        } // end finally

        return readValue;
    } // end method get
    
    // display current operation and buffer state
    public void displayState(String operation){
        System.out.printf("%-40s%d\n", operation, balance);
    } // end method displayState
} //end class Bank
