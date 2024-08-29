
package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class PayBill extends JFrame implements ActionListener {
   
    Choice mon;
    JButton pay,back;
    String m_num;
    PayBill(String m_num){
        this.m_num=m_num;
        setLayout(null);
        setBounds(300,150,600,600);
        
        JLabel heading =new JLabel("Electricity Bill");
        heading.setFont(new Font("Tahoma",Font.BOLD,24));
        heading.setBounds(200,0,400,30);
        add(heading);
        
        JLabel lblmeternumber=new JLabel("Meter Number:");
        lblmeternumber.setBounds(35,80,200,20);
        add(lblmeternumber);
        
        JLabel meternumber=new JLabel("");
        meternumber.setBounds(300,80,200,20);
        add(meternumber);
        
        JLabel lblname=new JLabel("Customer Name:");
        lblname.setBounds(35,140,200,20);
        add(lblname);
        
        JLabel name=new JLabel("");
        name.setBounds(300,140,200,20);
        add(name);
        
        JLabel lblmonth=new JLabel("Month:");
        lblmonth.setBounds(35,200,200,20);
        add(lblmonth);
        
        mon=new Choice();
        mon.setBounds(300,200,200,20);
        mon.add("January");
        mon.add("February");
        mon.add("March");
        mon.add("April");
        mon.add("May");
        mon.add("June");
        mon.add("July");
        mon.add("August");
        mon.add("September");
        mon.add("October");
        mon.add("November");
        mon.add("December");
        add(mon);
        
        JLabel lblunits=new JLabel("Units Consumed:");
        lblunits.setBounds(35,260,200,20);
        add(lblunits);
        
        JLabel units=new JLabel("");
        units.setBounds(300,260,200,20);
        add(units);
        
        JLabel lbltotalbill=new JLabel("Total Bill:");
        lbltotalbill.setBounds(35,320,200,20);
        add(lbltotalbill);
        
        JLabel totalbill=new JLabel("");
        totalbill.setBounds(300,320,200,20);
        add(totalbill);
        
        JLabel lblstatus=new JLabel("Payment Status:");
        lblstatus.setBounds(35,380,200,20);
        add(lblstatus);
        
        JLabel status=new JLabel("");
        status.setForeground(Color.red);
        status.setBounds(300,380,200,20);
        add(status);
        
        try{
            Conn c = new Conn();
            String query = "select * from customer where meter_no='" + m_num + "'";
            ResultSet rs = c.s.executeQuery(query);
            
            while(rs.next()) {
                meternumber.setText(m_num);
                name.setText(rs.getString("name"));
            }
            
            
            rs = c.s.executeQuery("select * from bill where meter_no='" + m_num + "'and month='"+mon.getSelectedItem()+"'");
            
            while(rs.next()) {
                units.setText(rs.getString("units"));
                totalbill.setText(rs.getString("totalBill"));
                status.setText(rs.getString("status"));
            }

            
        }catch(Exception e){
            e.printStackTrace();
        }
        mon.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ae){
                try{
            Conn c = new Conn();
            String query = "select * from customer where meter_no='" + m_num + "'";
            ResultSet rs = c.s.executeQuery(query);
            
            while(rs.next()) {
                meternumber.setText(m_num);
                name.setText(rs.getString("name"));
            }
            
            
            rs = c.s.executeQuery("select * from bill where meter_no='" + m_num + "'and month='"+mon.getSelectedItem()+"'");
            
            while(rs.next()) {
                units.setText(rs.getString("units"));
                totalbill.setText(rs.getString("totalBill"));
                status.setText(rs.getString("status"));
            }

            
        }catch(Exception e){
            e.printStackTrace();
        }
        }
        });
        
        pay=new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.setBounds(100,460,100,25);
        pay.addActionListener(this);
        add(pay);
        
        back=new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(230,460,100,25);
        back.addActionListener(this);
        add(back);
        
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==pay){
            
            try{
                Conn c=new Conn();
                c.s.executeUpdate("Update bill set status='paid' where meter_no='"+m_num+"' and month='"+mon.getSelectedItem()+"'");
            }catch(Exception e){
                e.printStackTrace();
            }
            setVisible(false);
            new Paytm(m_num);
        }else{
            setVisible(false);
        }
        
       
    }
    public static void main(String args[]){
        new PayBill("");
    }
}
