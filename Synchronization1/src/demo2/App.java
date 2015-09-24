package demo2;

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
