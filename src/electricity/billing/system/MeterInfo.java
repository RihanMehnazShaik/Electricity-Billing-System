
package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
public class MeterInfo extends JFrame implements ActionListener{
    
    JButton next;
    Choice meterlocation,metertype,phasecode,billtype;
    String meternumber;
    MeterInfo(String meternumber){
        this.meternumber=meternumber;
        setSize(700,500);
        setLocation(400,200);
        
        
        JPanel p=new JPanel();
        p.setLayout(null);
        add(p);
        p.setBackground(Color.WHITE);
        
        JLabel heading=new JLabel("Meter Information");
        heading.setBounds(180,10,250,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,24));
        p.add(heading);
        
        JLabel lblname=new JLabel("Meter name:");
        lblname.setBounds(100,80,100,20);
        p.add(lblname);
        
        JLabel lblmeternum=new JLabel(meternumber);
        lblmeternum.setBounds(240,80,100,20);
        p.add(lblmeternum);
        
        JLabel lblmeterNo=new JLabel("Meter Location:");
        lblmeterNo.setBounds(100,120,100,20);
        p.add(lblmeterNo);
        
        meterlocation=new Choice();
        meterlocation.add("Outside");
        meterlocation.add("Inside");
        meterlocation.setBounds(240,120,200,20);
        p.add(meterlocation);
        
        JLabel lbladdress=new JLabel("Meter Type:");
        lbladdress.setBounds(100,160,100,20);
        p.add(lbladdress);
        
        metertype=new Choice();
        metertype.add("Solar");
        metertype.add("Electric");
        metertype.add("SmartMeter");
        metertype.setBounds(240,160,200,20);
        p.add(metertype);
        
        JLabel lblcity=new JLabel("Phase Code:");
        lblcity.setBounds(100,200,100,20);
        p.add(lblcity);
        
        phasecode=new Choice();
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.add("088");
        phasecode.add("099");
        phasecode.setBounds(240,200,200,20);
        p.add(phasecode);
        
        JLabel lblstate=new JLabel("Bill Type:");
        lblstate.setBounds(100,240,100,20);
        p.add(lblstate);
        
        billtype=new Choice();
        billtype.add("Outside");
        billtype.add("Inside");
        billtype.setBounds(240,240,200,20);
        p.add(billtype);
        
        JLabel lblemail=new JLabel("Days:");
        lblemail.setBounds(100,280,100,20);
        p.add(lblemail);
        
        JLabel lblemails=new JLabel("30 Days");
        lblemails.setBounds(240,280,100,20);
        p.add(lblemails);
        
        JLabel lblnote=new JLabel("Note:");
        lblnote.setBounds(100,320,100,20);
        p.add(lblnote);
        
        JLabel lblnotes=new JLabel("By default bill is calculated for 30 days only");
        lblnotes.setBounds(240,320,400,20);
        p.add(lblnotes);
        
        next=new JButton("Submit");
        next.setBounds(340,360,100,20);
        next.addActionListener(this);
        p.add(next);
        
        
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== next){
           String meter=meternumber;
           String loc=meterlocation.getSelectedItem();
           String type=metertype.getSelectedItem();
           String code=phasecode.getSelectedItem();
           String bill_type=billtype.getSelectedItem();
           String days="30";
          
           try{
               Conn c=new Conn();
               String query1="insert into meter_info values('"+meter+"','"+loc+"','"+type+"','"+code+"','"+bill_type+"','"+days+"')";
               c.s.executeUpdate(query1);
               
               
               JOptionPane.showMessageDialog(null, "Meter Information Added Successfully");
               setVisible(false);
           }catch(Exception e){
               e.printStackTrace();
           }
        }else{
            setVisible(false);
        }
    }
    public static void main(String args[]){
        new MeterInfo("");
    }
}
