package view1;

import java.util.concurrent.Semaphore;

import controller1.Threads;

public class Main 
{
	public static void main(String[] args) 
	{
		
		Semaphore semaforo = new Semaphore(1);
		
		for (int i = 1; i<5; i++)
		{
			Thread t = new Threads(i, semaforo);
			t.start();
		}
		
		
		
	}
}
