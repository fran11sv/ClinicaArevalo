/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

/**
 *
 * @author sotoa
 */
public class ReporteEnfermedades extends javax.swing.JFrame {

    /**
     * Creates new form ReporteEnfermedades
     */
    public ReporteEnfermedades() {
        initComponents();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        txtEnfermedad = new javax.swing.JTextField();
        btnbuscarporCate = new javax.swing.JButton();
        btnBuscarEnfermedad = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        tbEnfermedades = new javax.swing.JTable();
        btnEnfermedad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(187, 232, 223));
        jPanel1.setPreferredSize(new java.awt.Dimension(1360, 700));

        jPanel2.setBackground(new java.awt.Color(253, 149, 53));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.setPreferredSize(new java.awt.Dimension(1360, 60));
        jPanel2.setRequestFocusEnabled(false);

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Enfermedades");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1356, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(15, 76, 129));
        jPanel3.setPreferredSize(new java.awt.Dimension(1110, 524));

        jLabel57.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(239, 239, 239));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel57.setText("Buscar Enfermedad:");
        jLabel57.setPreferredSize(new java.awt.Dimension(200, 40));

        txtEnfermedad.setBackground(new java.awt.Color(239, 239, 239));
        txtEnfermedad.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        txtEnfermedad.setForeground(new java.awt.Color(0, 0, 0));
        txtEnfermedad.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtEnfermedad.setPreferredSize(new java.awt.Dimension(200, 40));
        txtEnfermedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnfermedadActionPerformed(evt);
            }
        });
        txtEnfermedad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEnfermedadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEnfermedadKeyTyped(evt);
            }
        });

        btnbuscarporCate.setBackground(new java.awt.Color(76, 201, 223));
        btnbuscarporCate.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnbuscarporCate.setForeground(new java.awt.Color(0, 0, 0));
        btnbuscarporCate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Buscar 32.png"))); // NOI18N
        btnbuscarporCate.setText("Buscar por Categoría");
        btnbuscarporCate.setPreferredSize(new java.awt.Dimension(240, 40));
        btnbuscarporCate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarporCateActionPerformed(evt);
            }
        });

        btnBuscarEnfermedad.setBackground(new java.awt.Color(76, 201, 223));
        btnBuscarEnfermedad.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnBuscarEnfermedad.setForeground(new java.awt.Color(0, 0, 0));
        btnBuscarEnfermedad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Buscar 32.png"))); // NOI18N
        btnBuscarEnfermedad.setText("Buscar por Enfermedad");
        btnBuscarEnfermedad.setPreferredSize(new java.awt.Dimension(240, 40));
        btnBuscarEnfermedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEnfermedadActionPerformed(evt);
            }
        });

        tbEnfermedades.setBackground(new java.awt.Color(239, 239, 239));
        tbEnfermedades.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        tbEnfermedades.setForeground(new java.awt.Color(0, 0, 0));
        tbEnfermedades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbEnfermedades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbEnfermedadesMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tbEnfermedades);

        btnEnfermedad.setBackground(new java.awt.Color(76, 201, 223));
        btnEnfermedad.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnEnfermedad.setForeground(new java.awt.Color(0, 0, 0));
        btnEnfermedad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Generar Reporte.png"))); // NOI18N
        btnEnfermedad.setText("Generar Reporte Enfermedad");
        btnEnfermedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnfermedadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnEnfermedad))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane14)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEnfermedad, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnbuscarporCate, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscarEnfermedad, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)))))
                .addGap(70, 70, 70))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEnfermedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscarporCate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarEnfermedad, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnEnfermedad)
                .addGap(110, 110, 110))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEnfermedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnfermedadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnfermedadActionPerformed

    private void txtEnfermedadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnfermedadKeyReleased

    }//GEN-LAST:event_txtEnfermedadKeyReleased

    private void txtEnfermedadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnfermedadKeyTyped
        char car = evt.getKeyChar();
        if(Character.isLetter(car) || evt.getKeyChar()==KeyEvent.VK_BACK_SPACE || evt.getKeyChar()==KeyEvent.VK_SPACE){
        }else{
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtEnfermedadKeyTyped

    private void btnbuscarporCateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarporCateActionPerformed
        this.CrearModeloEnfermedades();
        this.CargarTablaPorCategoria(this.txtEnfermedad.getText());
    }//GEN-LAST:event_btnbuscarporCateActionPerformed

    private void btnBuscarEnfermedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEnfermedadActionPerformed
        this.CrearModeloEnfermedades();
        this.CargarTablaPorEnfermedad(this.txtEnfermedad.getText());
    }//GEN-LAST:event_btnBuscarEnfermedadActionPerformed

    private void tbEnfermedadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEnfermedadesMouseClicked

    }//GEN-LAST:event_tbEnfermedadesMouseClicked

    private void btnEnfermedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnfermedadActionPerformed
        try  {
            Conexion con = new Conexion();
            Connection conn = con.getConexion();
            int id = Integer.parseInt(tbEnfermedades.getValueAt(tbEnfermedades.getSelectedRow(), 0).toString());
            String path = "src\\Reportes\\Enfermedad.jasper";
            JasperReport reporte = null;
            reporte =(JasperReport) JRLoader.loadObjectFromFile(path);
            Map parametro = new HashMap();
            parametro.put("idEnfermedad", id);
            JasperPrint j = JasperFillManager.fillReport(reporte, parametro, conn);
            JasperViewer jv= new JasperViewer(j,false);
            jv.setTitle("Enfermedad Detalles / Clinica Arevalo");
            jv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jv.setVisible(true);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }//GEN-LAST:event_btnEnfermedadActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReporteEnfermedades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteEnfermedades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteEnfermedades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteEnfermedades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReporteEnfermedades().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarEnfermedad;
    private javax.swing.JButton btnEnfermedad;
    private javax.swing.JButton btnbuscarporCate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JTable tbEnfermedades;
    private javax.swing.JTextField txtEnfermedad;
    // End of variables declaration//GEN-END:variables
}
