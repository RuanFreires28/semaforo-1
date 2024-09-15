package controller2;

import java.util.concurrent.Semaphore;

public class Threads extends Thread
{

	private int pista = 0;
	
	private Semaphore semaforo1 /*= new Semaphore(1)*/;
	private Semaphore semaforo2 /*=  new Semaphore(1)*/;
	
	private int aviao;

	public Threads(int aviao, Semaphore semaforo1, Semaphore semaforo2)
	{
		
		this.aviao = aviao;
		this.semaforo1 = semaforo1;
		this.semaforo2 = semaforo2;
		
	}
	
	@Override
	public void run() 
	{
	
		SelecionaPista();
		
		switch (pista)
		{
		case 1:
			
			try 
			{
				semaforo1.acquire();
				decolagem();
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			finally 
			{
				semaforo1.release();
			}
			
		case 2: 
			try 	
			{
				semaforo2.acquire();
				decolagem();
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			finally 
			{
				semaforo2.release();
			}
		}
	}

	private void SelecionaPista() 
	{

		pista = (int) ((Math.random() * 2) + 1);
		System.out.println("o avião " + aviao + " vai decolar na pista " + pista);
			
	}

	private void decolagem() 
	{
		
		System.out.println("o avião " + aviao + " vai inciar a sua decolagem na pista " + pista);
		
		int t = 0;
		
		//manobrar
		System.out.println("o avião " + aviao + " vai manobrar");
		t = Etapa(300, 700);
		System.out.println("o avião " + aviao + " manobrou em " + t + "ms");
		
		//taxiar
		System.out.println("o avião " + aviao + " vai taxiar");
		t = Etapa(500, 1000);
		System.out.println("o avião " + aviao + " taxiou em " + t + "ms");
		
		//decolar
		System.out.println("o avião " + aviao + " vai decolar");
		t = Etapa(600, 800);
		System.out.println("o avião " + aviao + " decolou em " + t + "ms");

		//afastar
		System.out.println("o avião " + aviao + " vai afastar");
		t = Etapa(300, 800);
		System.out.println("o avião " + aviao + " afastou em " + t + "ms");

		
	}

	private int Etapa(int min, int max) 
	{
		
		int t = (int) ((Math.random() * (max - min)) + min);
		
		try 
		{
			sleep(t);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		
		return t;
		
		
	}
	
	
	
	
}
