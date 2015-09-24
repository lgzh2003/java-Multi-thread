package accountMimic;

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
//		here, the getBanlance will not effect the money amount, so it do not needs 
//		synchronized keywords
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
