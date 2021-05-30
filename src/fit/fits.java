/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author User
 */
public class fits
{
    public int allocationeff(fitselect fs)
    {
        float sum=0;
        float allsum=0;
        for(int i=0;i<fs.processes.size();i++)
        {
            sum=sum+fs.processes.get(i);
        }
        for(int j=0;j<fs.processes.size();j++)
        {
            if(fs.allocs.get(j)!= -1)
            allsum=allsum+fs.processes.get(j);
        }
        int x=(int)((int)allsum/sum*100);
//        System.out.println(x+"% is allocated");
        
       return x;
    }
    
    public int memoryeff(fitselect fs)
    {
        float sum=0;
        float value;
        for(int i=0;i<fs.blocks.size();i++)
        {
            sum=sum+((fs.copyblocks.get(i)-fs.blocks.get(i))/(float)fs.copyblocks.get(i));
        }
        value=(sum/fs.blocks.size())*100;
        return (int)value;
    }
    public void display(JPanel active,fitselect fst,ArrayList<Integer> allocs,ArrayList<Integer> blocks,JLabel arrprocs[],JLabel arrblock[])
    {
        int x=70;
        int x1=70;
        for (int k = 0; k < fst.n; k++) 
        { 
            JLabel pnoq=new JLabel("P.no");
            pnoq.setBounds(70, 40, 50, 30);
            pnoq.setFont(new Font(null, Font.BOLD, 15));
            active.add(pnoq);
            
            JLabel procs=new JLabel();
            procs.setBounds(60, x, 50, 30);
            procs.setVerticalAlignment(SwingConstants.CENTER);
            procs.setText(Integer.toString(k+1));
            procs.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            active.add(procs);
            
            JLabel anoq=new JLabel("allocated to");
            anoq.setBounds(130, 40, 100, 30);
            anoq.setFont(new Font(null, Font.BOLD, 15));
            active.add(anoq);
            
            arrprocs[k].setBounds(130, x, 85, 30);
            arrprocs[k].setVerticalAlignment(SwingConstants.CENTER);
            if (allocs.get(k) != -1) 
                arrprocs[k].setText(Integer.toString(allocs.get(k)+1));
            else
                arrprocs[k].setText("X");
            arrprocs[k].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            active.add(arrprocs[k]);
            
            fst.repaint();
            x=x+30;
            
        } 
        for(int l =0;l<fst.m;l++)
        {
            JLabel bnoq=new JLabel("B.no");
            bnoq.setBounds(230, 40, 50, 30);
            bnoq.setFont(new Font(null, Font.BOLD, 15));
            active.add(bnoq);
            
            JLabel blks=new JLabel();
            blks.setBounds(230, x1, 50, 30);
            blks.setVerticalAlignment(SwingConstants.CENTER);
            blks.setText(Integer.toString(l+1));
            blks.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            active.add(blks);
            
            JLabel rnoq=new JLabel("remaining blocks");
            rnoq.setBounds(300, 40, 120, 30);
            rnoq.setFont(new Font(null, Font.BOLD, 15));
            active.add(rnoq);
            
            arrblock[l].setBounds(300, x1, 120, 30);
            arrblock[l].setVerticalAlignment(SwingConstants.CENTER);
            arrblock[l].setText(Integer.toString(fst.blocks.get(l)));
            arrblock[l].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            active.add(arrblock[l]);
            fst.repaint();
            x1=x1+30;
        }
    }
       
    public void bestfit(fitselect fs) 
    { 
        fs.external=false;
        fs.internal=false;
        fs.blocks=(ArrayList<Integer>)fs.copyblocks.clone();
        fs.allocs=new ArrayList<>();
        for (int i = 0; i < fs.n; i++) 
            fs.allocs.add(-1); 
       
        for (int i=0; i<fs.n; i++) 
        { 
            int index = -1; 
            for (int j=0; j<fs.m; j++) 
            { 
                if (fs.blocks.get(j) >= fs.processes.get(i)) 
                {
                    if (index == -1) 
                        index = j; 
                    else if (fs.blocks.get(index) > fs.blocks.get(j))
                        index = j; 
                } 
            } 
            if (index != -1) 
            { 
                fs.allocs.set(i, index);
       
                fs.blocks.set(index,fs.blocks.get(index)-fs.processes.get(i) );
            }
            else
            {
                int sumit=0;
             for(int k=0;k<fs.blocks.size();k++)
             {
                 sumit=sumit+fs.blocks.get(k);
             }
             if(sumit>=fs.processes.get(i))
             {
                 fs.external=true;
             }
             else
             {
                 fs.internal=true;
             }
            }
        } 
    } 
    
    public void worstfit(fitselect fs)
    {
        fs.external=false;
        fs.internal=false;
        fs.blocks=(ArrayList<Integer>)fs.copyblocks.clone();
        fs.allocs=new ArrayList<>();
       
        for (int i = 0; i < fs.n; i++) 
            fs.allocs.add(-1);
       
        for (int i=0; i<fs.n; i++) 
        { 
            int windex = -1; 
            for (int j=0; j<fs.m; j++) 
            { 
                if (fs.blocks.get(j) >= fs.processes.get(i)) 
                { 
                    if (windex == -1) 
                        windex = j; 
                    else if (fs.blocks.get(windex) < fs.blocks.get(j)) 
                        windex = j; 
                } 
            } 
       
            if (windex != -1) 
            { 
                fs.allocs.set(i, windex); 
                fs.blocks.set(windex, fs.blocks.get(windex)-fs.processes.get(i));
            }
            else
            {
                int sumit=0;
             for(int k=0;k<fs.blocks.size();k++)
             {
                 sumit=sumit+fs.blocks.get(k);
             }
             if(sumit>=fs.processes.get(i))
             {
                 fs.external=true;
             }
             else
             {
                 fs.internal=true;
             }
            }
           
        }
    }
    
    public void firstfit(fitselect fs)
    {
        fs.external=false;
        fs.internal=false;
        fs.blocks=(ArrayList<Integer>)fs.copyblocks.clone();
        fs.allocs=new ArrayList<>();
      
        for (int i = 0; i < fs.n; i++) 
            fs.allocs.add(-1);
      
        for (int i = 0; i < fs.n; i++) 
        { 
            for (int j = 0; j < fs.m; j++) 
            { 
                if (fs.blocks.get(j) >= fs.processes.get(i)) 
                { 
                    fs.allocs.set(i, j);
                    fs.blocks.set(j,fs.blocks.get(j)-fs.processes.get(i));
                    break; 
                } 
            }
            if(fs.allocs.get(i)==-1)
            {
                int sumit=0;
                for(int k=0;k<fs.blocks.size();k++)
                {
                    sumit=sumit+fs.blocks.get(k);
                }
                if(sumit>=fs.processes.get(i))
                {
                     fs.external=true;
                }
                else
                {
                    fs.internal=true;
                }
            }
        } 
    }
}