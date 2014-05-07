package warcaby;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * @author Jacek Pietras
 */

public class Warcaby
{
	public static void  main(String args[])
	{
		okno OknoGlowne = new okno("Warcaby w Javie",640,480);
	}	
} 

class okno extends Frame implements ActionListener, MouseListener
{
	Plansza plansza;
	Ruchy ruchy;
	Bicia bicia;
	
        Button bTest;
	Button bTest_1;
	Button bNowa;
	Button bKomputer;
        
        Label lTekst;
	
	public okno(String Nazwa, int Szerokosc, int Wysokosc)
	{
		super(Nazwa);
		setLayout(null);
		
                //rozpoczęcie gry
		plansza = new Plansza();
		ruchy = new Ruchy();
		bicia = new Bicia();
		
		setSize(Szerokosc,Wysokosc);
		setLocation(10,10);
		setFont(new Font("Arial",1,14));
		setResizable(false); //blokowanie zmiany wymiaru okna
		setBackground(new Color(220,220,220));
		
                //metoda zamykająca okienko z grą
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing (WindowEvent e)
			{
				System.exit(0);
			}
		});
		
		bNowa = new Button("Nowa gra");
		bNowa.setSize(100,25);
		bNowa.setLocation(400,45);	
		bNowa.addActionListener(this);	
		add(bNowa);	
		
		bKomputer = new Button("Gra z komputerem");
                bKomputer.setSize(150,25);
                bKomputer.setLocation(400, 85);
                bKomputer.addActionListener(this);
                add(bKomputer);
                
                
                bTest = new Button("Test Damki");
		bTest.setSize(100,25);
		bTest.setLocation(400,165);	
		bTest.addActionListener(this);	
		add(bTest);	
		
		bTest_1 = new Button("Drugi test damki");
		bTest_1.setSize(130,25);
		bTest_1.setLocation(400,195);	
		bTest_1.addActionListener(this);	
		add(bTest_1);	
		
                
                
		lTekst = new Label(" Ruch: ");
		lTekst.setSize(50,25);
		lTekst.setLocation(5,385);
		add(lTekst);
		
        addMouseListener(this);
        
        show();
	}
	
	public void paint(Graphics g)
	{
		RysujPlansze(g);
	}	
	
	public void RysujPlansze(Graphics g)
	{	
		Image img = createImage(getSize().width,getSize().height);
		
		Graphics2D g2 = (Graphics2D) img.getGraphics();
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setColor(Color.black);
		g2.fillRect(18,38,322,322);	
				
		for (int j = 0; j < 8; j++)
		{
			for (int i = 0; i < 8; i++)	
			{
				if (plansza.pole[i][j] == 0) 
					g2.setColor(new Color(255,211,155)); //kolor planszy (jasny brąz)
				else 
					g2.setColor(new Color(139,69,19)); //kolor planszy (ciemny brąz)
				
				g2.fillRect(20 + 40*i, 40 + 40*j,38,38);	
				
				if (plansza.pole[i][j] > 1) 
				{
					g2.setColor(Color.black); //kolor obwódki pionków
					g2.fillOval(21 + 40*i, 41 + 40*j,36,36); //metoda wypełniająca czarym kolorem obwódkę pionka
					
					if (plansza.pole[i][j] == 2 || plansza.pole[i][j] == 4) 
						g2.setColor(new Color(255,255,255)); //biały kolor pionków
					if (plansza.pole[i][j] == 3 || plansza.pole[i][j] == 5) 
						g2.setColor(new Color(215,95,95)); //czerwony kolor pionków
						
					g2.fillOval(23 + 40*i, 43 + 40*j,32,32); //metoda wypełniająca czerwonym kolorem
					
					if (plansza.pole[i][j] == 4 || plansza.pole[i][j] == 5) //rysowanie damki dla pionków białych
					{
						g2.setColor(Color.black);
						g2.fillOval(26 + 40*i, 46 + 40*j,26,26);	
					}
					
				}
				
				if (bicia.pole[i][j] != 0) //wyświetlanie ramki dla pionka który biję
				{
					g2.setColor(new Color(255,255,0)); //kolor żółty
					g2.drawRect(20 + 40*i, 40 + 40*j,37,37);
				}
				
				if (ruchy.pole[i][j] == 1)
				{
					g2.setColor(new Color(255,255,255));
					g2.drawRect(20 + 40*i, 40 + 40*j,37,37);
				}
				
				if (ruchy.pole[i][j] == 2 || ruchy.pole[i][j] == 3)
				{
					g2.setColor(new Color(150,150,150));
					g2.fillRect(20 + 40*i, 40 + 40*j,38,38);
				}				
			}
		}
		//funkcja wyświetlająca aktualny ruch gracza
		g2.setColor(Color.black); //czarny
		g2.fillRect(58,378,42,42);
		
		 g2.setColor(new Color(255,211,155)); //jasny kolor pola z aktualnym ruchem gracza		
		g2.fillRect(60,380,38,38);
		
		if (ruchy.get_gracz() != 0 ) //rysowanie obwódki aktualnego ruchu gracza
		{
			g2.setColor(Color.black);
			g2.fillOval(61,381,36,36);
					
			if (ruchy.get_gracz() == 1) //wyświetlanie ruchów białych pionków	
			{
				g2.setColor(new Color(255,255,255));
			}	
			else
			{
				g2.setColor(new Color(215,95,95));
			}
			g2.fillOval(63,383,32,32);
		}
		g.drawImage(img,0,0,this); 
	}
	
	public void update(Graphics g)
	{
   		paint(g);
 	} 
	
	public void actionPerformed(ActionEvent ev)
	{
		Object 	cel = ev.getSource();
		if (cel == bNowa)
		{
			plansza.rozpoczecie();
			ruchy.zerowanie();
			ruchy.set_gracz(1);
			bicia.zerowanie();
			repaint();
		} 
		else if (cel == bTest)
		{
			int tmp_pole[][] = {{0,3,0,1,0,4,0,3},
						        {1,0,1,0,3,0,1,0},
							    {0,3,0,1,0,1,0,1},
							    {3,0,1,0,1,0,1,0},
							    {0,1,0,3,0,1,0,1},
							    {1,0,1,0,1,0,2,0},
							    {0,2,0,1,0,1,0,2},
							    {1,0,2,0,1,0,2,0},};
							 
			plansza.pole = tmp_pole;
			plansza.transpozycja();
			plansza.wyswietlanie();
			ruchy.zerowanie();
			ruchy.set_gracz(1);
			bicia.zerowanie();
			repaint();
		}
		else if (cel == bTest_1)
		{
			int tmp_pole[][] = {{0,3,0,1,0,4,0,3},
						        {1,0,1,0,1,0,1,0},
							    {0,3,0,1,0,1,0,1},
							    {3,0,1,0,1,0,1,0},
							    {0,1,0,3,0,4,0,1},
							    {1,0,1,0,1,0,2,0},
							    {0,2,0,1,0,1,0,2},
							    {1,0,2,0,5,0,2,0},};
							 
			plansza.pole = tmp_pole;
			plansza.transpozycja();
			plansza.wyswietlanie();
			ruchy.zerowanie();
			ruchy.set_gracz(1);
			bicia.zerowanie();
			repaint();
		}
                else if (cel == bKomputer)
                {
                    
                    plansza.rozpoczecie();
                    ruchy.zerowanie();
                    ruchy.set_gracz(1);
                    bicia.zerowanie();
                    repaint();
                    
                    
                    
                    
                }
        
        
        }
	
	public void mouseClicked(MouseEvent ev)
	{
		int x = ev.getX();
		int y = ev.getY();
				
		if (x >= 20 && x < 340 && y >= 40 && y < 360 
		    && (x + 22)%40 != 0 && (x + 21)%40 != 0
		    && (y + 2)%40 != 0 && (y + 1)%40 != 0)
		    {
		    	int _x, _y;
		    	_x = (x - 20)/40;
		    	_y = (y - 40)/40;
		    	System.out.println(_x + " " + _y);
		    	bicia.sprawdz_bicia(ruchy.get_gracz(),plansza);
		    	ruchy.klikniecie(_x,_y,plansza,bicia);
		    	repaint();
		    }
	}
	
	public void mousePressed(MouseEvent ev){}
	
	public void mouseReleased(MouseEvent ev){}
	
	public void mouseEntered(MouseEvent ev){}
	
	public void mouseExited(MouseEvent ev){}
}



