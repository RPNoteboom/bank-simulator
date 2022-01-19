/*  Name: Richard Noteboom
    Course: CNT 4714 Summer 2020
    Assignment title: Project 2–Synchronized, Cooperating ThreadsUnder Locking
    Due Date: June 14, 2020
*/
package bank;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BankSim{
    public static void main( String[] args ){   
        // create new thread pool with two threads   
        ExecutorService application = Executors.newFixedThreadPool(14);   
        // create BankAccount to store ints   
        Bank balance = new BankAccount();      
        System.out.printf("%-40s%s\t\t%s\n%-40s%s\n", "Deposit Threads",       "Withdrawl Threads", "Balance", "---------------", "-----------------\t\t--------");
        try{ // try to start depositor and withdrawler   
            application.execute(new Withdrawler(balance, "W1")); //W1
            application.execute(new Withdrawler(balance, "W2")); //W2
            application.execute(new Withdrawler(balance, "W3")); //W3
            application.execute(new Withdrawler(balance, "W4")); //W4
            application.execute(new Withdrawler(balance, "W5")); //W5
            application.execute(new Withdrawler(balance, "W6")); //W6
            application.execute(new Withdrawler(balance, "W7")); //W7
            application.execute(new Withdrawler(balance, "W8"));//W8
            application.execute(new Withdrawler(balance, "W9")); //W9
            application.execute(new Depositor(balance, "D1")); //D1    
            application.execute(new Depositor(balance, "D2")); //D2
            application.execute(new Depositor(balance, "D3")); //D3
            application.execute(new Depositor(balance, "D4")); //D4
            application.execute(new Depositor(balance, "D5")); //D5
            
        } // end try   
        catch ( Exception exception ){      
            exception.printStackTrace();
        } // end catch
    } // end main
}
