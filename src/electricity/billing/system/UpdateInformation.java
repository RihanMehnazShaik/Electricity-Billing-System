
package electricity.billing.system;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class UpdateInformation extends JFrame implements ActionListener{
   
    JTextField address,city,state,email,phone;
    JLabel name;
    JButton update,cancel;
    String m_num,NAME;
    UpdateInformation(String m_num,String NAME){
        this.m_num=m_num;
        this.NAME=NAME;
        setBounds(300,150,1050,450);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        
        JLabel heading=new JLabel("UPDATE CUSTOMER INFORMATION");
        heading.setBounds(110,0,500,40);
        heading.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(heading);
        
        JLabel lblname=new JLabel("Name:");
        lblname.setBounds(30,70,100,20);
        add(lblname);
        
        name=new JLabel("");
        name.setBounds(230,70,150,20);
        add(name);
        
        JLabel lblmeter=new JLabel("Meter Number:");
        lblmeter.setBounds(30,110,100,20);
        add(lblmeter);
        
        JLabel meter=new JLabel("");
        meter.setBounds(230,110,150,20);
        add(meter);
        
        JLabel lbladdress=new JLabel("Address:");
        lbladdress.setBounds(30,150,100,20);
        add(lbladdress);
        
        address=new JTextField();
        address.setBounds(230,150,150,20);
        add(address);
        
        JLabel lblcity=new JLabel("City:");
        lblcity.setBounds(30,190,100,20);
        add(lblcity);
        
        city=new JTextField();
        city.setBounds(230,190,150,20);
        add(city);
        
        JLabel lblstate=new JLabel("State:");
        lblstate.setBounds(30,230,100,20);
        add(lblstate);
        
        state=new JTextField();
        state.setBounds(230,230,150,20);
        add(state);
        
        JLabel lblemail=new JLabel("Email ID:");
        lblemail.setBounds(30,270,100,20);
        add(lblemail);
        
        email=new JTextField();
        email.setBounds(230,270,150,20);
        add(email);
        
        JLabel lblphone=new JLabel("Contact No:");
        lblphone.setBounds(30,310,100,20);
        add(lblphone);
        
        phone=new JTextField();
        phone.setBounds(230,310,150,20);
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
        cancel.setBounds(30,350,100,20);
        cancel.addActionListener(this);
        add(cancel);
        
        update=new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(230,350,100,20);
        update.addActionListener(this);
        add(update);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image i2=i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(550, 50, 400, 300);
        add(image);
        setVisible(true);
        
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()==update){
            String naam=NAME;
            String meterNUM=m_num;
            String addr=address.getText();
            String ucity=city.getText();
            String ustate=state.getText();
            String uemail=email.getText();
            String uphno=phone.getText();
            
            String query="Update Customer set address='"+addr+"',city='"+ucity+"',state='"+ustate+"',email='"+uemail+"',phone='"+uphno+"' where meter_no='"+meterNUM+"'and name='"+naam+"'";
            
            try{
                Conn c=new Conn();
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Successfully Updated Customer Details");
                setVisible(false);
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
                    
        }else{
            setVisible(false);
        }
    }
    public static void main(String args[]){
        new UpdateInformation("","");
    }
}
