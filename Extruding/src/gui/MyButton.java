    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JButton;

/**
 *
 * @author Geri
 */
public class MyButton extends JButton{
    private int i;
    private int j;

    public MyButton(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }
    
    @Override
    public String toString(){
        return "Mybutton{" + "i=" + i + ", j=" + j + "}";
    }
     
}
