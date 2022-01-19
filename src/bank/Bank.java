/*  Name: Richard Noteboom
    Course: CNT 4714 Summer 2020
    Assignment title: Project 2–Synchronized, Cooperating ThreadsUnder Locking
    Due Date: June 14, 2020
*/
package bank;

//Bank interface specifies methods called by Depositer and Withdrawler.

public interface Bank {
    public void set(int value, String id); // place int value into Balance
    public int get(int value, String id); // return int value from Balance
} //end Interface Bank
