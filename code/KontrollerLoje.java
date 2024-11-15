import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;

public class KontrollerLoje implements ActionListener, Serializable {
	
	private Lojtar lojtar; 
	private Labirint labirint;
	private MenuPanel menuPanel;
	private JFrame frame = new JFrame("Labirint");
	


	public void filloLoje() {

		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		labirint = new Labirint();
		menuPanel = new MenuPanel();
		lojtar = new Lojtar(Gjenerues.rand.nextInt(Gjenerues.SIZE_X), Gjenerues.rand.nextInt(Gjenerues.SIZE_Y));//vendos koordinata random
		labirint.gjeneroLabirint(frame, lojtar.getX(), lojtar.getY());

		frame.add(menuPanel, BorderLayout.EAST);
		frame.pack();//bashkon dy panelet, labirint dhe menu panel

		menuPanel.getUpButton().addActionListener(this);
		menuPanel.getDownButton().addActionListener(this);
		menuPanel.getLeftButton().addActionListener(this);
		menuPanel.getRightButton().addActionListener(this);
		menuPanel.getRuajLojenButton().addActionListener(this);
		menuPanel.getNgarkoLojenButton().addActionListener(this);
		menuPanel.getExitButton().addActionListener(this);
		menuPanel.getPerfundoLojenButton().addActionListener(this);
		//inicializon nje loje te re
	}
	public void luajRadhen(int x, int y) {
		//x y mund te jete 0, 1 ,-1
		lojtar.leviz(x, y); // tenton te leviz lojtarin
		kontroll();//kontrollon nese eshte rruge(e lejuar per lojtarin) apo jo
		labirint.update(lojtar.getX(), lojtar.getY(), x, y);//nese e lejuar, leviz lojtari

		//Menaxhon cdo radhe, duke lejuar lojtarin te levize dhe te mbledhe thesare
	}
	
	public void perfundoLojen(boolean hasFinished) {
		//mbyll lojen nese jemi te flamuri, ose nese eshte flake
		
		//Percakton rezultatin e lojes, update-on piket dhe shfaq rezultatet
		//ermiri: mbyll edhe labirintin kur thirret kjo metode besoj

		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//perditeson frame1, mund te mbyllet frame, por jo progrqmin, mund te hapen frame te tjera
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));//mbyll frame e par
		GameFinishedFrame gameFinishedFrame = new GameFinishedFrame(lojtar.getPiket(), lojtar.getNivelin(), hasFinished);

	}

	public void ruajLojen() throws IOException {
		/*Ruan gjendjen aktuale te lojes, duke perfshire piket e
		   lojareve dhe formen e labirintit ne nje file
		   something like: save(this);
		 */

		//update tiletype
		File outputFileLojtari = new File("C:\\Users\\User\\Desktop\\Java - Detyre Kursi\\Lojtari.txt");
		File outputFileTileType = new File("C:\\Users\\User\\Desktop\\Java - Detyre Kursi\\TileType.txt");
		ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(new FileOutputStream(outputFileLojtari, false));
		ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream(outputFileTileType, false));
		try{
			objectOutputStream1.writeObject(lojtar);
			objectOutputStream2.writeObject(labirint.getTileTypes());
		}
		catch (Exception e){
			System.out.println("Error");
		}
		 finally{
			objectOutputStream1.close();
			objectOutputStream2.close();
		}
	}


	public void ngarkoLojen() throws IOException {
		 //Merr objektin e ruajtur dhe i ben load

		File inputFileLojtari = new File("C:\\Users\\User\\Desktop\\Java - Detyre Kursi\\Lojtari.txt");
		File inputFileTileType = new File("C:\\Users\\User\\Desktop\\Java - Detyre Kursi\\TileType.txt");

		ObjectInputStream objectInputStream1 = new ObjectInputStream(new FileInputStream(inputFileLojtari));
		ObjectInputStream objectInputStream2 = new ObjectInputStream(new FileInputStream(inputFileTileType));
		try{

			lojtar = (Lojtar) objectInputStream1.readObject();
			//status = kontrollerLoje.status;
			//menuPanel = kontrollerLoje.menuPanel;
			//frame = kontrollerLoje.frame;



		}
		catch (EOFException eofException){

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally{
			//objectInputStream.close();
		}
	}

	public Lojtar merrLojarin() {
		return lojtar;
	}
	public Labirint merrLabirintin() {
		return labirint;
	}
	public MenuPanel merrMenuPanel() {
		return menuPanel;
	}
	public JFrame merrFrame() {
		return frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonPressed = (JButton) e.getSource();

		if(buttonPressed == menuPanel.getUpButton()){
			if(lojtar.getY() >0 && !labirint.eshteMur(lojtar.getX(), lojtar.getY()-1)){//kontrollo nese levizja behet
				luajRadhen(0, -1);//if true, beje levizjen
			}
			else{
				perfundoLojen(false);
			}
		}
		else if(buttonPressed == menuPanel.getDownButton()){
			if(lojtar.getY() < Gjenerues.SIZE_Y-1 && !labirint.eshteMur(lojtar.getX(), lojtar.getY()+1)){
				luajRadhen(0, 1);
			}
			else{
				// humb lojen
				perfundoLojen(false);
			}
		}
		else if(buttonPressed == menuPanel.getRightButton()){
			if(lojtar.getX() < Gjenerues.SIZE_X-1 && !labirint.eshteMur(lojtar.getX()+1, lojtar.getY())){
				luajRadhen(1, 0);
			}
			else{
				// humb lojen
				perfundoLojen(false);
			}
		}
		else if(buttonPressed == menuPanel.getLeftButton()){
			if(lojtar.getX() >0 && !labirint.eshteMur(lojtar.getX()-1, lojtar.getY())){
				luajRadhen(-1, 0);
			}
			else{
				// humb lojen
				perfundoLojen(false);
			}
		}
		else if(buttonPressed == menuPanel.getPerfundoLojenButton()){
			perfundoLojen(true);
		}
		else if(buttonPressed == menuPanel.getRuajLojenButton()){
			try {
				ruajLojen();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}

		}
		else if(buttonPressed == menuPanel.getNgarkoLojenButton()){

			try {
				ngarkoLojen();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}

		}
		else if(buttonPressed == menuPanel.getExitButton()){

			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

		}

	}
	public void kontroll() {
		//kontrolloj nese jemi ne finish,
		if(!labirint.eshteDalje(lojtar.getX(), lojtar.getY()))
			menuPanel.updatePerfundoLojenButton(false);
		if(labirint.eshteDalje(lojtar.getX(), lojtar.getY()) && (lojtar.getThesare()==labirint.getTotaliThesareve())) {
			lojtar.kaloNivelin();
			lojtar.setPiket(lojtar.getPiket()+lojtar.getThesare());
			lojtar.setThesaret(0);
			menuPanel.updateNivelin(lojtar.getNivelin());
			menuPanel.updateThesarin(lojtar.getThesare());
			menuPanel.updatePiket(lojtar.getPiket());
			menuPanel.updatePerfundoLojenButton(true);
			labirint.makeLabels(lojtar.getX(), lojtar.getY());
		}
		else if(labirint.eshteDalje(lojtar.getX(), lojtar.getY()) && !(lojtar.getThesare()==labirint.getTotaliThesareve())){
			perfundoLojen(false);
		}

		if(labirint.eshteThesar(lojtar.getX(), lojtar.getY())){
			lojtar.mblidhThesar();
			menuPanel.updateThesarin(lojtar.getThesare());

		}
	}


}
