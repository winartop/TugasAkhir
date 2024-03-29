/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewProgram;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import lvq.Main;


/**
 *
 * @author oikawa
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        this.setLocationRelativeTo(null); 
        ImageIcon icon = new ImageIcon(getClass().getResource("/aset/fruits.png"));
        setIconImage(icon.getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btn1MenuEkstrasiFitur = new javax.swing.JLabel();
        btn2MenuLVQ = new javax.swing.JLabel();
        btn3MenuBantuan = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Implementasi Image Processing dengan");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, -1, -1));

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 24)); // NOI18N
        jLabel3.setText("Aplikasi Pengenalan Citra Buah-buahan");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 160, -1, -1));

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Metode Learning Vector Quantization Untuk");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, -1, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aset/fruits.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 260, -1, -1));

        btn1MenuEkstrasiFitur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aset/btn1.png"))); // NOI18N
        btn1MenuEkstrasiFitur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn1MenuEkstrasiFiturMouseClicked(evt);
            }
        });
        jPanel1.add(btn1MenuEkstrasiFitur, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 500, -1, -1));

        btn2MenuLVQ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aset/btn2.png"))); // NOI18N
        btn2MenuLVQ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn2MenuLVQMouseClicked(evt);
            }
        });
        jPanel1.add(btn2MenuLVQ, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 500, -1, -1));

        btn3MenuBantuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aset/btn3.png"))); // NOI18N
        btn3MenuBantuan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn3MenuBantuanMouseClicked(evt);
            }
        });
        jPanel1.add(btn3MenuBantuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 500, -1, -1));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aset/icons8_delete_sign2_16px.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 0, 50, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aset/background.png"))); // NOI18N
        jLabel6.setMaximumSize(new java.awt.Dimension(1245, 695));
        jLabel6.setMinimumSize(new java.awt.Dimension(1245, 695));
        jLabel6.setPreferredSize(new java.awt.Dimension(1245, 695));
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1250, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this,
             "Apakah Anda yakin ingin menutup Aplikasi ini? ",  "Tutup Aplikasi? ",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
        System.exit(0);
        }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void btn1MenuEkstrasiFiturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn1MenuEkstrasiFiturMouseClicked
        new EkstrasiDTraining().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn1MenuEkstrasiFiturMouseClicked

    private void btn2MenuLVQMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn2MenuLVQMouseClicked
        new Main().main();
        this.setVisible(false);
    }//GEN-LAST:event_btn2MenuLVQMouseClicked

    private void btn3MenuBantuanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn3MenuBantuanMouseClicked
        new MenuBantuan().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn3MenuBantuanMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
                //ImageIcon icon = new ImageIcon(getClass().getResource("/aset/fruits.png"));
                //frameDepan.setIconImage(icon.getImage());
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn1MenuEkstrasiFitur;
    private javax.swing.JLabel btn2MenuLVQ;
    private javax.swing.JLabel btn3MenuBantuan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

}
