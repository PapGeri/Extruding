/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;


/**
 *
 * @author Geri
 */
public class Field {

    private Player who = Player.NOBODY;

    public Player getWho() {
        return who;
    }

    public void setWho(Player who) {
        this.who = who;
    }

}
