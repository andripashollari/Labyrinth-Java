import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Formatter;
import java.util.Scanner;

public class MenuPanel extends JPanel{
    private  JButton upButton;
    private JButton downButton;
    private JButton rightButton;
    private JButton leftButton;
    private JButton exitButton;
    private JButton ruajLojenButton;
    private JButton ngarkoLojenButton;
    private JButton perfundoLojenButton;
    private JLabel piketLabel;
    public   JLabel thesaretLabel;
    private  JLabel niveliLabel;
    private Image menuBackground = new ImageIcon("assets/MarioLavaBackground.jpeg").getImage();



    public MenuPanel()  {
        this.setPreferredSize(new Dimension(400, 800));
        this.setLayout(null);

        //this.setBackground(Color.GREEN);

        niveliLabel = new JLabel("Niveli 0");
        niveliLabel.setFont(new Font("Arial", Font.BOLD, 26));
        niveliLabel.setForeground(Color.WHITE);
        niveliLabel.setBounds(50, 50, 300, 80);

        piketLabel = new JLabel("Piket: 0");
        piketLabel.setFont(new Font("Arial", Font.BOLD, 26));
        piketLabel.setForeground(Color.WHITE);
        piketLabel.setBounds(50, 100, 300, 80);

        thesaretLabel = new JLabel("Thesaret: 0");
        thesaretLabel.setFont(new Font("Arial", Font.BOLD, 26));
        thesaretLabel.setForeground(Color.WHITE);
        thesaretLabel.setBounds(50, 150, 300, 80);

        perfundoLojenButton = new JButton("Perfundo");
        perfundoLojenButton.setFocusable(false);
        perfundoLojenButton.setFont(new Font("Arial", Font.BOLD, 26));
        perfundoLojenButton.setBackground(Color.GRAY);
        perfundoLojenButton.setForeground(Color.WHITE);
        perfundoLojenButton.setBounds(210, 80, 140, 120);
        perfundoLojenButton.setOpaque(false);
        perfundoLojenButton.setBorder(new LineBorder(Color.WHITE,5));
        perfundoLojenButton.setVisible(false);

        upButton = new JButton("↑");
        upButton.setFocusable(false);
        upButton.setFont(new Font("Arial", Font.BOLD, 50));
        upButton.setBackground(Color.GRAY);
        upButton.setForeground(Color.WHITE);
        upButton.setBounds(160, 575, 80, 80);
        upButton.setOpaque(false);
        upButton.setBorder(new LineBorder(Color.WHITE,5));

        downButton = new JButton("↓");
        downButton.setFocusable(false);
        downButton.setFont(new Font("Arial", Font.BOLD, 40));
        downButton.setBackground(Color.GRAY);
        downButton.setForeground(Color.WHITE);
        downButton.setBounds(160, 650, 80, 80);
        downButton.setOpaque(false);
        downButton.setBorder(new LineBorder(Color.WHITE,5));

        rightButton = new JButton("→");
        rightButton.setFocusable(false);
        rightButton.setFont(new Font("Arial", Font.BOLD, 40));
        rightButton.setBackground(Color.GRAY);
        rightButton.setForeground(Color.WHITE);
        rightButton.setBounds(235, 650, 80, 80);
        rightButton.setOpaque(false);
        rightButton.setBorder(new LineBorder(Color.WHITE,5));

        leftButton = new JButton("←");
        leftButton.setFocusable(false);
        leftButton.setFont(new Font("Arial", Font.BOLD, 40));
        leftButton.setBackground(Color.GRAY);
        leftButton.setForeground(Color.WHITE);
        leftButton.setBounds(85, 650, 80, 80);
        leftButton.setOpaque(false);
        leftButton.setBorder(new LineBorder(Color.WHITE,5));

        ruajLojenButton = new JButton("Ruaj lojen aktuale");
        ruajLojenButton.setFocusable(false);
        ruajLojenButton.setFont(new Font("Arial", Font.BOLD, 26));
        ruajLojenButton.setBackground(Color.GRAY);
        ruajLojenButton.setForeground(Color.WHITE);
        ruajLojenButton.setBounds(50, 250, 300, 80);
        ruajLojenButton.setOpaque(false);
        ruajLojenButton.setBorder(new LineBorder(Color.WHITE,5));

        ngarkoLojenButton = new JButton("Ngarko lojen e kaluar");
        ngarkoLojenButton.setFocusable(false);
        ngarkoLojenButton.setFont(new Font("Arial", Font.BOLD, 26));
        ngarkoLojenButton.setBackground(Color.GRAY);
        ngarkoLojenButton.setForeground(Color.WHITE);
        ngarkoLojenButton.setBounds(50, 350, 300, 80);
        ngarkoLojenButton.setOpaque(false);
        ngarkoLojenButton.setBorder(new LineBorder(Color.WHITE,5));

        exitButton = new JButton("Dil nga loja");
        exitButton.setFocusable(false);
        exitButton.setFont(new Font("Arial", Font.BOLD, 26));
        exitButton.setBackground(Color.GRAY);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBounds(50, 450, 300, 80);
        exitButton.setOpaque(false);
        exitButton.setBorder(new LineBorder(Color.WHITE,5));


        this.add(niveliLabel);
        this.add(thesaretLabel);
        this.add(piketLabel);
        this.add(perfundoLojenButton);
        this.add(ruajLojenButton);
        this.add(ngarkoLojenButton);
        this.add(exitButton);
        this.add(upButton);
        this.add(downButton);
        this.add(rightButton);
        this.add(leftButton);


    }

    public JButton getUpButton(){
        return upButton;
    }
    public JButton getDownButton(){
        return downButton;
    }
    public JButton getRightButton(){
        return rightButton;
    }
    public JButton getLeftButton(){
        return leftButton;
    }
    public JButton getRuajLojenButton(){
        return ruajLojenButton;
    }
    public JButton getNgarkoLojenButton(){
        return ngarkoLojenButton;
    }
    public JButton getExitButton(){
        return exitButton;
    }
    public JButton getPerfundoLojenButton(){
        return perfundoLojenButton;
    }

    public void updateThesarin(int thesaret){
        thesaretLabel.setText("Thesaret: "+thesaret);

    }
    public void updatePiket(int piket){
        piketLabel.setText("Piket: "+piket);

    }
    public void updateNivelin(int niveli){
        niveliLabel.setText("Niveli "+niveli);

    }
    public void updatePerfundoLojenButton(boolean isVisible){
        perfundoLojenButton.setVisible(isVisible);

    }


    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(menuBackground, 0, 0, null);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0,5,800);


    }


}
