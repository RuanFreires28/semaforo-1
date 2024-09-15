package view;

import java.util.concurrent.Semaphore;

import controller2.Threads;

public class Main 
{
	public static void main(String[] args) 
	{
		
		Semaphore s1 = new Semaphore(1);
		Semaphore s2 = new Semaphore(1);
				
		for(int i = 1; i < 13; i++) 
		{
			Thread t = new Threads(i, s1, s2);
			t.start();
		}
		
	}
}
