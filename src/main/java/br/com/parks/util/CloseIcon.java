/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.parks.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;

/**
 *
 * @author tiago
 */
public class CloseIcon implements Icon{

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Image img=Toolkit.getDefaultToolkit().getImage("/br/com/parks/icons/DeleteRed2.png");
//        g.setColor(Color.red);
//        g.drawLine(6, 6, getIconWidth()-7, getIconHeight()-7);
//        g.drawLine(getIconWidth()-7, 6, 6, getIconHeight()-7);
        g.drawImage(img, x, y, c);
    }

    @Override
    public int getIconWidth() {
        return 18;
    }

    @Override
    public int getIconHeight() {
        return 18;
    }
    
}
