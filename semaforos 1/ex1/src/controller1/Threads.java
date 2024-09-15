package controller1;

import java.util.concurrent.Semaphore;

public class Threads extends Thread
{
	
	static boolean pedra = true;
	static boolean tocha = true;
	static int[] portasAbertas = new int[4];
	static int indice = 0;
	
	Semaphore semaforo /*= new Semaphore(1)*/;
	
	int cavaleiro;
	
	public Threads(int cavaleiro, Semaphore semaforo)
	{
		this.cavaleiro = cavaleiro;
	}
	
	@Override
	public void run()
	{
		Corredor();
		
		try 
		{
			semaforo.acquire();
			Porta();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			semaforo.release();
		}
		
	}

	private void Corredor() 
	{
		int km = 0;
		boolean p = true;
		boolean t = true;
		
		
		while (km < 2000)
		{
			int corrida = (int)((Math.random() * 3) + 2);
			
			
			if ((km >= 500 && km < 504) && tocha)
			{
				tocha = !tocha;
				t = !t;
			}
			else if ((km >= 1500 && km <1504) && pedra && t )
			{
				pedra = !pedra;
				p = !p;
			}
			
			if (!p  || !t)
			{
				corrida = corrida + 2;
			}
			
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("O cavaleiro " + cavaleiro + " andou " + corrida + " metros");
			km = km + corrida;
		}
		
		System.out.println("O cavaleiro " + cavaleiro + " terminou a corrida");

	}
	
	private void Porta() 
	{
		
		boolean b = true;
		boolean duplicate;

		int porta = 0;
		
		while (b)
		{
			duplicate = false;
			porta = (int) ((Math.random()  * 4) + 1);
			
			for (int i: portasAbertas)
			{
				if (i == porta)
				{
					duplicate = !duplicate;
					break ;
				}
			}
			
			if (!duplicate)
			{
				b = !b;
			}
		}
		
		portasAbertas[indice] = porta;
		indice++;
		
		switch(porta)
		{
			case 1:
				System.out.println("O cavaleiro " + cavaleiro + " encontrou a saida na porta " + porta);
				break;
			default:
				System.out.println("O cavaleiro " + cavaleiro + " morreu para um monstro na porta " + porta);
				break;
		}
	}

}
