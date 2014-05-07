package warcaby;

/**
 * @author Jacek Pietras
 */
public class Tablica
{
	public int pole[][];
	
	public Tablica() //tworzenie planszy do gry o wielkości 8 na 8
	{
		pole = new int[8][8];
		this.zerowanie();
	}
	
	public void zerowanie() //resetowanie całej tablicy z pionkami
	{		
		for (int j = 0; j < 8; j++)
		{
			for (int i = 0; i < 8; i++)			
			{
				pole[i][j] = 0;	
			}
		}
	}
	
	public void wyswietlanie() //wyświetlanie pionków na tablicy
	{
		for (int j = 0; j < 8; j++)	
		{
			for (int i = 0; i < 8; i++)
			{
				System.out.print(pole[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void transpozycja() //przystosowanie planszy do testowania damek
	{
		int tmp_pole[][] = new int[8][8];
		for (int j = 0; j < 8; j++)	
		{
			for (int i = 0; i < 8; i++)
			{
				tmp_pole[j][i] = pole[i][j];
			}
		}
		pole = tmp_pole;
	}
}
