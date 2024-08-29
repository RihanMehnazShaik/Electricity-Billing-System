
package electricity.billing.system;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class Signup extends JFrame implements ActionListener{
    
    JButton back,create;
    Choice account_type;
    JTextField usn_in,n_in,pass_in,meter_in; 
    
    Signup(){
          setSize(700,500);
          setLocation(450,150);
          
          getContentPane().setBackground(Color.BLACK);
          setLayout(null);
          JPanel panel=new JPanel();
          panel.setBounds(30,30,650,400);
          panel.setBackground(Color.WHITE);
          panel.setBorder(new TitledBorder(new LineBorder(new Color(9,54,108),2),"Create Account",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(9,54,108)));
          panel.setLayout(null);
          panel.setForeground(Color.BLACK);
          add(panel);
          
          JLabel heading=new JLabel("Create as:");
          heading.setBounds(100,50,140,20);
          heading.setFont(new Font("Tahoma",Font.BOLD,14));
          panel.add(heading);
          
          account_type=new Choice();
          account_type.add("Admin");
          account_type.add("Customer");
          account_type.setBounds(260,50,140,20);
          panel.add(account_type);
          
          
          JLabel meter=new JLabel("Meter number:");
          meter.setBounds(100,100,140,20);
          meter.setFont(new Font("Tahoma",Font.BOLD,14));
          meter.setVisible(false);
          panel.add(meter);
          
          
          meter_in=new JTextField();
          meter_in.setBounds(260,100,140,20);
          meter_in.setVisible(false);
          panel.add(meter_in);
          
          JLabel usn=new JLabel("User name:");
          usn.setBounds(100,150,140,20);
          usn.setFont(new Font("Tahoma",Font.BOLD,14));
          panel.add(usn);
          
          usn_in=new JTextField();
          usn_in.setBounds(260,150,140,20);
          panel.add(usn_in);
          
          JLabel n=new JLabel("Name:");
          n.setBounds(100,200,140,20);
          n.setFont(new Font("Tahoma",Font.BOLD,14));
          panel.add(n);
          
          n_in=new JTextField();
          n_in.setBounds(260,200,140,20);
          panel.add(n_in);
        
          meter_in.addFocusListener(new FocusListener(){
              
              public void focusGained(FocusEvent fe){
                  
              }
              
              public void focusLost(FocusEvent fe){
                  try{
                      Conn c=new Conn();
                      ResultSet rs=c.s.executeQuery("select * from login where meter_no='"+meter_in.getText()+"'");
                      while(rs.next()){
                          n_in.setText(rs.getString("name"));
                      }
                  }catch(Exception e){
                      e.printStackTrace();
                  }
              }
            });
          
           account_type.addItemListener(new ItemListener(){
                public void itemStateChanged(ItemEvent ae){
                  String user=account_type.getSelectedItem();
                  if(user.equals("Customer")){
                      meter.setVisible(true);
                      meter_in.setVisible(true);
                      n_in.setEditable(false);
                  }else{
                      meter.setVisible(false);
                      meter_in.setVisible(false);
                      n_in.setEditable(true);
                  }
              }
            });
          
          
          JLabel pass=new JLabel("Password:");
          pass.setBounds(100,250,140,20);
          pass.setFont(new Font("Tahoma",Font.BOLD,14));
          panel.add(pass);
          
          pass_in=new JTextField();
          pass_in.setBounds(260,250,140,20);
          panel.add(pass_in);
          
          create=new JButton("create");
          create.setBounds(320,300,80,20);
          create.addActionListener(this);
          panel.add(create);
          
          back=new JButton("back");
          back.setBounds(100,300,80,20);
          back.addActionListener(this);
          panel.add(back);
        
          ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
          Image i2=i1.getImage().getScaledInstance(250, 250,Image.SCALE_DEFAULT );
          ImageIcon i3=new ImageIcon(i2);
          JLabel image=new JLabel(i3);
          image.setBounds(415,30,250,250);
          panel.add(image);
          
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==create){
            String atype=account_type.getSelectedItem();
            String susername=usn_in.getText();
            String sname=n_in.getText();
            String spassword=pass_in.getText();
            String smeter=meter_in.getText();
            
            try{
                Conn c=new Conn();
                String query=null;
                if(atype.equals("Admin")){
                  query="insert into login values('"+smeter+"','"+susername+"','"+sname+"','"+spassword+"','"+atype+"')";
                
                }else{
                   query="update login set username='"+susername+"',pass='"+spassword+"',user='"+atype+"' where meter_no='"+smeter+"'";
                   
                }
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                setVisible(false);
                new Login();
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource()==back){
            setVisible(false);
            new Login();
        }
    }
    
    public static void main(String[] args){
        new Signup();
    }
}
