package warcaby;

/**
 * @author Jacek Pietras
 */
public class Plansza extends Tablica
{
	public Plansza(){}	

	public void zerowanie() //resetowanie całej tablicy z pionkami
	{
		int k = 1;
		for (int j = 0; j < 8; j++)	
		{
			k = (k + 1) % 2;
			for (int i = 0; i < 8; i++)
			{	
				pole[i][j] = k;
				k = (k + 1) % 2;
			}
		}		
	}
	
	public void rozpoczecie()
	{
		zerowanie();
	//	gracz 2 - (czerwone pionki) góra planszy
		for (int j = 0; j < 3; j++)	
			for (int i = 0; i < 8; i++)
				if (pole[i][j] == 1) pole[i][j] = 3;
	
	//	gracz 1 -(białe pionki) dól planszy		
		for (int j = 5; j < 8; j++)	
			for (int i = 0; i < 8; i++)
				if (pole[i][j] == 1) pole[i][j] = 2;
	}
}
