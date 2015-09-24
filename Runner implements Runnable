package demo2;

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

public class App {
	public static void main(String[] args){
		Thread t1=new Thread(new Runner());
		Thread t2=new Thread(new Runner());
		
		t1.start();
		t2.start();
	}
}
