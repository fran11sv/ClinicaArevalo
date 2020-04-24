package Forms;

import Clases.Hora;
import Controladores.CitasJpaController;
import Controladores.PacienteJpaController;
import Controladores.exceptions.NonexistentEntityException;
import Entidades.Citas;
import Entidades.Paciente;
import Entidades.Usuario;
import Entidades.entityMain;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;



public class Agenda extends javax.swing.JFrame {
CitasJpaController CC = new CitasJpaController(entityMain.getInstance());
PacienteJpaController PC = new PacienteJpaController(entityMain.getInstance());
    Citas Eliminar;
    Paciente DatosPaciente;
    public Agenda() {
        initComponents();
        llenar_combobox();
        cargar_calendario();
    }
    
    private void llenar_combobox() {
        //Rellena Combobox
        this.cbUsuario.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int i, boolean bln, boolean bln1) {
                if (o instanceof Usuario) {
                    Usuario U = (Usuario) o;
                    return super.getListCellRendererComponent(jlist, U.getApellidos().concat(", "+U.getNombres()), i, bln, bln1);
                }
                return super.getListCellRendererComponent(jlist, o, i, bln, bln1);
            }
        });
        this.cbUsuarioAgenda.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int i, boolean bln, boolean bln1) {
                if (o instanceof Usuario) {
                    Usuario U = (Usuario) o;
                    return super.getListCellRendererComponent(jlist, U.getApellidos().concat(", "+U.getNombres()), i, bln, bln1);
                }
                return super.getListCellRendererComponent(jlist, o, i, bln, bln1);
            }
        });
            cbHora.addItem(new Hora(8, "08:00 AM"));
            cbHora.addItem(new Hora(9, "09:00 AM"));
            cbHora.addItem(new Hora(10, "10:00 AM"));
            cbHora.addItem(new Hora(11, "11:00 AM"));
            cbHora.addItem(new Hora(12, "12:00 PM"));
            cbHora.addItem(new Hora(13, "01:00 PM"));
            cbHora.addItem(new Hora(14, "02:00 PM"));
            cbHora.addItem(new Hora(15, "03:00 PM"));
            cbHora.addItem(new Hora(16, "04:00 PM"));
            cbHora.addItem(new Hora(17, "05:00 PM"));
    }

    private void cargar_calendario() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int offset = cal.get(Calendar.DAY_OF_WEEK)+2;
        int mon = calendar.getMonthChooser().getMonth() + 1;
        int yr = calendar.getYearChooser().getYear();
        JPanel jPanel = calendar.getDayChooser().getDayPanel();
        Component component[] = jPanel.getComponents();
        int ctr =0;
         for(int i = 7; i < 14; i++){
            if(component[i].isVisible() == false){
                ctr++;
            }
        }

        try {
            List<Citas> listCitas = CC.findbyUsuario((Usuario) this.cbUsuarioAgenda.getSelectedItem());
            for (int i = 0; i < listCitas.size(); i++) {
                Date fecha = listCitas.get(i).getFechaCita();
                int dayOfMonth = fecha.getDate();
                int month = fecha.getMonth()+1;
                int year = fecha.getYear()+1900;

                if (mon == month && yr == year) {
                    component[dayOfMonth + offset+ctr].setBackground(java.awt.Color.ORANGE);
                }

            
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        ClinicaArevaloPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("ClinicaArevaloPU").createEntityManager();
        antecedentesQuery = java.beans.Beans.isDesignTime() ? null : ClinicaArevaloPUEntityManager.createQuery("SELECT a FROM Antecedentes a");
        antecedentesList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : antecedentesQuery.getResultList();
        usuarioQuery = java.beans.Beans.isDesignTime() ? null : ClinicaArevaloPUEntityManager.createQuery("SELECT u FROM Usuario u");
        usuarioList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : usuarioQuery.getResultList();
        usuarioQuery1 = java.beans.Beans.isDesignTime() ? null : ClinicaArevaloPUEntityManager.createQuery("SELECT u FROM Usuario u");
        usuarioList1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : usuarioQuery1.getResultList();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        PanelAgenda = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbUsuarioAgenda = new javax.swing.JComboBox<>();
        calendar = new com.toedter.calendar.JCalendar();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbCitas = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        btnEliminarCita = new javax.swing.JButton();
        btnVerCitas = new javax.swing.JButton();
        PanelCitas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbUsuario = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtPaciente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPacientes = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        fechapicker = new com.toedter.calendar.JDateChooser();
        btnAgregar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cbHora = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setText("Seleccione Médico:");

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, usuarioList1, cbUsuarioAgenda);
        bindingGroup.addBinding(jComboBoxBinding);

        tbCitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbCitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCitasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbCitas);

        jButton3.setText("Recargar Calendario");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnEliminarCita.setText("Eliminar Cita");
        btnEliminarCita.setEnabled(false);
        btnEliminarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCitaActionPerformed(evt);
            }
        });

        btnVerCitas.setText("Ver Citas de Fecha Seleccionada");
        btnVerCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerCitasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelAgendaLayout = new javax.swing.GroupLayout(PanelAgenda);
        PanelAgenda.setLayout(PanelAgendaLayout);
        PanelAgendaLayout.setHorizontalGroup(
            PanelAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAgendaLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(PanelAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(calendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelAgendaLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbUsuarioAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminarCita)
                    .addComponent(btnVerCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        PanelAgendaLayout.setVerticalGroup(
            PanelAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAgendaLayout.createSequentialGroup()
                .addGroup(PanelAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAgendaLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(PanelAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbUsuarioAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelAgendaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnVerCitas)
                    .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarCita))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ver Agenda", PanelAgenda);

        jLabel1.setText("Seleccione Médico:");

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, usuarioList, cbUsuario);
        bindingGroup.addBinding(jComboBoxBinding);

        jLabel2.setText("Seleccione Paciente:");

        txtPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPacienteKeyReleased(evt);
            }
        });

        tbPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbPacientes);

        jLabel3.setText("Seleccione Fecha para Consulta:");

        fechapicker.setDateFormatString("dd-MM-yyyy");

        btnAgregar.setText("Agendar Cita");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jButton2.setText("Nuevo Paciente");

        jLabel5.setText("Seleccione Hora de Consulta:");

        javax.swing.GroupLayout PanelCitasLayout = new javax.swing.GroupLayout(PanelCitas);
        PanelCitas.setLayout(PanelCitasLayout);
        PanelCitasLayout.setHorizontalGroup(
            PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCitasLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelCitasLayout.createSequentialGroup()
                        .addGroup(PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbUsuario, 0, 193, Short.MAX_VALUE)
                            .addComponent(txtPaciente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(PanelCitasLayout.createSequentialGroup()
                        .addGroup(PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fechapicker, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbHora, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(326, Short.MAX_VALUE))
        );
        PanelCitasLayout.setVerticalGroup(
            PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCitasLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fechapicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(btnAgregar)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hacer Citas", PanelCitas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.cargar_calendario();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtPacienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPacienteKeyReleased
        CrearModeloPacientes();
        CargarTablaPacientesporNombre(this.txtPaciente.getText());
    }//GEN-LAST:event_txtPacienteKeyReleased

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
         try {
            Citas C = new Citas();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.fechapicker.getDate());
            Hora H = (Hora) this.cbHora.getSelectedItem();
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.HOUR,H.getValor());
            
            SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
            String spinnerValue = formater.format(calendar.getTime());
            Date date = formater.parse(spinnerValue);
            java.sql.Date datesql = new java.sql.Date(date.getTime());
            List<Citas> listCitas = CC.findbyUsuarioandFechaconHora((Usuario) this.cbUsuario.getSelectedItem(),datesql);
            if (listCitas.isEmpty()) {
            DatosPaciente = (Paciente) PC.findPaciente(Integer.parseInt(tbPacientes.getValueAt(tbPacientes.getSelectedRow(), 0).toString()));
            C.setIdPaciente(DatosPaciente);
            C.setIdUsuario((Usuario) this.cbUsuario.getSelectedItem());
            C.setFechaCita(date);
            CC.create(C);
            JOptionPane.showMessageDialog(null,"Cita Creada para el día "+ spinnerValue); 
            }
            else{
                JOptionPane.showMessageDialog(null,"Ya existe una cita creada: "+ listCitas.get(0).getIdPaciente().getApellidos().concat(", "+listCitas.get(0).getIdPaciente().getNombres()+" a esta hora y fecha")); 
            }           
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }  
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnVerCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerCitasActionPerformed
        CrearModeloCitas();
        CargarTablaCitas();
    }//GEN-LAST:event_btnVerCitasActionPerformed

    private void tbCitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCitasMouseClicked
        this.btnEliminarCita.setEnabled(true);
    }//GEN-LAST:event_tbCitasMouseClicked

    private void btnEliminarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCitaActionPerformed
        int resp = JOptionPane.showConfirmDialog(null, "¿Desea eliminar esta Cita?", "Eliminar Cita: Confirmación", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.YES_OPTION == resp) {
            try {
                String id = tbCitas.getValueAt(tbCitas.getSelectedRow(), 0).toString();
                Eliminar = (Citas) CC.findCitas(Integer.parseInt(id));
                CC.destroy(Eliminar.getIdCita());
                CrearModeloCitas();
                CargarTablaCitas();
                this.btnEliminarCita.setEnabled(false);
            } catch (NonexistentEntityException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnEliminarCitaActionPerformed
    DefaultTableModel modeloPacientes;
    private void CrearModeloPacientes() {
        try {
            modeloPacientes = (new DefaultTableModel(
                    null, new String[]{
                        "ID", "Nombres","Apellidos",
                        "Fecha de Nacimiento","DUI","Sexo","Ocupación",
                         "Dirección","Estado Civil","Teléfono"}) {
                Class[] types = new Class[]{
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
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
            tbPacientes.setModel(modeloPacientes);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    private void CargarTablaPacientesporNombre(String nombre ) {
        try {
            Object o[] = null;
            List<Paciente> listPacientes = PC.findPacienteporNombre(nombre);
            for (int i = 0; i < listPacientes.size(); i++) {
                modeloPacientes.addRow(o);
                modeloPacientes.setValueAt(listPacientes.get(i).getIdPaciente(), i, 0);
                modeloPacientes.setValueAt(listPacientes.get(i).getNombres(), i, 1);
                modeloPacientes.setValueAt(listPacientes.get(i).getApellidos(), i, 2);
                modeloPacientes.setValueAt(listPacientes.get(i).getFechaNacimiento(), i, 3);
                modeloPacientes.setValueAt(listPacientes.get(i).getDui(), i, 4);
                modeloPacientes.setValueAt(listPacientes.get(i).getSexo(), i, 5);
                modeloPacientes.setValueAt(listPacientes.get(i).getOcupacion(), i, 6);
                modeloPacientes.setValueAt(listPacientes.get(i).getDireccion(), i, 7);
                modeloPacientes.setValueAt(listPacientes.get(i).getEstadoCivil(), i, 8);
                modeloPacientes.setValueAt(listPacientes.get(i).getTelefono(), i, 9);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }    
    }
    DefaultTableModel modeloCitas;
    private void CrearModeloCitas() {
        try {
            modeloCitas = (new DefaultTableModel(
                    null, new String[]{
                        "ID de Cita", "Paciente","Fecha",
                        "Médico"}) {
                Class[] types = new Class[]{
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
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
            this.tbCitas.setModel(modeloCitas);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    private void CargarTablaCitas() {
        try {
            Object o[] = null;
            
            java.util.Date fecha1 = calendar.getDate();
            fecha1.setHours(0);
            fecha1.setMinutes(0);
            fecha1.setSeconds(0);
            java.util.Date fecha2 = calendar.getDate();
            fecha2.setHours(0);
            fecha2.setMinutes(0);
            fecha2.setSeconds(0);
            fecha2.setDate(fecha1.getDate()+1);
            java.sql.Date date1 = new java.sql.Date(fecha1.getTime());
            java.sql.Date date2 = new java.sql.Date(fecha2.getTime());
            List<Citas> listCitas = CC.findbyUsuarioandFecha((Usuario) this.cbUsuarioAgenda.getSelectedItem(), date1,date2);
            for (int i = 0; i < listCitas.size(); i++) {
                SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
                String fechaformat = formater.format(listCitas.get(i).getFechaCita());
                modeloCitas.addRow(o);
                modeloCitas.setValueAt(listCitas.get(i).getIdCita(), i, 0);
                modeloCitas.setValueAt(listCitas.get(i).getIdPaciente().getApellidos().concat(", "+listCitas.get(i).getIdPaciente().getNombres()), i, 1);
                modeloCitas.setValueAt(fechaformat, i, 2);
                modeloCitas.setValueAt(listCitas.get(i).getIdUsuario().getApellidos().concat(", "+listCitas.get(i).getIdUsuario().getNombres()), i, 3);
            }
            this.btnEliminarCita.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }    
    }
    public class HoraListCellRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(
                JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {
            if (value instanceof Hora) {
                value = ((Hora) value).getNombre();
            }
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            return this;
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
            java.util.logging.Logger.getLogger(Agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Agenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager ClinicaArevaloPUEntityManager;
    private javax.swing.JPanel PanelAgenda;
    private javax.swing.JPanel PanelCitas;
    private java.util.List<Entidades.Antecedentes> antecedentesList;
    private javax.persistence.Query antecedentesQuery;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminarCita;
    private javax.swing.JButton btnVerCitas;
    private com.toedter.calendar.JCalendar calendar;
    private javax.swing.JComboBox<Hora> cbHora;
    private javax.swing.JComboBox<String> cbUsuario;
    private javax.swing.JComboBox<String> cbUsuarioAgenda;
    private com.toedter.calendar.JDateChooser fechapicker;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbCitas;
    private javax.swing.JTable tbPacientes;
    private javax.swing.JTextField txtPaciente;
    private java.util.List<Entidades.Usuario> usuarioList;
    private java.util.List<Entidades.Usuario> usuarioList1;
    private javax.persistence.Query usuarioQuery;
    private javax.persistence.Query usuarioQuery1;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
