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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
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
        Date date = new Date();
        fechapicker.setDate(date);
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
        int offset = cal.get(Calendar.DAY_OF_WEEK);
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
        usuarioQuery2 = java.beans.Beans.isDesignTime() ? null : ClinicaArevaloPUEntityManager.createQuery("SELECT u FROM Usuario u");
        usuarioList2 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : usuarioQuery2.getResultList();
        jPanel1 = new javax.swing.JPanel();
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
        btnMenuPaciente1 = new javax.swing.JButton();
        PanelCitas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
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
        btnMenuPaciente = new javax.swing.JButton();
        lblError = new javax.swing.JLabel();
        cbUsuario = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(187, 232, 223));

        jTabbedPane1.setBackground(new java.awt.Color(76, 201, 223));
        jTabbedPane1.setForeground(new java.awt.Color(0, 0, 0));
        jTabbedPane1.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N

        PanelAgenda.setBackground(new java.awt.Color(15, 76, 129));

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(239, 239, 239));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Seleccione Médico:");
        jLabel4.setPreferredSize(new java.awt.Dimension(200, 30));

        cbUsuarioAgenda.setBackground(new java.awt.Color(239, 239, 239));
        cbUsuarioAgenda.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        cbUsuarioAgenda.setForeground(new java.awt.Color(0, 0, 0));
        cbUsuarioAgenda.setPreferredSize(new java.awt.Dimension(100, 30));

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, usuarioList, cbUsuarioAgenda);
        bindingGroup.addBinding(jComboBoxBinding);

        calendar.setBackground(new java.awt.Color(15, 76, 129));
        calendar.setForeground(new java.awt.Color(0, 0, 0));

        tbCitas.setBackground(new java.awt.Color(239, 239, 239));
        tbCitas.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        tbCitas.setForeground(new java.awt.Color(0, 0, 0));
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

        jButton3.setBackground(new java.awt.Color(76, 201, 223));
        jButton3.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Recargar Calendario.png"))); // NOI18N
        jButton3.setText("Recargar Calendario");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnEliminarCita.setBackground(new java.awt.Color(76, 201, 223));
        btnEliminarCita.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnEliminarCita.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminarCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Eliminar M.png"))); // NOI18N
        btnEliminarCita.setText(" Eliminar Cita");
        btnEliminarCita.setEnabled(false);
        btnEliminarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCitaActionPerformed(evt);
            }
        });

        btnVerCitas.setBackground(new java.awt.Color(76, 201, 223));
        btnVerCitas.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnVerCitas.setForeground(new java.awt.Color(0, 0, 0));
        btnVerCitas.setText("Ver Citas de Fecha Seleccionada");
        btnVerCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerCitasActionPerformed(evt);
            }
        });

        btnMenuPaciente1.setBackground(new java.awt.Color(76, 201, 223));
        btnMenuPaciente1.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnMenuPaciente1.setForeground(new java.awt.Color(0, 0, 0));
        btnMenuPaciente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Menu.png"))); // NOI18N
        btnMenuPaciente1.setText("Volver Al Menú");
        btnMenuPaciente1.setPreferredSize(new java.awt.Dimension(100, 60));
        btnMenuPaciente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuPaciente1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelAgendaLayout = new javax.swing.GroupLayout(PanelAgenda);
        PanelAgenda.setLayout(PanelAgendaLayout);
        PanelAgendaLayout.setHorizontalGroup(
            PanelAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAgendaLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(PanelAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelAgendaLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbUsuarioAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PanelAgendaLayout.createSequentialGroup()
                        .addGroup(PanelAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PanelAgendaLayout.createSequentialGroup()
                                .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(PanelAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnVerCitas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 839, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminarCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnMenuPaciente1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35))))
        );
        PanelAgendaLayout.setVerticalGroup(
            PanelAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAgendaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PanelAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbUsuarioAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAgendaLayout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(20, 20, 20)
                        .addComponent(btnVerCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(PanelAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAgendaLayout.createSequentialGroup()
                        .addComponent(btnEliminarCita)
                        .addGap(49, 49, 49)
                        .addComponent(btnMenuPaciente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        jTabbedPane1.addTab("Ver Agenda", PanelAgenda);

        PanelCitas.setBackground(new java.awt.Color(15, 76, 129));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(239, 239, 239));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Seleccione Médico:");
        jLabel1.setPreferredSize(new java.awt.Dimension(30, 30));

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(239, 239, 239));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Seleccione Paciente (Nombre):");
        jLabel2.setPreferredSize(new java.awt.Dimension(30, 30));

        txtPaciente.setBackground(new java.awt.Color(239, 239, 239));
        txtPaciente.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtPaciente.setForeground(new java.awt.Color(0, 0, 0));
        txtPaciente.setPreferredSize(new java.awt.Dimension(30, 30));
        txtPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPacienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPacienteKeyTyped(evt);
            }
        });

        tbPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPacientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPacientes);

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(239, 239, 239));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Seleccione Fecha para Consulta:");
        jLabel3.setPreferredSize(new java.awt.Dimension(30, 30));

        fechapicker.setBackground(new java.awt.Color(239, 239, 239));
        fechapicker.setForeground(new java.awt.Color(0, 0, 0));
        fechapicker.setDateFormatString("dd-MM-yyyy");
        fechapicker.setPreferredSize(new java.awt.Dimension(30, 30));

        btnAgregar.setBackground(new java.awt.Color(76, 201, 223));
        btnAgregar.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Agendar 32.png"))); // NOI18N
        btnAgregar.setText(" Agendar Cita");
        btnAgregar.setEnabled(false);
        btnAgregar.setPreferredSize(new java.awt.Dimension(200, 40));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(76, 201, 223));
        jButton2.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Agregar Paciente 32.png"))); // NOI18N
        jButton2.setText(" Nuevo Paciente");
        jButton2.setPreferredSize(new java.awt.Dimension(200, 40));

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(239, 239, 239));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Seleccione Hora de Consulta:");
        jLabel5.setPreferredSize(new java.awt.Dimension(30, 30));

        cbHora.setBackground(new java.awt.Color(239, 239, 239));
        cbHora.setEditable(true);
        cbHora.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        cbHora.setForeground(new java.awt.Color(0, 0, 0));

        btnMenuPaciente.setBackground(new java.awt.Color(76, 201, 223));
        btnMenuPaciente.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnMenuPaciente.setForeground(new java.awt.Color(0, 0, 0));
        btnMenuPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Menu.png"))); // NOI18N
        btnMenuPaciente.setText(" Volver Al Menú");
        btnMenuPaciente.setPreferredSize(new java.awt.Dimension(100, 60));
        btnMenuPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuPacienteActionPerformed(evt);
            }
        });

        lblError.setForeground(new java.awt.Color(255, 153, 51));

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, usuarioList, cbUsuario);
        bindingGroup.addBinding(jComboBoxBinding);

        javax.swing.GroupLayout PanelCitasLayout = new javax.swing.GroupLayout(PanelCitas);
        PanelCitas.setLayout(PanelCitasLayout);
        PanelCitasLayout.setHorizontalGroup(
            PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelCitasLayout.createSequentialGroup()
                .addGroup(PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelCitasLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMenuPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelCitasLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelCitasLayout.createSequentialGroup()
                                .addGroup(PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbHora, javax.swing.GroupLayout.Alignment.LEADING, 0, 200, Short.MAX_VALUE)
                                    .addComponent(fechapicker, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtPaciente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(11, 11, 11)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblError))
                            .addGroup(PanelCitasLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        PanelCitasLayout.setVerticalGroup(
            PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCitasLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechapicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbHora, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelCitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCitasLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMenuPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(PanelCitasLayout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Hacer Citas", PanelCitas);

        jPanel2.setBackground(new java.awt.Color(253, 149, 53));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.setPreferredSize(new java.awt.Dimension(1360, 60));

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Agenda");
        jLabel6.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 1344, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        if (!tbPacientes.getValueAt(tbPacientes.getSelectedRow(), 0).toString().isEmpty()) {
            try {
                Citas C = new Citas();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(this.fechapicker.getDate());
                Hora H = (Hora) this.cbHora.getSelectedItem();
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.HOUR, H.getValor());

                SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
                String spinnerValue = formater.format(calendar.getTime());
                Date date = formater.parse(spinnerValue);
                java.sql.Date datesql = new java.sql.Date(date.getTime());
                List<Citas> listCitas = CC.findbyUsuarioandFechaconHora((Usuario) this.cbUsuario.getSelectedItem(), datesql);
                if (listCitas.isEmpty()) {
                    DatosPaciente = (Paciente) PC.findPaciente(Integer.parseInt(tbPacientes.getValueAt(tbPacientes.getSelectedRow(), 0).toString()));
                    C.setIdPaciente(DatosPaciente);
                    C.setIdUsuario((Usuario) this.cbUsuario.getSelectedItem());
                    C.setFechaCita(date);
                    CC.create(C);
                    JOptionPane.showMessageDialog(null, "Cita Creada para el día " + spinnerValue);
                } else {
                    JOptionPane.showMessageDialog(null, "Ya existe una cita creada: " + listCitas.get(0).getIdPaciente().getApellidos().concat(", " + listCitas.get(0).getIdPaciente().getNombres() + " a esta hora y fecha"));
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Seleccione Paciente");
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

    private void btnMenuPaciente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuPaciente1ActionPerformed
           int resp = JOptionPane.showConfirmDialog(null, "¿Desea volver al menú principal? Recuerde que los datos ingresados sin guardar se perderán", "Regresar a Menú", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.YES_OPTION == resp) {
           MenuPrincipal menu = new MenuPrincipal();
           menu.setVisible(true);           
           this.setVisible(false);
        }
    }//GEN-LAST:event_btnMenuPaciente1ActionPerformed

    private void btnMenuPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuPacienteActionPerformed
           int resp = JOptionPane.showConfirmDialog(null, "¿Desea volver al menú principal? Recuerde que los datos ingresados sin guardar se perderán", "Regresar a Menú", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.YES_OPTION == resp) {
           MenuPrincipal menu = new MenuPrincipal();
           menu.setVisible(true);           
           this.setVisible(false);
        }
    }//GEN-LAST:event_btnMenuPacienteActionPerformed

    private void txtPacienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPacienteKeyTyped
        char car = evt.getKeyChar();
        if(Character.isLetter(car)){
            lblError.setText("");
        }else{
            evt.consume();
            lblError.setText("Ingresa solo letras");
        }
    }//GEN-LAST:event_txtPacienteKeyTyped

    private void tbPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacientesMouseClicked
       btnAgregar.setEnabled(true);
    }//GEN-LAST:event_tbPacientesMouseClicked
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
    private javax.swing.JButton btnMenuPaciente;
    private javax.swing.JButton btnMenuPaciente1;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblError;
    private javax.swing.JTable tbCitas;
    private javax.swing.JTable tbPacientes;
    private javax.swing.JTextField txtPaciente;
    private java.util.List<Entidades.Usuario> usuarioList;
    private java.util.List<Entidades.Usuario> usuarioList1;
    private java.util.List<Entidades.Usuario> usuarioList2;
    private javax.persistence.Query usuarioQuery;
    private javax.persistence.Query usuarioQuery1;
    private javax.persistence.Query usuarioQuery2;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
