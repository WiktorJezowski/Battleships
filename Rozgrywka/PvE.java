package Rozgrywka;

import Rozgrywka.Funkcje.Funkcje;
import Rozgrywka.Plansza.Ustaw_statki;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PvE extends Gra{

    public boolean temp=false;
    public int x0=20;
    public int y0=20;

    public PvE() {
        super();
        new Ustaw_statki(this,false,w1);
    }

    public void losuj_SI() {

        boolean [][] s = Funkcje.generuj();

        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {

                p1[i][j] = new JButton(" ");
                p2[i][j] = new JButton(" ");

                if(w1[i][j].getText().equals("S")) {
                    p1[i][j].setBackground(Color.GRAY);
                }
                else p1[i][j].setBackground(Color.WHITE);
                if(s[i][j]) {
                    p2[i][j].setBackground(Color.GRAY);
                }
                else p2[i][j].setBackground(Color.WHITE);

                w1[i][j] = new JButton(" ");
                w2[i][j] = new JButton(" ");
                w1[i][j].setBackground(Color.WHITE);
                w2[i][j].setBackground(Color.WHITE);
            }
        }

        plansza2.setLayout(new GridLayout(10,10));

//        plansza1.removeAll();
//        plansza2.removeAll();
        for(int i=0; i<10; i++) {
            for (int j = 0; j < 10; j++) {
                plansza1.add(p1[i][j]);
                plansza2.add(w1[i][j]);
            }
        }

//        ramka.getContentPane().removeAll();
        ramka.setTitle("Statki");
        ramka.setSize(900,520);
        ramka.getContentPane().add(BorderLayout.NORTH, info);
        ramka.getContentPane().add(BorderLayout.WEST, plansza1);
        ramka.getContentPane().add(BorderLayout.EAST, plansza2);

        for(int i=0; i<10; i++) {
            for (int j = 0; j < 10; j++) {
                w1[i][j].addActionListener(new Akcja());
            }
        }

        Random random = new Random();
        if(random.nextInt(2)==0) {
            ruch_gracza();
        }
        else ruch_SI();
    }

    class Akcja implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(temp) {
                JButton b = (JButton) e.getSource();
                if (b.getText().equals(" ") && b.getBackground().equals(Color.WHITE)) {
                    temp=false;
                    int [] xy = Funkcje.getXY(b,w1);
                    boolean [] o = Funkcje.oznacz(xy[0],xy[1],w1,p2,s1);
                    if(o[0]) {
                        if(o[1]) {
                            komunikat.setText("Zatopiony");
                        }
                        else komunikat.setText("Trafiony");
//                        Funkcje.sleep(200);
                        ruch_gracza();
                    }
                    else {
                        Funkcje.przekolorowanie(w1,p1);
                        komunikat.setText("Pudło");
//                        Funkcje.sleep(200);
                        ruch_SI();
                    }
                }
            }
        }
    }

    public void ruch_gracza() {
        if(Funkcje.czy_koniec(s1)) {
            komunikat.setText("Wygrana!");
            info.setBackground(Color.ORANGE);
            return;
        }
        komunikat.setText("Wybierz pole");
        temp=true;
    }

    public void ruch_SI() {
        if(Funkcje.czy_koniec(s2)) {
            plansza2.removeAll();
            for(int i=0; i<10; i++) {
                for(int j=0; j<10; j++) {
                    plansza2.add(p2[i][j]);
                }
            }
            komunikat.setText("Porażka!");
            info.setBackground(Color.RED);
            return;
        }
        komunikat.setText("Ruch przeciwnika");
//        Funkcje.sleep(1000);
        boolean [] o;
        int [] xy;
        if(x0==20)
            xy = Funkcje.SI_wybor(w2,Funkcje.getMax(s2));
        else
            xy = Funkcje.dobij(w2,Funkcje.getMax(s2),x0,y0);
        o = Funkcje.oznacz(xy[0],xy[1],w2,p1,s2);
        if(o[0]) {
            if(o[1]) {
                komunikat.setText("Zatopiony");
                x0=20;
                y0=20;
            }
            else {
                komunikat.setText("Trafiony");
                x0=xy[0];
                y0=xy[1];
            }
//            Funkcje.sleep(200);
            ruch_SI();
        }
        else {
            Funkcje.przekolorowanie(w2,p2);
            komunikat.setText("Pudło");
//            Funkcje.sleep(200);
            ruch_gracza();
        }
    }

}
