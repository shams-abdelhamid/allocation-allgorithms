/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author User
 */
public class fitselect extends JFrame 
{
    fits f;
    ArrayList<Integer> processes;
    ArrayList<Integer> blocks;
    ArrayList<Integer> allocs;
    ArrayList<Integer> copyblocks;
    JLabel[] pd;
    JLabel[] bd;
    int n;
    int m;
    JPanel bpanel=new JPanel();
    JPanel wpanel=new JPanel();
    JPanel fpanel=new JPanel();
    JLabel feedback;
    boolean internal,external;
    Container c;
    fitselect(ArrayList<Integer> p,ArrayList<Integer> b,input q)
    {
        internal=false;
        external=false;
        c=getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(0, 255, 191));
        this.processes=p;
        this.blocks=b;
        
        copyblocks=(ArrayList<Integer>)b.clone();
        
        pd=new JLabel[processes.size()];
        for(int i=0;i<processes.size();i++)
        {
            pd[i]=new JLabel();
        }
        bd=new JLabel[blocks.size()];
        for(int i=0;i<blocks.size();i++)
        {
            bd[i]=new JLabel();
        }
        
        bpanel.setBounds(200, 50, 450, 400);
        bpanel.setBackground(Color.WHITE);
        bpanel.setLayout(null);
        
        wpanel.setBounds(200, 50, 450, 400);
        wpanel.setBackground(Color.WHITE);
        wpanel.setLayout(null);
        
        fpanel.setBounds(200, 50, 450, 400);
        fpanel.setBackground(Color.WHITE);
        fpanel.setLayout(null);
        
        feedback=new JLabel();
        feedback.setBounds(50, 300, 800, 400);
        feedback.setBackground(Color.WHITE);
        c.add(feedback);
        
        
        m = blocks.size(); 
        n = processes.size();
        
        setSize(800, 600);
       
        
        JButton best =new JButton("best fit");
        best.setBounds(50, 50, 100, 60);
        best.addActionListener(new bestbut());
        best.setBackground(new Color(140, 0, 71));
        best.setFont(new Font(null, Font.BOLD, 15));
        best.setForeground(Color.WHITE);
        c.add(best);
        
        JButton worst =new JButton("worst fit");
        worst.setBounds(50, best.getY()+70, 100, 60);
        worst.addActionListener(new worstfitbut());
        worst.setBackground(new Color(140, 0, 71));
        worst.setFont(new Font(null, Font.BOLD, 15));
        worst.setForeground(Color.WHITE);
        c.add(worst);
        
        JButton first =new JButton("first fit");
        first.setBounds(50, worst.getY()+70, 100, 60);
        first.addActionListener(new firstfitbut());
        first.setBackground(new Color(140, 0, 71));
        first.setFont(new Font(null, Font.BOLD, 15));
        first.setForeground(Color.WHITE);
        c.add(first);
        
        JButton rank =new JButton("Rank Fits");
        rank.setBounds(50, first.getY()+70, 100, 60);
        rank.addActionListener(new rankbutton());
        rank.setBackground(new Color(140, 0, 71));
        rank.setFont(new Font(null, Font.BOLD, 15));
        rank.setForeground(Color.WHITE);
        c.add(rank);
        
        JButton readd =new JButton("back");
        readd.setBounds(50, rank.getY()+70, 100, 60);
        readd.addActionListener(new backbutton(q));
        readd.setBackground(new Color(140, 0, 71));
        readd.setFont(new Font(null, Font.BOLD, 15));
        readd.setForeground(Color.WHITE);
        c.add(readd);
        
        JButton reset =new JButton("reset");
        reset.setBounds(50, readd.getY()+70, 100, 60);
        reset.addActionListener(new resetbutton(q));
        reset.setBackground(new Color(140, 0, 71));
        reset.setFont(new Font(null, Font.BOLD, 15));
        reset.setForeground(Color.WHITE);
        c.add(reset);
        
    
    }
    
    private class  bestbut implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            f=new fits();
            f.bestfit(fitselect.this);
            f.display(bpanel,fitselect.this, allocs, blocks,pd,bd);
            feedback.setText(feedbackupdate(fitselect.this));
            wpanel.setVisible(false);
            fpanel.setVisible(false);
            bpanel.setVisible(true);
            c.remove(wpanel);
            c.remove(fpanel);
            c.add(bpanel);
            repaint();
            
        }
        
    }
    
    private class worstfitbut implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            f=new fits();
            f.worstfit(fitselect.this);
            f.display(wpanel,fitselect.this, allocs, blocks,pd,bd);
            feedback.setText(feedbackupdate(fitselect.this));
            System.out.println("memory saved for worst fit "+f.memoryeff(fitselect.this)+"%");
            wpanel.setVisible(true);
            fpanel.setVisible(false);
            bpanel.setVisible(false);
            c.add(wpanel);
            repaint();
        } 
       
        
    }
    
    private class firstfitbut implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
        
            f=new fits();
            f.firstfit(fitselect.this);
            f.display(fpanel,fitselect.this, allocs, blocks,pd,bd);
            feedback.setText(feedbackupdate(fitselect.this));
            wpanel.setVisible(false);
            fpanel.setVisible(true);
            bpanel.setVisible(false);
            c.add(fpanel);
            repaint();
        
        }
        
    }
    public String feedbackupdate(fitselect fs)
    {
        String report=null;
        if(fs.external==true)
        {
            report= "<html>REPORT: Deffeciency is due: External Fragmentation<br/>SOLUTION: compaction will make 100% effeciency</html>";
        }
        if(fs.internal==true)
        {
            report="<html>REPORT: Deffeciency is due process size is more than availabel space<br/>SOLUTION : rearranging or modifying might improve effeciency</html>";
        }
        if((fs.external==true)&&(fs.internal==true))
        {
            report="<html>REPORT: Deffeciency is due: External Fragmentation and some processes size is more than availabel space <br/> SOLUTION:: compaction will improve effeciency</html<";
        }
        if((fs.external==false)&&(fs.internal==false))
            report="Successful Allocation";
        
        return report;
    }
    
    private class backbutton implements ActionListener
    {
        input q;
        public backbutton(input q) 
        {
            this.q=q;
        }
        

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            q.setVisible(true);
            dispose();
        }
        
    }
    
    private class resetbutton implements ActionListener
    {
        input q;
        public resetbutton(input q) 
        {
            this.q=q;
        }
        

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            q=new input();
            q.setVisible(true);
            q.setDefaultCloseOperation(input.EXIT_ON_CLOSE);
            dispose();
        }
        
    }
    
    private class rankbutton implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            int alleff[]=new int[3];
            int memeff[]=new int[3];
            f=new fits();
            
            f.bestfit(fitselect.this);
            alleff[0]=f.allocationeff(fitselect.this);
            memeff[0]=f.memoryeff(fitselect.this);
          
            
            f.worstfit(fitselect.this);
            alleff[1]=f.allocationeff(fitselect.this);
            memeff[1]=f.memoryeff(fitselect.this);
            
            f.firstfit(fitselect.this);
            alleff[2]=f.allocationeff(fitselect.this);
            memeff[2]=f.memoryeff(fitselect.this);
            
            compare cm=new compare( alleff,memeff);
            cm.setVisible(true);
        }
        
    }
}
    
