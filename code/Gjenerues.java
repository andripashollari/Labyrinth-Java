import java.awt.*;
import java.util.Random;

import javax.swing.*;

public class Gjenerues{
	
	public static final int MAX_ROAD = 200; //gjatesia maksimale e rruges kryesore
	public static final int MAX_ALT = 300; // gjatesia max e degeve pa lidhje
	public static final int SIZE_X = 40; //kuti ne boshtin e x-ve
	public static final int SIZE_Y = 40; // kuti ne boshtin e y-ve
	
	private static final Directions[] allDirections = Directions.values(); //matrice qe permban drejtimet, qe pastaj t'i gjenerojme randomly
	public static final Random rand = new Random(); //vec per nr random e perdora kte. po u leviz setBlocks ne klase tjt, edhe kjo do levizet
	private TileType[][] blockType;
	
	//private int nrThesareve;
	
	public TileType[][] gjeneroBlloqe(int x, int y) {
		blockType = new TileType[SIZE_X][SIZE_Y];
		makeRoad(x,y ,true); //kte mund ta kalosh ne ndonje klase tjeter si funksion, e t'i besh return ktu, po u desh. Matrica e blockType do futej si argument pastaj.
		//vazhdim i komentit lart: Ndaj i kam bere public ato MAX_ROAD, MAX_ALT etj

		for(int i=0; i<100; i++)
			makeRoad();
		blockType[x][y] = TileType.PLAYER;
		return blockType;
	}
	
	//per te futur coins mund te perdoresh for-loop, if (panel[i][j].getBackGround == road && rand.nextInt(nr per tip probabiliteti) == 0) panel[i][j].setIcon(Coin image adress)
	
	public static Directions nextEnum() {
		return allDirections[rand.nextInt(4)];
	}
	
	public void makeRoad(int x, int y, boolean isMain) {
		if(!isMain && blockType[x][y]!= null) {
			makeRoad();
			return;
		}
		int count=0;
		boolean noWayLeft = false;
		boolean noWayDown = false;
		boolean noWayUp = false;
		boolean noWayRight = false;
		int LENGTH = MAX_ALT;
		int startingX = x;
		int startingY = y;
		if(isMain)
			LENGTH = MAX_ROAD;
			
		while(count < LENGTH) {
			Directions a = nextEnum();
			if(!isMain && stopAlternate(x,y))
				return;
			switch(a) {
				case LEFT:
					if(x>0 && blockType[x-1][y] ==null ) {
						noWayLeft= false;
						setBlock(x, y, -1, 0, isMain);
						x-= 1;
						count++;
					}
					else
						noWayLeft= true;
					break;
				case RIGHT:
					if(x+1 < SIZE_X && blockType[x+1][y] ==null) {			
						noWayRight = false;	
						setBlock(x, y, 1, 0, isMain);
						x+= 1;
						count++;
					}
					else
						noWayRight = true;
					break;
				case UP:
					if(y>0 && blockType[x][y-1] ==null) {
						noWayUp = false;
						setBlock(x, y, 0, -1, isMain);
						y-= 1;
						count++;
					}
					else
						noWayUp = true;
					break;
				case DOWN:
					if(y +1 < SIZE_Y && blockType[x][y+1] ==null) {
						noWayDown = false;
						setBlock(x, y, 0, 1, isMain);
						y+= 1;
						count++;
					}
					else
						noWayDown = true;
					break;
				}
				if(noWayUp && noWayDown && noWayLeft && noWayRight) { //kur s'ka ku te shkoje (ngaterrohet me veten e vet), ndalon gjenerimi dhe s'e arrijme dot gjatesine max
					if(!isMain)
						arrange();
					break;
				}
							
			}
			if(isMain) { //funksioni behet dhe njeher nga e para nqs pika e fillimit dhe finishi jane teper prane, mund ta benim me teoreme pitagore po ta donim fiks fiks, po s'dum
				if(Math.abs(startingX-x) + Math.abs(startingY-y) < 20) { //ktheje 40
					blockType = new TileType[SIZE_X][SIZE_Y];
					makeRoad(startingX, startingY, true);
				}
				else
					blockType[x][y]= TileType.FLAG; //me BLACK kam lene finishPointin
			}
	}
	public void makeRoad() {
		makeRoad(rand.nextInt(SIZE_X), rand.nextInt(SIZE_Y), false);
	}



	public TileType[][] merrTileType() {
		return blockType;
	}
	
	public void setBlock(int x,int y,int i, int j, boolean isMain) {
		if(isMain) {
			if(rand.nextInt(4) == 0)
				blockType[x+i][y+j] = TileType.THESAR;
			else
				blockType[x+i][y+j] = TileType.MAINROAD;
		}
		else
			blockType[x+i][y+j] = TileType.ROAD;
		if(i!= 0) {
			if(y>0 && blockType[x][y-1] ==null) //bejme mure perreth, qe mos behen shume te gjera rruget, se kshu m'u duk me bukur
				blockType[x][y-1] = TileType.MUR;
			if(y+1< SIZE_Y && blockType[x][y+1] ==null)
				blockType[x][y+1] = TileType.MUR;
		}
		else if(j!=0) {
			if(x+1< SIZE_X && blockType[x+1][y] == null)	
				blockType[x+1][y] = TileType.MUR;
			if(x-1>= 0 && blockType[x-1][y] == null)
				blockType[x-1][y] = TileType.MUR;
		}
	}
	
	public boolean stopAlternate(int x, int y) {
		if(x>1 && blockType[x-2][y] == TileType.MAINROAD) {
			setBlock(x,y, -1, 0, false);
			return true;
		}
		if(x+2 < SIZE_X && blockType[x+2][y] == TileType.MAINROAD) {
			setBlock(x,y, 1, 0, false);
			return true;
		}
		if(y>1 && blockType[x][y-2] == TileType.MAINROAD) {
			setBlock(x,y, 0, -1, false);
			return true;
		}
		if(y+2 < SIZE_Y && blockType[x][y+2] == TileType.MAINROAD) {
			setBlock(x,y, 0, 1, false);
			return true;
		}
		return false;
	}
	public void arrange() {
		for(int i=0; i< SIZE_X; i++)
			for(int j=0; j< SIZE_Y; j++)
				if(blockType[i][j] == TileType.ROAD)
					blockType[i][j] = TileType.MAINROAD;
	}
	
	public int getNrThesareve(){
		int count = 0;
		for(int i=0; i< SIZE_X; i++)
			for(int j=0; j< SIZE_Y; j++)
				if(blockType[i][j] == TileType.THESAR)
					count++;
		return count;
	}
	
}
