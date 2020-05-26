/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Controladores.CategoriaProductoJpaController;
import Controladores.ProductoJpaController;
import Entidades.CategoriaProducto;
import Entidades.Producto;
import Entidades.entityMain;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sotoa
 */
public class Servicios extends javax.swing.JFrame {
    ProductoJpaController PC = new ProductoJpaController(entityMain.getInstance());
    CategoriaProductoJpaController CC = new CategoriaProductoJpaController(entityMain.getInstance());
    CategoriaProducto EditarCategoria;
    CategoriaProducto EliminarCategoria;
    Producto Editar;
    Producto Eliminar;
    /**
     * Creates new form Servicios
     */
    public Servicios() {
        initComponents();
        llenar_combobox();
        CrearModelo();
        CrearModeloCategoria();
        CargarTabla();
        CargarTablaCategoria();
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        ClinicaArevaloPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("ClinicaArevaloPU").createEntityManager();
        antecedentesQuery = java.beans.Beans.isDesignTime() ? null : ClinicaArevaloPUEntityManager.createQuery("SELECT a FROM Antecedentes a");
        antecedentesList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : antecedentesQuery.getResultList();
        categoriaProductoQuery = java.beans.Beans.isDesignTime() ? null : ClinicaArevaloPUEntityManager.createQuery("SELECT c FROM CategoriaProducto c");
        categoriaProductoList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : categoriaProductoQuery.getResultList();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();
        btnAgregarProducto = new javax.swing.JButton();
        btnNuevoProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        btnEditarProducto = new javax.swing.JButton();
        btnCancelarProducto = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbProductos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        txtDetalleProducto = new javax.swing.JTextField();
        txtNombreProducto = new javax.swing.JTextField();
        txtPrecioProducto = new javax.swing.JTextField();
        cbCategoria = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        btnVolver1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtIdCategoria = new javax.swing.JTextField();
        txtCategoriaProducto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCategoria = new javax.swing.JTable();
        btnNuevoCategoria = new javax.swing.JButton();
        btnEliminarCategoria = new javax.swing.JButton();
        btnEditarCategoria = new javax.swing.JButton();
        btnCancelarCategoria = new javax.swing.JButton();
        btnAgregarCategoria = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(187, 232, 223));

        jPanel2.setBackground(new java.awt.Color(253, 149, 53));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.setPreferredSize(new java.awt.Dimension(1360, 60));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Servicios");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1344, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.setForeground(new java.awt.Color(0, 0, 0));
        jTabbedPane1.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1110, 524));

        jPanel3.setBackground(new java.awt.Color(15, 76, 129));

        btnVolver.setBackground(new java.awt.Color(76, 201, 223));
        btnVolver.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(0, 0, 0));
        btnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Menu.png"))); // NOI18N
        btnVolver.setText(" Menú Principal");
        btnVolver.setPreferredSize(new java.awt.Dimension(200, 60));
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnAgregarProducto.setBackground(new java.awt.Color(76, 201, 223));
        btnAgregarProducto.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnAgregarProducto.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Agg 32.png"))); // NOI18N
        btnAgregarProducto.setText(" Agregar ");
        btnAgregarProducto.setEnabled(false);
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        btnNuevoProducto.setBackground(new java.awt.Color(76, 201, 223));
        btnNuevoProducto.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnNuevoProducto.setForeground(new java.awt.Color(0, 0, 0));
        btnNuevoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Editar 32.png"))); // NOI18N
        btnNuevoProducto.setText(" Nuevo");
        btnNuevoProducto.setPreferredSize(new java.awt.Dimension(200, 40));
        btnNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProductoActionPerformed(evt);
            }
        });

        btnEliminarProducto.setBackground(new java.awt.Color(76, 201, 223));
        btnEliminarProducto.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnEliminarProducto.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Eliminar 32.png"))); // NOI18N
        btnEliminarProducto.setText(" Eliminar");
        btnEliminarProducto.setEnabled(false);
        btnEliminarProducto.setPreferredSize(new java.awt.Dimension(200, 40));
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });

        btnEditarProducto.setBackground(new java.awt.Color(76, 201, 223));
        btnEditarProducto.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnEditarProducto.setForeground(new java.awt.Color(0, 0, 0));
        btnEditarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Guardar Ed.png"))); // NOI18N
        btnEditarProducto.setText(" Guardar Edición");
        btnEditarProducto.setEnabled(false);
        btnEditarProducto.setPreferredSize(new java.awt.Dimension(200, 40));
        btnEditarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProductoActionPerformed(evt);
            }
        });

        btnCancelarProducto.setBackground(new java.awt.Color(76, 201, 223));
        btnCancelarProducto.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnCancelarProducto.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Cancelar 32.png"))); // NOI18N
        btnCancelarProducto.setText(" Cancelar");
        btnCancelarProducto.setEnabled(false);
        btnCancelarProducto.setPreferredSize(new java.awt.Dimension(200, 40));
        btnCancelarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarProductoActionPerformed(evt);
            }
        });

        tbProductos.setBackground(new java.awt.Color(239, 239, 239));
        tbProductos.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        tbProductos.setForeground(new java.awt.Color(0, 0, 0));
        tbProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbProductos.setToolTipText("");
        tbProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProductosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbProductos);

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(239, 239, 239));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Id Producto:");
        jLabel4.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(239, 239, 239));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Categoria:");
        jLabel5.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(239, 239, 239));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Nombre:");
        jLabel6.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel7.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(239, 239, 239));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Detalle:");
        jLabel7.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(239, 239, 239));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Precio:");
        jLabel8.setPreferredSize(new java.awt.Dimension(100, 30));

        jTextField3.setBackground(new java.awt.Color(239, 239, 239));
        jTextField3.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(0, 0, 0));
        jTextField3.setEnabled(false);
        jTextField3.setPreferredSize(new java.awt.Dimension(200, 30));

        txtDetalleProducto.setBackground(new java.awt.Color(239, 239, 239));
        txtDetalleProducto.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtDetalleProducto.setForeground(new java.awt.Color(0, 0, 0));
        txtDetalleProducto.setEnabled(false);
        txtDetalleProducto.setPreferredSize(new java.awt.Dimension(200, 30));
        txtDetalleProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDetalleProductoKeyTyped(evt);
            }
        });

        txtNombreProducto.setBackground(new java.awt.Color(239, 239, 239));
        txtNombreProducto.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtNombreProducto.setForeground(new java.awt.Color(0, 0, 0));
        txtNombreProducto.setEnabled(false);
        txtNombreProducto.setPreferredSize(new java.awt.Dimension(200, 30));
        txtNombreProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreProductoKeyTyped(evt);
            }
        });

        txtPrecioProducto.setBackground(new java.awt.Color(239, 239, 239));
        txtPrecioProducto.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtPrecioProducto.setForeground(new java.awt.Color(0, 0, 0));
        txtPrecioProducto.setEnabled(false);
        txtPrecioProducto.setPreferredSize(new java.awt.Dimension(200, 30));
        txtPrecioProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioProductoKeyTyped(evt);
            }
        });

        cbCategoria.setBackground(new java.awt.Color(239, 239, 239));
        cbCategoria.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        cbCategoria.setForeground(new java.awt.Color(0, 0, 0));
        cbCategoria.setEnabled(false);
        cbCategoria.setPreferredSize(new java.awt.Dimension(200, 30));

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, categoriaProductoList, cbCategoria);
        bindingGroup.addBinding(jComboBoxBinding);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 18, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDetalleProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(txtPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNuevoProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(btnAgregarProducto))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDetalleProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnNuevoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnEliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnEditarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnCancelarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        jTabbedPane1.addTab("Productos", jPanel3);

        jPanel4.setBackground(new java.awt.Color(15, 76, 129));

        btnVolver1.setBackground(new java.awt.Color(76, 201, 223));
        btnVolver1.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnVolver1.setForeground(new java.awt.Color(0, 0, 0));
        btnVolver1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Menu.png"))); // NOI18N
        btnVolver1.setText(" Menú Principal");
        btnVolver1.setPreferredSize(new java.awt.Dimension(200, 60));
        btnVolver1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolver1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(239, 239, 239));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Id Categoria:");
        jLabel2.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(239, 239, 239));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Categoria:");
        jLabel3.setPreferredSize(new java.awt.Dimension(200, 30));

        txtIdCategoria.setBackground(new java.awt.Color(239, 239, 239));
        txtIdCategoria.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtIdCategoria.setForeground(new java.awt.Color(0, 0, 0));
        txtIdCategoria.setEnabled(false);
        txtIdCategoria.setPreferredSize(new java.awt.Dimension(200, 30));

        txtCategoriaProducto.setBackground(new java.awt.Color(239, 239, 239));
        txtCategoriaProducto.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtCategoriaProducto.setForeground(new java.awt.Color(0, 0, 0));
        txtCategoriaProducto.setEnabled(false);
        txtCategoriaProducto.setPreferredSize(new java.awt.Dimension(200, 30));
        txtCategoriaProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCategoriaProductoKeyTyped(evt);
            }
        });

        tbCategoria.setBackground(new java.awt.Color(239, 239, 239));
        tbCategoria.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        tbCategoria.setForeground(new java.awt.Color(0, 0, 0));
        tbCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCategoriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbCategoria);

        btnNuevoCategoria.setBackground(new java.awt.Color(76, 201, 223));
        btnNuevoCategoria.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnNuevoCategoria.setForeground(new java.awt.Color(0, 0, 0));
        btnNuevoCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Editar 32.png"))); // NOI18N
        btnNuevoCategoria.setText(" Nuevo");
        btnNuevoCategoria.setPreferredSize(new java.awt.Dimension(200, 40));
        btnNuevoCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoCategoriaActionPerformed(evt);
            }
        });

        btnEliminarCategoria.setBackground(new java.awt.Color(76, 201, 223));
        btnEliminarCategoria.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnEliminarCategoria.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminarCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Eliminar 32.png"))); // NOI18N
        btnEliminarCategoria.setText(" Eliminar");
        btnEliminarCategoria.setEnabled(false);
        btnEliminarCategoria.setPreferredSize(new java.awt.Dimension(200, 40));
        btnEliminarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCategoriaActionPerformed(evt);
            }
        });

        btnEditarCategoria.setBackground(new java.awt.Color(76, 201, 223));
        btnEditarCategoria.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnEditarCategoria.setForeground(new java.awt.Color(0, 0, 0));
        btnEditarCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Guardar Ed.png"))); // NOI18N
        btnEditarCategoria.setText(" Guardar Edición");
        btnEditarCategoria.setEnabled(false);
        btnEditarCategoria.setPreferredSize(new java.awt.Dimension(200, 40));
        btnEditarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarCategoriaActionPerformed(evt);
            }
        });

        btnCancelarCategoria.setBackground(new java.awt.Color(76, 201, 223));
        btnCancelarCategoria.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnCancelarCategoria.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelarCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Cancelar 32.png"))); // NOI18N
        btnCancelarCategoria.setText(" Cancelar");
        btnCancelarCategoria.setEnabled(false);
        btnCancelarCategoria.setPreferredSize(new java.awt.Dimension(200, 40));
        btnCancelarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCategoriaActionPerformed(evt);
            }
        });

        btnAgregarCategoria.setBackground(new java.awt.Color(76, 201, 223));
        btnAgregarCategoria.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnAgregarCategoria.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Agg 32.png"))); // NOI18N
        btnAgregarCategoria.setText(" Agregar ");
        btnAgregarCategoria.setEnabled(false);
        btnAgregarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnVolver1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAgregarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminarCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNuevoCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditarCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCategoriaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCategoriaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnNuevoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnEliminarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnEditarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnCancelarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(btnVolver1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        jTabbedPane1.addTab("Categorias", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void llenar_combobox() {
        //Rellena Combobox       
         this.cbCategoria.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int i, boolean bln, boolean bln1) {
                if (o instanceof CategoriaProducto) {
                    CategoriaProducto U = (CategoriaProducto) o;
                    return super.getListCellRendererComponent(jlist, U.getNombreCategoria(), i, bln, bln1);
                }
                return super.getListCellRendererComponent(jlist, o, i, bln, bln1);
            }
        });
    }
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
   //Validación de regreso
        int mensaje = JOptionPane.showConfirmDialog(null, "¿Realmente desea regresar al menú principal?"
                + "Se descartarán los datos no guardados.", "Regresar al menú",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (mensaje == 0) {
                    MenuPrincipal menu = new MenuPrincipal();
                    menu.setVisible(true);
                    this.dispose();
            }
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnVolver1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolver1ActionPerformed
        //Validación de regreso
        int mensaje = JOptionPane.showConfirmDialog(null, "¿Realmente desea regresar al menú principal?"
                + "Se descartarán los datos no guardados.", "Regresar al menú",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (mensaje == 0) {
                    MenuPrincipal menu = new MenuPrincipal();
                    menu.setVisible(true);
                    this.dispose();
            }
    }//GEN-LAST:event_btnVolver1ActionPerformed

    private void btnNuevoCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoCategoriaActionPerformed
        txtCategoriaProducto.setText("");
        txtCategoriaProducto.setEnabled(true);
        btnAgregarCategoria.setEnabled(true);
        btnCancelarCategoria.setEnabled(true);
        btnEditarCategoria.setEnabled(false);
        btnEliminarCategoria.setEnabled(false);
        btnNuevoCategoria.setEnabled(false);
    }//GEN-LAST:event_btnNuevoCategoriaActionPerformed

    private void btnEliminarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCategoriaActionPerformed
        try {
        //Confirmación de eliminación de registro
                int mensaje = JOptionPane.showConfirmDialog(null, "Realmente desea eliminar la categoria de producto", "Eliminar categoria",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (mensaje == 0) {
                CC.destroy(EliminarCategoria.getIdCategoria());
                JOptionPane.showMessageDialog(null, "El registro fue eliminado con Exito");
                CrearModeloCategoria();
                CargarTablaCategoria();
                //Limpia los campos luego de eliminar un registro
                txtCategoriaProducto.setText("");
                txtCategoriaProducto.setEnabled(false);
                btnAgregarCategoria.setEnabled(false);
                btnCancelarCategoria.setEnabled(false);
                btnEditarCategoria.setEnabled(false);
                btnEliminarCategoria.setEnabled(false);
                btnNuevoCategoria.setEnabled(true);
                llenar_combobox();
            } else {
                JOptionPane.showMessageDialog(null, "El registro no fue eliminado");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnEliminarCategoriaActionPerformed

    private void btnEditarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCategoriaActionPerformed
        if (txtCategoriaProducto.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Los campos no pueden estar vacíos","Error, campos vacíos",JOptionPane.INFORMATION_MESSAGE);
        }else{
            try {
             EditarCategoria.setNombreCategoria(txtCategoriaProducto.getText());
             
             //Validación de datos para editar
            int mensaje = JOptionPane.showConfirmDialog(null, "¿Datos correctos?", "Editar Categoria",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (mensaje == 0) {
                CC.edit(EditarCategoria);
                    JOptionPane.showMessageDialog(null, "El registro fue editado con éxito");
                    CrearModeloCategoria();
                    CargarTablaCategoria();
                    //Limpia los campos luego de editar un registro
                    txtCategoriaProducto.setText("");
                    txtCategoriaProducto.setEnabled(false);
                    btnAgregarCategoria.setEnabled(false);
                    btnCancelarCategoria.setEnabled(false);
                    btnEditarCategoria.setEnabled(false);
                    btnEliminarCategoria.setEnabled(false);
                    btnNuevoCategoria.setEnabled(true);
                    llenar_combobox();

                } else {
                    JOptionPane.showMessageDialog(null, "El registro no se editó");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_btnEditarCategoriaActionPerformed

    private void btnCancelarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCategoriaActionPerformed
        txtCategoriaProducto.setText("");
        txtCategoriaProducto.setEnabled(false);
        btnAgregarCategoria.setEnabled(false);
        btnCancelarCategoria.setEnabled(false);
        btnEditarCategoria.setEnabled(false);
        btnEliminarCategoria.setEnabled(false);
        btnNuevoCategoria.setEnabled(true);
    }//GEN-LAST:event_btnCancelarCategoriaActionPerformed

    private void btnAgregarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCategoriaActionPerformed
        if (txtCategoriaProducto.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "El campo no puede estar vacío", "Error, Campo Vacío", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                CategoriaProducto P = new CategoriaProducto();
            P.setNombreCategoria(this.txtCategoriaProducto.getText());
            //Validación de datos
            int mensaje = JOptionPane.showConfirmDialog(null, "¿Datos correctos?", "Crear Categoria",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (mensaje == 0) {
                CC.create(P);
                JOptionPane.showMessageDialog(null,"La categoria fue añadido exitosamente");
                CrearModeloCategoria();
                CargarTablaCategoria();
                llenar_combobox();
                //Limpia los campos luego de añadir un nuevo registro
                txtCategoriaProducto.setText("");
            }else{
                    JOptionPane.showMessageDialog(null,"La categoria no fue añadida");
                }      
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
       }
    }//GEN-LAST:event_btnAgregarCategoriaActionPerformed

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
       if (txtNombreProducto.getText().equals("") || txtDetalleProducto.getText().equals("") || txtPrecioProducto.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Los campos no pueden estar vacíos","Error, Campos Vacíos",JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
            Producto P = new Producto();

            P.setIdCategoria((CategoriaProducto) this.cbCategoria.getSelectedItem());
            P.setNombre(this.txtNombreProducto.getText());
            P.setDetalle(this.txtDetalleProducto.getText());
            P.setPrecio(Long.parseLong(this.txtPrecioProducto.getText()));

            //Validación de datos
            int mensaje = JOptionPane.showConfirmDialog(null, "¿Datos correctos?", "Crear producto",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (mensaje == 0) {
                PC.create(P);
                JOptionPane.showMessageDialog(null,"El producto fue añadido exitosamente");
                CrearModelo();
                CargarTabla();
                //Limpia los campos luego de añadir un nuevo registro
                txtNombreProducto.setText("");
                txtDetalleProducto.setText("");
                txtPrecioProducto.setText("");
            }else{
                    JOptionPane.showMessageDialog(null,"El paciente no fue añadido");
                }      
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
       }
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void btnNuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProductoActionPerformed
        cbCategoria.setSelectedIndex(0);
        txtNombreProducto.setText("");
        txtDetalleProducto.setText("");
        txtPrecioProducto.setText("");
        cbCategoria.setEnabled(true);
        txtNombreProducto.setEnabled(true);
        txtDetalleProducto.setEnabled(true);
        txtPrecioProducto.setEnabled(true);
        btnAgregarProducto.setEnabled(true);
        btnCancelarProducto.setEnabled(true);
        btnEditarProducto.setEnabled(false);
        btnEliminarProducto.setEnabled(false);
        btnNuevoProducto.setEnabled(false);
  
    }//GEN-LAST:event_btnNuevoProductoActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        try {
        //Confirmación de eliminación de registro
                int mensaje = JOptionPane.showConfirmDialog(null, "Realmente desea eliminar el producto", "Eliminar producto",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (mensaje == 0) {
                    PC.destroy(Eliminar.getIdProducto());
                    JOptionPane.showMessageDialog(null, "El registro fue eliminado con Exito");
                    CrearModelo();
                    CargarTabla();
                    //Limpia los campos luego de eliminar un registro
                    cbCategoria.setSelectedIndex(0);
                    txtNombreProducto.setText("");
                    txtDetalleProducto.setText("");
                    txtPrecioProducto.setText("");
                    txtNombreProducto.setEnabled(false);
                    txtDetalleProducto.setEnabled(false);
                    txtPrecioProducto.setEnabled(false);
                    btnAgregarProducto.setEnabled(false);
                    btnCancelarProducto.setEnabled(false);
                    btnEditarProducto.setEnabled(true);
                    btnEliminarProducto.setEnabled(true);
                    btnNuevoProducto.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null,"El registro no fue eliminado");
                }
               
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnEditarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProductoActionPerformed
        if (txtNombreProducto.getText().equals("") || txtDetalleProducto.getText().equals("") || txtPrecioProducto.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Los campos no pueden estar vacíos","Error, campos vacíos",JOptionPane.INFORMATION_MESSAGE);
        }else{
            try {
             Editar.setNombre(this.txtNombreProducto.getText());
             Editar.setPrecio(Long.parseLong(this.txtPrecioProducto.getText()));
             Editar.setDetalle(this.txtDetalleProducto.getText());
             Editar.setIdCategoria((CategoriaProducto) this.cbCategoria.getSelectedItem());
             
             //Validación de datos para editar
            int mensaje = JOptionPane.showConfirmDialog(null, "¿Datos correctos?", "Editar producto",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (mensaje == 0) {
                PC.edit(Editar);
                    JOptionPane.showMessageDialog(null, "El registro fue editado con éxito");
                    CrearModelo();
                    CargarTabla();
                    //Limpia los campos luego de editar un registro
                    cbCategoria.setSelectedIndex(0);
                    txtNombreProducto.setText("");
                    txtDetalleProducto.setText("");
                    txtPrecioProducto.setText("");
                    txtNombreProducto.setEnabled(false);
                    txtDetalleProducto.setEnabled(false);
                    txtPrecioProducto.setEnabled(false);
                    btnAgregarProducto.setEnabled(false);
                    btnCancelarProducto.setEnabled(false);
                    btnEditarProducto.setEnabled(true);
                    btnEliminarProducto.setEnabled(true);
                    btnNuevoProducto.setEnabled(true);

                } else {
                    JOptionPane.showMessageDialog(null,"El registro no se editó");
                }           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }
    }//GEN-LAST:event_btnEditarProductoActionPerformed

    private void btnCancelarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarProductoActionPerformed
        cbCategoria.setSelectedIndex(0);
        txtNombreProducto.setText("");
        txtDetalleProducto.setText("");
        txtPrecioProducto.setText("");
        cbCategoria.setEnabled(false);
        txtNombreProducto.setEnabled(false);
        txtDetalleProducto.setEnabled(false);
        txtPrecioProducto.setEnabled(false);
        btnAgregarProducto.setEnabled(false);
        btnCancelarProducto.setEnabled(false);
        btnEditarProducto.setEnabled(true);
        btnEliminarProducto.setEnabled(false);
        btnNuevoProducto.setEnabled(true);
    }//GEN-LAST:event_btnCancelarProductoActionPerformed

    private void txtNombreProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProductoKeyTyped
        char car = evt.getKeyChar();
        if(Character.isLetter(car) || evt.getKeyChar()==KeyEvent.VK_BACK_SPACE || evt.getKeyChar()==KeyEvent.VK_SPACE){
        }else{
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtNombreProductoKeyTyped

    private void txtDetalleProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDetalleProductoKeyTyped
        char car = evt.getKeyChar();
        if(Character.isLetter(car) || evt.getKeyChar()==KeyEvent.VK_BACK_SPACE || evt.getKeyChar()==KeyEvent.VK_SPACE){
        }else{
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtDetalleProductoKeyTyped

    private void txtPrecioProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioProductoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioProductoKeyTyped

    private void txtCategoriaProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCategoriaProductoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCategoriaProductoKeyTyped

    private void tbProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductosMouseClicked
        txtNombreProducto.setText(tbProductos.getValueAt(tbProductos.getSelectedRow(), 1).toString());
        cbCategoria.setSelectedItem(tbProductos.getValueAt(tbProductos.getSelectedRow(), 2));
        txtPrecioProducto.setText(tbProductos.getValueAt(tbProductos.getSelectedRow(), 3).toString());
        txtDetalleProducto.setText(tbProductos.getValueAt(tbProductos.getSelectedRow(), 4).toString());

        txtNombreProducto.setEnabled(true);
        txtDetalleProducto.setEnabled(true);
        txtPrecioProducto.setEnabled(true);
        btnAgregarProducto.setEnabled(false);
        btnCancelarProducto.setEnabled(true);
        btnEditarProducto.setEnabled(true);
        btnEliminarProducto.setEnabled(true);
        btnNuevoProducto.setEnabled(false);
        cbCategoria.setEnabled(true);

        String id = tbProductos.getValueAt(tbProductos.getSelectedRow(), 0).toString();
        Editar = (Producto) PC.findProducto(Integer.parseInt(id));
        Eliminar = (Producto) PC.findProducto(Integer.parseInt(id)); 
    }//GEN-LAST:event_tbProductosMouseClicked

    private void tbCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCategoriaMouseClicked
        txtCategoriaProducto.setText(tbCategoria.getValueAt(tbCategoria.getSelectedRow(), 1).toString());
        txtCategoriaProducto.setEnabled(true);
        btnAgregarCategoria.setEnabled(true);
        btnCancelarCategoria.setEnabled(true);
        btnEditarCategoria.setEnabled(true);
        btnEliminarCategoria.setEnabled(true);
        btnNuevoCategoria.setEnabled(false);
        String id = tbCategoria.getValueAt(tbCategoria.getSelectedRow(), 0).toString();
        EditarCategoria = (CategoriaProducto) CC.findCategoriaProducto(Integer.parseInt(id));
        EliminarCategoria = (CategoriaProducto) CC.findCategoriaProducto(Integer.parseInt(id)); 
    }//GEN-LAST:event_tbCategoriaMouseClicked
    DefaultTableModel modeloCategoria;
    private void CrearModeloCategoria() {
        try {
            modeloCategoria = (new DefaultTableModel(
                    null, new String[]{
                        "ID","Nombre Categoria"}) {
                Class[] types = new Class[]{
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class

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
            tbCategoria.setModel(modeloCategoria);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    //Carga datos de pacientes a la tabla
    private void CargarTablaCategoria() {
        try {
            Object o[] = null;
            List<CategoriaProducto> listUsuarios = CC.findCategoriaProductoEntities();
            for (int i = 0; i < listUsuarios.size(); i++) {
                modeloCategoria.addRow(o);
                modeloCategoria.setValueAt(listUsuarios.get(i).getIdCategoria(), i, 0);
                modeloCategoria.setValueAt(listUsuarios.get(i).getNombreCategoria(), i, 1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    DefaultTableModel modelo;
    private void CrearModelo() {
        try {
            modelo = (new DefaultTableModel(
                    null, new String[]{
                        "ID","Nombres","Categoria",
                        "Precio","Detalle"}) {
                Class[] types = new Class[]{
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class

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
            tbProductos.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    //Carga datos de pacientes a la tabla
    private void CargarTabla() {
        try {           
            Object o[] = null;
            List<Producto> listUsuarios = PC.findProductoEntities();
            for (int i = 0; i < listUsuarios.size(); i++) {
                modelo.addRow(o);
                modelo.setValueAt(listUsuarios.get(i).getIdProducto(), i, 0);
                modelo.setValueAt(listUsuarios.get(i).getNombre(), i, 1);
                modelo.setValueAt(listUsuarios.get(i).getIdCategoria().getNombreCategoria(), i, 2);
                modelo.setValueAt(listUsuarios.get(i).getPrecio(), i, 3);
                modelo.setValueAt(listUsuarios.get(i).getDetalle(), i, 4);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
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
            java.util.logging.Logger.getLogger(Servicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Servicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Servicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Servicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Servicios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager ClinicaArevaloPUEntityManager;
    private java.util.List<Entidades.Antecedentes> antecedentesList;
    private javax.persistence.Query antecedentesQuery;
    private javax.swing.JButton btnAgregarCategoria;
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnCancelarCategoria;
    private javax.swing.JButton btnCancelarProducto;
    private javax.swing.JButton btnEditarCategoria;
    private javax.swing.JButton btnEditarProducto;
    private javax.swing.JButton btnEliminarCategoria;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnNuevoCategoria;
    private javax.swing.JButton btnNuevoProducto;
    private javax.swing.JButton btnVolver;
    private javax.swing.JButton btnVolver1;
    private java.util.List<Entidades.CategoriaProducto> categoriaProductoList;
    private javax.persistence.Query categoriaProductoQuery;
    private javax.swing.JComboBox<String> cbCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTable tbCategoria;
    private javax.swing.JTable tbProductos;
    private javax.swing.JTextField txtCategoriaProducto;
    private javax.swing.JTextField txtDetalleProducto;
    private javax.swing.JTextField txtIdCategoria;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtPrecioProducto;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
