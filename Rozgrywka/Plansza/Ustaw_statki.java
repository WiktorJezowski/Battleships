package Rozgrywka.Plansza;

import Rozgrywka.Funkcje.Funkcje;
import Rozgrywka.Gra;
import Rozgrywka.PvE;
import Rozgrywka.PvP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ustaw_statki {

    public Gra g;
    public JButton [][] w;
    public boolean temp;

    public Ustaw_statki(Gra g, boolean temp, JButton [][] w) {
        this.g=g;
        this.w=w;
        this.temp=temp;
        if(g instanceof PvE)
            g.ramka.setTitle("Rozstawienie statków");
        else {
            if(temp) g.ramka.setTitle("Gracz 2: rozstawienie");
            else g.ramka.setTitle("Gracz 1: rozstawienie");
        }

        g.ramka.setSize(500,550);
        g.komunikat.setText("Rozmieść swoje statki");
        g.plansza1.setLayout(new GridLayout(10,10));

        for (int i=0; i<10; i++) {
            for (int j = 0; j < 10; j++) {
                w[i][j] = new JButton(" ");
                w[i][j].setBackground(Color.WHITE);
                g.plansza1.add(w[i][j]);
                w[i][j].addActionListener(new Akcja());
            }
        }

        g.plansza2.setLayout(new BoxLayout(g.plansza2, BoxLayout.LINE_AXIS));

        JButton reset = new JButton("Reset");
        reset.addActionListener(new reset_b());
        g.plansza2.add(reset);
        JButton losuj = new JButton("Losuj");
        losuj.addActionListener(new losuj_b());
        g.plansza2.add(losuj);
        JButton ok = new JButton("Zakończ");
        ok.addActionListener(new ok_b());
        g.plansza2.add(ok);

        g.ramka.getContentPane().add(BorderLayout.NORTH, g.info);
        g.ramka.getContentPane().add(BorderLayout.CENTER, g.plansza1);
        g.ramka.getContentPane().add(BorderLayout.SOUTH, g.plansza2);

        g.ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        g.ramka.setVisible(true);
    }

    class Akcja implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            if(b.getText().equals(" ")) {
                b.setText("S");
                b.setBackground(Color.BLACK);
            }
            else {
                b.setText(" ");
                b.setBackground(Color.WHITE);
            }
        }
    }

    class reset_b implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i=0; i<10; i++) {
                for (int j=0; j<10; j++) {
                    w[i][j].setText(" ");
                    w[i][j].setBackground(Color.WHITE);
                }
            }
            g.komunikat.setText("Rozmieść swoje statki");
        }
    }

    class losuj_b implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            boolean [][] s = Funkcje.generuj();
            for(int i=0; i<10; i++) {
                for(int j=0; j<10; j++) {
                    w[i][j].setText(" ");
                    w[i][j].setBackground(Color.WHITE);
                    if(s[i][j]) {
                        w[i][j].setText("S");
                        w[i][j].setBackground(Color.BLACK);
                    }
                }
            }
        }
    }

    class ok_b implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(Funkcje.sprawdz(w)) {
//                boolean [][] odp = new boolean[10][10];
//                for(int i=0; i<10; i++) {
//                    for(int j=0; j<10; j++) {
//                        if(g.w[i][j])
//                        odp[i][j]
//                    }
//                }
                g.plansza1.removeAll();
                g.plansza2.removeAll();
                if(g instanceof PvE) {
                    ((PvE) g).losuj_SI();
                }
                if(g instanceof PvP) {
                    if(temp) {
                        ((PvP) g).rozpocznij();
                    }
                    else {
                        ((PvP) g).przejscie_rozstaw();
                    }
                }
            }
            else {
                g.komunikat.setText("Niepoprawne rozmieszcznie");
            }
        }
    }
}
