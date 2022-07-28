package Menu;

import Rozgrywka.Mecz_pve;
import Rozgrywka.PvE;
import Rozgrywka.PvP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Wybor_trybu {

    private JFrame ramka = new JFrame();

    public Wybor_trybu() {
        ramka.setTitle("Wybór trybu gry");
        ramka.setSize(300,200);

        JPanel info = new JPanel();
        ramka.getContentPane().add(info);

        JLabel komunikat = new JLabel("Wybierz tryb gry");
        info.add(komunikat);

        JPanel panel = new JPanel();
        ramka.getContentPane().add(panel);

        panel.setLayout(new GridLayout(2, 1));

        JButton pve = new JButton("1 gracz");
        JButton pvp = new JButton("2 graczy");
        panel.add(pve);
        panel.add(pvp);
        pve.addActionListener(new open_pve());
        pvp.addActionListener(new open_pvp());


        ramka.getContentPane().add(BorderLayout.NORTH, info);
        ramka.getContentPane().add(BorderLayout.CENTER, panel);

        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ramka.setVisible(true);
    }

    class open_pve implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           ramka.dispose();
           new PvE();
        }
    }

    class open_pvp implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ramka.dispose();
            new PvP();
        }
    }
}
