package Rozgrywka;

import javax.swing.*;

public class Gra {

    public JFrame ramka = new JFrame();
    public JLabel komunikat = new JLabel("Rozstaw swoje statki");
    public JButton [][] p1 = new JButton[10][10];
    public JButton [][] p2 = new JButton[10][10];
    public JButton [][] w1 = new JButton[10][10];
    public JButton [][] w2 = new JButton[10][10];
    public int [] s1 = new int[4];
    public int [] s2 = new int[4];

    public JPanel info = new JPanel();
    public JPanel plansza1 = new JPanel();
    public JPanel plansza2 = new JPanel();

    public Gra() {
        info.add(komunikat);
        ramka.getContentPane().add(info);
        ramka.getContentPane().add(plansza1);
        ramka.getContentPane().add(plansza2);

        s1[0]=4;
        s1[1]=3;
        s1[2]=2;
        s1[3]=1;
        s2[0]=4;
        s2[1]=3;
        s2[2]=2;
        s2[3]=1;

//        for(int i=0; i<10; i++) {
//            for(int j=0; j<10; j++) {
//                p[i][j] = new JLabel(" ");
//                w[i][j] = new JButton(" ");
//            }
//        }
    }
}
