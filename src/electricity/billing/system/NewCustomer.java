package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
public class NewCustomer extends JFrame implements ActionListener{
    JTextField name,addr,city,state,email,phone;
    JButton next,cancel;
    JLabel lblmeter;
    NewCustomer(){
        setSize(700,500);
        setLocation(400,200);
        
        
        JPanel p=new JPanel();
        p.setLayout(null);
        add(p);
        p.setBackground(Color.WHITE);
        
        JLabel heading=new JLabel("New Customer");
        heading.setBounds(180,10,200,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,24));
        p.add(heading);
        
        JLabel lblname=new JLabel("Customer name:");
        lblname.setBounds(100,80,100,20);
        p.add(lblname);
        
        name=new JTextField();
        name.setBounds(240,80,200,20);
        p.add(name);
        
        JLabel lblmeterNo=new JLabel("Meter Number:");
        lblmeterNo.setBounds(100,120,100,20);
        p.add(lblmeterNo);
        
        lblmeter=new JLabel("");
        lblmeter.setBounds(240,120,200,20);
        p.add(lblmeter);
        
        Random ran=new Random();
        long number=ran.nextLong()%1000000;
        lblmeter.setText(""+Math.abs(number));
        
        JLabel lbladdress=new JLabel("Address:");
        lbladdress.setBounds(100,160,100,20);
        p.add(lbladdress);
        
        addr=new JTextField();
        addr.setBounds(240,160,200,20);
        p.add(addr);
        
        JLabel lblcity=new JLabel("City:");
        lblcity.setBounds(100,200,100,20);
        p.add(lblcity);
        
        city=new JTextField();
        city.setBounds(240,200,200,20);
        p.add(city);
        
        JLabel lblstate=new JLabel("State:");
        lblstate.setBounds(100,240,100,20);
        p.add(lblstate);
        
        state=new JTextField();
        state.setBounds(240,240,200,20);
        p.add(state);
        
        JLabel lblemail=new JLabel("Email:");
        lblemail.setBounds(100,280,100,20);
        p.add(lblemail);
        
        email=new JTextField();
        email.setBounds(240,280,200,20);
        p.add(email);
        
        JLabel lblphone=new JLabel("Contact No:");
        lblphone.setBounds(100,320,100,20);
        p.add(lblphone);
        
        phone=new JTextField();
        phone.setBounds(240,320,200,20);
        p.add(phone);
        
        next=new JButton("Next");
        next.setBounds(340,360,100,20);
        next.addActionListener(this);
        p.add(next);
        
        cancel=new JButton("cancel");
        cancel.setBounds(100,360,100,20);
        cancel.addActionListener(this);
        p.add(cancel);
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== next){
           String pname=name.getText();
           String pmeter=lblmeter.getText();
           String paddr=addr.getText();
           String pcity=city.getText();
           String pstate=state.getText();
           String pemail=email.getText();
           String phno=phone.getText();
           try{
               Conn c=new Conn();
               String query1="insert into customer values('"+pname+"','"+pmeter+"','"+paddr+"','"+pcity+"','"+pstate+"','"+pemail+"','"+phno+"')";
               String query2="insert into login values('"+pmeter+"','','"+pname+"','','')";
               c.s.executeUpdate(query1);
               c.s.executeUpdate(query2);
               
               JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
               setVisible(false);
               
               new MeterInfo(pmeter);
           }catch(Exception e){
               e.printStackTrace();
           }
        }else{
            setVisible(false);
        }
    }
    public static void main(String args[]){
        new NewCustomer();
    }
}
