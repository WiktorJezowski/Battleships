package Rozgrywka.Funkcje;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Funkcje {

    private static Random random = new Random();

    public static boolean [][] generuj() {
        boolean [][] d = new boolean[10][10];
        boolean [][] s = new boolean[10][10];

        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                d[i][j]=true;
                s[i][j]=false;
            }
        }
        if(!umiesc(4,d,s)) return generuj();
        if(!umiesc(3,d,s)) return generuj();
        if(!umiesc(3,d,s)) return generuj();
        if(!umiesc(2,d,s)) return generuj();
        if(!umiesc(2,d,s)) return generuj();
        if(!umiesc(2,d,s)) return generuj();
        if(!umiesc(1,d,s)) return generuj();
        if(!umiesc(1,d,s)) return generuj();
        if(!umiesc(1,d,s)) return generuj();
        if(!umiesc(1,d,s)) return generuj();

//        umiesc(3,d,s);
//        umiesc(3,d,s);
//        umiesc(2,d,s);
//        umiesc(2,d,s);
//        umiesc(2,d,s);
//        umiesc(1,d,s);
//        umiesc(1,d,s);
//        umiesc(1,d,s);
//        umiesc(1,d,s);
        return s;
    }

    public static boolean umiesc(int x, boolean [][] d, boolean [][] s) {
        int l=0;
        if(x>1) {
            for(int i=0; i<10; i++) {
                for(int j=0; j<10; j++) {
                    for(int k=1; k<5; k++) {
                        if(czy_pasuje(x,d,i,j,k)) l++;
                    }
                }
            }
            if(l==0) return false;
            int y = random.nextInt(l);
            for(int i=0; i<10; i++) {
                for(int j=0; j<10; j++) {
//                    poprawić boolean
                    boolean temp=true;
                    if(czy_pasuje(x,d,i,j,1)) {
                        if(y==0) {
                            for(int k=0; k<x; k++) {
                                s[i-k][j]=true;
                            }
                            for(int m=Math.max(0,i-x); m<=Math.min(9,i+1); m++) {
                                for(int k=Math.max(0,j-1); k<=Math.min(9,j+1); k++) {
                                    d[m][k]=false;
                                }
                            }
//                            System.out.println(i + " " + j + " " + 1);
                            i=10;
                            j=10;
                            temp=false;
                        }
                        y--;
                    }
                    if(temp && czy_pasuje(x,d,i,j,2)) {
                        if(y==0) {
                            for(int k=0; k<x; k++) {
                                s[i][j-k]=true;
                            }
                            for(int m=Math.max(0,i-1); m<=Math.min(9,i+1); m++) {
                                for(int k=Math.max(0,j-x); k<=Math.min(9,j+1); k++) {
                                    d[m][k]=false;
                                }
                            }
//                            System.out.println(i + " " + j + " " + 2);
                            i=10;
                            j=10;
                            temp=false;
                        }
                        y--;
                    }
                    if(temp && czy_pasuje(x,d,i,j,3)) {
                        if(y==0) {
                            for(int k=0; k<x; k++) {
                                s[i+k][j]=true;
                            }
                            for(int m=Math.max(0,i-1); m<=Math.min(9,i+x); m++) {
                                for(int k=Math.max(0,j-1); k<=Math.min(9,j+1); k++) {
                                    d[m][k]=false;
                                }
                            }
//                            System.out.println(i + " " + j + " " + 3);
                            i=10;
                            j=10;
                            temp=false;
                        }
                        y--;
                    }
                    if(temp && czy_pasuje(x,d,i,j,4)) {
                        if(y==0) {
                            for(int k=0; k<x; k++) {
                                s[i][j+k]=true;
                            }
                            for(int m=Math.max(0,i-1); m<=Math.min(9,i+1); m++) {
                                for(int k=Math.max(0,j-1); k<=Math.min(9,j+x); k++) {
                                    d[m][k]=false;
                                }
                            }
//                            System.out.println(i + " " + j + " " + 4);
                            i=10;
                            j=10;
                        }
                        y--;
                    }
                }
            }
            return true;
        }
        for(int i=0; i<10; i++) {
            for (int j = 0; j < 10; j++) {
                if(d[i][j]) l++;
            }
        }
        if(l==0) return false;
        int y = random.nextInt(l);
        for(int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                if(d[i][j]) {
                    if(y==0) {
                        s[i][j]=true;
                        for(int k=Math.max(0,i-1); k<=Math.min(9,i+1); k++) {
                            for(int m=Math.max(0,j-1); m<=Math.min(9,j+1); m++) {
                                d[k][m]=false;
                            }
                        }
//                        System.out.println(i + " " + j);
                        return true;
                    }
                    y--;
                }
            }
        }
        return true;
    }

    public static boolean czy_pasuje(int x, boolean [][] d, int a, int b, int k) {
        if(!d[a][b])    return false;
        switch (k) {
            case 1: {
                if(a>=x-1) {
                    for(int i=1; i<x; i++) {
                        if(!d[a-i][b])  return false;
                    }
                }
                else return false;
            } break;
            case 2: {
                if(b>=x-1) {
                    for(int i=1; i<x; i++) {
                        if(!d[a][b-i])  return false;
                    }
                }
                else return false;
            } break;
            case 3: {
                if(a<11-x) {
                    for(int i=1; i<x; i++) {
                        if(!d[a+i][b])  return false;
                    }
                }
                else return false;
            } break;
            case 4: {
                if(b<11-x) {
                    for(int i=1; i<x; i++) {
                        if(!d[a][b+i])  return false;
                    }
                }
                else return false;
            } break;
        }
        return true;
    }

    public static boolean sprawdz(JButton[][] p) {
        int [] s = {4,3,2,1};

        boolean [][] stan = new boolean[10][10];
        boolean [][] odwiedzone = new boolean[10][10];
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                odwiedzone[i][j]=false;
                if(p[i][j].getText().equals("S")) {
                    stan[i][j]=true;
                }
                else stan[i][j]=false;
            }
        }
//do uogólnienia
        for(int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                if(stan[i][j] && !odwiedzone[i][j]) {
                    if(i<9 && ((j>0 && stan[i+1][j-1]) || (j<9 && stan[i+1][j+1])))
                        return false;
                    if(j<9 && stan[i][j+1]) {
                        odwiedzone[i][j+1]=true;
                        if(i<9 && (stan[i+1][j] || (j<8 && stan[i+1][j+2])))
                            return false;
                        if(j<8 && stan[i][j+2]) {
                            odwiedzone[i][j+2]=true;
                            if(i<9 && j<7 && stan[i+1][j+3])
                                return false;
                            if(j<7 && stan[i][j+3]) {
                                odwiedzone[i][j+3]=true;
                                if(i<9 && j<6 && (stan[i+1][j+4] || stan[i][j+4]))
                                    return false;
//                                if(j<6 && stan[i][j+4])
//                                    return false;
                                s[3]--;
                            }
                            else s[2]--;
                        }
                        else s[1]--;
                    }
                    else {
                        if(i<9 && stan[i+1][j]) {
                            odwiedzone[i+1][j]=true;
                            if(i<8 && ((j>0 && stan[i+2][j-1]) || (j<9 && stan[i+2][j+1])))
                                return false;
                            if(i<8 && stan[i+2][j]) {
                                odwiedzone[i+2][j]=true;
                                if(i<7 && ((j>0 && stan[i+3][j-1]) || (j<9 && stan[i+3][j+1])))
                                    return false;
                                if(i<7 && stan[i+3][j]) {
                                    odwiedzone[i+3][j]=true;
                                    if(i<6 && ((j>0 && stan[i+4][j-1]) || (j<9 && stan[i+4][j+1]) || stan[i+4][j]))
                                        return false;
                                    s[3]--;
                                }
                                else s[2]--;
                            }
                            else s[1]--;
                        }
                        else s[0]--;
                    }
                }
            }
        }

//        for(int i=0; i<4; i++) {
//            System.out.println(s[i]);
//        }

        for(int i=0; i<4; i++) {
            if(s[i]!=0)
                return false;
        }
        return true;
    }

    public static int [] getXY(Object x, JButton [][] p) {
        int [] xy = new int[2];
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                if(x == p[i][j]) {
                    xy[0]=i;
                    xy[1]=j;
                    return xy;
                }
            }
        }
        return xy;
    }

    public static boolean [] oznacz(int x, int y, JButton [][] w, JButton [][] p, int [] s) {
//        if(x>9 || y>9) System.out.println(x + " " + y);
        if(p[x][y].getBackground().equals(Color.WHITE)) {
            p[x][y].setText("X");
            w[x][y].setText("X");
            p[x][y].setForeground(Color.RED);
            w[x][y].setForeground(Color.RED);
            return new boolean[] {false,false};
        }
        w[x][y].setBackground(Color.RED);
        p[x][y].setBackground(Color.RED);
        int a=0, b=0;
        while(x-a>0 && (p[x-a-1][y].getBackground().equals(Color.BLACK) || p[x-a-1][y].getBackground().equals(Color.RED))) {
            a++;
        }
        if(x-a>0 && p[x-a-1][y].getBackground().equals(Color.GRAY))
            return new boolean[] {true,false};
        while(x+b<9 && (p[x+b+1][y].getBackground().equals(Color.BLACK) || p[x+b+1][y].getBackground().equals(Color.RED))) {
            b++;
        }
        if(x+b<9 && p[x+b+1][y].getBackground().equals(Color.GRAY))
            return new boolean[] {true,false};
        if(a>0 || b>0) {
            s[a+b]--;
            if(x-a>0) {
                if(w[x-a-1][y].getText().equals(" ")) {
                    w[x-a-1][y].setText("X");
                    p[x-a-1][y].setText("X");
                    w[x-a-1][y].setForeground(Color.RED);
                    p[x-a-1][y].setForeground(Color.RED);
                }
            }
            if(x+b<9) {
                if(w[x+b+1][y].getText().equals(" ")) {
                    w[x+b+1][y].setText("X");
                    p[x+b+1][y].setText("X");
                    w[x+b+1][y].setForeground(Color.RED);
                    p[x+b+1][y].setForeground(Color.RED);
                }
            }
            for(int i=Math.max(0,x-a-1); i<=Math.min(9,x+b+1); i++) {
                if(y>0) {
                    if(w[i][y-1].getText().equals(" ")) {
                        w[i][y-1].setText("X");
                        p[i][y-1].setText("X");
                        w[i][y-1].setForeground(Color.RED);
                        p[i][y-1].setForeground(Color.RED);
                    }
                }
                if(y<9) {
                    if(w[i][y+1].getText().equals(" ")) {
                        w[i][y+1].setText("X");
                        p[i][y+1].setText("X");
                        w[i][y+1].setForeground(Color.RED);
                        p[i][y+1].setForeground(Color.RED);
                    }
                }
            }
            return new boolean[] {true,true};
        }

        while(y-a>0 && (p[x][y-a-1].getBackground().equals(Color.BLACK) || p[x][y-a-1].getBackground().equals(Color.RED))) {
            a++;
        }
        if(y-a>0 && p[x][y-a-1].getBackground().equals(Color.GRAY))
            return new boolean[] {true,false};
        while(y+b<9 && (p[x][y+b+1].getBackground().equals(Color.BLACK) || p[x][y+b+1].getBackground().equals(Color.RED))) {
            b++;
        }
        if(y+b<9 && p[x][y+b+1].getBackground().equals(Color.GRAY))
            return new boolean[] {true,false};
        s[a+b]--;
        if(y-a>0) {
            if(w[x][y-a-1].getText().equals(" ")) {
                w[x][y-a-1].setText("X");
                p[x][y-a-1].setText("X");
                w[x][y-a-1].setForeground(Color.RED);
                p[x][y-a-1].setForeground(Color.RED);
            }
        }
        if(y+b<9) {
            if(w[x][y+b+1].getText().equals(" ")) {
                w[x][y+b+1].setText("X");
                p[x][y+b+1].setText("X");
                w[x][y+b+1].setForeground(Color.RED);
                p[x][y+b+1].setForeground(Color.RED);
            }
        }
        for(int i=Math.max(0,y-a-1); i<=Math.min(9,y+b+1); i++) {
            if(x>0) {
                if(w[x-1][i].getText().equals(" ")) {
                    w[x-1][i].setText("X");
                    p[x-1][i].setText("X");
                    w[x-1][i].setForeground(Color.RED);
                    p[x-1][i].setForeground(Color.RED);
                }
            }
            if(x<9) {
                if(w[x+1][i].getText().equals(" ")) {
                    w[x+1][i].setText("X");
                    p[x+1][i].setText("X");
                    w[x+1][i].setForeground(Color.RED);
                    p[x+1][i].setForeground(Color.RED);
                }
            }
        }
        return new boolean[] {true,true};

//        if((x>0 && !p[x-1][y].getBackground().equals(Color.WHITE)) || (x<9 && !p[x+1][y].getBackground().equals(Color.WHITE))) {
//            int a=1;
//            int b=1;
//            boolean temp=true;
//            while(x-a>=0 && !p[x-a][y].getBackground().equals(Color.WHITE)) {
//                a++;
//            }
//            while(x-a+b<10 && p[x-a+b])
//        }

    }

    public static void sleep(int x) {
        try {
            Thread.currentThread().sleep(x);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean czy_koniec(int [] s) {
        for (int i = 0; i < 4; i++) {
            if (s[i] > 0) return false;
        }
        return true;
    }

    public static int getMax(int [] s) {
        if(s[3]>0) return 4;
        if(s[2]>0) return 3;
        if(s[1]>0) return 2;
        if(s[0]>0) return 1;
        return 0;
    }

    public static int [] SI_wybor(JButton [][] w, int max) {
        boolean [][] d = new boolean[10][10];
        int [][] m = new int[10][10];
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                if(w[i][j].getBackground().equals(Color.WHITE) && w[i][j].getText().equals(" ")) {
                    d[i][j]=true;
                }
                else d[i][j]=false;
                m[i][j]=0;
            }
        }
        for(int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                if(czy_pasuje(max,d,i,j,1)) {
                    for(int k=0; k<max; k++) {
                        m[i-k][j]++;
                    }
                }
                if(czy_pasuje(max,d,i,j,2)) {
                    for(int k=0; k<max; k++) {
                        m[i][j-k]++;
                    }
                }
                if(czy_pasuje(max,d,i,j,3)) {
                    for(int k=0; k<max; k++) {
                        m[i+k][j]++;
                    }
                }
                if(czy_pasuje(max,d,i,j,4)) {
                    for(int k=0; k<max; k++) {
                        m[i][j+k]++;
                    }
                }
            }
        }
        int l=0;
        for(int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                m[i][j]/=2;
                if(i==0) m[i][j]*=2;
                if(i==9) m[i][j]*=2;
                if(j==0) m[i][j]*=2;
                if(j==9) m[i][j]*=2;
                m[i][j]=m[i][j]*m[i][j];
                l+=m[i][j];
            }
        }
        int r = random.nextInt(l);
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                r-=m[i][j];
                if(r<0) return new int[] {i,j};
            }
        }
        return null;
    }

    public static int [] dobij(JButton [][] w, int max, int x, int y) {
        if ((x>0 && (w[x-1][y].getBackground().equals(Color.BLACK) || w[x-1][y].getBackground().equals(Color.RED)))
                || (x<9 && (w[x+1][y].getBackground().equals(Color.BLACK) || w[x+1][y].getBackground().equals(Color.RED)))) {
            int x_p = x, x_k = x;
            while (x_p>0 && (w[x_p-1][y].getBackground().equals(Color.BLACK) || w[x_p-1][y].getBackground().equals(Color.RED))) x_p--;
            while (x_k<9 && (w[x_k+1][y].getBackground().equals(Color.BLACK) || w[x_k+1][y].getBackground().equals(Color.RED))) x_k++;
            if (x_p==0 || w[x_p-1][y].getText().equals("X")) {
                return new int[]{x_k + 1, y};
            }
            if (x_k == 9 || w[x_k + 1][y].getText().equals("X")) {
                return new int[]{x_p - 1, y};
            }
            if (x_k - x_p == 2 || max < 4) {
                if (random.nextInt(2) == 0) return new int[]{x_p - 1, y};
                return new int[]{x_k + 1, y};
            }
            if (x_p > 1 && w[x_p - 2][y].getText().equals(" ")) {
                if (x_k < 8 && w[x_k + 2][y].getText().equals(" ")) {
                    if (random.nextInt(2) == 0) return new int[]{x_p - 1, y};
                    return new int[]{x_k + 1, y};
                }
                return new int[]{x_p - 1, y};
            }
            if (x_k < 8 && w[x_k + 2][y].getText().equals(" ")) {
                return new int[]{x_k + 1, y};
            }
            if (random.nextInt(2) == 0) return new int[]{x_p - 1, y};
            return new int[]{x_k + 1, y};
        }

        if ((y>0 && (w[x][y-1].getBackground().equals(Color.BLACK) || w[x][y-1].getBackground().equals(Color.RED)))
                || (y<9 && (w[x][y+1].getBackground().equals(Color.BLACK) || w[x][y+1].getBackground().equals(Color.RED)))) {
            int y_p = y, y_k = y;
            while (y_p > 0 && (w[x][y_p-1].getBackground().equals(Color.BLACK) || w[x][y_p-1].getBackground().equals(Color.RED))) y_p--;
            while (y_k < 9 && (w[x][y_k+1].getBackground().equals(Color.BLACK) || w[x][y_k+1].getBackground().equals(Color.RED))) y_k++;
            if (y_p==0 || w[x][y_p-1].getText().equals("X")) {
                return new int[]{x, y_k+1};
            }
            if (y_k==9 || w[x][y_k+1].getText().equals("X")) {
                return new int[]{x, y_p-1};
            }
            if (y_k - y_p == 2 || max < 4) {
                if (random.nextInt(2) == 0) return new int[]{x, y_p - 1};
                return new int[]{x, y_k + 1};
            }
            if (y_p > 1 && w[x][y_p - 2].getText().equals(" ")) {
                if (y_k < 8 && w[x][y_k + 2].getText().equals(" ")) {
                    if (random.nextInt(2) == 0) return new int[]{x, y_p - 1};
                    return new int[]{x, y_k + 1};
                }
                return new int[]{x, y_p - 1};
            }
            if (y_k < 8 && w[x][y_k + 2].getText().equals(" ")) {
                return new int[]{x, y_k + 1};
            }
            if (random.nextInt(2) == 0) return new int[]{x, y_p - 1};
            return new int[]{x, y_k + 1};
        }

        boolean[][] d = new boolean[10][10];
        int m = max;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (w[i][j].getText().equals(" ")) {
                    d[i][j] = true;
                } else d[i][j] = false;
            }
        }
        while (m > 1) {
            int l = 0;
            for (int k = 1; k < 5; k++) {
                if (czy_pasuje(m, d, x, y, k)) l++;
            }
            if (l == 0) m--;
            else {
                int r = random.nextInt(l);
                if (czy_pasuje(m, d, x, y, 1)) {
                    r--;
                    if (r < 0) return new int[]{x-1, y};
                }
                if (czy_pasuje(m, d, x, y, 2)) {
                    r--;
                    if (r<0) return new int[]{x, y-1};
                }
                if (czy_pasuje(m, d, x, y, 3)) {
                    r--;
                    if (r < 0) return new int[]{x+1, y};
                }
                return new int[]{x, y+1};
            }
        }
        return null;
    }

    public static void przekolorowanie(JButton[][] w, JButton[][] p) {
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                if(p[i][j].getBackground().equals(Color.RED)) {
                    p[i][j].setBackground(Color.BLACK);
                }
                if(p[i][j].getForeground().equals(Color.RED)) {
                    p[i][j].setForeground(Color.BLACK);
                }
                if(w[i][j].getBackground().equals(Color.RED)) {
                    w[i][j].setBackground(Color.BLACK);
                }
                if(w[i][j].getForeground().equals(Color.RED)) {
                    w[i][j].setForeground(Color.BLACK);
                }
            }
        }
    }
}

