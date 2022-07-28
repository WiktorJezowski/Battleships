package Rozgrywka;

import Rozgrywka.Funkcje.Funkcje;
import Rozgrywka.Plansza.Ustaw_statki;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PvP extends Gra{

    public boolean temp1=false;
    public boolean temp2=false;

    public PvP() {
        super();
        new Ustaw_statki(this,false,w1);
    }

    public void przejscie_rozstaw() {
        ramka.setSize(200,100);

        JButton ok0 = new JButton("OK");
        ok0.addActionListener(new Zatwierdz0());
        plansza1.add(ok0);
    }

    public void rozstawienie2() {
        new Ustaw_statki(this,true,w2);
    }

    public void rozpocznij() {
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {

                p1[i][j] = new JButton(" ");
                p2[i][j] = new JButton(" ");

                if(w1[i][j].getText().equals("S")) {
                    p1[i][j].setBackground(Color.GRAY);
                }
                else p1[i][j].setBackground(Color.WHITE);

                if(w2[i][j].getText().equals("S")) {
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
        ramka.setTitle("Statki");
        ramka.setSize(900,520);
        ramka.getContentPane().add(BorderLayout.NORTH, info);
        ramka.getContentPane().add(BorderLayout.WEST, plansza1);
        ramka.getContentPane().add(BorderLayout.EAST, plansza2);

        for(int i=0; i<10; i++) {
            for (int j = 0; j < 10; j++) {
                w1[i][j].addActionListener(new Akcja());
                w2[i][j].addActionListener(new Akcja());
            }
        }

        Random random = new Random();
        if(random.nextInt(2)==0)
            temp1=true;
        else temp2=true;
        zmiana();
    }

    public void zmiana() {
        plansza1.removeAll();
        plansza2.removeAll();
        ramka.setSize(200,100);
        if(temp1)
            komunikat.setText("Ruch gracza nr 1");
        else komunikat.setText("Ruch gracza nr 2");
        JButton ok = new JButton("OK");
        ok.addActionListener(new Zatwierdz());
        plansza1.add(ok);
    }

    public void g1() {
        ramka.setSize(900,520);
        for(int i=0; i<10; i++) {
            for (int j = 0; j < 10; j++) {
                plansza1.add(p1[i][j]);
                plansza2.add(w1[i][j]);
            }
        }
    }

    public void g2() {
        ramka.setSize(900,520);
        for(int i=0; i<10; i++) {
            for (int j = 0; j < 10; j++) {
                plansza1.add(p2[i][j]);
                plansza2.add(w2[i][j]);
            }
        }
    }

    class Zatwierdz0 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            plansza1.removeAll();
            rozstawienie2();
        }
    }

    class Zatwierdz implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            plansza1.removeAll();
            if(temp1) {
                g1();
            }
            else g2();
        }
    }

    class Akcja implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            if (b.getText().equals(" ") && b.getBackground().equals(Color.WHITE)) {
                if(temp1) {
                    temp1=false;
                    int [] xy = Funkcje.getXY(b,w1);
                    boolean [] o = Funkcje.oznacz(xy[0],xy[1],w1,p2,s1);
                    if(o[0]) {
                        if(Funkcje.czy_koniec(s1)) {
                            koniec(true);
                        }
                        else {
                            if(o[1]) {
                                komunikat.setText("Zatopiony");
                            }
                            else komunikat.setText("Trafiony");
                            temp1=true;
                            g1();
                        }
                    }
                    else {
                        Funkcje.przekolorowanie(w1,p1);
                        komunikat.setText("Pudło");
                        temp2=true;
                        zmiana();
                    }
                }
                else if(temp2) {
                    temp2=false;
                    int [] xy = Funkcje.getXY(b,w2);
                    boolean [] o = Funkcje.oznacz(xy[0],xy[1],w2,p1,s2);
                    if(o[0]) {
                        if(Funkcje.czy_koniec(s1)) {
                            koniec(false);
                        }
                        else {
                            if(o[1]) {
                                komunikat.setText("Zatopiony");
                            }
                            else komunikat.setText("Trafiony");
                            temp2=true;
                            g2();
                        }
                    }
                    else {
                        Funkcje.przekolorowanie(w2,p2);
                        komunikat.setText("Pudło");
                        temp1=true;
                        zmiana();
                    }
                }
            }
        }
    }

    public void koniec(boolean temp) {
        plansza1.removeAll();
        plansza2.removeAll();
        for(int i=0; i<10; i++) {
            for (int j = 0; j < 10; j++) {
                plansza1.add(p1[i][j]);
                plansza2.add(p2[i][j]);
            }
        }
        if(temp) komunikat.setText("Wygrywa gracz nr 1");
        else komunikat.setText("Wygrywa gracz nr 2");
    }
}
