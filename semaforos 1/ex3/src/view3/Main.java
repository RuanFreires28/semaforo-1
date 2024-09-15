package view3;

import java.util.concurrent.Semaphore;

import controller3.Threads;

public class Main 
{
	public static void main(String[] args) 
	{
	
		Semaphore s1 = new Semaphore(5);
		Semaphore s2 = new Semaphore(1);
		
		
		for(int i = 0; i<25; i++)
		{
			Thread t = new Threads(i, s1, s2);
			t.start();
		}
		
	}
}
