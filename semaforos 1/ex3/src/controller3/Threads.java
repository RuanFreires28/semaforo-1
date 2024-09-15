package controller3;

import java.util.concurrent.Semaphore;
import br.fateczl.calc.quicksort.operations;


public class Threads extends Thread
{

	private Semaphore semaforo /*= new Semaphore(5)*/;
	private Semaphore semaforo2 /*= new Semaphore(1)*/;
	private operations op = new operations();
	
	static int posicao = 1;
	static int [] classificacao = new int[25];
	
	
	int[] pontTiro = new int[3];
	int atleta;
	
	public Threads(int atleta, Semaphore semaforo1, Semaphore semaforo2) 
	{
	
		this.atleta = atleta;
		this.semaforo = semaforo1;
		this.semaforo2 = semaforo2;
	}
	
	@Override
	public void run() 
	{
		Corrida();
		
		try 
		{
			semaforo.acquire();
			TiroAoAlvo();
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			semaforo.release();
		}
		
		Ciclismo();
		
		try 
		{
			semaforo2.acquire();
			Pont();
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			semaforo2.release();
		}
		
		if (posicao == 26)
		{
			classificacao = op.QuickSort(classificacao, 0, 24);
			for (int i: classificacao)
			{
				System.out.print(i + " ");
			}
		}
	}

	private void Corrida() 
	{
		int distanciaTotal = 3000;
		
		System.out.println("O atleta " + atleta + " iniciou a prova de corrida");
		
		while (distanciaTotal >= 0)
		{
			int distanciaPercorrida = (int)((Math.random() * 5) + 20);    
		
			try {
				sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			distanciaTotal -= distanciaPercorrida;	
			
			System.out.println("O atleta " + atleta + " correu " + distanciaPercorrida);
		}
		
		System.out.println("O atleta " + atleta + " terminou a prova decorrida");
	}

	private void TiroAoAlvo() 
	{
		System.out.println("O atleta " + atleta + " vai iniciar a prova do tiro");

		
		for (int tiro = 0; tiro < 3; tiro++)
		{
			pontTiro[tiro] = (int)(Math.random() * 10);
			
			try {
				sleep((long) ((Math.random() * 2500) + 500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("O atleta " + atleta + " ganhou " + pontTiro[tiro] + " pontos no tiro " + tiro);
		}
		
		System.out.println("O atleta " + atleta + " terminou a prova do tiro");
		
	}

	private void Ciclismo() 
	{
		int distanciaTotal = 5000;
		
		System.out.println("O atleta " + atleta + " iniciou a prova de ciclismo");

		while (distanciaTotal >= 0)
		{
			int distanciaPercorrida = (int)((Math.random() * 10) + 30);    
		
			try {
				sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			distanciaTotal -= distanciaPercorrida;	
			
			System.out.println("O atleta " + atleta + " pedalou " + distanciaPercorrida);
		}
		 
		System.out.println("O atleta " + atleta + " terminou a prova de ciclismo");
	
	}
	
	private void Pont() 
	{
		
		int desconto = 10 * (posicao - 1);
		int pontuacao = 250 - desconto;
		
		for (int i: pontTiro)
		{
			pontuacao += i;
		}
		
		System.out.println("O atleta " + atleta + " pontuou " + pontuacao);
		
		classificacao[posicao - 1] = pontuacao;
		posicao++;
		
	}
}
