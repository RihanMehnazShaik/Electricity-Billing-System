
package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class GenerateBill extends JFrame implements ActionListener{
    
    String meter;
    JButton bill;
    Choice mon;
    JTextArea area;
    GenerateBill(String meter){ 
        this.meter=meter;
        setSize(500,800);
        setLocation(550,30);
       
        setLayout(new BorderLayout());
        
        JPanel panel=new JPanel();
        
        JLabel heading=new JLabel("Generate Bill");
        JLabel meternumber=new JLabel(meter);
        
        
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
        
        area=new JTextArea(50,15);
        area.setText("\n\n\t-------------Click on the---------\n\tGenerate Bill Button to get\n\t the bill of the selected month");
        area.setFont(new Font("Tahoma",Font.ITALIC,18));
        JScrollPane pane=new JScrollPane(area);
        
        bill=new JButton("Generate Bill");
        bill.addActionListener(this);
        
        panel.add(heading);
        panel.add(meternumber);
        panel.add(mon);
        add(panel,"North");
        add(pane,"Center");
        add(bill,"South");
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        try{
            Conn c=new Conn();
            String month=mon.getSelectedItem();
            area.setText("\tReliance Power Limited\n\tELECTRICITY BILL GENERATED FOR THE MONTH OF \n\t"+month+", 2022\n\n\n");
            ResultSet rs=c.s.executeQuery("select * from customer where meter_no='"+meter+"'");
            
            if(rs.next()){
                area.append("\n    Customer Name: " + rs.getString("name"));
                area.append("\n    Meter Number   : " + rs.getString("meter_no"));
                area.append("\n    Address             : " + rs.getString("address"));
                area.append("\n    City                 : " + rs.getString("city"));
                area.append("\n    State                : " + rs.getString("state"));
                area.append("\n    Email                : " + rs.getString("email"));
                area.append("\n    Phone                 : " + rs.getString("phone"));
                area.append("\n---------------------------------------------------");
                area.append("\n");
            }
            
            rs=c.s.executeQuery("select * from meter_info where meter_no='"+meter+"'");
            
            if(rs.next()){
                area.append("\n    Meter Location: " + rs.getString("meter_loc"));
                area.append("\n    Meter Type   : " + rs.getString("meter_type"));
                area.append("\n    Phase Code             : " + rs.getString("phase_code"));
                area.append("\n    Bill Type                 : " + rs.getString("bill_type"));
                area.append("\n    Days                : " + rs.getString("days"));
                area.append("\n---------------------------------------------------");
                area.append("\n");
            }
            
            rs=c.s.executeQuery("select * from tax ");
            
            if(rs.next()){
                area.append("\n");
                area.append("\n");
                area.append("\n    Cost per unit              : " + rs.getString("cost_per_unit"));
                area.append("\n    Meter Rent                 : " + rs.getString("meter_rent"));
                area.append("\n    Service charge             : " + rs.getString("service_charge"));
                area.append("\n    Cost per unit              : " + rs.getString("cost_per_unit"));
                area.append("\n    Swacch Bharat Cess         : " + rs.getString("swacch_bharat_cess"));
                area.append("\n    Fixed tax                  : " + rs.getString("fixed_tax"));
                area.append("\n---------------------------------------------------");
                area.append("\n");
            }    
            
            rs=c.s.executeQuery("select * from bill where meter_no='"+meter+"'and month='"+month+"'");
            
            if(rs.next()){
                
                area.append("\n");
                area.append("\n    Current Month              : " + rs.getString("month"));
                area.append("\n    Units                 : " + rs.getString("units"));
                area.append("\n    Total Charges            : " + rs.getString("totalBill"));
                area.append("\n---------------------------------------------------");
                area.append("\n    Total payment              : " + rs.getString("totalBill"));
                
                area.append("\n");
            }
    }catch(Exception e){
        e.printStackTrace();
    }
    }  
    public static void main(String args[]){
        new GenerateBill("");
    }
}
