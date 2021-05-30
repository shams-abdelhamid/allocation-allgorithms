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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class input extends JFrame 
{
    Container c;
    ArrayList<Integer> procs=new ArrayList<>();
    ArrayList<Integer> bloks=new ArrayList<>();
    JTextField process;
    JTextField blocks;
    JButton uppr;
    JButton upbl;
    JButton addpr;
     JButton addbl;
    JButton resetbut;
    
    input()
    {
        setSize(750, 750);
        setTitle("input");
        
        
        c=getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(0, 255, 191));
        
        addpr = new JButton("add process");
        addpr.setBounds(10, 150, 150, 30);
        addpr.setBackground(new Color(140, 0, 71));
        addpr.setForeground(Color.WHITE);
        addpr.setBorder(null);
        addpr.addActionListener(new addprocs());
        c.add(addpr);
        
        uppr = new JButton("upload processes file");
        uppr.setBounds(addpr.getX(), addpr.getY()-addpr.getHeight()-5, 150, 30);
        uppr.setBackground(new Color(140, 0, 71));
        uppr.setForeground(Color.WHITE);
        uppr.setBorder(null);
        uppr.addActionListener(new uploadprocess());
        c.add(uppr);
        
        upbl = new JButton("upload blocks file");
        upbl.setBounds(addpr.getX()+300, addpr.getY()-addpr.getHeight()-5, 150, 30);
        upbl.setBackground(new Color(140, 0, 71));
        upbl.setForeground(Color.WHITE);
        upbl.setBorder(null);
        upbl.addActionListener(new uploadblock());
        c.add(upbl);
        
        addbl = new JButton("add block");
        addbl.setBounds(addpr.getX()+300, addpr.getY(), 150, 30);
        addbl.setBackground(new Color(140, 0, 71));
        addbl.setForeground(Color.WHITE);
        addbl.setBorder(null);
        addbl.addActionListener(new addblock());
        c.add(addbl);
        
        JButton savep = new JButton("Save Data");
        savep.setBounds(uppr.getX(), uppr.getY()-uppr.getHeight()-5, 150, 30);
        savep.setBackground(new Color(140, 0, 71));
        savep.setForeground(Color.WHITE);
        savep.setBorder(null);
        savep.addActionListener(new saveprocess(procs));
        c.add(savep);
        
        JButton saveb = new JButton("Save Data");
        saveb.setBounds(addbl.getX(), uppr.getY()-uppr.getHeight()-5, 150, 30);
        saveb.setBackground(new Color(140, 0, 71));
        saveb.setForeground(Color.WHITE);
        saveb.setBorder(null);
        saveb.addActionListener(new saveblock(bloks));
        c.add(saveb);
        
        process=new JTextField();
        process.setBounds(addpr.getX(), addpr.getY()+40, 150, 30);
        process.setBorder(null);
        c.add(process);
        
        blocks=new JTextField();
        blocks.setBounds(addbl.getX(), addbl.getY()+40, 150, 30);
        blocks.setBorder(null);
        c.add(blocks);
        
        JButton view = new JButton("Compute Allocation");
        view.setBounds(570,10, 150, 30);
        view.setBackground(new Color(140, 0, 71));
        view.setForeground(Color.WHITE);
        view.setBorder(null);
        view.addActionListener(new viewps(procs,bloks));
        c.add(view);
        
        resetbut= new JButton("Reset");
        resetbut.setBounds(view.getX(),view.getY()+view.getHeight()+5, 150, 30);
        resetbut.setBackground(new Color(140, 0, 71));
        resetbut.setForeground(Color.WHITE);
        resetbut.setBorder(null);
        resetbut.addActionListener(new reset(input.this));
        c.add(resetbut);
    }
    private class addprocs implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            
            
            try {
                int y=210;
                int x=parseInt(process.getText());
                if(x==0)
                {
                    JOptionPane.showMessageDialog(null, "zero size is neglected",
                                "error", JOptionPane.WARNING_MESSAGE);
                    process.setText(null);
                }
                else if(x<0)
                {
                    JOptionPane.showMessageDialog(null, "Size Can't Be Negative",
                                "error", JOptionPane.WARNING_MESSAGE);
                    process.setText(null);

                }
                else
                {
                    procs.add(x);
                process.setText(null);
                for(int i=0;i<procs.size();i++)
                {
                    JLabel vp=new JLabel();
                    vp.setBounds(10, y, 200, 50);
                    vp.setBorder(null);
                    vp.setFont(new Font(null, Font.BOLD, 15));
                    vp.setText("process "+(i+1)+" : "+Integer.toString(procs.get(i)));
                    c.add(vp);
                    y=y+20;
                }
                uppr.setBackground(Color.GRAY);
                uppr.setForeground(Color.BLACK);
                if(uppr.getActionListeners().length!=0)
                {
                    uppr.removeActionListener(uppr.getActionListeners()[0]);

                }
                repaint();
                }
                
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "wrong input format",
                                "error", JOptionPane.ERROR_MESSAGE);
                    process.setText(null);
                }
            
        }   
    }
    
    private class addblock implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            try {
                int y=210;
            int x=parseInt(blocks.getText());
            if(x==0)
                {
                    JOptionPane.showMessageDialog(null, "zero size is neglected",
                                "error", JOptionPane.WARNING_MESSAGE);
                    process.setText(null);
                }
                else if(x<0)
                {
                    JOptionPane.showMessageDialog(null, "Size Can't Be Negative",
                                "error", JOptionPane.WARNING_MESSAGE);
                    process.setText(null);

                }
            else
                {
                   bloks.add(x);
            blocks.setText(null);
            for(int i=0;i<bloks.size();i++)
            {
                JLabel vp=new JLabel();
                vp.setBounds(310, y, 200, 50);
                vp.setBorder(null);
                vp.setFont(new Font(null, Font.BOLD, 15));
                vp.setText("Block "+(i+1)+" : "+Integer.toString(bloks.get(i)));
                c.add(vp);
                y=y+20;
            }
                upbl.setBackground(Color.GRAY);
                upbl.setForeground(Color.BLACK);
                if(upbl.getActionListeners().length!=0)
                {
                    upbl.removeActionListener(upbl.getActionListeners()[0]);
                }
            repaint(); 
                }
            } catch (NumberFormatException e) {
                 JOptionPane.showMessageDialog(null, "wrong input format",
                            "error", JOptionPane.ERROR_MESSAGE);
                process.setText(null);
            }
            
        }   
    }
    
    private class viewps implements ActionListener
    {
        ArrayList<Integer> y;
        ArrayList<Integer> x;
        viewps(ArrayList<Integer> p,ArrayList<Integer> b)
        {
            this.y=p;
            this.x=b;
            
           
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            if((y.isEmpty())&&(x.isEmpty()))
            {
                JOptionPane.showMessageDialog(null, "No data entered",
                            "No Data", JOptionPane.ERROR_MESSAGE);
            }
            else if(y.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "No processes entered",
                            "No Data", JOptionPane.ERROR_MESSAGE);
            }
            else if(x.isEmpty())
            {
                 JOptionPane.showMessageDialog(null, "No blocks entered",
                            "No Data", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                int u=JOptionPane.showConfirmDialog(c, "Do you want to submit ?", "confirm", 0);
                if(u==0)
                {
                    fitselect fs=new fitselect(y,x,input.this);
                    fs.setVisible(true);
                    fs.setDefaultCloseOperation(fitselect.EXIT_ON_CLOSE);
                    setVisible(false);
                }
                
            }
        }
    }
    
    private class saveprocess implements ActionListener
    {
        ArrayList<Integer> y;
        public saveprocess(ArrayList<Integer> x) 
        {
            this.y=x;
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            FileOutputStream fos;
            try {
                if(!y.isEmpty())
                {
                    Color col=new Color(255, 255, 153);
                if(uppr.getBackground().getRGB()!=col.getRGB())
                {
                    JFileChooser chooser =new JFileChooser();
                    chooser.showSaveDialog(null);
                    File file =chooser.getSelectedFile();
                    if(file!=null)
                    {
                        String filename=(file.getAbsolutePath()+".prc");
                        fos = new FileOutputStream(new File(filename));
                        ObjectOutputStream oos=new ObjectOutputStream(fos);
                        oos.writeObject(y);
                        fos.close();
                        oos.close();
                        
                    }
                    else
                    JOptionPane.showMessageDialog(null, "No path Selected",
                                "File Not Saved", JOptionPane.WARNING_MESSAGE);
                    
                }
                else
                    JOptionPane.showMessageDialog(null, "File Already Exists",
                                "error", JOptionPane.WARNING_MESSAGE);
                }
                else
                            JOptionPane.showMessageDialog(null, "File is empty",
                                "Error", JOptionPane.WARNING_MESSAGE);
                
                    
            } catch (FileNotFoundException ex) {
                Logger.getLogger(input.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(input.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
        }
        
    }
    
    private class saveblock implements ActionListener
    {
        ArrayList<Integer> z;
        public saveblock(ArrayList<Integer> x) 
        {
            this.z=x;
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            FileOutputStream fos;
            try {
                if(!z.isEmpty())
                {
                    Color col=new Color(255, 255, 153);
                if(upbl.getBackground().getRGB()!=col.getRGB())
                {
                    JFileChooser chooser =new JFileChooser();
                    chooser.showSaveDialog(null);
                    File file =chooser.getSelectedFile();
                    if(file!=null)
                    {
                        String filename=(file.getAbsolutePath()+".blk");
                        fos = new FileOutputStream(new File(filename));
                        ObjectOutputStream oos=new ObjectOutputStream(fos);
                        oos.writeObject(z);
                        fos.close();
                        oos.close();
                        
                    }
                    else
                    JOptionPane.showMessageDialog(null, "No path Selected",
                                "File Not Saved", JOptionPane.WARNING_MESSAGE);
                    
                }
                else
                    JOptionPane.showMessageDialog(null, "File Already Exists",
                                "error", JOptionPane.WARNING_MESSAGE);
                }
                else
                            JOptionPane.showMessageDialog(null, "File is empty",
                                "Error", JOptionPane.WARNING_MESSAGE);
                
                    
            } catch (FileNotFoundException ex) {
                Logger.getLogger(input.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(input.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
        }
        
    }
    
    private class uploadprocess implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            FileInputStream fis =null;
            ArrayList<Integer> x;
            try {
                JFileChooser chooser =new JFileChooser();
                chooser.showOpenDialog(null);
                File file =chooser.getSelectedFile();
                if(file!=null)
                {
                    if(file.exists())
                    {
                        String filename=(file.getAbsolutePath());
                        if(filename.endsWith("prc"))
                        {
                            fis = new FileInputStream(filename);
                            ObjectInputStream ois=new ObjectInputStream(fis);
                            x=(ArrayList<Integer>)ois.readObject();
                            procs.clear();
                            for(int i=0;i<x.size();i++)
                            {
                                procs.add(x.get(i));
                            }
                            uppr.setBackground(new Color(255, 255, 153));
                            uppr.setText("data file is added");
                            uppr.setForeground(Color.BLACK);
                            addpr.removeActionListener(addpr.getActionListeners()[0]);
                            addpr.addActionListener(new subpro());
                            fis.close();
                            ois.close();
                        }
                        else
                        JOptionPane.showMessageDialog(null, "Wrong File Format, it has to be ( .prc )",
                                "error", JOptionPane.WARNING_MESSAGE);
                    
                        
                    }
                    else
                        JOptionPane.showMessageDialog(null, "File Not Found",
                                "error", JOptionPane.WARNING_MESSAGE);
                    
                }
                else
                    JOptionPane.showMessageDialog(null, "No File Selected",
                                "error", JOptionPane.WARNING_MESSAGE);
               
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(input.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(input.class.getName()).log(Level.SEVERE, null, ex);
                } 
        }
        
    }
    
    private class uploadblock implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            FileInputStream fis =null;
            ArrayList<Integer> x;
            try {
                JFileChooser chooser =new JFileChooser();
                chooser.showOpenDialog(null);
                File file =chooser.getSelectedFile();
                if(file!=null)
                {
                    if(file.exists())
                    {
                        String filename=(file.getAbsolutePath());
                        if(filename.endsWith("blk"))
                        {
                            fis = new FileInputStream(filename);
                            ObjectInputStream ois=new ObjectInputStream(fis);
                            x=(ArrayList<Integer>)ois.readObject();
                            for(int i=0;i<x.size();i++)
                            {
                                bloks.add(x.get(i));
                            }
                            upbl.setBackground(new Color(255, 255, 153));
                            upbl.setText("data file is added");
                            upbl.setForeground(Color.BLACK);
                            addbl.removeActionListener(addbl.getActionListeners()[0]);
                            addbl.addActionListener(new subblo());
                            fis.close();
                            ois.close();
                        }
                        else
                        JOptionPane.showMessageDialog(null, "Wrong File Format, it has to be ( .blk )",
                                "error", JOptionPane.WARNING_MESSAGE);
                    
                    }
                    else
                        JOptionPane.showMessageDialog(null, "File Not Found",
                                "error", JOptionPane.WARNING_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(null, "No File Selected",
                                "error", JOptionPane.WARNING_MESSAGE);
               
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(input.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(input.class.getName()).log(Level.SEVERE, null, ex);
                } 
        }
        
    }
    
    private class subpro implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            process.setText("Using File Data!");
        }
        
    }
    
    private class subblo implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            blocks.setText("Using File Data!");
        }
        
    }
    
    private class reset implements ActionListener
    {
        input im;
        public reset(input i)
        {
            this.im=i;
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            im=new input();
            im.setVisible(true);
            im.setDefaultCloseOperation(input.EXIT_ON_CLOSE);
            dispose();
        }
        
    }
}
