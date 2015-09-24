# java-Multi-thread

###Call a Thread
     class Runner extends Thread{
	public void run() {//override run() click right-> source-> override method
		for(int i=0; i<10;i++){
			System.out.println("Hello "+i);	
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
     }

==========================

      class Runner implements Runnable{//Runnable is an interface that has only one method in it
	
	
	public void run() {//override run() click right-> source-> override method
		for(int i=0; i<10;i++){
			System.out.println("Hello "+i);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
     }


==================================

      Thread t1=new Thread(new Runnable(){
			public void run(){
				for(int i=0;i<10;i++){
					System.out.println("Hello "+i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});//override the run() when new it.
		
		
========================================================

###volatitle keywords
     import java.util.Scanner;

     class Processor extends Thread{
	
	private volatile boolean running =true;
	//volatile is to make sure that instruct will not be overlooked when being optimized.
	//volatile is used in multi-thread programming(guarantee)
	public void run(){//override
		while(running){
			System.out.println("Hello");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void shutdown(){
		running = false;
	}
     }

     public class App {
	public static void main(String[] args){
		Processor proc1=new Processor();
		proc1.start();
		
		System.out.println("Press return to stop....");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		
		proc1.shutdown();
	}
     }


==================================================================

###synchronized keyword

      public class App {
	private int count=0;
	public synchronized void increment(){
		//without synchronized keywords the count will be fault sometimes
		//
		count++;
	}
	public static void main(String[] args){
		App app=new App();
		app.doWork();
		
	}
	public void doWork(){
		Thread t1 = new Thread(new Runnable(){
			public void run() {//override the only method in Runnable interface
				for(int i=0;i<10000;i++){
					increment();
				}
			}
		});
		Thread t2 = new Thread(new Runnable(){
			public void run() {//override the only method in Runnable interface
				for(int i=0;i<10000;i++){
					increment();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();// wait until other thread finished 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Count is "+count);
	}
      }


==========================================================

###Using synchronized Example
      //The codes mimic tons of people input and out put money into the same account at the same time
      class Account{
	String name;
	float money;
	
	public Account(String name,float money){
		this.name=name;
		this.money=money;
	}
	public synchronized void inputMoney(float inputMoney){
		float tmp=money;
		tmp +=inputMoney;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// ignore
		}
		money=tmp;
	}
	public synchronized void outputMoney(float outputMoney){
		float tmp=money;
		tmp -=outputMoney;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// ignore
		}
		money=tmp;
	}
	public float getBalance() {
       //	here, the getBanlance will not effect the money amount, so it do not needs 
       //	synchronized keywords
        return money;
      }
    }

     public class App {
	private static int NUM_OF_THREAD = 10;
	static Thread[] threads = new Thread[NUM_OF_THREAD];
    
     public static void main(String[] args){
        final Account acc = new Account("John", 1000.0f);
        for (int i = 0; i< NUM_OF_THREAD; i++) {
            threads[i] = new Thread(new Runnable() {
            	public void run(){//override
            		acc.inputMoney(100.0f);
            		System.out.println("John's current balance is:" + acc.getBalance());
            		acc.outputMoney(100.0f);
            		System.out.println("John's current balance is:" + acc.getBalance());
            	}
            });
            threads[i].start();
            }
        for (int i=0; i<NUM_OF_THREAD; i++){
            try {
                threads[i].join(); //等待所有线程运行结束
            } catch (InterruptedException e) {
                // ignore
            }
        }
        System.out.println("Finally, John's balance is:" + acc.getBalance());
      }
     }

