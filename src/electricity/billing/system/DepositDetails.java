
package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class DepositDetails extends JFrame implements ActionListener{
    
    Choice meternumber,mon;
    JTable table;
    JButton search,print;
    DepositDetails(){
        super("Deposit Details");
        setSize(700,700);
        setLocation(400,100);
        
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel lblmeternumber=new JLabel("Search By Meter Number:");
        lblmeternumber.setBounds(20,20,150,20);
        add(lblmeternumber);
        
        meternumber=new Choice();
        meternumber.setBounds(180,20,150,20);
        add(meternumber);
        
        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select * from customer");
            while(rs.next()){
                meternumber.add(rs.getString("meter_no"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JLabel lblmonth=new JLabel("Search By Month:");
        lblmonth.setBounds(400,20,100,20);
        add(lblmonth);
        
        mon=new Choice();
        mon.setBounds(560,20,100,20);
        add(mon);
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
        
        search=new JButton("Search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);
        
        print=new JButton("Print");
        print.setBounds(560, 70, 80, 20);
        print.addActionListener(this);
        add(print);
        
        table=new JTable();
        
        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("Select * from Bill");
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JScrollPane sp=new JScrollPane(table);
        sp.setBounds(0,100,700,600);
        add(sp);
        
       
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==search){
            String query="select * from bill where meter_no='"+meternumber.getSelectedItem()+"'and month='"+mon.getSelectedItem()+"'";
            
            try{
                Conn c=new Conn();
                ResultSet rs=c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }catch(Exception e){
                
            }
        }else{
            try{
                table.print();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String args[]){
        new DepositDetails();
    }
}
