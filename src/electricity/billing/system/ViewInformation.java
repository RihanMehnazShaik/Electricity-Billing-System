
package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class ViewInformation extends JFrame implements ActionListener{
    
    JButton cancel;
    ViewInformation(String m_num){
        setBounds(350,15,850,650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading=new JLabel("VIEW CUSTOMER INFORMATION");
        heading.setBounds(250,0,500,40);
        heading.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(heading);
        
        JLabel lblname=new JLabel("Name:");
        lblname.setBounds(70,80,100,20);
        add(lblname);
        
        JLabel name=new JLabel("");
        name.setBounds(250,80,100,20);
        add(name);
        
        JLabel lblmeter=new JLabel("Meter Number:");
        lblmeter.setBounds(70,140,100,20);
        add(lblmeter);
        
        JLabel meter=new JLabel("");
        meter.setBounds(250,140,100,20);
        add(meter);
        
        JLabel lbladdress=new JLabel("Address:");
        lbladdress.setBounds(70,200,100,20);
        add(lbladdress);
        
        JLabel address=new JLabel("");
        address.setBounds(250,200,100,20);
        add(address);
        
        JLabel lblcity=new JLabel("City:");
        lblcity.setBounds(70,260,100,20);
        add(lblcity);
        
        JLabel city=new JLabel("");
        city.setBounds(250,260,100,20);
        add(city);
        
        JLabel lblstate=new JLabel("State:");
        lblstate.setBounds(400,80,100,20);
        add(lblstate);
        
        JLabel state=new JLabel("");
        state.setBounds(580,80,100,20);
        add(state);
        
        JLabel lblemail=new JLabel("Email ID:");
        lblemail.setBounds(400,140,100,20);
        add(lblemail);
        
        JLabel email=new JLabel("");
        email.setBounds(580,140,100,20);
        add(email);
        
        JLabel lblphone=new JLabel("Contact No:");
        lblphone.setBounds(400,200,100,20);
        add(lblphone);
        
        JLabel phone=new JLabel("");
        phone.setBounds(580,200,100,20);
        add(phone);
        
        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select * from customer where  meter_no='"+m_num+"'");
            while(rs.next()){
                name.setText(rs.getString("name"));
                meter.setText(rs.getString("meter_no"));
                address.setText(rs.getString("address"));
                city.setText(rs.getString("city"));
                state.setText(rs.getString("state"));
                email.setText(rs.getString("email"));
                phone.setText(rs.getString("phone"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        cancel=new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(250,320,100,20);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image i2=i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(20, 350, 600, 300);
        add(image);
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
    }


    public static void main(String args[]){
        new ViewInformation("");
    }
}
