package Rozgrywka.Plansza;

import Rozgrywka.Funkcje.Funkcje;
import Rozgrywka.Mecz_pve;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rozstawienie {
    private static JFrame ramka;
    private static JLabel komunikat;
    private static JButton[][] p;

    public Rozstawienie(JFrame ramka, JLabel komunikat) {

    }
    public static void ustaw_statki(JFrame ramka, JLabel komunikat) {

        ramka.setTitle("Ustawienie statków");
        ramka.setSize(500,500);

        JPanel info = new JPanel();
        ramka.getContentPane().add(info);

        info.add(komunikat);

        JPanel plansza = new JPanel();
        ramka.getContentPane().add(plansza);

        plansza.setLayout(new GridLayout(10,10));

        p = new JButton[10][10];
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                p[i][j] = new JButton(" ");
                p[i][j].setBackground(Color.WHITE);
//                p[i][j].setSize(10,10);
                plansza.add(p[i][j]);
                p[i][j].addActionListener(new Akcja());
            }
        }

        JPanel wybor = new JPanel();
        ramka.getContentPane().add(wybor);
        wybor.setLayout(new BoxLayout(wybor, BoxLayout.LINE_AXIS));

        JButton reset = new JButton("Reset");
        reset.addActionListener(new reset_b());
        wybor.add(reset);
        JButton losuj = new JButton("Losuj");
        losuj.addActionListener(new losuj_b());
        wybor.add(losuj);
        JButton ok = new JButton("Zakończ");
        ok.addActionListener(new ok_b());
        wybor.add(ok);

        ramka.getContentPane().add(BorderLayout.NORTH, info);
        ramka.getContentPane().add(BorderLayout.CENTER, plansza);
        ramka.getContentPane().add(BorderLayout.SOUTH, wybor);

        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ramka.setVisible(true);
    }

    static class Akcja implements ActionListener {
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

    static class reset_b implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i=0; i<10; i++) {
                for (int j=0; j<10; j++) {
                    p[i][j].setText(" ");
                    p[i][j].setBackground(Color.WHITE);
                }
            }
            komunikat.setText("Rozmieść swoje statki");
        }
    }

    static class losuj_b implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            boolean [][] s = Funkcje.generuj();
            for(int i=0; i<10; i++) {
                for(int j=0; j<10; j++) {
                    p[i][j].setText(" ");
                    p[i][j].setBackground(Color.WHITE);
                    if(s[i][j]) {
                        p[i][j].setText("S");
                        p[i][j].setBackground(Color.BLACK);
                    }
                }
            }
        }
    }

    static class ok_b implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(Funkcje.sprawdz(p)) {
//                ramka.dispose();
//                zakończ
            }
            else {
                komunikat.setText("Niepoprawne rozmieszcznie");
            }
        }
    }
}
