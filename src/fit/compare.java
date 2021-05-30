/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author User
 */
public class compare extends JFrame
{
    Container c;
    public compare(int all[],int mem[])
    {
        JLabel bestlabel,worstlabel,firstlabel,alllabel,memlabel;
        JLabel ab,aw,af,mb,mw,mf;
        
        setSize(700,400);
        setTitle("Algorithms Comparison");
        
        c=getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(0, 255, 191));
        
        bestlabel=new JLabel("Best Fit");
        bestlabel.setBounds(250, 70, 80, 30);
        bestlabel.setFont(new Font(null, Font.BOLD, 15));
        c.add(bestlabel);
        
        worstlabel=new JLabel("Worst Fit");
        worstlabel.setBounds(bestlabel.getX()+120, 70, 80, 30);
        worstlabel.setFont(new Font(null, Font.BOLD, 15));
        c.add(worstlabel);
        
        firstlabel=new JLabel("First Fit");
        firstlabel.setBounds(worstlabel.getX()+120, 70, 80, 30);
        firstlabel.setFont(new Font(null, Font.BOLD, 15));
        c.add(firstlabel);
        
        alllabel=new JLabel("Successful Allocation");
        alllabel.setBounds(60, 130, 170, 30);
        alllabel.setFont(new Font(null, Font.BOLD, 15));
        c.add(alllabel);
        
        memlabel=new JLabel("Memory Effeciency");
        memlabel.setBounds(alllabel.getX(), alllabel.getY()+60, 140, 30);
        memlabel.setFont(new Font(null, Font.BOLD, 15));
        c.add(memlabel);
        
        ab=new JLabel("   "+Integer.toString(all[0])+"%");
        ab.setBounds(245, 120, 60, 40);
        ab.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        ab.setFont(new Font(null, Font.BOLD, 15));
        c.add(ab);
        
        mb=new JLabel("   "+Integer.toString(mem[0])+"%");
        mb.setBounds(245, memlabel.getY(), 60, 40);
        mb.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        mb.setFont(new Font(null, Font.BOLD, 15));
        c.add(mb);
        
        aw=new JLabel("   "+Integer.toString(all[1])+"%");
        aw.setBounds(373, 120, 60, 40);
        aw.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        aw.setFont(new Font(null, Font.BOLD, 15));
        c.add(aw);
        
        mw=new JLabel("   "+Integer.toString(mem[1])+"%");
        mw.setBounds(373, memlabel.getY(), 60, 40);
        mw.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        mw.setFont(new Font(null, Font.BOLD, 15));
        c.add(mw);
        
        af=new JLabel("   "+Integer.toString(all[2])+"%");
        af.setBounds(490, 120, 60, 40);
        af.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        af.setFont(new Font(null, Font.BOLD, 15));
        c.add(af);
        
        mf=new JLabel("   "+Integer.toString(mem[2])+"%");
        mf.setBounds(490, memlabel.getY(), 60, 40);
        mf.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        mf.setFont(new Font(null, Font.BOLD, 15));
        c.add(mf);
    }
    
    
    
}
