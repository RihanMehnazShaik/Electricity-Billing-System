package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
public class CalculateBill extends JFrame implements ActionListener{
    JTextField addr,units;
    JButton next,cancel;
    JLabel lblname;
    Choice meter_No,mon;
    CalculateBill(){
        setSize(600,400);
        setLocation(400,200);

        JPanel p=new JPanel();
        p.setLayout(null);
        add(p);
        p.setBackground(Color.WHITE);
        
        JLabel heading=new JLabel("Calculate Electricity Bill");
        heading.setBounds(180,10,500,40);
        heading.setFont(new Font("Tahoma",Font.BOLD,24));
        p.add(heading);
        
        JLabel meterNum=new JLabel("Meter Number:");
        meterNum.setBounds(100,80,100,20);
        p.add(meterNum);
        
        
        meter_No=new Choice();
        meter_No.setBounds(240,80,200,20);
        p.add(meter_No);
        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select * from customer");
            while(rs.next()){
                meter_No.add(rs.getString("meter_no"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        JLabel Cname=new JLabel("Name:");
        Cname.setBounds(100,120,100,20);
        p.add(Cname);
        
        lblname=new JLabel("");
        lblname.setBounds(240,120,200,20);
        p.add(lblname);
     
        
        JLabel lbladdress=new JLabel("Address:");
        lbladdress.setBounds(100,160,100,20);
        p.add(lbladdress);
        
        addr=new JTextField();
        addr.setBounds(240,160,200,20);
        p.add(addr);
        
         
        meter_No.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                 try{
                    Conn c=new Conn();
                    ResultSet rs=c.s.executeQuery("select * from customer where meter_no='"+meter_No.getSelectedItem()+"'");
                    while(rs.next()){
                    lblname.setText(rs.getString("name"));
                    addr.setText(rs.getString("address"));
                    }
                }catch(Exception e){
                e.printStackTrace();
                }
             }      
    });
         
         
        JLabel lblcity=new JLabel("Units Consumed:");
        lblcity.setBounds(100,200,100,20);
        p.add(lblcity);
        
        units=new JTextField();
        units.setBounds(240,200,200,20);
        p.add(units);
        
        JLabel lblstate=new JLabel("Month:");
        lblstate.setBounds(100,240,100,20);
        p.add(lblstate);
        
        mon=new Choice();
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
        mon.setBounds(240,240,200,20);
        p.add(mon);
        
        
        next=new JButton("Submit");
        next.setBounds(340,280,100,20);
        next.addActionListener(this);
        p.add(next);
        
        cancel=new JButton("cancel");
        cancel.setBounds(100,280,100,20);
        cancel.addActionListener(this);
        p.add(cancel);
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== next){
           
           String pmeter=meter_No.getSelectedItem();
           String punits=units.getText();
           String month=mon.getSelectedItem();
           
           int totalBill=0;
           int units_consumed=Integer.parseInt(punits);
           
           try{
               Conn c=new Conn();
               String query1="select * from tax";
               ResultSet rs=c.s.executeQuery(query1);
               
               while(rs.next()){
                   totalBill+=units_consumed*Integer.parseInt(rs.getString("cost_per_unit"));
                   totalBill+=Integer.parseInt(rs.getString("meter_rent"));
                   totalBill+=Integer.parseInt(rs.getString("service_charge"));
                   totalBill+=Integer.parseInt(rs.getString("service_tax"));
                   totalBill+=Integer.parseInt(rs.getString("swacch_bharat_cess"));
                   totalBill+=Integer.parseInt(rs.getString("fixed_tax"));    
               }
               }catch(Exception e){
               e.printStackTrace();
           }
           String query2="insert into bill values('"+pmeter+"','"+month+"','"+punits+"','"+totalBill+"','Not Paid')";
           try{
               Conn c=new Conn();
               c.s.executeUpdate(query2);
               JOptionPane.showMessageDialog(null, "Successfully updated Customer Bill");
               setVisible(false);
               }catch(Exception e){
               e.printStackTrace();
           }
        }else{
            setVisible(false);
        }
    }
    public static void main(String args[]){
        new CalculateBill();
    }
}
