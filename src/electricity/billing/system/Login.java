
package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame  implements ActionListener{
    JButton login,cancel,signup;
    Choice loggingin;
    JTextField username,password;
    
    Login(){
        super("Login Page");
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);
        
        JLabel login_uname=new JLabel("Username:");
        login_uname.setBounds(300,20,100,20);
        login_uname.setForeground(Color.red);
        add(login_uname);
        
        username=new JTextField();
        username.setBounds(400,20,150,20);
        add(username);
        
        JLabel login_pass=new JLabel("Password:");
        login_pass.setBounds(300,60,100,20);
        login_pass.setForeground(Color.red);
        add(login_pass);
        
        password=new JTextField();
        password.setBounds(400,60,150,20);
        add(password);
        
        JLabel login_inAs=new JLabel("Logging in as:");
        login_inAs.setBounds(300,100,100,20);
        login_inAs.setForeground(Color.red);
        add(login_inAs);
        
        loggingin=new Choice();
        loggingin.add("Admin");
        loggingin.add("Customer");
        loggingin.setBounds(400,100,150,20);
        add(loggingin);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/login.jpg"));
        Image i2=i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        login=new JButton("Login",new ImageIcon(i2));
        login.setBounds(300,160,100,20);
        login.addActionListener(this);
        add(login);
       
        ImageIcon i3=new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i4=i3.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        cancel=new JButton("Cancel",new ImageIcon(i4));
        cancel.setBounds(450,160,100,20);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i5=new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i6=i5.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        signup=new JButton("Sign up",new ImageIcon(i6));
        signup.setBounds(375,220,160,20);
        signup.addActionListener(this);
        add(signup);
        
        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i8=i7.getImage().getScaledInstance(250, 270, Image.SCALE_DEFAULT);
        ImageIcon i9=new ImageIcon(i8);
        JLabel image=new JLabel(i9);
        image.setBounds(0,0,250,270);
        add(image);
        
        setSize(640,300);
        setLocation(400,200);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
         if(ae.getSource()==login){
             String susername=username.getText();
             String spassword=password.getText();
             String user=loggingin.getSelectedItem();
             
             try{
                 Conn c=new Conn();
                 String query="select * from login where username='"+susername+"'and pass='"+spassword+"' and user='"+user+"'";
                 ResultSet rs=c.s.executeQuery(query);
                 if(rs.next()){
                     String m_num=rs.getString("meter_no");
                     setVisible(false);
                     new Project(user,m_num,susername);
                 }else{
                     JOptionPane.showMessageDialog(null,"Invalid Login");
                     username.setText("");
                     password.setText("");
                           
                 }
                 
             }catch(Exception e){
                 e.printStackTrace();
             }
             
         }else if(ae.getSource()==cancel){
             setVisible(false);
         }else if(ae.getSource()==signup){
             setVisible(false);
             new Signup();
         }
    }
    public static void main(String[] args){
        new Login();
    }
}
