/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.util.Random;

/**
 *
 * @author Geri
 */
public class GameEngine {

    private int SIZE;
    private Field[][] fields;
    private int rounds;
    boolean white = false;
    int whitee;
    int black;

    public GameEngine(int size) { //konstruktor
        this.SIZE = size;
        rounds = 5 * SIZE;

        fields = new Field[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                fields[i][j] = new Field();
            }
        }
    }

    public String getRounds() { // a kör megjelenítése
        return rounds + " round(s) remaining.";
    }

    public int getRoundsInt() {
        return rounds;
    }

    public void placeFields() { // random lerakja a játékosok kavicsait

        Random random = new Random();

        int x = random.nextInt(SIZE);
        int y = random.nextInt(SIZE);

        int white = SIZE;
        int black = SIZE;
        while (white > 0 || black > 0) {
            if (getFieldStatus(x, y) == 0) {
                if (white > 0) {
                    fields[x][y].setWho(Player.WHITE);
                    white--;

                    x = random.nextInt(SIZE);
                    y = random.nextInt(SIZE);
                } else {
                    fields[x][y].setWho(Player.BLACK);
                    black--;

                    x = random.nextInt(SIZE);
                    y = random.nextInt(SIZE);
                }

            } else {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            }
        }
    }

    public int getFieldStatus(int x, int y) { // egy adott mező státuszát foglalttá teszi
        if (fields[x][y].getWho().equals(Player.BLACK)) {
            return 1;
        }
        if (fields[x][y].getWho().equals(Player.WHITE)) {
            return 2;
        }
        return 0;
    }

    public void moveLeft(int i, int j) {
        if ((fields[i][j].getWho().equals(Player.WHITE) && white == false) || (fields[i][j].getWho().equals(Player.BLACK) && white == true)) {

        } else {
            if (j >= 0 && (fields[i][j].getWho().equals(Player.WHITE) && white == true)) { // ha a fehér játékos következik
                white = false;

                if (fields[i][j - 1].getWho().equals(Player.BLACK) || fields[i][j - 1].getWho().equals(Player.WHITE)) { // a balra van szomszédja
                    int count = 0;
                    //amikor több szomszédja is van
                    for (int k = 1; j - k > 0; k++) {
                        if (fields[i][j - k].getWho().equals(Player.BLACK) || fields[i][j - k].getWho().equals(Player.WHITE)) {
                            count++;
                        } else {
                            break;
                        }
                    }
                    //sorba eltolja őket
                    while (count >= 0) {
                        if (j - count - 1 < 0) {
                            fields[i][j - count].setWho(Player.NOBODY); // ez kitörli azt a kavicsot ami leesne és a többit továbbtolja

                        } else {

                            fields[i][j - count - 1].setWho(fields[i][j - count].getWho());

                        }
                        count--;
                    }
                    fields[i][j].setWho(Player.NOBODY);
                    rounds--;
                } else { //ha nincs szomszédja
                    fields[i][j - 1].setWho(fields[i][j].getWho());
                    fields[i][j].setWho(Player.NOBODY);
                    rounds--;
                }

            }
            if ((fields[i][j].getWho().equals(Player.BLACK) && white == false)) { // ha a fekete játkos következik
                white = true;
                if (fields[i][j - 1].getWho().equals(Player.BLACK) || fields[i][j - 1].getWho().equals(Player.WHITE)) { // ha balra van szomszédja

                    int count = 0;
                    for (int k = 1; j - k > 0; k++) {
                        if (fields[i][j - k].getWho().equals(Player.BLACK) || fields[i][j - k].getWho().equals(Player.WHITE)) {
                            count++;
                        } else {
                            break;
                        }
                    }

                    while (count >= 0) {
                        if (j - count - 1 < 0) {
                            fields[i][j - count].setWho(Player.NOBODY);

                        } else {

                            fields[i][j - count - 1].setWho(fields[i][j - count].getWho());

                        }
                        count--;
                    }
                    fields[i][j].setWho(Player.NOBODY);
                    rounds--;
                } else {
                    fields[i][j - 1].setWho(fields[i][j].getWho());
                    fields[i][j].setWho(Player.NOBODY);
                    rounds--;
                }
            }
        }
    }

    public void moveRight(int i, int j) {
        if ((fields[i][j].getWho().equals(Player.WHITE) && white == false) || (fields[i][j].getWho().equals(Player.BLACK) && white == true)) {

        } else {
            if (j >= 0 && (fields[i][j].getWho().equals(Player.WHITE) && white == true)) {
                white = false;

                if (fields[i][j + 1].getWho().equals(Player.BLACK) || fields[i][j + 1].getWho().equals(Player.WHITE)) {
                    int count = 0;
                    for (int k = 1; j + k < SIZE - 1; k++) {
                        if (fields[i][j + k].getWho().equals(Player.BLACK) || fields[i][j + k].getWho().equals(Player.WHITE)) {
                            count++;
                        } else {
                            break;
                        }
                    }

                    while (count >= 0) {

                        if (j + count + 1 > SIZE - 1) {
                            fields[i][j + count].setWho(Player.NOBODY);

                        } else {

                            fields[i][j + count + 1].setWho(fields[i][j + count].getWho());

                        }
                        count--;

                    }

                    fields[i][j].setWho(Player.NOBODY);
                    rounds--;
                } else {
                    fields[i][j + 1].setWho(fields[i][j].getWho());
                    fields[i][j].setWho(Player.NOBODY);
                    rounds--;
                }

            }
            if ((fields[i][j].getWho().equals(Player.BLACK) && white == false)) {
                white = true;
                if (fields[i][j + 1].getWho().equals(Player.BLACK) || fields[i][j + 1].getWho().equals(Player.WHITE)) {
                    int count = 0;
                    for (int k = 1; j + k < SIZE - 1; k++) {
                        if (fields[i][j + k].getWho().equals(Player.BLACK) || fields[i][j + k].getWho().equals(Player.WHITE)) {
                            count++;
                        } else {
                            break;
                        }
                    }

                    while (count >= 0) {

                        if (j + count + 1 > SIZE - 1) {
                            fields[i][j + count].setWho(Player.NOBODY);

                        } else {

                            fields[i][j + count + 1].setWho(fields[i][j + count].getWho());

                        }
                        count--;

                    }

                    fields[i][j].setWho(Player.NOBODY);
                    rounds--;
                } else {
                    fields[i][j + 1].setWho(fields[i][j].getWho());
                    fields[i][j].setWho(Player.NOBODY);
                    rounds--;
                }
            }
        }
    }

    public void moveUp(int i, int j) {
        if ((fields[i][j].getWho().equals(Player.WHITE) && white == false) || (fields[i][j].getWho().equals(Player.BLACK) && white == true)) {

        } else {
            if (i >= 0 && (fields[i][j].getWho().equals(Player.WHITE) && white == true)) {
                white = false;
                if (fields[i - 1][j].getWho().equals(Player.BLACK) || fields[i - 1][j].getWho().equals(Player.WHITE)) {

                    int count = 0;
                    for (int k = 1; i - k > 0; k++) {
                        if (fields[i - k][j].getWho().equals(Player.BLACK) || fields[i - k][j].getWho().equals(Player.WHITE)) {
                            count++;
                        } else {
                            break;
                        }
                    }

                    while (count >= 0) {
                        if (i - count - 1 < 0) {
                            fields[i - count][j].setWho(Player.NOBODY);

                        } else {

                            fields[i - count - 1][j].setWho(fields[i - count][j].getWho());

                        }
                        count--;
                    }
                    fields[i][j].setWho(Player.NOBODY);
                    rounds--;
                } else {
                    fields[i - 1][j].setWho(fields[i][j].getWho());
                    fields[i][j].setWho(Player.NOBODY);
                    rounds--;
                }
            }
            if (i >= 0 && (fields[i][j].getWho().equals(Player.BLACK) && white == false)) {
                white = true;
                if (fields[i - 1][j].getWho().equals(Player.BLACK) || fields[i - 1][j].getWho().equals(Player.WHITE)) {
                    int count = 0;
                    for (int k = 1; i - k > 0; k++) {
                        if (fields[i - k][j].getWho().equals(Player.BLACK) || fields[i - k][j].getWho().equals(Player.WHITE)) {
                            count++;
                        } else {
                            break;
                        }
                    }

                    while (count >= 0) {
                        if (i - count - 1 < 0) {
                            fields[i - count][j].setWho(Player.NOBODY);

                        } else {

                            fields[i - count - 1][j].setWho(fields[i - count][j].getWho());

                        }
                        count--;
                    }
                    fields[i][j].setWho(Player.NOBODY);
                    rounds--;
                } else {
                    fields[i - 1][j].setWho(fields[i][j].getWho());
                    fields[i][j].setWho(Player.NOBODY);
                    rounds--;
                }
            }

        }
    }

    public void moveDown(int i, int j) {
        if ((fields[i][j].getWho().equals(Player.WHITE) && white == false) || (fields[i][j].getWho().equals(Player.BLACK) && white == true)) {

        } else {
            if (j >= 0 && (fields[i][j].getWho().equals(Player.WHITE) && white == true)) {
                white = false;

                if (fields[i + 1][j].getWho().equals(Player.BLACK) || fields[i + 1][j].getWho().equals(Player.WHITE)) {

                    int count = 0;
                    for (int k = 1; i + k < SIZE - 1; k++) {
                        if (fields[i + k][j].getWho().equals(Player.BLACK) || fields[i + k][j].getWho().equals(Player.WHITE)) {
                            count++;
                        } else {
                            break;
                        }
                    }

                    while (count >= 0) {

                        if (i + count + 1 > SIZE - 1) {
                            fields[i + count][j].setWho(Player.NOBODY);

                        } else {

                            fields[i + count + 1][j].setWho(fields[i + count][j].getWho());

                        }
                        count--;

                    }

                    fields[i][j].setWho(Player.NOBODY);
                    rounds--;
                } else {
                    fields[i + 1][j].setWho(fields[i][j].getWho());
                    fields[i][j].setWho(Player.NOBODY);
                    rounds--;
                }

            }
            if ((fields[i][j].getWho().equals(Player.BLACK) && white == false)) {
                white = true;
                if (fields[i + 1][j].getWho().equals(Player.BLACK) || fields[i + 1][j].getWho().equals(Player.WHITE)) {
                    int count = 0;
                    for (int k = 1; i + k < SIZE - 1; k++) {
                        if (fields[i + k][j].getWho().equals(Player.BLACK) || fields[i + k][j].getWho().equals(Player.WHITE)) {
                            count++;
                        } else {
                            break;
                        }
                    }

                    while (count >= 0) {
                        if (i + count + 1 > SIZE - 1) {
                            fields[i + count][j].setWho(Player.NOBODY);

                        } else {

                            fields[i + count + 1][j].setWho(fields[i + count][j].getWho());

                        }
                        count--;
                    }
                    fields[i][j].setWho(Player.NOBODY);
                    rounds--;
                } else {
                    fields[i + 1][j].setWho(fields[i][j].getWho());
                    fields[i][j].setWho(Player.NOBODY);
                    rounds--;
                }
            }
        }
    }

    public int gameOver() {

        if (getRoundsInt() == 0) {

            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (fields[i][j].getWho().equals(Player.BLACK)) {
                        black++;
                    }
                    if (fields[i][j].getWho().equals(Player.WHITE)) {
                        whitee++;
                    }
                }
            }
        }

        if (black > whitee) {
            return 2;
        } else if (whitee > black) {
            return 1;
        } else {
            if (getRoundsInt() == 0) {
                return 3;
            }
        }
        return 0;
    }
}
