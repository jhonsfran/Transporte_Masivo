/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.*;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

class BgPanel extends JPanel {
    Image bg = new ImageIcon("mapa_cali.jpg").getImage();
    private int x;
    private int y;
    
    public BgPanel(int x, int y){
       this.x=x;
       this.y=y;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
        g.setFont(Font.getFont(Font.SANS_SERIF));
        g.setColor(Color.red);
        g.fillRect(this.x, this.y, 4, 4);
        g.drawString("Posición actual", this.x + 10, this.y + 10);
        
    }
}

class LoginPanel extends JPanel {
    LoginPanel() {
        setOpaque(false);
        setLayout(new FlowLayout());
    }
}

public class FrameTestBase extends JFrame {

}