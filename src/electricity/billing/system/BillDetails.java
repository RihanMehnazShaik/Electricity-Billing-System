
package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class BillDetails extends JFrame {
    
    String m_num;
    BillDetails(String m_num){
        this.m_num=m_num;
        setSize(700,600);
        setLocation(400,150);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
        JTable table=new JTable();
        try{
            Conn c=new Conn();
            String query="select * from bill where meter_no='"+m_num+"'";
            ResultSet rs=c.s.executeQuery(query);
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JScrollPane sp=new JScrollPane(table);
        sp.setBounds(0,0,700,600);
        add(sp);

        
    }
    public static void main(String args[]){
        new BillDetails("");
    }
}
