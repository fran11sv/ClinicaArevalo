package Forms;
import Controladores.DetalleFacturaJpaController;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Entidades.Factura;
import Controladores.FacturaJpaController;
import Controladores.ProductoJpaController;
import Entidades.entityMain;
import java.text.SimpleDateFormat;
import java.util.Date;
import Controladores.UsuarioJpaController;
import Entidades.DetalleFactura;
import Entidades.Producto;
import Entidades.Usuario;
import java.awt.Component;
import java.util.Calendar;
import java.util.HashSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import static org.eclipse.persistence.expressions.ExpressionOperator.Cast;

/**
 *
 * @author Miguel
 */
public class Facturar extends javax.swing.JFrame {
FacturaJpaController FC = new FacturaJpaController(entityMain.getInstance());
UsuarioJpaController UC = new UsuarioJpaController(entityMain.getInstance());
ProductoJpaController PC = new ProductoJpaController(entityMain.getInstance());
DetalleFacturaJpaController DFC = new DetalleFacturaJpaController(entityMain.getInstance());
Factura numFac;
    public Facturar() {
        initComponents();
        PanelPestañas.setEnabledAt(1, false);
        CrearModelo();
        CargarTabla();
        Llenar_Combo();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        ClinicaArevaloPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("ClinicaArevaloPU").createEntityManager();
        usuarioQuery = java.beans.Beans.isDesignTime() ? null : ClinicaArevaloPUEntityManager.createQuery("SELECT u FROM Usuario u");
        usuarioList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : usuarioQuery.getResultList();
        usuarioQuery1 = java.beans.Beans.isDesignTime() ? null : ClinicaArevaloPUEntityManager.createQuery("SELECT u FROM Usuario u");
        usuarioList1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : usuarioQuery1.getResultList();
        PanelPestañas = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Date date = new Date();
        SpinnerDateModel sm =
        new SpinnerDateModel(date, null, null, Calendar.DAY_OF_MONTH);
        spFecha = new javax.swing.JSpinner(sm);
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnMenu = new javax.swing.JButton();
        txtCliente = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbFactura = new javax.swing.JTable();
        cmbUsuario = new javax.swing.JComboBox<>();
        txtNumFac = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblFactura = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtProd = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbProducto = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbDetalleFactura = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        btnAgregar2 = new javax.swing.JButton();
        txtCancelar = new javax.swing.JButton();
        txtEliminar = new javax.swing.JButton();
        txtCobrar = new javax.swing.JButton();
        txtImprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Factura");
        setExtendedState(6);
        setSize(new java.awt.Dimension(1180, 830));

        jPanel1.setBackground(new java.awt.Color(15, 76, 129));

        jLabel1.setText("N° de Factura");

        jLabel2.setText("Fecha de Factura");

        JSpinner.DateEditor de = new JSpinner.DateEditor(spFecha, "dd-MM-yyyy");
        spFecha.setEditor(de);
        spFecha.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        spFecha.setEnabled(false);

        spFecha.setPreferredSize(new java.awt.Dimension(200, 35));
        spFecha.setEnabled(false);

        jLabel3.setText("Cliente");

        jLabel4.setText("Usuario");

        btnSiguiente.setText("Siguiente");
        btnSiguiente.setEnabled(false);
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.setEnabled(false);

        btnMenu.setText("Menú Principal");

        txtCliente.setEnabled(false);
        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.setEnabled(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        tbFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFacturaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbFactura);

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, usuarioList, cmbUsuario);
        bindingGroup.addBinding(jComboBoxBinding);

        txtNumFac.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumFac, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(35, 35, 35)
                                .addComponent(spFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(210, 210, 210))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnNuevo)
                                .addGap(18, 18, 18)
                                .addComponent(btnAgregar)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtNumFac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSiguiente))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(spFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cmbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNuevo)
                            .addComponent(btnAgregar)
                            .addComponent(btnEliminar)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar)
                        .addGap(18, 18, 18)
                        .addComponent(btnMenu)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        PanelPestañas.addTab("Datos de Factura", jPanel1);

        jPanel2.setBackground(new java.awt.Color(15, 76, 129));

        jLabel6.setText("N° de Factura");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, txtNumFac, org.jdesktop.beansbinding.ELProperty.create("${text}"), lblFactura, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel5.setText("Producto");

        txtProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProdKeyReleased(evt);
            }
        });

        jLabel8.setText("Total");

        tbProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProductoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbProducto);

        tbDetalleFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tbDetalleFactura);

        jLabel9.setText("$15");

        btnAgregar2.setText("Agregar");
        btnAgregar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregar2ActionPerformed(evt);
            }
        });

        txtCancelar.setText("Cancelar");

        txtEliminar.setText("Eliminar");

        txtCobrar.setText("Cobrar");

        txtImprimir.setText("Imprimir");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAgregar2)
                        .addGap(18, 18, 18)
                        .addComponent(txtCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(txtEliminar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtProd, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9))
                            .addComponent(txtImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCobrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lblFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar2)
                    .addComponent(txtCancelar)
                    .addComponent(txtEliminar))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCobrar)
                        .addGap(34, 34, 34)
                        .addComponent(txtImprimir)
                        .addGap(47, 47, 47)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(24, 24, 24))
        );

        PanelPestañas.addTab("Detalle de Factura", jPanel2);

        getContentPane().add(PanelPestañas, java.awt.BorderLayout.CENTER);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try
        {
            Factura F = new Factura();
            SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
            String spinnerValue = formater.format(spFecha.getValue());
            Date date = formater.parse(spinnerValue);
            String id = txtNumFac.getText();
            numFac = (Factura) FC.findFactura(Integer.parseInt(id));

           
            
          
            F.setNombreCliente(this.txtCliente.getText());
            F.setIdUsuario((Usuario) cmbUsuario.getSelectedItem());
            F.setFechaFactura(date);
            F.setEstadoFactura(0);
            
            JOptionPane.showMessageDialog(null, "Datos agregados exitosamente!");
           FC.create(F);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        CargarTabla();
        this.txtCliente.setText("");

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        this.spFecha.setEnabled(true);
        this.txtCliente.setEnabled(true);
        this.btnAgregar.setEnabled(true);
        this.btnEliminar.setEnabled(true);
        this.btnLimpiar.setEnabled(true);
        this.btnNuevo.setEnabled(false);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtProdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProdKeyReleased
        CrearModeloProd();
        CargarTablaProducto(this.txtProd.getText());
    }//GEN-LAST:event_txtProdKeyReleased

    private void tbProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductoMouseClicked
        txtProd.setText(tbProducto.getValueAt(tbProducto.getSelectedRow(), 1).toString());
    }//GEN-LAST:event_tbProductoMouseClicked

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
         this.PanelPestañas.setSelectedIndex(1);
         PanelPestañas.setEnabledAt(0, false);
         CargarTablaProductosinNombre();
         
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        CrearModelo();
        CargarTabla();
    }//GEN-LAST:event_txtClienteKeyReleased

    private void btnAgregar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar2ActionPerformed
        try {
            DetalleFactura DF = new DetalleFactura();
            String id = tbFactura.getValueAt(tbFactura.getSelectedRow(),0).toString();
            numFac = (Factura) FC.findFactura(Integer.parseInt(id));
            
            DF.setIdProducto((Producto) this.tbProducto.getValueAt(tbProducto.getSelectedRow(),0));
            DF.setNumFactura(numFac);
            DFC.create(DF);
            
            
            CrearTablaDetalle();
            CrearModelo();
            CargarTablaDetalle();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAgregar2ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tbFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFacturaMouseClicked
        this.btnSiguiente.setEnabled(true);
        btnNuevo.setEnabled(false);
        btnAgregar.setEnabled(false);
        
        txtNumFac.setText(tbFactura.getValueAt(tbFactura.getSelectedRow(), 0).toString());
        txtCliente.setText(tbFactura.getValueAt(tbFactura.getSelectedRow(),1).toString());
        cmbUsuario.setSelectedItem(tbFactura.getValueAt(tbFactura.getSelectedRow(), 2).toString());
        spFecha.setValue(tbFactura.getValueAt(tbFactura.getSelectedRow(), 3));
                       
    }//GEN-LAST:event_tbFacturaMouseClicked
DefaultTableModel modelo;
    private void CrearModelo() {
        try {
            modelo = (new DefaultTableModel(
                    null, new String[]{
                        "ID", "Cliente","Usuario","Fecha"}) {
                Class[] types = new Class[]{
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,

                };
                boolean[] canEdit = new boolean[]{
                    false, false, false, false
                };

                @Override
                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return canEdit[colIndex];
                }
            });
            tbFactura.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    private void CargarTabla() {
        try {
            Object o[] = null;
            List<Factura> listFactura = FC.findFacturaEntities();
            for (int i = 0; i < listFactura.size(); i++) {
              
                modelo.addRow(o);
                modelo.setValueAt(listFactura.get(i).getNumFactura(), i, 0);
                modelo.setValueAt(listFactura.get(i).getNombreCliente(), i, 1);
                modelo.setValueAt(listFactura.get(i).getIdUsuario().getUsuario(), i, 2);
                modelo.setValueAt(listFactura.get(i).getFechaFactura(), i, 3);
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }    
    }
    
   DefaultTableModel modeloProd;
   private void CrearModeloProd(){
       try {
            modeloProd = (new DefaultTableModel(
                    null, new String[]{
                        "ID", "Nombre","Precio"}) {
                Class[] types = new Class[]{
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,

                };
                boolean[] canEdit = new boolean[]{
                    false, false, false, false
                };

                @Override
                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return canEdit[colIndex];
                }
            });
            tbProducto.setModel(modeloProd);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
   }
   private void CargarTablaProducto(String nombre){
       try {
            Object o[] = null;
            List<Producto> listProducto = PC.findProductoporNombre(nombre);
            for (int i = 0; i < listProducto.size(); i++) {
              
                modeloProd.addRow(o);
                modeloProd.setValueAt(listProducto.get(i).getIdProducto(), i, 0);
                modeloProd.setValueAt(listProducto.get(i).getNombre(), i, 1);
                modeloProd.setValueAt(listProducto.get(i).getPrecio(),i,2);
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }    
   }
   private void CargarTablaProductosinNombre(){
       try {
            Object o[] = null;
            List<Producto> listProducto = PC.findProductoEntities();
            for (int i = 0; i < listProducto.size(); i++) {
              
                modeloProd.addRow(o);
                modeloProd.setValueAt(listProducto.get(i).getIdProducto(), i, 0);
                modeloProd.setValueAt(listProducto.get(i).getNombre(), i, 1);
                modeloProd.setValueAt(listProducto.get(i).getPrecio(),i,2);
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }    
   }
   DefaultTableModel modeloDetalle;
   private void CrearTablaDetalle(){
    try {
            modeloDetalle = (new DefaultTableModel(
                    null, new String[]{
                        "ID", "Nombre","Factura"}) {
                Class[] types = new Class[]{
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,

                };
                boolean[] canEdit = new boolean[]{
                    false, false, false, false
                };

                @Override
                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return canEdit[colIndex];
                }
            });
            tbDetalleFactura.setModel(modeloDetalle);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
}
   private void CargarTablaDetalle(){
       try {
            Object o[] = null;
            List<DetalleFactura> listDetalle  = DFC.findDetalleFacturaEntities();
            for (int i = 0; i < listDetalle.size(); i++) {
              
                modeloDetalle.addRow(o);
                modeloDetalle.setValueAt(listDetalle.get(i).getIdDetalleFactura(), i, 0);
                modeloDetalle.setValueAt(listDetalle.get(i).getIdProducto().getNombre(), i, 1);
                modeloDetalle.setValueAt(listDetalle.get(i).getNumFactura(),i,2);
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }    
   }
    private void Llenar_Combo(){
        cmbUsuario.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int i, boolean bln, boolean bln1) {
                if (o instanceof Usuario) {
                    Usuario U = (Usuario) o;
                    return super.getListCellRendererComponent(jlist, U.getUsuario(), i, bln, bln1);
                }
                return super.getListCellRendererComponent(jlist, o, i, bln, bln1);
            }
        });
    }
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
            java.util.logging.Logger.getLogger(Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Facturar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager ClinicaArevaloPUEntityManager;
    private javax.swing.JTabbedPane PanelPestañas;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregar2;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JComboBox<String> cmbUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblFactura;
    private javax.swing.JSpinner spFecha;
    private javax.swing.JTable tbDetalleFactura;
    private javax.swing.JTable tbFactura;
    private javax.swing.JTable tbProducto;
    private javax.swing.JButton txtCancelar;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JButton txtCobrar;
    private javax.swing.JButton txtEliminar;
    private javax.swing.JButton txtImprimir;
    private javax.swing.JTextField txtNumFac;
    private javax.swing.JTextField txtProd;
    private java.util.List<Entidades.Usuario> usuarioList;
    private java.util.List<Entidades.Usuario> usuarioList1;
    private javax.persistence.Query usuarioQuery;
    private javax.persistence.Query usuarioQuery1;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
