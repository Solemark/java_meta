package bmi.view;
import bmi.model.BmiQueries;
import static bmi.model.BmiQueries.list;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
public class BMIView extends javax.swing.JFrame {

    static public String sID,rating,dbOut="",sHeight,sWeight,sMinBMI,sMaxBMI;
    static public double height,weight,tRating,minBMI,maxBMI;
    DecimalFormat df=new DecimalFormat("#.00");
    
    public BMIView() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Hills Public School Bmi Calculator");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Input");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Output");

        jLabel4.setText("Student#");

        jLabel5.setText("Height(cms)");

        jLabel6.setText("Weight(kgs)");

        jLabel7.setText("Bmi");

        jLabel8.setText("Rating");

        jLabel9.setText("Range of Bmi");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Queries");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Functions");

        jTextField4.setEditable(false);
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTextField5.setEditable(false);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("All Students");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Specific Student");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Bmi in Range");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("At Risk Student");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Update");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Add New");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Clear");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Exit");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField1)
                                            .addComponent(jTextField2)
                                            .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextField5)
                                            .addComponent(jTextField3)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane1)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8)))))
                .addGap(14, 14, 14))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //At Risk Students
        clear();
        BmiQueries.getAtRisk();
        int i;
        
        jTextArea1.setText("At Risk Students");
        tableHead();
        for(i=0;i<BmiQueries.list.size();i++){
            jTextArea1.append(BmiQueries.list.get(i)+"\n");
        }
        lineBreak();
        jTextArea1.append("Total students at risk: "+i);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //Update
        sID=jTextField1.getText();
        sID=sID.toUpperCase();
        sHeight=jTextField2.getText();
        height=Double.parseDouble(sHeight);
        if(height<=0){
            JOptionPane.showMessageDialog(null,"Error! No Student height entered","ERROR",JOptionPane.ERROR_MESSAGE);
            jTextField2.requestFocus();
            return;
        }
        sWeight=jTextField3.getText();
        weight=Double.parseDouble(sWeight);
        if(weight<=0){
            JOptionPane.showMessageDialog(null,"Error! No Student weight entered","ERROR",JOptionPane.ERROR_MESSAGE);
            jTextField3.requestFocus();
            return;
        }
        rating=jTextField5.getText();
        clear();
        dbOut=sID+", "+height+", "+weight+", "+rating;
        tableHead();
        jTextArea1.append(dbOut+"\n");
        lineBreak();
        jTextArea1.append("Update Successful");
        
        BmiQueries.updateStudent(sID,height,weight,rating);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //Add New
        sID=jTextField1.getText();
        sID=sID.toUpperCase();
        sHeight=jTextField2.getText();
        height=Double.parseDouble(sHeight);
        if(height<=0){
            JOptionPane.showMessageDialog(null,"Error! No Student height entered","ERROR",JOptionPane.ERROR_MESSAGE);
            jTextField2.requestFocus();
            return;
        }
        sWeight=jTextField3.getText();
        weight=Double.parseDouble(sWeight);
        if(weight<=0){
            JOptionPane.showMessageDialog(null,"Error! No Student weight entered","ERROR",JOptionPane.ERROR_MESSAGE);
            jTextField3.requestFocus();
            return;
        }
        height=height/100;
        tRating=(height*height);
        tRating=(weight/tRating);
        rating=df.format(tRating);
        jTextField4.setText(rating);
        
        clear();
        dbOut=sID+", "+height+", "+weight+", "+rating;
        tableHead();
        jTextArea1.append(dbOut+"\n");
        lineBreak();
        jTextArea1.append("New student added");
                
        bmiRating();
        rating=jTextField5.getText();
        BmiQueries.addNewStudent(sID,height,weight,rating);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        clear();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //All Students Button
        clear();        
        tableHead();
        try{
            Connection con=DriverManager.getConnection(BmiQueries.getHost());
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=stmt.executeQuery(BmiQueries.getAllStudents());
            
            rs.beforeFirst();
            while(rs.next()){
                sID=rs.getString("STUDENTID");
                height=rs.getDouble("HEIGHT");
                weight=rs.getDouble("WEIGHT");
                rating=rs.getString("RATING");
                dbOut=sID+", "+height+", "+weight+", "+rating;
        
                list.add(dbOut);
                System.out.println(dbOut);
                jTextArea1.append(dbOut+"\n");
            }
            System.out.println("Done!");
            lineBreak();
            jTextArea1.append("Total number of students: "+list.size());
            con.close();
            rs.close();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
            JOptionPane.showMessageDialog(null,"Error!\n"+err.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        } catch (Exception err) {
            System.out.println(err.getMessage());
            JOptionPane.showMessageDialog(null,"Error, unable to connect to server","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Specific Student
        try{
            sID=jTextField1.getText();
            sID=sID.toUpperCase();
            clear();
            
            Connection con=DriverManager.getConnection(BmiQueries.getHost());
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=stmt.executeQuery(BmiQueries.getSpecificStudent(sID));
            
            if(sID.equals("")){
            JOptionPane.showMessageDialog(null,"No Student ID entered","ERROR",JOptionPane.ERROR_MESSAGE);
            jTextField1.requestFocus();
            return;
            }
            rs.beforeFirst();
            while(rs.next()){
                sID=rs.getString("STUDENTID");
                height=rs.getDouble("HEIGHT");
                weight=rs.getDouble("WEIGHT");
                rating=rs.getString("RATING");
                dbOut=sID+" "+height+" "+weight+" "+rating;
        
                list.add(sID);
                System.out.println(dbOut);
                jTextArea1.append(dbOut+"\n");
                jTextField1.setText(sID);
                jTextField2.setText(""+height);
                jTextField3.setText(""+weight);
                
                height=height/100;
                tRating=(height*height);
                tRating=(weight/tRating);
                rating=df.format(tRating);
                jTextField4.setText(rating);
                bmiRating();   
            }
            for(int i=0;i<=list.size();i++){
                if(list.get(i).equals(sID)){
                    break;
                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid Student ID entered","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
            System.out.println("Done!");
            con.close();
            rs.close();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
            JOptionPane.showMessageDialog(null,"Error!\n"+err.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        } catch (Exception err) {
            System.out.println(err.getMessage());
            JOptionPane.showMessageDialog(null,"Error!\n"+err.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //BMI in Range
        jTextArea1.setText("");
        try{
            sMinBMI=jTextField6.getText();
            minBMI=Double.parseDouble(sMinBMI);
            sMaxBMI=jTextField7.getText();
            maxBMI=Double.parseDouble(sMaxBMI);
            Connection con=DriverManager.getConnection(BmiQueries.getHost());
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=stmt.executeQuery(BmiQueries.getBMIRange(minBMI,maxBMI));
            int i=0;
            
            jTextArea1.setText("Bmi in Range");
            tableHead();
            rs.beforeFirst();
            while(rs.next()){
                sID=rs.getString("STUDENTID");
                height=rs.getDouble("HEIGHT");
                weight=rs.getDouble("WEIGHT");
                rating=rs.getString("RATING");
                dbOut=sID+" "+height+" "+weight+" "+rating;
        
                System.out.println(dbOut);
                jTextArea1.append(dbOut+"\n");
                i++;
            }
            System.out.println("Done!");
            lineBreak();
            jTextArea1.append("Total students in range: "+i);
            con.close();
            rs.close();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
            JOptionPane.showMessageDialog(null,"Error!\n"+err.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        } catch (Exception err) {
            System.out.println(err.getMessage());
            JOptionPane.showMessageDialog(null,"Error!\n"+err.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    public static void main(String[] args){
    }
    public void bmiRating(){
        if(tRating<16.00){
            jTextField5.setText("Bulimic");
            jTextField6.setText("16.00");
        }
        if(tRating>16.00&tRating<16.99){
            jTextField5.setText("Lean");
            jTextField6.setText("16.00");
            jTextField7.setText("16.99");
        }
        if(tRating>17.00&tRating<18.49){
            jTextField5.setText("Under");
            jTextField6.setText("17.00");
            jTextField7.setText("18.49");
        }
        if(tRating>18.50&tRating<24.99){
            jTextField5.setText("Normal");
            jTextField6.setText("18.50");
            jTextField7.setText("24.99");
        }
        if(tRating>25.00&tRating<29.99){
            jTextField5.setText("Over");
            jTextField6.setText("25.00");
            jTextField7.setText("29.99");
        }
        if(tRating>30.00&tRating<34.99){
            jTextField5.setText("Obese");
            jTextField6.setText("30.00");
            jTextField7.setText("34.99");
        }
        if(tRating>34.99){
            jTextField5.setText("Morbid");
            jTextField7.setText("34.99");
        }
    }
    public void clear(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextArea1.setText("");
        list.clear();
    }
    public void tableHead(){
        jTextArea1.setText("Display all Students\nStudent ID, Height, Weight, Rating\n");
        lineBreak();
    }
    public void lineBreak(){
        jTextArea1.append("------------------------------\n");
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}