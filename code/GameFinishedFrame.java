import javax.swing.*;
import java.awt.*;

public class GameFinishedFrame extends JFrame {
    private JLabel mesazhiLabel;
    private JLabel piketLabel;
    private JLabel niveliLabel;
    private JLabel backgroundLabel;
    private String mesazhi;
    private Color color;




    public GameFinishedFrame(int piket, int niveli, boolean hasFinished) {
        this.setLayout(null);
        //this.setPreferredSize(new Dimension(400, 400));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);

        if (hasFinished) {
            mesazhi = "Keni Fituar";
            color = new Color(44, 161, 1);
        } else {
            mesazhi = "Keni Humbur";
            color = new Color(235, 35, 21);

        }

        mesazhiLabel = new JLabel(mesazhi);
        mesazhiLabel.setFont(new Font("Impact", Font.PLAIN, 70));
        mesazhiLabel.setForeground(color);
        mesazhiLabel.setBounds(80, 95, 500, 80);

        niveliLabel = new JLabel();
        niveliLabel.setText("Niveli " + niveli);
        niveliLabel.setFont(new Font("Impact", Font.PLAIN, 40));
        niveliLabel.setForeground(color);
        niveliLabel.setBounds(185, 180, 500, 80);

        piketLabel = new JLabel();
        piketLabel.setText("Piket: " + piket);
        piketLabel.setFont(new Font("Impact", Font.BOLD, 40));
        piketLabel.setForeground(color);
        piketLabel.setBounds(185, 230, 500, 80);

        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, 500, 500);
        backgroundLabel.setIcon(Imazhet.background);


        this.add(mesazhiLabel);
        this.add(piketLabel);
        this.add(niveliLabel);
        this.add(backgroundLabel);

        this.setVisible(true);

    }
}