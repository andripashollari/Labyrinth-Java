import java.awt.*;
import java.awt.event.KeyEvent;
//import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Labirint{
	
	private JLabel[][] rrjetDinamik = new JLabel[Gjenerues.SIZE_X][Gjenerues.SIZE_Y];// rrjeti dinamik qe paraqet labirintin

	private Gjenerues gen;

	public void gjeneroLabirint(JFrame frame, int x, int y){
		//JFrame frame = new JFrame("Labyrinth");

		
		//frame.addKeyListener((KeyListener) this);
		gen = new Gjenerues();

		JPanel container = new JPanel();
		container.setLayout(new GridLayout(Gjenerues.SIZE_Y,Gjenerues.SIZE_X));
		container.setPreferredSize(new Dimension(800, 800));

		makeLabels(gen.gjeneroBlloqe(x, y),true);
		for(int i=0; i< Gjenerues.SIZE_Y; i++)
			for(int j=0; j< Gjenerues.SIZE_X; j++)
				container.add( rrjetDinamik[j][i]);
		frame.add(container , BorderLayout.WEST);
		frame.setResizable(false);


		frame.setVisible(true);
	}

	public void makeLabels(int x, int y){
		gen = new Gjenerues();
		makeLabels(gen.gjeneroBlloqe(x, y),false);
	}
	public void makeLabels(TileType[][] blockType, boolean first){
		for(int i=0; i< Gjenerues.SIZE_Y; i++) {
			for(int j=0; j< Gjenerues.SIZE_X; j++) {
				if(first)
					rrjetDinamik[j][i]= new JLabel();
				if(blockType[j][i]!= null){
					if(blockType[j][i] == TileType.MUR)
						rrjetDinamik[j][i].setIcon(Imazhet.grass);
					else if(blockType[j][i]== TileType.ROAD || blockType[j][i]== TileType.MAINROAD)
						rrjetDinamik[j][i].setIcon(Imazhet.asfalt);
					else if(blockType[j][i]== TileType.PLAYER)
						rrjetDinamik[j][i].setIcon(Imazhet.playerIcon);
					else if(blockType[j][i]== TileType.FLAG){
						rrjetDinamik[j][i].setBackground(Color.DARK_GRAY);
						rrjetDinamik[j][i].setIcon(Imazhet.flagIcon);
					}
					else if(blockType[j][i] == TileType.THESAR){
						rrjetDinamik[j][i].setBackground(Color.GRAY);
						rrjetDinamik[j][i].setIcon(Imazhet.coinIcon);
					}
						//setIcon
						 //ktu do hiqni kte, do e beni me ikone si ato lart
				}
				else
					rrjetDinamik[j][i].setIcon(Imazhet.grass);
				//if(first)
				rrjetDinamik[j][i].setOpaque(true);
				 //panelet po shtohen sipas radhes qe jane krijuar, ngaqe ne perdorem koordinaten j dhe kur perfunduam, vazhuam me i, boshti x perkon me j dhe y me i. E di qe s'e shpjegova mire po vl
			}
		}
	}
	public boolean eshteMur(int x, int y) {//kontrollon nese nje vendndodhje eshte mur
		if(rrjetDinamik[x][y].getIcon() == Imazhet.grass)
			return true;
		return false;
	}
	
	public boolean eshteThesar(int x, int y) {//		kontrollon nese nje vendndodhje eshte me thesar
		if(rrjetDinamik[x][y].getIcon() == Imazhet.coinIcon)           //ktu do fusni nje ikone per thesaret
			return true;
		return false;
	}
	
	public boolean eshteDalje(int x, int y) {//		kontrollon nese nje vendndodhje eshte dalje
		if(rrjetDinamik[x][y].getIcon() == Imazhet.flagIcon)
			return true;
		return false;
	}

	public int getTotaliThesareve() {
		return gen.getNrThesareve();
	}
	public void update(int posX, int posY, int moveX, int moveY) { 
			rrjetDinamik[posX][posY].setIcon(Imazhet.playerIcon);
			rrjetDinamik[posX-moveX][posY-moveY].setIcon(Imazhet.asfalt);


	}
	public TileType[][] getTileTypes(){ // kur i lexon nga filet
		TileType[][] tile = new TileType[Gjenerues.SIZE_X][Gjenerues.SIZE_Y];
		for(int i=0; i< Gjenerues.SIZE_Y; i++) {
			for (int j = 0; j < Gjenerues.SIZE_X; j++) {
				if (rrjetDinamik[j][i].getIcon() == Imazhet.grass)
					tile[j][i] = TileType.MUR;
				else if (rrjetDinamik[j][i].getIcon() == Imazhet.asfalt)
					tile[j][i] = TileType.ROAD;
				else if (rrjetDinamik[j][i].getIcon() == Imazhet.playerIcon)
					tile[j][i] = TileType.PLAYER;
				else if (rrjetDinamik[j][i].getIcon() == Imazhet.flagIcon) {
					tile[j][i] = TileType.FLAG;
				} else if (rrjetDinamik[j][i].getIcon() == Imazhet.coinIcon)
					tile[j][i] = TileType.THESAR; //ktu do hiqni kte, do e beni me ikone si ato lart
				else if (rrjetDinamik[j][i].getIcon() == Imazhet.playerIcon) {
					tile[j][i] = TileType.PLAYER;
				}
			}
		}
		return  tile;
	}

}


	

