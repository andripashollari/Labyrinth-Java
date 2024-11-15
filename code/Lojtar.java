import java.io.Serializable;

public class Lojtar implements Serializable {
	private int pozicionX;// pozicioni i lojtarit ne loje
	private int pozicionY;// pozicioni i lojtarit ne loje
	private int thesaret = 0;// numri i thesareve e grumbulluara
	private int piket;// piket e fituara
	private  int niveli = 0;
	
	public Lojtar(int startingX, int startingY) {
		this.pozicionX = startingX;
		this.pozicionY = startingY;
	}
	
	public void leviz(int x, int y) {//x y ka gjithmon merr 0, 1, -1
		pozicionX += x;
		pozicionY += y;
		//lejon lojtarin te levize ne labirint
	}
	
	public void mblidhThesar() {
		thesaret++;
		//mbledh thesaret
	}
	public void kaloNivelin() {
		niveli++;
		//mbledh thesaret
	}
	
	/*public void neDalje() {
		//kontrollon nese lojtari eshte ne dalje
	}*/
	


	public int getX(){
		return pozicionX;
	}
	public int getY(){
		return pozicionY;
	}
	public int getPiket(){
		return piket;
	}
	public void setPiket(int piket){
		this.piket = piket;
	}
	public void setThesaret(int thesaret){
		this.thesaret = thesaret;
	}
	public int getThesare() {
		return thesaret;
	}
	public void setNivelin(int niveli){
		this.niveli = niveli;
	}
	public int getNivelin() {
		return niveli;
	}

}