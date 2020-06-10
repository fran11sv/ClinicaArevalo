package Forms;

import Clases.Conexion;
import Clases.Hora;
import Controladores.AntecedentesJpaController;
import Controladores.CitasJpaController;
import Controladores.ConsultaJpaController;
import Controladores.DetalleDiagnosticoJpaController;
import Controladores.DetalleRecetaJpaController;
import Controladores.DiagnosticoJpaController;
import Controladores.EnfermedadesCie10JpaController;
import Controladores.PacienteJpaController;
import Controladores.RecetaJpaController;
import Controladores.VademecumJpaController;
import Entidades.Antecedentes;
import Entidades.Citas;
import Entidades.Consulta;
import Entidades.DetalleDiagnostico;
import Entidades.DetalleReceta;
import Entidades.Diagnostico;
import Entidades.EnfermedadesCie10;
import Entidades.Paciente;
import Entidades.Receta;
import Entidades.Usuario;
import Entidades.Vademecum;
import Entidades.entityMain;
import com.mysql.jdbc.Connection;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Consulta_Medica extends javax.swing.JFrame {
    //Creacion de Controladores con su respectivo constructor unido a la persistencia.

    PacienteJpaController PC = new PacienteJpaController(entityMain.getInstance());
    AntecedentesJpaController AC = new AntecedentesJpaController(entityMain.getInstance());
    ConsultaJpaController CC = new ConsultaJpaController(entityMain.getInstance());
    EnfermedadesCie10JpaController EC = new EnfermedadesCie10JpaController(entityMain.getInstance());
    DetalleDiagnosticoJpaController DC = new DetalleDiagnosticoJpaController(entityMain.getInstance());
    RecetaJpaController RC = new RecetaJpaController(entityMain.getInstance());
    VademecumJpaController VC = new VademecumJpaController(entityMain.getInstance());
    DetalleRecetaJpaController DRC = new DetalleRecetaJpaController(entityMain.getInstance());
    CitasJpaController CitasC = new CitasJpaController(entityMain.getInstance());
    DiagnosticoJpaController DGC = new DiagnosticoJpaController(entityMain.getInstance());
    //Creación de Objetos para guardar datos para operaciones de la base de datos
    Receta DatosReceta;
    Receta EditarReceta;
    Vademecum Insumo;
    DetalleReceta EditarDetalleReceta;
    DetalleReceta EliminarDetalleReceta;
    Paciente DatosPaciente;
    Consulta UltimaConsulta;
    Consulta DatosConsulta;
    Antecedentes EditarAntecedente;
    Antecedentes EliminarAntecedente;
    EnfermedadesCie10 AgregarEnfermedad;
    DetalleDiagnostico EliminarDetalle;
    DetalleDiagnostico EditarDetalle;
    Diagnostico DatosDiagnostico;
    String CamposVacios = "Aún tiene campos vacíos";
    String ValorIncorrecto = "Valores incorrectos";
    String SexoIncorrecto = "Ingrese Masculino o Femenino";

    public Consulta_Medica() {
        initComponents();
        CrearModeloPacientes();
        CargarCombo();
    }
    DefaultTableModel modeloPacientes;

    private void CrearModeloPacientes() {
        try {
            modeloPacientes = (new DefaultTableModel(
                    null, new String[]{
                        "ID", "Nombres", "Apellidos",
                        "Fecha de Nacimiento", "DUI", "Sexo", "Ocupación",
                        "Dirección", "Estado Civil", "Teléfono"}) {
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
                    java.lang.String.class,};
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

    private void CargarTablaPacientesporNombre(String nombre) {
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
            this.btnSeleccionPaciente.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void CargarTablaPacientesporDui(String dui) {
        try {
            Object o[] = null;
            List<Paciente> listPacientes = PC.findPacienteporDui(dui);
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
            this.btnSeleccionPaciente.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        PanelPestañas = new javax.swing.JTabbedPane();
        PanelSeleccion = new javax.swing.JPanel();
        Date date = new Date();
        SpinnerDateModel sm =
        new SpinnerDateModel(date, null, null, Calendar.DAY_OF_MONTH);
        spFechaPaciente = new javax.swing.JSpinner(sm);
        btnAgregarPaciente = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPacientes = new javax.swing.JTable();
        btnNuevoPaciente = new javax.swing.JButton();
        btnCancelarPaciente = new javax.swing.JButton();
        btnSeleccionPaciente = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtNombreBusqueda = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtDUIBusqueda = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        txtNombrePaciente = new javax.swing.JTextField();
        txtIdPaciente = new javax.swing.JTextField();
        txtApellidoPaciente = new javax.swing.JTextField();
        txtDireccionPaciente = new javax.swing.JTextField();
        txtSexoPaciente = new javax.swing.JTextField();
        txtOcupacionPaciente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCivilPaciente = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnMenuPaciente = new javax.swing.JButton();
        lblError = new javax.swing.JLabel();
        txtDUIPaciente = new javax.swing.JFormattedTextField();
        txtTelefonoPaciente = new javax.swing.JFormattedTextField();
        PanelIdentificacion = new javax.swing.JPanel();
        btnCancelarIdenti = new javax.swing.JButton();
        btnSiguienteIdenti = new javax.swing.JButton();
        btnMenuIdenti = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacionesIdenti = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblSexoIdenti = new javax.swing.JLabel();
        lblDireccionIdenti = new javax.swing.JLabel();
        lblTelefonoIdenti = new javax.swing.JLabel();
        lblFechaIdenti1 = new javax.swing.JLabel();
        lblNombreIdenti = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblEdad = new javax.swing.JLabel();
        lblApellidoIdenti = new javax.swing.JLabel();
        lblDuiIdenti = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblCivilIdenti = new javax.swing.JLabel();
        PanelConsulta = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        lblPacienteConsulta = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lblFechaUltima = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lblUltimoMotivo = new javax.swing.JLabel();
        btnHistorial = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        txtMotivoConsulta = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtPresenteConsulta = new javax.swing.JTextArea();
        btnSiguienteConsulta = new javax.swing.JButton();
        btnCancelarCancelar = new javax.swing.JButton();
        btnMenuConsulta = new javax.swing.JButton();
        Date dateConsulta = new Date();
        SpinnerDateModel sdm =
        new SpinnerDateModel(dateConsulta, null, null, Calendar.DAY_OF_MONTH);
        spFechaConsulta = new javax.swing.JSpinner(sdm);
        PanelAntecedentes = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        lblPacienteAnte = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtPersonalAnte = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtFamiliarAnte = new javax.swing.JTextArea();
        btnSiguienteAnte = new javax.swing.JButton();
        btnCancelarL2 = new javax.swing.JButton();
        btnMenuAnte = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbAntecendentes = new javax.swing.JTable();
        btnNuevoAnte = new javax.swing.JButton();
        btnCancelarAnte = new javax.swing.JButton();
        btnAgregarAnte = new javax.swing.JButton();
        btnEditarAnte = new javax.swing.JButton();
        btnElminarAnte = new javax.swing.JButton();
        btnReporteAntecedentes = new javax.swing.JButton();
        PanelExploracion = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        lblPacienteFisica = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtPresion = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtPulso = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtFCC = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtFR = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtTemperatura = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtMasa = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        txtTalla = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtFisico = new javax.swing.JTextArea();
        btnSiguienteFisica = new javax.swing.JButton();
        btnCancelarFisica = new javax.swing.JButton();
        btnMenuFisica = new javax.swing.JButton();
        lblIMC = new javax.swing.JTextField();
        PanelDiagnostico = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        lblPacienteDiagnostico = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tbDiagnostico = new javax.swing.JTable();
        btnEnfermedades = new javax.swing.JButton();
        txtAgregarDiagnostico = new javax.swing.JButton();
        btnEliminarDiagnostico = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        txtObservacionesDiagnostico = new javax.swing.JTextArea();
        btnSiguienteFisica1 = new javax.swing.JButton();
        btnMenuFisica1 = new javax.swing.JButton();
        txtEnfermedad = new javax.swing.JTextField();
        jScrollPane14 = new javax.swing.JScrollPane();
        tbEnfermedades = new javax.swing.JTable();
        btnbuscarporCate = new javax.swing.JButton();
        btnBuscarEnfermedad = new javax.swing.JButton();
        btnDiagnostico = new javax.swing.JButton();
        btnEditarDiagnostico = new javax.swing.JButton();
        PanelExamenes = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtExamenes = new javax.swing.JTextArea();
        jLabel60 = new javax.swing.JLabel();
        lblPacienteExamen = new javax.swing.JLabel();
        btnSiguienteExamen = new javax.swing.JButton();
        btnMenuExamen = new javax.swing.JButton();
        PanelTratamiento = new javax.swing.JPanel();
        lblPacienteTratamiento = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        txtMedicamentoBusqueda = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        txtDosis = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbVademecum = new javax.swing.JTable();
        btnEliminarMedicamento = new javax.swing.JButton();
        btnEditarMedicamento = new javax.swing.JButton();
        txtVademecum = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbReceta = new javax.swing.JTable();
        jLabel68 = new javax.swing.JLabel();
        btnAgregarMedicamento = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnHacerCita = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnAgenda = new javax.swing.JButton();
        btnTerminar = new javax.swing.JButton();
        btnMenuTratamiento = new javax.swing.JButton();
        cbHora = new javax.swing.JComboBox<>();
        fechapicker = new com.toedter.calendar.JDateChooser();
        btnReceta = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(6);
        setResizable(false);

        jPanel4.setBackground(new java.awt.Color(187, 232, 223));

        PanelPestañas.setBackground(new java.awt.Color(76, 201, 223));
        PanelPestañas.setForeground(new java.awt.Color(0, 0, 0));
        PanelPestañas.setFocusable(false);
        PanelPestañas.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        PanelPestañas.setPreferredSize(new java.awt.Dimension(876, 1000));

        PanelSeleccion.setBackground(new java.awt.Color(15, 76, 129));
        PanelSeleccion.setPreferredSize(new java.awt.Dimension(1320, 710));

        JSpinner.DateEditor de = new JSpinner.DateEditor(spFechaPaciente, "dd-MM-yyyy");
        spFechaPaciente.setEditor(de);
        spFechaPaciente.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        spFechaPaciente.setEnabled(false);
        spFechaPaciente.setNextFocusableComponent(txtTelefonoPaciente);
        spFechaPaciente.setPreferredSize(new java.awt.Dimension(200, 30));

        btnAgregarPaciente.setBackground(new java.awt.Color(76, 201, 223));
        btnAgregarPaciente.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnAgregarPaciente.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Guardar Paciente.png"))); // NOI18N
        btnAgregarPaciente.setText(" Agregar");
        btnAgregarPaciente.setEnabled(false);
        btnAgregarPaciente.setPreferredSize(new java.awt.Dimension(200, 60));
        btnAgregarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPacienteActionPerformed(evt);
            }
        });

        tbPacientes.setBackground(new java.awt.Color(239, 239, 239));
        tbPacientes.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        tbPacientes.setForeground(new java.awt.Color(0, 0, 0));
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
        jScrollPane2.setViewportView(tbPacientes);

        btnNuevoPaciente.setBackground(new java.awt.Color(76, 201, 223));
        btnNuevoPaciente.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnNuevoPaciente.setForeground(new java.awt.Color(0, 0, 0));
        btnNuevoPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Nuevo Paciente.png"))); // NOI18N
        btnNuevoPaciente.setText(" Nuevo");
        btnNuevoPaciente.setPreferredSize(new java.awt.Dimension(200, 60));
        btnNuevoPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoPacienteActionPerformed(evt);
            }
        });

        btnCancelarPaciente.setBackground(new java.awt.Color(76, 201, 223));
        btnCancelarPaciente.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnCancelarPaciente.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Cancelar.png"))); // NOI18N
        btnCancelarPaciente.setText(" Cancelar");
        btnCancelarPaciente.setEnabled(false);
        btnCancelarPaciente.setPreferredSize(new java.awt.Dimension(200, 60));
        btnCancelarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPacienteActionPerformed(evt);
            }
        });

        btnSeleccionPaciente.setBackground(new java.awt.Color(76, 201, 223));
        btnSeleccionPaciente.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnSeleccionPaciente.setForeground(new java.awt.Color(0, 0, 0));
        btnSeleccionPaciente.setText("SELECCIONAR PACIENTE");
        btnSeleccionPaciente.setEnabled(false);
        btnSeleccionPaciente.setPreferredSize(new java.awt.Dimension(100, 40));
        btnSeleccionPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionPacienteActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(239, 239, 239));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Buscar por:");
        jLabel21.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel22.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(239, 239, 239));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Nombre:");
        jLabel22.setPreferredSize(new java.awt.Dimension(100, 30));

        txtNombreBusqueda.setBackground(new java.awt.Color(239, 239, 239));
        txtNombreBusqueda.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtNombreBusqueda.setForeground(new java.awt.Color(0, 0, 0));
        txtNombreBusqueda.setNextFocusableComponent(txtDUIBusqueda);
        txtNombreBusqueda.setPreferredSize(new java.awt.Dimension(100, 30));
        txtNombreBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreBusquedaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreBusquedaKeyTyped(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(239, 239, 239));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("DUI:");
        jLabel23.setPreferredSize(new java.awt.Dimension(100, 30));

        txtDUIBusqueda.setBackground(new java.awt.Color(239, 239, 239));
        txtDUIBusqueda.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtDUIBusqueda.setForeground(new java.awt.Color(0, 0, 0));
        txtDUIBusqueda.setNextFocusableComponent(btnSeleccionPaciente);
        txtDUIBusqueda.setPreferredSize(new java.awt.Dimension(100, 30));
        txtDUIBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDUIBusquedaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDUIBusquedaKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(239, 239, 239));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Nombres:");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel1.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel70.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(239, 239, 239));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel70.setText("ID Paciente:");
        jLabel70.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel70.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel71.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(239, 239, 239));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel71.setText("Apellidos:");
        jLabel71.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel71.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel72.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(239, 239, 239));
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel72.setText("Direccion:");
        jLabel72.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel72.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel73.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(239, 239, 239));
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel73.setText("DUI:");
        jLabel73.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel73.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel74.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(239, 239, 239));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel74.setText("Sexo:");
        jLabel74.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel74.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel75.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(239, 239, 239));
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel75.setText("Ocupacion:");
        jLabel75.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel75.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel76.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(239, 239, 239));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel76.setText("Fecha de Nacimiento:");
        jLabel76.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel76.setPreferredSize(new java.awt.Dimension(200, 30));

        txtNombrePaciente.setBackground(new java.awt.Color(239, 239, 239));
        txtNombrePaciente.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtNombrePaciente.setForeground(new java.awt.Color(0, 0, 0));
        txtNombrePaciente.setEnabled(false);
        txtNombrePaciente.setNextFocusableComponent(txtApellidoPaciente);
        txtNombrePaciente.setPreferredSize(new java.awt.Dimension(200, 30));
        txtNombrePaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombrePacienteKeyTyped(evt);
            }
        });

        txtIdPaciente.setBackground(new java.awt.Color(239, 239, 239));
        txtIdPaciente.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtIdPaciente.setForeground(new java.awt.Color(0, 0, 0));
        txtIdPaciente.setEnabled(false);
        txtIdPaciente.setPreferredSize(new java.awt.Dimension(200, 30));

        txtApellidoPaciente.setBackground(new java.awt.Color(239, 239, 239));
        txtApellidoPaciente.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtApellidoPaciente.setForeground(new java.awt.Color(0, 0, 0));
        txtApellidoPaciente.setEnabled(false);
        txtApellidoPaciente.setNextFocusableComponent(txtSexoPaciente);
        txtApellidoPaciente.setPreferredSize(new java.awt.Dimension(200, 30));
        txtApellidoPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoPacienteKeyTyped(evt);
            }
        });

        txtDireccionPaciente.setBackground(new java.awt.Color(239, 239, 239));
        txtDireccionPaciente.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtDireccionPaciente.setForeground(new java.awt.Color(0, 0, 0));
        txtDireccionPaciente.setEnabled(false);
        txtDireccionPaciente.setNextFocusableComponent(txtOcupacionPaciente);
        txtDireccionPaciente.setPreferredSize(new java.awt.Dimension(200, 30));

        txtSexoPaciente.setBackground(new java.awt.Color(239, 239, 239));
        txtSexoPaciente.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtSexoPaciente.setForeground(new java.awt.Color(0, 0, 0));
        txtSexoPaciente.setEnabled(false);
        txtSexoPaciente.setNextFocusableComponent(txtDireccionPaciente);
        txtSexoPaciente.setPreferredSize(new java.awt.Dimension(200, 30));
        txtSexoPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSexoPacienteKeyTyped(evt);
            }
        });

        txtOcupacionPaciente.setBackground(new java.awt.Color(239, 239, 239));
        txtOcupacionPaciente.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtOcupacionPaciente.setForeground(new java.awt.Color(0, 0, 0));
        txtOcupacionPaciente.setEnabled(false);
        txtOcupacionPaciente.setNextFocusableComponent(spFechaPaciente);
        txtOcupacionPaciente.setPreferredSize(new java.awt.Dimension(200, 30));
        txtOcupacionPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOcupacionPacienteKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(239, 239, 239));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Estado Civil:");
        jLabel3.setPreferredSize(new java.awt.Dimension(200, 30));

        txtCivilPaciente.setBackground(new java.awt.Color(239, 239, 239));
        txtCivilPaciente.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtCivilPaciente.setForeground(new java.awt.Color(0, 0, 0));
        txtCivilPaciente.setEnabled(false);
        txtCivilPaciente.setNextFocusableComponent(btnAgregarPaciente);
        txtCivilPaciente.setPreferredSize(new java.awt.Dimension(200, 30));
        txtCivilPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCivilPacienteKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(239, 239, 239));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Teléfono:");
        jLabel14.setPreferredSize(new java.awt.Dimension(200, 30));

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

        lblError.setFont(new java.awt.Font("Liberation Sans", 3, 14)); // NOI18N
        lblError.setForeground(new java.awt.Color(255, 0, 0));
        lblError.setEnabled(false);

        txtDUIPaciente.setBackground(new java.awt.Color(255, 255, 255));
        txtDUIPaciente.setForeground(new java.awt.Color(0, 0, 0));
        try {
            txtDUIPaciente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDUIPaciente.setEnabled(false);
        txtDUIPaciente.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtDUIPaciente.setPreferredSize(new java.awt.Dimension(4, 27));

        txtTelefonoPaciente.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefonoPaciente.setForeground(new java.awt.Color(0, 0, 0));
        try {
            txtTelefonoPaciente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefonoPaciente.setEnabled(false);
        txtTelefonoPaciente.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N

        javax.swing.GroupLayout PanelSeleccionLayout = new javax.swing.GroupLayout(PanelSeleccion);
        PanelSeleccion.setLayout(PanelSeleccionLayout);
        PanelSeleccionLayout.setHorizontalGroup(
            PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSeleccionLayout.createSequentialGroup()
                .addGroup(PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSeleccionLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(jLabel72, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnMenuPaciente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelSeleccionLayout.createSequentialGroup()
                                .addGroup(PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombrePaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDireccionPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTelefonoPaciente))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(jLabel75, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(jLabel73, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtOcupacionPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtApellidoPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDUIPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(jLabel74, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(jLabel76, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(spFechaPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSexoPaciente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtCivilPaciente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(PanelSeleccionLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addGroup(PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelSeleccionLayout.createSequentialGroup()
                                .addComponent(btnNuevoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAgregarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(PanelSeleccionLayout.createSequentialGroup()
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNombreBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtDUIBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnSeleccionPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        PanelSeleccionLayout.setVerticalGroup(
            PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSeleccionLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombrePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSexoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccionPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOcupacionPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spFechaPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelefonoPaciente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCivilPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDUIPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSeleccionLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCancelarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAgregarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNuevoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(PanelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDUIBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnMenuPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        PanelPestañas.addTab("Seleccionar Paciente", PanelSeleccion);

        PanelIdentificacion.setBackground(new java.awt.Color(15, 76, 129));

        btnCancelarIdenti.setBackground(new java.awt.Color(76, 201, 223));
        btnCancelarIdenti.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnCancelarIdenti.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelarIdenti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Anterior 32.png"))); // NOI18N
        btnCancelarIdenti.setText(" Anterior");
        btnCancelarIdenti.setPreferredSize(new java.awt.Dimension(200, 40));
        btnCancelarIdenti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarIdentiActionPerformed(evt);
            }
        });

        btnSiguienteIdenti.setBackground(new java.awt.Color(76, 201, 223));
        btnSiguienteIdenti.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnSiguienteIdenti.setForeground(new java.awt.Color(0, 0, 0));
        btnSiguienteIdenti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Siguiente 32.png"))); // NOI18N
        btnSiguienteIdenti.setText("Siguiente ");
        btnSiguienteIdenti.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnSiguienteIdenti.setNextFocusableComponent(btnCancelarIdenti);
        btnSiguienteIdenti.setPreferredSize(new java.awt.Dimension(200, 40));
        btnSiguienteIdenti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteIdentiActionPerformed(evt);
            }
        });

        btnMenuIdenti.setBackground(new java.awt.Color(76, 201, 223));
        btnMenuIdenti.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnMenuIdenti.setForeground(new java.awt.Color(0, 0, 0));
        btnMenuIdenti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Menu.png"))); // NOI18N
        btnMenuIdenti.setText(" Volver al menú");
        btnMenuIdenti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuIdentiActionPerformed(evt);
            }
        });

        txtObservacionesIdenti.setBackground(new java.awt.Color(239, 239, 239));
        txtObservacionesIdenti.setColumns(20);
        txtObservacionesIdenti.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtObservacionesIdenti.setForeground(new java.awt.Color(0, 0, 0));
        txtObservacionesIdenti.setRows(5);
        txtObservacionesIdenti.setWrapStyleWord(true);
        txtObservacionesIdenti.setNextFocusableComponent(btnSiguienteIdenti);
        jScrollPane1.setViewportView(txtObservacionesIdenti);

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(239, 239, 239));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Nombres:");
        jLabel4.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(239, 239, 239));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Fecha de Nacimiento:");
        jLabel5.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(239, 239, 239));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Teléfono:");
        jLabel8.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel10.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(239, 239, 239));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Dirección:");
        jLabel10.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel11.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(239, 239, 239));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Sexo:");
        jLabel11.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel13.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(239, 239, 239));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Observaciones:");
        jLabel13.setPreferredSize(new java.awt.Dimension(100, 30));

        lblSexoIdenti.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        lblSexoIdenti.setForeground(new java.awt.Color(239, 239, 239));
        lblSexoIdenti.setText("...");
        lblSexoIdenti.setPreferredSize(new java.awt.Dimension(100, 30));

        lblDireccionIdenti.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        lblDireccionIdenti.setForeground(new java.awt.Color(239, 239, 239));
        lblDireccionIdenti.setText("...");
        lblDireccionIdenti.setPreferredSize(new java.awt.Dimension(100, 30));

        lblTelefonoIdenti.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        lblTelefonoIdenti.setForeground(new java.awt.Color(239, 239, 239));
        lblTelefonoIdenti.setText("...");
        lblTelefonoIdenti.setPreferredSize(new java.awt.Dimension(100, 30));

        lblFechaIdenti1.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        lblFechaIdenti1.setForeground(new java.awt.Color(239, 239, 239));
        lblFechaIdenti1.setText("...");
        lblFechaIdenti1.setPreferredSize(new java.awt.Dimension(100, 30));

        lblNombreIdenti.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        lblNombreIdenti.setForeground(new java.awt.Color(239, 239, 239));
        lblNombreIdenti.setText("...");
        lblNombreIdenti.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(239, 239, 239));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Apellidos:");
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(239, 239, 239));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Edad:");
        jLabel6.setPreferredSize(new java.awt.Dimension(100, 30));

        lblEdad.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        lblEdad.setForeground(new java.awt.Color(239, 239, 239));
        lblEdad.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEdad.setText("-     Años");
        lblEdad.setPreferredSize(new java.awt.Dimension(100, 30));

        lblApellidoIdenti.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        lblApellidoIdenti.setForeground(new java.awt.Color(239, 239, 239));
        lblApellidoIdenti.setText("...");
        lblApellidoIdenti.setPreferredSize(new java.awt.Dimension(100, 30));

        lblDuiIdenti.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        lblDuiIdenti.setForeground(new java.awt.Color(239, 239, 239));
        lblDuiIdenti.setText("...");
        lblDuiIdenti.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel9.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(239, 239, 239));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("DUI:");
        jLabel9.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel12.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(239, 239, 239));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Estado Civil:");
        jLabel12.setPreferredSize(new java.awt.Dimension(100, 30));

        lblCivilIdenti.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        lblCivilIdenti.setForeground(new java.awt.Color(239, 239, 239));
        lblCivilIdenti.setText("...");
        lblCivilIdenti.setPreferredSize(new java.awt.Dimension(100, 30));

        javax.swing.GroupLayout PanelIdentificacionLayout = new javax.swing.GroupLayout(PanelIdentificacion);
        PanelIdentificacion.setLayout(PanelIdentificacionLayout);
        PanelIdentificacionLayout.setHorizontalGroup(
            PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelIdentificacionLayout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelIdentificacionLayout.createSequentialGroup()
                        .addGroup(PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelIdentificacionLayout.createSequentialGroup()
                                .addComponent(lblTelefonoIdenti, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelIdentificacionLayout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(118, 118, 118))
                                    .addGroup(PanelIdentificacionLayout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblDuiIdenti, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(278, 278, 278))
                            .addGroup(PanelIdentificacionLayout.createSequentialGroup()
                                .addComponent(lblDireccionIdenti, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(PanelIdentificacionLayout.createSequentialGroup()
                        .addGroup(PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelIdentificacionLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnMenuIdenti, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(PanelIdentificacionLayout.createSequentialGroup()
                                        .addGroup(PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PanelIdentificacionLayout.createSequentialGroup()
                                                .addComponent(lblSexoIdenti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(16, 16, 16)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(lblCivilIdenti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(PanelIdentificacionLayout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(btnCancelarIdenti, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                    .addComponent(btnSiguienteIdenti, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                            .addGroup(PanelIdentificacionLayout.createSequentialGroup()
                                .addGroup(PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFechaIdenti1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(PanelIdentificacionLayout.createSequentialGroup()
                                        .addComponent(lblNombreIdenti, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblApellidoIdenti, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())))
        );
        PanelIdentificacionLayout.setVerticalGroup(
            PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelIdentificacionLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreIdenti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellidoIdenti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelIdentificacionLayout.createSequentialGroup()
                        .addGroup(PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaIdenti1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTelefonoIdenti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelIdentificacionLayout.createSequentialGroup()
                        .addGroup(PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDuiIdenti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDireccionIdenti, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSexoIdenti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCivilIdenti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(PanelIdentificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelIdentificacionLayout.createSequentialGroup()
                        .addComponent(btnSiguienteIdenti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnCancelarIdenti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMenuIdenti, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        PanelPestañas.addTab("Identificación", PanelIdentificacion);

        PanelConsulta.setBackground(new java.awt.Color(15, 76, 129));

        jLabel28.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(239, 239, 239));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Paciente:");
        jLabel28.setPreferredSize(new java.awt.Dimension(200, 30));

        lblPacienteConsulta.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        lblPacienteConsulta.setForeground(new java.awt.Color(239, 239, 239));
        lblPacienteConsulta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPacienteConsulta.setText("Nombre de Paciente");
        lblPacienteConsulta.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel30.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(239, 239, 239));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("Fecha de consulta:");
        jLabel30.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel31.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(239, 239, 239));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText("Última Consulta:");
        jLabel31.setPreferredSize(new java.awt.Dimension(200, 30));

        lblFechaUltima.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        lblFechaUltima.setForeground(new java.awt.Color(239, 239, 239));
        lblFechaUltima.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFechaUltima.setText("Fecha ultima consulta");
        lblFechaUltima.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel33.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(239, 239, 239));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel33.setText("Último motivo de consulta:");
        jLabel33.setPreferredSize(new java.awt.Dimension(200, 30));

        lblUltimoMotivo.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        lblUltimoMotivo.setForeground(new java.awt.Color(239, 239, 239));
        lblUltimoMotivo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUltimoMotivo.setText("Ultimo motivo de consulta");
        lblUltimoMotivo.setPreferredSize(new java.awt.Dimension(200, 30));

        btnHistorial.setBackground(new java.awt.Color(76, 201, 223));
        btnHistorial.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnHistorial.setForeground(new java.awt.Color(0, 0, 0));
        btnHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Expedientes.png"))); // NOI18N
        btnHistorial.setText("Ver Historial Clínico de Paciente");
        btnHistorial.setEnabled(false);
        btnHistorial.setPreferredSize(new java.awt.Dimension(250, 35));
        btnHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(239, 239, 239));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText("Consulta por:");
        jLabel35.setPreferredSize(new java.awt.Dimension(200, 30));

        txtMotivoConsulta.setBackground(new java.awt.Color(239, 239, 239));
        txtMotivoConsulta.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtMotivoConsulta.setForeground(new java.awt.Color(0, 0, 0));
        txtMotivoConsulta.setNextFocusableComponent(txtPresenteConsulta);
        txtMotivoConsulta.setPreferredSize(new java.awt.Dimension(200, 40));

        jLabel36.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(239, 239, 239));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel36.setText("Presente Enfermedad:");
        jLabel36.setPreferredSize(new java.awt.Dimension(200, 30));

        txtPresenteConsulta.setBackground(new java.awt.Color(239, 239, 239));
        txtPresenteConsulta.setColumns(20);
        txtPresenteConsulta.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtPresenteConsulta.setForeground(new java.awt.Color(0, 0, 0));
        txtPresenteConsulta.setRows(5);
        txtPresenteConsulta.setWrapStyleWord(true);
        txtPresenteConsulta.setNextFocusableComponent(btnSiguienteConsulta);
        txtPresenteConsulta.setPreferredSize(new java.awt.Dimension(412, 150));
        txtPresenteConsulta.setRequestFocusEnabled(false);
        jScrollPane5.setViewportView(txtPresenteConsulta);

        btnSiguienteConsulta.setBackground(new java.awt.Color(76, 201, 223));
        btnSiguienteConsulta.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnSiguienteConsulta.setForeground(new java.awt.Color(0, 0, 0));
        btnSiguienteConsulta.setText("Siguiente");
        btnSiguienteConsulta.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnSiguienteConsulta.setNextFocusableComponent(btnCancelarCancelar);
        btnSiguienteConsulta.setPreferredSize(new java.awt.Dimension(150, 40));
        btnSiguienteConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteConsultaActionPerformed(evt);
            }
        });

        btnCancelarCancelar.setBackground(new java.awt.Color(76, 201, 223));
        btnCancelarCancelar.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnCancelarCancelar.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelarCancelar.setText("Anterior");
        btnCancelarCancelar.setPreferredSize(new java.awt.Dimension(150, 40));
        btnCancelarCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCancelarActionPerformed(evt);
            }
        });

        btnMenuConsulta.setBackground(new java.awt.Color(76, 201, 223));
        btnMenuConsulta.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnMenuConsulta.setForeground(new java.awt.Color(0, 0, 0));
        btnMenuConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Menu.png"))); // NOI18N
        btnMenuConsulta.setText(" Volver al menú");
        btnMenuConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuConsultaActionPerformed(evt);
            }
        });

        JSpinner.DateEditor datee = new JSpinner.DateEditor(spFechaConsulta, "dd-MM-yyyy");
        spFechaConsulta.setEditor(datee);
        spFechaConsulta.setEnabled(true);
        spFechaConsulta.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        spFechaConsulta.setPreferredSize(new java.awt.Dimension(200, 30));
        spFechaConsulta.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

        javax.swing.GroupLayout PanelConsultaLayout = new javax.swing.GroupLayout(PanelConsulta);
        PanelConsulta.setLayout(PanelConsultaLayout);
        PanelConsultaLayout.setHorizontalGroup(
            PanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelConsultaLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(PanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelConsultaLayout.createSequentialGroup()
                        .addGroup(PanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPacienteConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelConsultaLayout.createSequentialGroup()
                                .addComponent(spFechaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblFechaUltima, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelConsultaLayout.createSequentialGroup()
                                .addComponent(lblUltimoMotivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(26, 26, 26)
                                .addComponent(btnHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(86, 86, 86))))
                    .addGroup(PanelConsultaLayout.createSequentialGroup()
                        .addGroup(PanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMotivoConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCancelarCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSiguienteConsulta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnMenuConsulta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        PanelConsultaLayout.setVerticalGroup(
            PanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelConsultaLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(PanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(PanelConsultaLayout.createSequentialGroup()
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6))
                        .addGroup(PanelConsultaLayout.createSequentialGroup()
                            .addComponent(lblPacienteConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addGroup(PanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(spFechaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblFechaUltima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(10, 10, 10)
                            .addGroup(PanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblUltimoMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20)
                .addGroup(PanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelConsultaLayout.createSequentialGroup()
                        .addGroup(PanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMotivoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(PanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelConsultaLayout.createSequentialGroup()
                        .addComponent(btnSiguienteConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnCancelarCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnMenuConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        PanelPestañas.addTab("Consulta Por", PanelConsulta);

        PanelAntecedentes.setBackground(new java.awt.Color(15, 76, 129));

        jLabel24.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(239, 239, 239));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Paciente:");
        jLabel24.setPreferredSize(new java.awt.Dimension(200, 30));

        lblPacienteAnte.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        lblPacienteAnte.setForeground(new java.awt.Color(239, 239, 239));
        lblPacienteAnte.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPacienteAnte.setText("Nombre de Paciente");
        lblPacienteAnte.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel26.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(239, 239, 239));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("Antecedentes Personales:");

        jLabel27.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(239, 239, 239));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("Antecedentes Familiares:");

        txtPersonalAnte.setBackground(new java.awt.Color(239, 239, 239));
        txtPersonalAnte.setColumns(20);
        txtPersonalAnte.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtPersonalAnte.setForeground(new java.awt.Color(0, 0, 0));
        txtPersonalAnte.setRows(5);
        txtPersonalAnte.setWrapStyleWord(true);
        txtPersonalAnte.setEnabled(false);
        txtPersonalAnte.setNextFocusableComponent(txtFamiliarAnte);
        jScrollPane3.setViewportView(txtPersonalAnte);

        txtFamiliarAnte.setBackground(new java.awt.Color(239, 239, 239));
        txtFamiliarAnte.setColumns(20);
        txtFamiliarAnte.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtFamiliarAnte.setForeground(new java.awt.Color(0, 0, 0));
        txtFamiliarAnte.setRows(5);
        txtFamiliarAnte.setWrapStyleWord(true);
        txtFamiliarAnte.setEnabled(false);
        txtFamiliarAnte.setNextFocusableComponent(btnAgregarAnte);
        jScrollPane4.setViewportView(txtFamiliarAnte);

        btnSiguienteAnte.setBackground(new java.awt.Color(76, 201, 223));
        btnSiguienteAnte.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnSiguienteAnte.setForeground(new java.awt.Color(0, 0, 0));
        btnSiguienteAnte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Siguiente 32.png"))); // NOI18N
        btnSiguienteAnte.setText("Siguiente ");
        btnSiguienteAnte.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnSiguienteAnte.setNextFocusableComponent(btnCancelarAnte);
        btnSiguienteAnte.setPreferredSize(new java.awt.Dimension(150, 40));
        btnSiguienteAnte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteAnteActionPerformed(evt);
            }
        });

        btnCancelarL2.setBackground(new java.awt.Color(76, 201, 223));
        btnCancelarL2.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnCancelarL2.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelarL2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Anterior 32.png"))); // NOI18N
        btnCancelarL2.setText(" Anterior");
        btnCancelarL2.setPreferredSize(new java.awt.Dimension(150, 40));
        btnCancelarL2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarL2ActionPerformed(evt);
            }
        });

        btnMenuAnte.setBackground(new java.awt.Color(76, 201, 223));
        btnMenuAnte.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnMenuAnte.setForeground(new java.awt.Color(0, 0, 0));
        btnMenuAnte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Menu.png"))); // NOI18N
        btnMenuAnte.setText("Volver al menú");
        btnMenuAnte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuAnteActionPerformed(evt);
            }
        });

        tbAntecendentes.setBackground(new java.awt.Color(239, 239, 239));
        tbAntecendentes.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        tbAntecendentes.setForeground(new java.awt.Color(0, 0, 0));
        tbAntecendentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbAntecendentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAntecendentesMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tbAntecendentes);

        btnNuevoAnte.setBackground(new java.awt.Color(76, 201, 223));
        btnNuevoAnte.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnNuevoAnte.setForeground(new java.awt.Color(0, 0, 0));
        btnNuevoAnte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Editar 32.png"))); // NOI18N
        btnNuevoAnte.setText(" Nuevo");
        btnNuevoAnte.setPreferredSize(new java.awt.Dimension(150, 40));
        btnNuevoAnte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoAnteActionPerformed(evt);
            }
        });

        btnCancelarAnte.setBackground(new java.awt.Color(76, 201, 223));
        btnCancelarAnte.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnCancelarAnte.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelarAnte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Cancelar 32.png"))); // NOI18N
        btnCancelarAnte.setText(" Cancelar");
        btnCancelarAnte.setBorder(null);
        btnCancelarAnte.setEnabled(false);
        btnCancelarAnte.setPreferredSize(new java.awt.Dimension(150, 40));
        btnCancelarAnte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarAnteActionPerformed(evt);
            }
        });

        btnAgregarAnte.setBackground(new java.awt.Color(76, 201, 223));
        btnAgregarAnte.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnAgregarAnte.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarAnte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Agg 32.png"))); // NOI18N
        btnAgregarAnte.setText(" Agregar");
        btnAgregarAnte.setBorder(null);
        btnAgregarAnte.setEnabled(false);
        btnAgregarAnte.setNextFocusableComponent(btnSiguienteAnte);
        btnAgregarAnte.setPreferredSize(new java.awt.Dimension(150, 40));
        btnAgregarAnte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarAnteActionPerformed(evt);
            }
        });

        btnEditarAnte.setBackground(new java.awt.Color(76, 201, 223));
        btnEditarAnte.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnEditarAnte.setForeground(new java.awt.Color(0, 0, 0));
        btnEditarAnte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Guardar Ed.png"))); // NOI18N
        btnEditarAnte.setText("Guardar Edición");
        btnEditarAnte.setBorder(null);
        btnEditarAnte.setEnabled(false);
        btnEditarAnte.setPreferredSize(new java.awt.Dimension(120, 60));
        btnEditarAnte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarAnteActionPerformed(evt);
            }
        });

        btnElminarAnte.setBackground(new java.awt.Color(76, 201, 223));
        btnElminarAnte.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnElminarAnte.setForeground(new java.awt.Color(0, 0, 0));
        btnElminarAnte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Eliminar 32.png"))); // NOI18N
        btnElminarAnte.setText(" Eliminar");
        btnElminarAnte.setBorder(null);
        btnElminarAnte.setEnabled(false);
        btnElminarAnte.setPreferredSize(new java.awt.Dimension(150, 40));
        btnElminarAnte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElminarAnteActionPerformed(evt);
            }
        });

        btnReporteAntecedentes.setBackground(new java.awt.Color(76, 201, 223));
        btnReporteAntecedentes.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        btnReporteAntecedentes.setForeground(new java.awt.Color(0, 0, 0));
        btnReporteAntecedentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Generar Reporte.png"))); // NOI18N
        btnReporteAntecedentes.setText("Reporte de Antecedentes");
        btnReporteAntecedentes.setEnabled(false);
        btnReporteAntecedentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteAntecedentesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelAntecedentesLayout = new javax.swing.GroupLayout(PanelAntecedentes);
        PanelAntecedentes.setLayout(PanelAntecedentesLayout);
        PanelAntecedentesLayout.setHorizontalGroup(
            PanelAntecedentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAntecedentesLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(PanelAntecedentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAntecedentesLayout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(PanelAntecedentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelAntecedentesLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(PanelAntecedentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnCancelarAnte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnNuevoAnte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(PanelAntecedentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAgregarAnte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnElminarAnte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PanelAntecedentesLayout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(btnEditarAnte, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(160, 160, 160))
                    .addGroup(PanelAntecedentesLayout.createSequentialGroup()
                        .addGroup(PanelAntecedentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelAntecedentesLayout.createSequentialGroup()
                                .addGroup(PanelAntecedentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelAntecedentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(PanelAntecedentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(PanelAntecedentesLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(PanelAntecedentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnCancelarL2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnSiguienteAnte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(PanelAntecedentesLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnMenuAnte, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                                    .addGroup(PanelAntecedentesLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnReporteAntecedentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(PanelAntecedentesLayout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblPacienteAnte, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(42, 42, 42))))
        );
        PanelAntecedentesLayout.setVerticalGroup(
            PanelAntecedentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAntecedentesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PanelAntecedentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPacienteAnte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(PanelAntecedentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAntecedentesLayout.createSequentialGroup()
                        .addComponent(btnSiguienteAnte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelarL2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReporteAntecedentes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMenuAnte, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(143, 143, 143))
                    .addGroup(PanelAntecedentesLayout.createSequentialGroup()
                        .addGroup(PanelAntecedentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelAntecedentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(PanelAntecedentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAntecedentesLayout.createSequentialGroup()
                        .addGroup(PanelAntecedentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNuevoAnte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregarAnte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(PanelAntecedentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelarAnte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnElminarAnte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addComponent(btnEditarAnte, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(52, 52, 52))
        );

        PanelPestañas.addTab("Antecedentes", PanelAntecedentes);

        PanelExploracion.setBackground(new java.awt.Color(15, 76, 129));
        PanelExploracion.setForeground(new java.awt.Color(239, 239, 239));

        jLabel37.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel37.setText("Paciente:");
        jLabel37.setPreferredSize(new java.awt.Dimension(30, 30));

        lblPacienteFisica.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        lblPacienteFisica.setForeground(new java.awt.Color(255, 255, 255));
        lblPacienteFisica.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPacienteFisica.setText("Nombre de Paciente");
        lblPacienteFisica.setPreferredSize(new java.awt.Dimension(30, 30));

        jLabel39.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(239, 239, 239));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Signos Vitales");
        jLabel39.setPreferredSize(new java.awt.Dimension(30, 30));

        jLabel40.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(239, 239, 239));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel40.setText("P.A.:");
        jLabel40.setPreferredSize(new java.awt.Dimension(30, 30));

        jLabel41.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(239, 239, 239));
        jLabel41.setText("mmHg");
        jLabel41.setPreferredSize(new java.awt.Dimension(30, 30));

        txtPresion.setBackground(new java.awt.Color(239, 239, 239));
        txtPresion.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtPresion.setForeground(new java.awt.Color(0, 0, 0));
        txtPresion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPresion.setNextFocusableComponent(txtPulso);
        txtPresion.setPreferredSize(new java.awt.Dimension(30, 30));
        txtPresion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPresionKeyTyped(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(239, 239, 239));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel42.setText("Pulso:");
        jLabel42.setPreferredSize(new java.awt.Dimension(30, 30));

        txtPulso.setBackground(new java.awt.Color(239, 239, 239));
        txtPulso.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtPulso.setForeground(new java.awt.Color(0, 0, 0));
        txtPulso.setNextFocusableComponent(txtFCC);
        txtPulso.setPreferredSize(new java.awt.Dimension(30, 30));
        txtPulso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPulsoKeyTyped(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(239, 239, 239));
        jLabel43.setText("ppm");
        jLabel43.setPreferredSize(new java.awt.Dimension(30, 30));

        jLabel44.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(239, 239, 239));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel44.setText("FCC:");
        jLabel44.setPreferredSize(new java.awt.Dimension(30, 30));

        txtFCC.setBackground(new java.awt.Color(239, 239, 239));
        txtFCC.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtFCC.setForeground(new java.awt.Color(0, 0, 0));
        txtFCC.setNextFocusableComponent(txtFR);
        txtFCC.setPreferredSize(new java.awt.Dimension(30, 30));
        txtFCC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFCCKeyTyped(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(239, 239, 239));
        jLabel45.setText("lpm");
        jLabel45.setPreferredSize(new java.awt.Dimension(30, 30));

        jLabel46.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(239, 239, 239));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel46.setText("FR:");
        jLabel46.setPreferredSize(new java.awt.Dimension(30, 30));

        txtFR.setBackground(new java.awt.Color(239, 239, 239));
        txtFR.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtFR.setForeground(new java.awt.Color(0, 0, 0));
        txtFR.setNextFocusableComponent(txtTemperatura);
        txtFR.setPreferredSize(new java.awt.Dimension(30, 30));
        txtFR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFRKeyTyped(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(239, 239, 239));
        jLabel47.setText("rpm");
        jLabel47.setPreferredSize(new java.awt.Dimension(30, 30));

        jLabel48.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(239, 239, 239));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel48.setText("Temp:");
        jLabel48.setPreferredSize(new java.awt.Dimension(30, 30));

        txtTemperatura.setBackground(new java.awt.Color(239, 239, 239));
        txtTemperatura.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtTemperatura.setForeground(new java.awt.Color(0, 0, 0));
        txtTemperatura.setNextFocusableComponent(txtMasa);
        txtTemperatura.setPreferredSize(new java.awt.Dimension(30, 30));
        txtTemperatura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTemperaturaKeyTyped(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(239, 239, 239));
        jLabel49.setText("°C");
        jLabel49.setPreferredSize(new java.awt.Dimension(30, 30));

        jLabel50.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(239, 239, 239));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel50.setText("Masa:");
        jLabel50.setPreferredSize(new java.awt.Dimension(30, 30));

        txtMasa.setBackground(new java.awt.Color(239, 239, 239));
        txtMasa.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtMasa.setForeground(new java.awt.Color(0, 0, 0));
        txtMasa.setNextFocusableComponent(txtTalla);
        txtMasa.setPreferredSize(new java.awt.Dimension(30, 30));
        txtMasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMasaActionPerformed(evt);
            }
        });
        txtMasa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMasaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMasaKeyTyped(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(239, 239, 239));
        jLabel51.setText("kg");
        jLabel51.setPreferredSize(new java.awt.Dimension(30, 30));

        jLabel52.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(239, 239, 239));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel52.setText("Talla:");
        jLabel52.setPreferredSize(new java.awt.Dimension(30, 30));

        txtTalla.setBackground(new java.awt.Color(239, 239, 239));
        txtTalla.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtTalla.setForeground(new java.awt.Color(0, 0, 0));
        txtTalla.setNextFocusableComponent(txtFisico);
        txtTalla.setPreferredSize(new java.awt.Dimension(30, 30));
        txtTalla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTallaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTallaKeyTyped(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(239, 239, 239));
        jLabel53.setText("cm");
        jLabel53.setPreferredSize(new java.awt.Dimension(30, 30));

        jLabel54.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(239, 239, 239));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel54.setText("IMC:");
        jLabel54.setPreferredSize(new java.awt.Dimension(30, 30));

        jLabel56.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(239, 239, 239));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel56.setText("Exámen Fisico:");
        jLabel56.setPreferredSize(new java.awt.Dimension(30, 30));

        txtFisico.setBackground(new java.awt.Color(239, 239, 239));
        txtFisico.setColumns(20);
        txtFisico.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtFisico.setForeground(new java.awt.Color(0, 0, 0));
        txtFisico.setRows(5);
        txtFisico.setWrapStyleWord(true);
        txtFisico.setNextFocusableComponent(btnSiguienteFisica);
        jScrollPane6.setViewportView(txtFisico);

        btnSiguienteFisica.setBackground(new java.awt.Color(76, 201, 223));
        btnSiguienteFisica.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnSiguienteFisica.setForeground(new java.awt.Color(0, 0, 0));
        btnSiguienteFisica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Siguiente 32.png"))); // NOI18N
        btnSiguienteFisica.setText("Siguiente ");
        btnSiguienteFisica.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnSiguienteFisica.setNextFocusableComponent(btnCancelarFisica);
        btnSiguienteFisica.setPreferredSize(new java.awt.Dimension(150, 40));
        btnSiguienteFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteFisicaActionPerformed(evt);
            }
        });

        btnCancelarFisica.setBackground(new java.awt.Color(76, 201, 223));
        btnCancelarFisica.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnCancelarFisica.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelarFisica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Anterior 32.png"))); // NOI18N
        btnCancelarFisica.setText(" Anterior");
        btnCancelarFisica.setPreferredSize(new java.awt.Dimension(150, 40));
        btnCancelarFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarFisicaActionPerformed(evt);
            }
        });

        btnMenuFisica.setBackground(new java.awt.Color(76, 201, 223));
        btnMenuFisica.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnMenuFisica.setForeground(new java.awt.Color(0, 0, 0));
        btnMenuFisica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Menu.png"))); // NOI18N
        btnMenuFisica.setText(" Volver al menú");
        btnMenuFisica.setPreferredSize(new java.awt.Dimension(100, 40));
        btnMenuFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuFisicaActionPerformed(evt);
            }
        });

        lblIMC.setBackground(new java.awt.Color(239, 239, 239));
        lblIMC.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        lblIMC.setForeground(new java.awt.Color(0, 0, 0));
        lblIMC.setEnabled(false);
        lblIMC.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout PanelExploracionLayout = new javax.swing.GroupLayout(PanelExploracion);
        PanelExploracion.setLayout(PanelExploracionLayout);
        PanelExploracionLayout.setHorizontalGroup(
            PanelExploracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelExploracionLayout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addGroup(PanelExploracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelExploracionLayout.createSequentialGroup()
                        .addGroup(PanelExploracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelExploracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(PanelExploracionLayout.createSequentialGroup()
                                    .addGroup(PanelExploracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(PanelExploracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtMasa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtFR, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPresion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(PanelExploracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(PanelExploracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(PanelExploracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(PanelExploracionLayout.createSequentialGroup()
                                            .addComponent(txtTalla, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblIMC, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(PanelExploracionLayout.createSequentialGroup()
                                            .addGroup(PanelExploracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtPulso, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtTemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(PanelExploracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(PanelExploracionLayout.createSequentialGroup()
                                                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtFCC, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelExploracionLayout.createSequentialGroup()
                                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PanelExploracionLayout.createSequentialGroup()
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblPacienteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PanelExploracionLayout.createSequentialGroup()
                                .addGap(209, 209, 209)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(PanelExploracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnSiguienteFisica, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelarFisica, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelExploracionLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMenuFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        PanelExploracionLayout.setVerticalGroup(
            PanelExploracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelExploracionLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(PanelExploracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPacienteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(PanelExploracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPresion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPulso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(PanelExploracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(PanelExploracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIMC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(PanelExploracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelExploracionLayout.createSequentialGroup()
                        .addComponent(btnSiguienteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnCancelarFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(btnMenuFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        PanelPestañas.addTab("Exploración Fisica", PanelExploracion);

        PanelDiagnostico.setBackground(new java.awt.Color(15, 76, 129));

        jLabel57.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(239, 239, 239));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel57.setText("Diagnóstico Clínico:");
        jLabel57.setPreferredSize(new java.awt.Dimension(200, 30));

        lblPacienteDiagnostico.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        lblPacienteDiagnostico.setForeground(new java.awt.Color(239, 239, 239));
        lblPacienteDiagnostico.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPacienteDiagnostico.setText("Nombre de Paciente");
        lblPacienteDiagnostico.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel59.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(239, 239, 239));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel59.setText("Paciente:");
        jLabel59.setPreferredSize(new java.awt.Dimension(200, 30));

        tbDiagnostico.setBackground(new java.awt.Color(239, 239, 239));
        tbDiagnostico.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        tbDiagnostico.setForeground(new java.awt.Color(0, 0, 0));
        tbDiagnostico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbDiagnostico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDiagnosticoMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tbDiagnostico);

        btnEnfermedades.setBackground(new java.awt.Color(76, 201, 223));
        btnEnfermedades.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnEnfermedades.setForeground(new java.awt.Color(0, 0, 0));
        btnEnfermedades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Enfermedades 32.png"))); // NOI18N
        btnEnfermedades.setText("Ver Enfermedades");
        btnEnfermedades.setPreferredSize(new java.awt.Dimension(240, 40));
        btnEnfermedades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnfermedadesActionPerformed(evt);
            }
        });

        txtAgregarDiagnostico.setBackground(new java.awt.Color(76, 201, 223));
        txtAgregarDiagnostico.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        txtAgregarDiagnostico.setForeground(new java.awt.Color(0, 0, 0));
        txtAgregarDiagnostico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Agg 32.png"))); // NOI18N
        txtAgregarDiagnostico.setText("Agregar");
        txtAgregarDiagnostico.setEnabled(false);
        txtAgregarDiagnostico.setPreferredSize(new java.awt.Dimension(150, 40));
        txtAgregarDiagnostico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgregarDiagnosticoActionPerformed(evt);
            }
        });

        btnEliminarDiagnostico.setBackground(new java.awt.Color(76, 201, 223));
        btnEliminarDiagnostico.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnEliminarDiagnostico.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminarDiagnostico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Eliminar 32.png"))); // NOI18N
        btnEliminarDiagnostico.setText("Eliminar");
        btnEliminarDiagnostico.setEnabled(false);
        btnEliminarDiagnostico.setPreferredSize(new java.awt.Dimension(150, 40));
        btnEliminarDiagnostico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDiagnosticoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(239, 239, 239));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Observaciones:");
        jLabel7.setPreferredSize(new java.awt.Dimension(200, 30));

        txtObservacionesDiagnostico.setBackground(new java.awt.Color(239, 239, 239));
        txtObservacionesDiagnostico.setColumns(20);
        txtObservacionesDiagnostico.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtObservacionesDiagnostico.setForeground(new java.awt.Color(0, 0, 0));
        txtObservacionesDiagnostico.setRows(5);
        txtObservacionesDiagnostico.setWrapStyleWord(true);
        txtObservacionesDiagnostico.setEnabled(false);
        txtObservacionesDiagnostico.setPreferredSize(new java.awt.Dimension(412, 150));
        jScrollPane13.setViewportView(txtObservacionesDiagnostico);

        btnSiguienteFisica1.setBackground(new java.awt.Color(76, 201, 223));
        btnSiguienteFisica1.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnSiguienteFisica1.setForeground(new java.awt.Color(0, 0, 0));
        btnSiguienteFisica1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Siguiente 32.png"))); // NOI18N
        btnSiguienteFisica1.setText("Siguiente");
        btnSiguienteFisica1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnSiguienteFisica1.setPreferredSize(new java.awt.Dimension(150, 40));
        btnSiguienteFisica1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteFisica1ActionPerformed(evt);
            }
        });

        btnMenuFisica1.setBackground(new java.awt.Color(76, 201, 223));
        btnMenuFisica1.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnMenuFisica1.setForeground(new java.awt.Color(0, 0, 0));
        btnMenuFisica1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Menu.png"))); // NOI18N
        btnMenuFisica1.setText("Volver al menú");
        btnMenuFisica1.setPreferredSize(new java.awt.Dimension(100, 40));
        btnMenuFisica1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuFisica1ActionPerformed(evt);
            }
        });

        txtEnfermedad.setBackground(new java.awt.Color(239, 239, 239));
        txtEnfermedad.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtEnfermedad.setForeground(new java.awt.Color(0, 0, 0));
        txtEnfermedad.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtEnfermedad.setEnabled(false);
        txtEnfermedad.setNextFocusableComponent(btnbuscarporCate);
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

        btnbuscarporCate.setBackground(new java.awt.Color(76, 201, 223));
        btnbuscarporCate.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnbuscarporCate.setForeground(new java.awt.Color(0, 0, 0));
        btnbuscarporCate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Buscar 32.png"))); // NOI18N
        btnbuscarporCate.setText("Buscar por Categoría");
        btnbuscarporCate.setEnabled(false);
        btnbuscarporCate.setNextFocusableComponent(btnBuscarEnfermedad);
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
        btnBuscarEnfermedad.setEnabled(false);
        btnBuscarEnfermedad.setPreferredSize(new java.awt.Dimension(240, 40));
        btnBuscarEnfermedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEnfermedadActionPerformed(evt);
            }
        });

        btnDiagnostico.setBackground(new java.awt.Color(76, 201, 223));
        btnDiagnostico.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnDiagnostico.setForeground(new java.awt.Color(0, 0, 0));
        btnDiagnostico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Nuevo Diag 32.png"))); // NOI18N
        btnDiagnostico.setText("Diagnostico");
        btnDiagnostico.setPreferredSize(new java.awt.Dimension(240, 40));
        btnDiagnostico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiagnosticoActionPerformed(evt);
            }
        });

        btnEditarDiagnostico.setBackground(new java.awt.Color(76, 201, 223));
        btnEditarDiagnostico.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnEditarDiagnostico.setForeground(new java.awt.Color(0, 0, 0));
        btnEditarDiagnostico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Guardar Ed.png"))); // NOI18N
        btnEditarDiagnostico.setText("Guardar Edición");
        btnEditarDiagnostico.setBorder(null);
        btnEditarDiagnostico.setEnabled(false);
        btnEditarDiagnostico.setPreferredSize(new java.awt.Dimension(120, 60));
        btnEditarDiagnostico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarDiagnosticoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelDiagnosticoLayout = new javax.swing.GroupLayout(PanelDiagnostico);
        PanelDiagnostico.setLayout(PanelDiagnosticoLayout);
        PanelDiagnosticoLayout.setHorizontalGroup(
            PanelDiagnosticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDiagnosticoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PanelDiagnosticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDiagnosticoLayout.createSequentialGroup()
                        .addGroup(PanelDiagnosticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(PanelDiagnosticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEnfermedad, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(lblPacienteDiagnostico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelDiagnosticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDiagnostico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnbuscarporCate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelDiagnosticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarEnfermedad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEnfermedades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 984, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelDiagnosticoLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelDiagnosticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                            .addComponent(jScrollPane13))
                        .addGroup(PanelDiagnosticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelDiagnosticoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelDiagnosticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnEditarDiagnostico, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(txtAgregarDiagnostico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnEliminarDiagnostico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(PanelDiagnosticoLayout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addGroup(PanelDiagnosticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnSiguienteFisica1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnMenuFisica1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        PanelDiagnosticoLayout.setVerticalGroup(
            PanelDiagnosticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDiagnosticoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PanelDiagnosticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDiagnosticoLayout.createSequentialGroup()
                        .addGroup(PanelDiagnosticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPacienteDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(PanelDiagnosticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEnfermedad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelDiagnosticoLayout.createSequentialGroup()
                        .addGroup(PanelDiagnosticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEnfermedades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelDiagnosticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarEnfermedad, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                            .addComponent(btnbuscarporCate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(PanelDiagnosticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelDiagnosticoLayout.createSequentialGroup()
                        .addGroup(PanelDiagnosticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelDiagnosticoLayout.createSequentialGroup()
                                .addComponent(txtAgregarDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditarDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminarDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(PanelDiagnosticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PanelDiagnosticoLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(btnSiguienteFisica1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnMenuFisica1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelPestañas.addTab("Diagnostico", PanelDiagnostico);

        PanelExamenes.setBackground(new java.awt.Color(15, 76, 129));

        jLabel66.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(239, 239, 239));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel66.setText("Exámenes de Laboratorio y Gabinete:");
        jLabel66.setPreferredSize(new java.awt.Dimension(400, 30));

        txtExamenes.setBackground(new java.awt.Color(239, 239, 239));
        txtExamenes.setColumns(20);
        txtExamenes.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtExamenes.setForeground(new java.awt.Color(0, 0, 0));
        txtExamenes.setRows(5);
        txtExamenes.setWrapStyleWord(true);
        jScrollPane8.setViewportView(txtExamenes);

        jLabel60.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(239, 239, 239));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel60.setText("Paciente:");
        jLabel60.setPreferredSize(new java.awt.Dimension(400, 30));

        lblPacienteExamen.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        lblPacienteExamen.setForeground(new java.awt.Color(239, 239, 239));
        lblPacienteExamen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPacienteExamen.setText("Nombre de Paciente");
        lblPacienteExamen.setPreferredSize(new java.awt.Dimension(400, 30));

        btnSiguienteExamen.setBackground(new java.awt.Color(76, 201, 223));
        btnSiguienteExamen.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnSiguienteExamen.setForeground(new java.awt.Color(0, 0, 0));
        btnSiguienteExamen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Siguiente 32.png"))); // NOI18N
        btnSiguienteExamen.setText("Siguiente ");
        btnSiguienteExamen.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnSiguienteExamen.setPreferredSize(new java.awt.Dimension(150, 40));
        btnSiguienteExamen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteExamenActionPerformed(evt);
            }
        });

        btnMenuExamen.setBackground(new java.awt.Color(76, 201, 223));
        btnMenuExamen.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnMenuExamen.setForeground(new java.awt.Color(0, 0, 0));
        btnMenuExamen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Menu.png"))); // NOI18N
        btnMenuExamen.setText(" Volver al menú");
        btnMenuExamen.setPreferredSize(new java.awt.Dimension(100, 40));
        btnMenuExamen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuExamenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelExamenesLayout = new javax.swing.GroupLayout(PanelExamenes);
        PanelExamenes.setLayout(PanelExamenesLayout);
        PanelExamenesLayout.setHorizontalGroup(
            PanelExamenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelExamenesLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(PanelExamenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnMenuExamen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelExamenesLayout.createSequentialGroup()
                        .addGroup(PanelExamenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelExamenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelExamenesLayout.createSequentialGroup()
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSiguienteExamen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblPacienteExamen, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        PanelExamenesLayout.setVerticalGroup(
            PanelExamenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelExamenesLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(PanelExamenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPacienteExamen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addGroup(PanelExamenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelExamenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnSiguienteExamen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnMenuExamen, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(200, Short.MAX_VALUE))
        );

        PanelPestañas.addTab("Exámenes", PanelExamenes);

        PanelTratamiento.setBackground(new java.awt.Color(15, 76, 129));
        PanelTratamiento.setPreferredSize(new java.awt.Dimension(874, 800));

        lblPacienteTratamiento.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        lblPacienteTratamiento.setForeground(new java.awt.Color(239, 239, 239));
        lblPacienteTratamiento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPacienteTratamiento.setText("Nombre de Paciente");
        lblPacienteTratamiento.setPreferredSize(new java.awt.Dimension(40, 30));

        jLabel61.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(239, 239, 239));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel61.setText("Paciente:");
        jLabel61.setPreferredSize(new java.awt.Dimension(40, 30));

        jLabel63.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(239, 239, 239));
        jLabel63.setText("Buscar Medicamento por Nombre:");
        jLabel63.setPreferredSize(new java.awt.Dimension(40, 30));

        txtMedicamentoBusqueda.setBackground(new java.awt.Color(239, 239, 239));
        txtMedicamentoBusqueda.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtMedicamentoBusqueda.setForeground(new java.awt.Color(0, 0, 0));
        txtMedicamentoBusqueda.setEnabled(false);
        txtMedicamentoBusqueda.setPreferredSize(new java.awt.Dimension(40, 45));
        txtMedicamentoBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMedicamentoBusquedaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMedicamentoBusquedaKeyTyped(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(239, 239, 239));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel64.setText("Cantidad:");
        jLabel64.setPreferredSize(new java.awt.Dimension(40, 30));

        txtCantidad.setBackground(new java.awt.Color(239, 239, 239));
        txtCantidad.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtCantidad.setForeground(new java.awt.Color(0, 0, 0));
        txtCantidad.setEnabled(false);
        txtCantidad.setPreferredSize(new java.awt.Dimension(40, 30));
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        jLabel65.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(239, 239, 239));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel65.setText("Dosis:");
        jLabel65.setPreferredSize(new java.awt.Dimension(40, 30));

        txtDosis.setBackground(new java.awt.Color(239, 239, 239));
        txtDosis.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        txtDosis.setForeground(new java.awt.Color(0, 0, 0));
        txtDosis.setEnabled(false);
        txtDosis.setPreferredSize(new java.awt.Dimension(40, 30));
        txtDosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDosisKeyTyped(evt);
            }
        });

        tbVademecum.setBackground(new java.awt.Color(239, 239, 239));
        tbVademecum.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        tbVademecum.setForeground(new java.awt.Color(0, 0, 0));
        tbVademecum.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbVademecum.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVademecumMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbVademecum);

        btnEliminarMedicamento.setBackground(new java.awt.Color(76, 201, 223));
        btnEliminarMedicamento.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnEliminarMedicamento.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminarMedicamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Eliminar 32.png"))); // NOI18N
        btnEliminarMedicamento.setText(" Eliminar Medicamento");
        btnEliminarMedicamento.setEnabled(false);
        btnEliminarMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarMedicamentoActionPerformed(evt);
            }
        });

        btnEditarMedicamento.setBackground(new java.awt.Color(76, 201, 223));
        btnEditarMedicamento.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnEditarMedicamento.setForeground(new java.awt.Color(0, 0, 0));
        btnEditarMedicamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Editar 32.png"))); // NOI18N
        btnEditarMedicamento.setText(" Editar Medicamento");
        btnEditarMedicamento.setEnabled(false);
        btnEditarMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarMedicamentoActionPerformed(evt);
            }
        });

        txtVademecum.setBackground(new java.awt.Color(76, 201, 223));
        txtVademecum.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtVademecum.setForeground(new java.awt.Color(0, 0, 0));
        txtVademecum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Vademecum 32.png"))); // NOI18N
        txtVademecum.setText(" Ver Vademecum");
        txtVademecum.setPreferredSize(new java.awt.Dimension(40, 40));
        txtVademecum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVademecumActionPerformed(evt);
            }
        });

        tbReceta.setBackground(new java.awt.Color(239, 239, 239));
        tbReceta.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        tbReceta.setForeground(new java.awt.Color(0, 0, 0));
        tbReceta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbReceta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRecetaMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tbReceta);

        jLabel68.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(239, 239, 239));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setText("Medicamentos Recetados:");
        jLabel68.setPreferredSize(new java.awt.Dimension(150, 30));

        btnAgregarMedicamento.setBackground(new java.awt.Color(76, 201, 223));
        btnAgregarMedicamento.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnAgregarMedicamento.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarMedicamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Agg 32.png"))); // NOI18N
        btnAgregarMedicamento.setText(" Agregar Medicamento");
        btnAgregarMedicamento.setEnabled(false);
        btnAgregarMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarMedicamentoActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(15, 76, 129));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        btnHacerCita.setBackground(new java.awt.Color(76, 201, 223));
        btnHacerCita.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnHacerCita.setForeground(new java.awt.Color(0, 0, 0));
        btnHacerCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Agendar 32.png"))); // NOI18N
        btnHacerCita.setText("Agendar Cita");
        btnHacerCita.setEnabled(false);
        btnHacerCita.setPreferredSize(new java.awt.Dimension(150, 40));
        btnHacerCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHacerCitaActionPerformed(evt);
            }
        });

        btnAgenda.setBackground(new java.awt.Color(76, 201, 223));
        btnAgenda.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnAgenda.setForeground(new java.awt.Color(0, 0, 0));
        btnAgenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Agenda 32.png"))); // NOI18N
        btnAgenda.setText("Ver Agenda");
        btnAgenda.setPreferredSize(new java.awt.Dimension(150, 40));

        btnTerminar.setBackground(new java.awt.Color(76, 201, 223));
        btnTerminar.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnTerminar.setForeground(new java.awt.Color(0, 0, 0));
        btnTerminar.setText("Terminar y Facturar");
        btnTerminar.setEnabled(false);
        btnTerminar.setPreferredSize(new java.awt.Dimension(150, 40));
        btnTerminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminarActionPerformed(evt);
            }
        });

        btnMenuTratamiento.setBackground(new java.awt.Color(76, 201, 223));
        btnMenuTratamiento.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        btnMenuTratamiento.setForeground(new java.awt.Color(0, 0, 0));
        btnMenuTratamiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Menu.png"))); // NOI18N
        btnMenuTratamiento.setText(" Volver al Menú");
        btnMenuTratamiento.setPreferredSize(new java.awt.Dimension(150, 50));
        btnMenuTratamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuTratamientoActionPerformed(evt);
            }
        });

        cbHora.setPreferredSize(new java.awt.Dimension(30, 30));

        fechapicker.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHacerCita, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMenuTratamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbHora, javax.swing.GroupLayout.Alignment.LEADING, 0, 196, Short.MAX_VALUE)
                                .addComponent(fechapicker, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(fechapicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(cbHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHacerCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnMenuTratamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        btnReceta.setBackground(new java.awt.Color(76, 201, 223));
        btnReceta.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnReceta.setForeground(new java.awt.Color(0, 0, 0));
        btnReceta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Nuevo Diag 32.png"))); // NOI18N
        btnReceta.setText(" Receta");
        btnReceta.setPreferredSize(new java.awt.Dimension(40, 40));
        btnReceta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecetaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelTratamientoLayout = new javax.swing.GroupLayout(PanelTratamiento);
        PanelTratamiento.setLayout(PanelTratamientoLayout);
        PanelTratamientoLayout.setHorizontalGroup(
            PanelTratamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTratamientoLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(PanelTratamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelTratamientoLayout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 837, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelTratamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnEditarMedicamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarMedicamento, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(PanelTratamientoLayout.createSequentialGroup()
                        .addGroup(PanelTratamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelTratamientoLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(PanelTratamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(PanelTratamientoLayout.createSequentialGroup()
                                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMedicamentoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelTratamientoLayout.createSequentialGroup()
                                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblPacienteTratamiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(PanelTratamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtVademecum, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                    .addComponent(btnReceta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(PanelTratamientoLayout.createSequentialGroup()
                                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDosis, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAgregarMedicamento))
                            .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelTratamientoLayout.setVerticalGroup(
            PanelTratamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTratamientoLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(PanelTratamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelTratamientoLayout.createSequentialGroup()
                        .addGroup(PanelTratamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelTratamientoLayout.createSequentialGroup()
                                .addGroup(PanelTratamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPacienteTratamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(PanelTratamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMedicamentoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PanelTratamientoLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(btnReceta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txtVademecum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelTratamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDosis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregarMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(PanelTratamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelTratamientoLayout.createSequentialGroup()
                        .addComponent(btnEliminarMedicamento)
                        .addGap(20, 20, 20)
                        .addComponent(btnEditarMedicamento)))
                .addGap(83, 83, 83))
        );

        PanelPestañas.addTab("Tratamiento", PanelTratamiento);

        jPanel2.setBackground(new java.awt.Color(253, 149, 53));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.setPreferredSize(new java.awt.Dimension(100, 40));

        jLabel15.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Consulta Médica");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 1360, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1376, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(PanelPestañas, javax.swing.GroupLayout.PREFERRED_SIZE, 1110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(PanelPestañas, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPacienteActionPerformed
        //Desabilitar botones y textbox
        this.btnSeleccionPaciente.setEnabled(false);
        this.txtDUIBusqueda.setEnabled(true);
        this.txtNombreBusqueda.setEnabled(true);
        this.tbPacientes.setEnabled(true);
        this.btnNuevoPaciente.setEnabled(true);
        //Limpieza de Textbox
        this.txtIdPaciente.setText("");
        this.txtNombrePaciente.setText("");
        this.txtApellidoPaciente.setText("");
        this.txtApellidoPaciente.setText("");
        this.txtDUIPaciente.setText("");
        this.txtDireccionPaciente.setText("");
        this.txtSexoPaciente.setText("");
        this.txtCivilPaciente.setText("");
        this.txtOcupacionPaciente.setText("");
        this.txtTelefonoPaciente.setText("");
        //Habilitar botones y Textbox
        this.btnCancelarPaciente.setEnabled(false);
        this.btnAgregarPaciente.setEnabled(false);
        this.txtNombrePaciente.setEnabled(false);
        this.txtApellidoPaciente.setEnabled(false);
        this.txtApellidoPaciente.setEnabled(false);
        this.txtDUIPaciente.setEnabled(false);
        this.txtDireccionPaciente.setEnabled(false);
        this.txtSexoPaciente.setEnabled(false);
        this.txtCivilPaciente.setEnabled(false);
        this.txtOcupacionPaciente.setEnabled(false);
        this.spFechaPaciente.setEnabled(false);
        this.txtTelefonoPaciente.setEnabled(false);
    }//GEN-LAST:event_btnCancelarPacienteActionPerformed

    private void btnNuevoPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoPacienteActionPerformed
        //Desabilitar botones y textbox
        this.btnSeleccionPaciente.setEnabled(false);
        this.txtDUIBusqueda.setEnabled(false);
        this.txtNombreBusqueda.setEnabled(false);
        this.txtNombreBusqueda.setEnabled(false);
        this.tbPacientes.setEnabled(false);
        this.btnNuevoPaciente.setEnabled(false);
        //Limpieza de Textbox
        this.txtIdPaciente.setText("");
        this.txtNombrePaciente.setText("");
        this.txtApellidoPaciente.setText("");
        this.txtApellidoPaciente.setText("");
        this.txtDUIPaciente.setText("");
        this.txtDireccionPaciente.setText("");
        this.txtSexoPaciente.setText("");
        this.txtCivilPaciente.setText("");
        this.txtOcupacionPaciente.setText("");
        this.txtTelefonoPaciente.setText("");
        //Habilitar botones y Textbox
        this.btnCancelarPaciente.setEnabled(true);
        this.btnAgregarPaciente.setEnabled(true);
        this.txtNombrePaciente.setEnabled(true);
        this.txtApellidoPaciente.setEnabled(true);
        this.txtApellidoPaciente.setEnabled(true);
        this.txtDUIPaciente.setEnabled(true);
        this.txtDireccionPaciente.setEnabled(true);
        this.txtSexoPaciente.setEnabled(true);
        this.txtCivilPaciente.setEnabled(true);
        this.txtOcupacionPaciente.setEnabled(true);
        this.spFechaPaciente.setEnabled(true);
        this.txtTelefonoPaciente.setEnabled(true);
        txtNombrePaciente.requestFocus();
    }//GEN-LAST:event_btnNuevoPacienteActionPerformed

    private void tbPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacientesMouseClicked
        txtIdPaciente.setText(tbPacientes.getValueAt(tbPacientes.getSelectedRow(), 0).toString());
        txtNombrePaciente.setText(tbPacientes.getValueAt(tbPacientes.getSelectedRow(), 1).toString());
        txtApellidoPaciente.setText(tbPacientes.getValueAt(tbPacientes.getSelectedRow(), 2).toString());
        spFechaPaciente.setValue(tbPacientes.getValueAt(tbPacientes.getSelectedRow(), 3));
        txtDUIPaciente.setText(tbPacientes.getValueAt(tbPacientes.getSelectedRow(), 4).toString());
        txtSexoPaciente.setText(tbPacientes.getValueAt(tbPacientes.getSelectedRow(), 5).toString());
        txtOcupacionPaciente.setText(tbPacientes.getValueAt(tbPacientes.getSelectedRow(), 6).toString());
        txtDireccionPaciente.setText(tbPacientes.getValueAt(tbPacientes.getSelectedRow(), 7).toString());
        txtCivilPaciente.setText(tbPacientes.getValueAt(tbPacientes.getSelectedRow(), 8).toString());
        txtTelefonoPaciente.setText(tbPacientes.getValueAt(tbPacientes.getSelectedRow(), 9).toString());
        if (txtIdPaciente.getText().equals("")) {
            this.btnSeleccionPaciente.setEnabled(false);
        } else {
            this.btnSeleccionPaciente.setEnabled(true);
        }
    }//GEN-LAST:event_tbPacientesMouseClicked

    private void btnAgregarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPacienteActionPerformed
        if (txtNombrePaciente.getText().equals("") || txtDUIPaciente.getText().equals("") || txtSexoPaciente.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Los campos de Nombre, DUI y sexp no pueden estar vacíos", "Error, campos vacíos", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                int mensaje = JOptionPane.showConfirmDialog(null, "¿Están sus datos correctos?", "Agregar paciente",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (mensaje == 0) {

                } else {
                }
                Paciente P = new Paciente();
                char caracterSexo = txtSexoPaciente.getText().charAt(0);
                char caracterEstado = txtCivilPaciente.getText().charAt(0);

                SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
                String spinnerValue = formater.format(this.spFechaPaciente.getValue());
                Date date = formater.parse(spinnerValue);

                P.setSexo(caracterSexo);
                P.setNombres(this.txtNombrePaciente.getText());
                P.setApellidos(this.txtApellidoPaciente.getText());
                P.setDui(this.txtDUIPaciente.getText().toString());
                P.setFechaNacimiento(date);
                P.setOcupacion(this.txtOcupacionPaciente.getText());
                P.setDireccion(this.txtDireccionPaciente.getText());
                P.setEstadoCivil(caracterEstado);
                P.setTelefono(this.txtTelefonoPaciente.getText());

                PC.create(P);
                JOptionPane.showMessageDialog(null, "Datos Guardados Exitosamente");
                CrearModeloPacientes();
                //Desabilitar botones y textbox
                this.btnSeleccionPaciente.setEnabled(false);
                this.txtDUIBusqueda.setEnabled(true);
                this.txtNombreBusqueda.setEnabled(true);
                this.tbPacientes.setEnabled(true);
                this.btnNuevoPaciente.setEnabled(true);
                //Limpieza de Textbox
                this.txtIdPaciente.setText("");
                this.txtNombrePaciente.setText("");
                this.txtApellidoPaciente.setText("");
                this.txtApellidoPaciente.setText("");
                this.txtDUIPaciente.setText("");
                this.txtDireccionPaciente.setText("");
                this.txtSexoPaciente.setText("");
                this.txtCivilPaciente.setText("");
                this.txtOcupacionPaciente.setText("");
                this.txtTelefonoPaciente.setText("");
                //Habilitar botones y Textbox
                this.btnCancelarPaciente.setEnabled(false);
                this.btnAgregarPaciente.setEnabled(false);
                this.txtNombrePaciente.setEnabled(false);
                this.txtApellidoPaciente.setEnabled(false);
                this.txtApellidoPaciente.setEnabled(false);
                this.txtDUIPaciente.setEnabled(false);
                this.txtDireccionPaciente.setEnabled(false);
                this.txtSexoPaciente.setEnabled(false);
                this.txtCivilPaciente.setEnabled(false);
                this.txtOcupacionPaciente.setEnabled(false);
                this.spFechaPaciente.setEnabled(false);
                this.txtTelefonoPaciente.setEnabled(false);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

    }//GEN-LAST:event_btnAgregarPacienteActionPerformed

    private void txtDUIBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDUIBusquedaKeyReleased
        CrearModeloPacientes();
        CargarTablaPacientesporDui(txtDUIBusqueda.getText().toString());
    }//GEN-LAST:event_txtDUIBusquedaKeyReleased

    private void btnSeleccionPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionPacienteActionPerformed

        try {
            //Cargar todos los datos de Paciente en un objeto del mismo tipo
            String id = this.txtIdPaciente.getText();
            DatosPaciente = (Paciente) PC.findPaciente(Integer.parseInt(id));

            //Pestaña de Identidad
            this.lblNombreIdenti.setText(this.txtNombrePaciente.getText());
            this.lblApellidoIdenti.setText(this.txtApellidoPaciente.getText());
            this.lblDuiIdenti.setText(this.txtDUIPaciente.getText());
            this.lblTelefonoIdenti.setText(this.txtTelefonoPaciente.getText());
            this.lblCivilIdenti.setText(this.txtCivilPaciente.getText());
            this.lblDireccionIdenti.setText(this.txtDireccionPaciente.getText());
            this.lblSexoIdenti.setText(this.txtSexoPaciente.getText());

            //Poner nombre de paciente a todas las pestañas
            String Paciente = this.txtApellidoPaciente.getText() + ", " + this.txtNombrePaciente.getText();
            this.lblPacienteAnte.setText(Paciente);
            this.lblPacienteConsulta.setText(Paciente);
            this.lblPacienteDiagnostico.setText(Paciente);
            this.lblPacienteFisica.setText(Paciente);
            this.lblPacienteTratamiento.setText(Paciente);
            this.lblPacienteExamen.setText(Paciente);

            //Calcula la edad y da formato a la fecha de nacimiento
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String fechaconformato = formato.format((Date) this.spFechaPaciente.getValue());
            this.lblFechaIdenti1.setText(fechaconformato);
            SimpleDateFormat years = new SimpleDateFormat("yyyy");
            String fechaYears = years.format((Date) this.spFechaPaciente.getValue());
            this.lblEdad.setText(CalcularEdad(fechaYears) + " AÑOS");

            //Carga la tabla de la pestaña de Antecedentes
            CrearModeloAntecedentes();
            CargarTablaAntecedentes();

            //Calcula IMC en estado normal
            this.lblIMC.setText("0");

            //Pasa a la pestaña de Identidad
            this.PanelPestañas.setSelectedIndex(1);

            //Busca la ultima consulta
            List<Consulta> listconsulta = CC.findbyIdPacienteDESC(DatosPaciente);
            if (listconsulta.size() > 0) {
                int idConsulta = listconsulta.get(0).getIdConsulta();
                UltimaConsulta = (Consulta) CC.findConsulta(idConsulta);
                this.lblUltimoMotivo.setText(UltimaConsulta.getMotivo());
                SimpleDateFormat formatoConsulta = new SimpleDateFormat("dd/MM/yyyy");
                String fechaConsulta = formatoConsulta.format(UltimaConsulta.getFechaConsulta());
                this.lblFechaUltima.setText(fechaConsulta);
            }
            btnTerminar.setEnabled(true);
            btnHacerCita.setEnabled(true);
            btnHistorial.setEnabled(true);
            btnReporteAntecedentes.setEnabled(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_btnSeleccionPacienteActionPerformed

    private void txtNombreBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreBusquedaKeyReleased
        CrearModeloPacientes();
        CargarTablaPacientesporNombre(this.txtNombreBusqueda.getText());
    }//GEN-LAST:event_txtNombreBusquedaKeyReleased

    private void btnMenuPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuPacienteActionPerformed
        //Validación de regreso
        int mensaje = JOptionPane.showConfirmDialog(null, "¿Realmente desea regresar al menú principal?"
                + "Se descartarán los datos no guardados.", "Regresar al menú",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (mensaje == 0) {
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
            this.dispose();
        } else {

        }
    }//GEN-LAST:event_btnMenuPacienteActionPerformed

    private void btnMenuIdentiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuIdentiActionPerformed
        //Validación de regreso
        int mensaje = JOptionPane.showConfirmDialog(null, "¿Realmente desea regresar al menú principal?"
                + "Se descartarán los datos no guardados.", "Regresar al menú",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (mensaje == 0) {
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
            this.dispose();
        } else {

        }
    }//GEN-LAST:event_btnMenuIdentiActionPerformed

    private void btnMenuConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuConsultaActionPerformed
        //Validación de regreso
        int mensaje = JOptionPane.showConfirmDialog(null, "¿Realmente desea regresar al menú principal?"
                + "Se descartarán los datos no guardados.", "Regresar al menú",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (mensaje == 0) {
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
            this.dispose();
        } else {

        }
    }//GEN-LAST:event_btnMenuConsultaActionPerformed

    private void btnMenuFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuFisicaActionPerformed
        //Validación de regreso
        int mensaje = JOptionPane.showConfirmDialog(null, "¿Realmente desea regresar al menú principal?"
                + "Se descartarán los datos no guardados.", "Regresar al menú",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (mensaje == 0) {
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
            this.dispose();
        } else {

        }
    }//GEN-LAST:event_btnMenuFisicaActionPerformed

    private void btnMenuFisica1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuFisica1ActionPerformed
        //Validación de regreso
        int mensaje = JOptionPane.showConfirmDialog(null, "¿Realmente desea regresar al menú principal?"
                + "Se descartarán los datos no guardados.", "Regresar al menú",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (mensaje == 0) {
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
            this.dispose();
        } else {

        }
    }//GEN-LAST:event_btnMenuFisica1ActionPerformed

    private void btnSiguienteFisica1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteFisica1ActionPerformed
        this.PanelPestañas.setSelectedIndex(6);

    }//GEN-LAST:event_btnSiguienteFisica1ActionPerformed

    private void btnMenuTratamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuTratamientoActionPerformed
        //Validación de regreso
        int mensaje = JOptionPane.showConfirmDialog(null, "¿Realmente desea regresar al menú principal?"
                + "Se descartarán los datos no guardados.", "Regresar al menú",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (mensaje == 0) {
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
            this.dispose();
        } else {

        }
    }//GEN-LAST:event_btnMenuTratamientoActionPerformed

    private void btnSiguienteIdentiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteIdentiActionPerformed
        this.PanelPestañas.setSelectedIndex(2);
    }//GEN-LAST:event_btnSiguienteIdentiActionPerformed

    private void btnCancelarIdentiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarIdentiActionPerformed
        this.PanelPestañas.setSelectedIndex(0);
    }//GEN-LAST:event_btnCancelarIdentiActionPerformed

    private void btnSiguienteConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteConsultaActionPerformed
        if (txtMotivoConsulta.getText().equals("") || txtPresenteConsulta.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ingrese motivo de consulta y presente enfermedad", "Campos vacíos", JOptionPane.INFORMATION_MESSAGE);
        } else {
            this.PanelPestañas.setSelectedIndex(3);
            txtPresion.requestFocus();
        }
    }//GEN-LAST:event_btnSiguienteConsultaActionPerformed

    private void btnCancelarCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCancelarActionPerformed
        this.PanelPestañas.setSelectedIndex(2);
    }//GEN-LAST:event_btnCancelarCancelarActionPerformed

    private void btnSiguienteFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteFisicaActionPerformed
        if (txtPresion.getText().isEmpty() || txtPulso.getText().isEmpty() || txtFCC.getText().isEmpty() || txtFR.getText().isEmpty()
                || txtTemperatura.getText().isEmpty() || txtMasa.getText().isEmpty() || txtTalla.getText().isEmpty()) {
            int resp = JOptionPane.showConfirmDialog(null, "Aún tiene campos vacíos ¿Desea continuar?", "Datos sin ingresar", JOptionPane.YES_NO_OPTION);
            if (JOptionPane.YES_OPTION == resp) {
                this.PanelPestañas.setSelectedIndex(5);
                txtEnfermedad.requestFocus();
            } else {

            }
        }
    }//GEN-LAST:event_btnSiguienteFisicaActionPerformed

    private void btnCancelarFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarFisicaActionPerformed
        this.PanelPestañas.setSelectedIndex(3);
    }//GEN-LAST:event_btnCancelarFisicaActionPerformed

    private void txtMasaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMasaKeyReleased
        if (this.txtTalla.getText().isEmpty()) {
            if (this.txtMasa.getText().isEmpty()) {
                this.lblIMC.setText("Ingresar Masa y Talla");
            } else {
                this.lblIMC.setText("Ingresar Talla");
            }
        } else {
            if (this.txtMasa.getText().isEmpty()) {
                this.lblIMC.setText("Ingresar Masa");
            } else {
                this.lblIMC.setText(CalcularIMC(Double.parseDouble(this.txtMasa.getText()), Double.parseDouble(this.txtTalla.getText())));
            }
        }
    }//GEN-LAST:event_txtMasaKeyReleased

    private void txtTallaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaKeyReleased
        if (this.txtTalla.getText().isEmpty()) {
            if (this.txtMasa.getText().isEmpty()) {
                this.lblIMC.setText("Ingresar Masa y Talla");
            } else {
                this.lblIMC.setText("Ingresar Talla");
            }
        } else {
            if (this.txtMasa.getText().isEmpty()) {
                this.lblIMC.setText("Ingresar Masa");
            } else {
                this.lblIMC.setText(CalcularIMC(Double.parseDouble(this.txtMasa.getText()), Double.parseDouble(this.txtTalla.getText())));
            }
        }
    }//GEN-LAST:event_txtTallaKeyReleased

    private void txtMasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMasaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMasaActionPerformed

    private void txtEnfermedadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnfermedadKeyReleased

    }//GEN-LAST:event_txtEnfermedadKeyReleased

    private void tbEnfermedadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEnfermedadesMouseClicked
        this.txtAgregarDiagnostico.setEnabled(true);
        this.txtObservacionesDiagnostico.setEnabled(true);
    }//GEN-LAST:event_tbEnfermedadesMouseClicked

    private void txtAgregarDiagnosticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgregarDiagnosticoActionPerformed
        try {
            DetalleDiagnostico DD = new DetalleDiagnostico();
            String id = tbEnfermedades.getValueAt(tbEnfermedades.getSelectedRow(), 0).toString();
            AgregarEnfermedad = (EnfermedadesCie10) EC.findEnfermedadesCie10(Integer.parseInt(id));
            DD.setIdDiagnostico(DatosDiagnostico);
            DD.setIdEnfermedad(AgregarEnfermedad);
            DD.setDescripcion(this.txtObservacionesDiagnostico.getText());

            DC.create(DD);

            CrearModeloEnfermedades();
            CrearModeloDiagnostico();
            CargarTablaDiagnostico();

            this.txtAgregarDiagnostico.setEnabled(false);
            this.txtObservacionesDiagnostico.setEnabled(true);
            this.txtObservacionesDiagnostico.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtAgregarDiagnosticoActionPerformed

    private void tbDiagnosticoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagnosticoMouseClicked
        this.btnEliminarDiagnostico.setEnabled(true);
        btnEditarDiagnostico.setEnabled(true);
    }//GEN-LAST:event_tbDiagnosticoMouseClicked

    private void btnEliminarDiagnosticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDiagnosticoActionPerformed
        int resp = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar este registro?", "Eliminar Registro", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.YES_OPTION == resp) {
            try {
                EliminarDetalle = (DetalleDiagnostico) DC.findDetalleDiagnostico(Integer.parseInt(this.tbDiagnostico.getValueAt(tbDiagnostico.getSelectedRow(), 0).toString()));
                DC.destroy(EliminarDetalle.getIdDetalleDiagnostico());
                JOptionPane.showMessageDialog(null, "El registro fue eliminado con Exito");

                CrearModeloDiagnostico();
                CargarTablaDiagnostico();

                this.btnEliminarDiagnostico.setEnabled(false);
                btnEditarDiagnostico.setEnabled(false);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Seleccione Registro a Eliminar");
            }
        }
    }//GEN-LAST:event_btnEliminarDiagnosticoActionPerformed

    private void btnbuscarporCateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarporCateActionPerformed
        this.CrearModeloEnfermedades();
        this.CargarTablaPorCategoria(this.txtEnfermedad.getText());
    }//GEN-LAST:event_btnbuscarporCateActionPerformed

    private void txtEnfermedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnfermedadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnfermedadActionPerformed

    private void btnBuscarEnfermedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEnfermedadActionPerformed
        this.CrearModeloEnfermedades();
        this.CargarTablaPorEnfermedad(this.txtEnfermedad.getText());
    }//GEN-LAST:event_btnBuscarEnfermedadActionPerformed

    private void btnSiguienteExamenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteExamenActionPerformed
        //Orden de Pestañas
        this.PanelPestañas.setSelectedIndex(7);

    }//GEN-LAST:event_btnSiguienteExamenActionPerformed

    private void btnMenuExamenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuExamenActionPerformed
        //Validación de regreso
        int mensaje = JOptionPane.showConfirmDialog(null, "¿Realmente desea regresar al menú principal?"
                + "Se descartarán los datos no guardados.", "Regresar al menú",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (mensaje == 0) {
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
            this.dispose();
        } else {

        }
    }//GEN-LAST:event_btnMenuExamenActionPerformed

    private void txtMedicamentoBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMedicamentoBusquedaKeyReleased
        this.CrearModeloVademecum();
        this.CargarTablaVademecum(this.txtMedicamentoBusqueda.getText());
    }//GEN-LAST:event_txtMedicamentoBusquedaKeyReleased

    private void tbVademecumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVademecumMouseClicked

    }//GEN-LAST:event_tbVademecumMouseClicked

    private void btnAgregarMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMedicamentoActionPerformed
        if (txtCantidad.getText().equals("") || txtDosis.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Los campos de dosis y cantidad no pueden estar vacíos", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                DetalleReceta DD = new DetalleReceta();
                String id = tbVademecum.getValueAt(tbVademecum.getSelectedRow(), 0).toString();
                Insumo = (Vademecum) VC.findVademecum(Integer.parseInt(id));
                DD.setIdInsumo(Insumo);
                DD.setNumReceta(DatosReceta);
                DD.setCantidad(Integer.parseInt(this.txtCantidad.getText()));
                DD.setDosis(this.txtDosis.getText());

                DRC.create(DD);

                CrearModeloReceta();
                CrearModeloVademecum();
                CargarTablaReceta();

                this.txtDosis.setText("");
                this.txtCantidad.setText("");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btnAgregarMedicamentoActionPerformed

    private void tbRecetaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRecetaMouseClicked
        this.txtCantidad.setText(tbReceta.getValueAt(tbReceta.getSelectedRow(), 2).toString());
        this.txtDosis.setText(tbReceta.getValueAt(tbReceta.getSelectedRow(), 3).toString());

        String id = tbReceta.getValueAt(tbReceta.getSelectedRow(), 0).toString();
        EditarDetalleReceta = (DetalleReceta) DRC.findDetalleReceta(Integer.parseInt(id));
        EliminarDetalleReceta = (DetalleReceta) DRC.findDetalleReceta(Integer.parseInt(id));

        this.btnEditarMedicamento.setEnabled(true);
        this.btnEliminarMedicamento.setEnabled(true);
        this.txtDosis.setEnabled(true);
        this.txtCantidad.setEnabled(true);
    }//GEN-LAST:event_tbRecetaMouseClicked

    private void btnEliminarMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarMedicamentoActionPerformed
        int resp = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar este medicamento?", "Eliminar Medicamento", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.YES_OPTION == resp) {
            try {
                DRC.destroy(EliminarDetalleReceta.getDetalleReceta());
                JOptionPane.showMessageDialog(null, "El registro fue eliminado con Exito");
                CrearModeloReceta();
                CargarTablaReceta();
                this.btnAgregarMedicamento.setEnabled(false);
                this.btnEliminarMedicamento.setEnabled(false);
                this.btnEditarMedicamento.setEnabled(false);
                this.txtDosis.setText("");
                this.txtCantidad.setText("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_btnEliminarMedicamentoActionPerformed

    private void btnEditarMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarMedicamentoActionPerformed
        try {
            EditarDetalleReceta.setCantidad(Integer.parseInt(this.txtCantidad.getText()));
            EditarDetalleReceta.setDosis(this.txtDosis.getText());
            DRC.edit(EditarDetalleReceta);
            JOptionPane.showMessageDialog(null, "El registro fue editado con Éxito");
            CrearModeloReceta();
            CargarTablaReceta();

            this.btnAgregarMedicamento.setEnabled(false);
            this.btnEliminarMedicamento.setEnabled(false);
            this.btnEditarMedicamento.setEnabled(false);
            this.txtDosis.setText("");
            this.txtCantidad.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnEditarMedicamentoActionPerformed

    private void btnHacerCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHacerCitaActionPerformed
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
            List<Citas> listCitas = CitasC.findbyUsuarioandFechaconHora(Login.DatosUsuario, datesql);
            if (listCitas.isEmpty()) {
                DatosPaciente = (Paciente) PC.findPaciente(Integer.parseInt(tbPacientes.getValueAt(tbPacientes.getSelectedRow(), 0).toString()));
                C.setIdPaciente(DatosPaciente);
                C.setIdUsuario(Login.DatosUsuario);
                C.setFechaCita(date);
                CitasC.create(C);
                JOptionPane.showMessageDialog(null, "Cita Creada para el día " + spinnerValue);
            } else {
                JOptionPane.showMessageDialog(null, "Ya existe una cita creada: " + listCitas.get(0).getIdPaciente().getApellidos().concat(", " + listCitas.get(0).getIdPaciente().getNombres() + " a esta hora y fecha"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnHacerCitaActionPerformed

    private void btnElminarAnteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElminarAnteActionPerformed
        int resp = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar este registro?", "Eliminar Registro", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.YES_OPTION == resp) {
            try {
                AC.destroy(EliminarAntecedente.getIdAntecedentes());
                JOptionPane.showMessageDialog(null, "El registro fue eliminado con Exito");
                CrearModeloAntecedentes();
                CargarTablaAntecedentes();
                this.btnAgregarAnte.setEnabled(false);
                this.btnCancelarAnte.setEnabled(false);
                this.btnNuevoAnte.setEnabled(true);
                this.btnEditarAnte.setEnabled(false);
                this.btnElminarAnte.setEnabled(false);
                this.txtFamiliarAnte.setEnabled(false);
                this.txtPersonalAnte.setEnabled(false);

                this.txtFamiliarAnte.setText("");
                this.txtPersonalAnte.setText("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else {

        }
    }//GEN-LAST:event_btnElminarAnteActionPerformed

    private void btnEditarAnteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarAnteActionPerformed
        int resp = JOptionPane.showConfirmDialog(null, "¿Están todos los datos correctos?", "Registrar antecedentes", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.YES_OPTION == resp) {
            try {
                EditarAntecedente.setFamiliares(this.txtFamiliarAnte.getText());
                EditarAntecedente.setPersonales(this.txtPersonalAnte.getText());
                AC.edit(EditarAntecedente);
                JOptionPane.showMessageDialog(null, "El registro fue editado con Éxito");
                CrearModeloAntecedentes();
                CargarTablaAntecedentes();
                this.btnAgregarAnte.setEnabled(false);
                this.btnCancelarAnte.setEnabled(false);
                this.btnNuevoAnte.setEnabled(true);
                this.btnEditarAnte.setEnabled(false);
                this.btnElminarAnte.setEnabled(false);
                this.txtFamiliarAnte.setEnabled(false);
                this.txtPersonalAnte.setEnabled(false);
                this.txtFamiliarAnte.setText("");
                this.txtPersonalAnte.setText("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else {
        }
    }//GEN-LAST:event_btnEditarAnteActionPerformed

    private void btnAgregarAnteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarAnteActionPerformed
        int resp = JOptionPane.showConfirmDialog(null, "¿Están todos los datos correctos?", "Registrar antecedentes", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.YES_OPTION == resp) {
            try {

                Antecedentes A = new Antecedentes();
                A.setIdPaciente(DatosPaciente);
                A.setFamiliares(this.txtFamiliarAnte.getText());
                A.setPersonales(this.txtPersonalAnte.getText());
                AC.create(A);
                CrearModeloAntecedentes();
                CargarTablaAntecedentes();
                this.btnAgregarAnte.setEnabled(false);
                this.btnCancelarAnte.setEnabled(false);
                this.btnNuevoAnte.setEnabled(true);
                this.btnEditarAnte.setEnabled(false);
                this.btnElminarAnte.setEnabled(false);
                this.txtFamiliarAnte.setEnabled(false);
                this.txtPersonalAnte.setEnabled(false);

                this.txtFamiliarAnte.setText("");
                this.txtPersonalAnte.setText("");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else {
        }

    }//GEN-LAST:event_btnAgregarAnteActionPerformed

    private void btnCancelarAnteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarAnteActionPerformed
        this.btnAgregarAnte.setEnabled(false);
        this.btnCancelarAnte.setEnabled(false);
        this.btnNuevoAnte.setEnabled(true);
        this.btnEditarAnte.setEnabled(false);
        this.btnElminarAnte.setEnabled(false);
        this.txtFamiliarAnte.setEnabled(false);
        this.txtPersonalAnte.setEnabled(false);

        this.txtFamiliarAnte.setText("");
        this.txtPersonalAnte.setText("");
    }//GEN-LAST:event_btnCancelarAnteActionPerformed

    private void btnNuevoAnteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoAnteActionPerformed
        this.btnAgregarAnte.setEnabled(true);
        this.btnCancelarAnte.setEnabled(true);
        this.btnNuevoAnte.setEnabled(false);
        this.btnEditarAnte.setEnabled(false);
        this.btnElminarAnte.setEnabled(false);
        this.txtFamiliarAnte.setEnabled(true);
        this.txtPersonalAnte.setEnabled(true);

        this.txtFamiliarAnte.setText("");
        this.txtPersonalAnte.setText("");
    }//GEN-LAST:event_btnNuevoAnteActionPerformed

    private void tbAntecendentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAntecendentesMouseClicked
        this.txtFamiliarAnte.setText(tbAntecendentes.getValueAt(tbAntecendentes.getSelectedRow(), 2).toString());
        this.txtPersonalAnte.setText(tbAntecendentes.getValueAt(tbAntecendentes.getSelectedRow(), 3).toString());

        String id = tbAntecendentes.getValueAt(tbAntecendentes.getSelectedRow(), 0).toString();
        EditarAntecedente = (Antecedentes) AC.findAntecedentes(Integer.parseInt(id));
        EliminarAntecedente = (Antecedentes) AC.findAntecedentes(Integer.parseInt(id));

        this.btnEditarAnte.setEnabled(true);
        this.btnElminarAnte.setEnabled(true);
        this.txtFamiliarAnte.setEnabled(true);
        this.txtPersonalAnte.setEnabled(true);
    }//GEN-LAST:event_tbAntecendentesMouseClicked

    private void btnMenuAnteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuAnteActionPerformed
        //Validación de regreso
        int mensaje = JOptionPane.showConfirmDialog(null, "¿Realmente desea regresar al menú principal?"
                + "Se descartarán los datos no guardados.", "Regresar al menú",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (mensaje == 0) {
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
            this.dispose();
        } else {

        }
    }//GEN-LAST:event_btnMenuAnteActionPerformed

    private void btnCancelarL2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarL2ActionPerformed
        this.PanelPestañas.setSelectedIndex(1);
    }//GEN-LAST:event_btnCancelarL2ActionPerformed

    private void btnSiguienteAnteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteAnteActionPerformed
        this.PanelPestañas.setSelectedIndex(4);


    }//GEN-LAST:event_btnSiguienteAnteActionPerformed

    private void txtVademecumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVademecumActionPerformed
        ReporteVademecum menu = new ReporteVademecum();
        menu.setVisible(true);
    }//GEN-LAST:event_txtVademecumActionPerformed

    private void btnTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminarActionPerformed
        if (txtPresion.getText().isEmpty() || txtPulso.getText().isEmpty() || txtFCC.getText().isEmpty() || txtFR.getText().isEmpty()
                || txtTemperatura.getText().isEmpty() || txtMasa.getText().isEmpty() || txtTalla.getText().isEmpty()) {
            int resp = JOptionPane.showConfirmDialog(null, "Tiene datos sin ingresar en Examen Fisico, si no los ingresa su valor sera de 0 ¿Desea continuar?", "Datos sin ingresar", JOptionPane.YES_NO_OPTION);
            if (JOptionPane.YES_OPTION == resp) {
                if (txtPresion.getText().isEmpty()) {
                    txtPresion.setText("0");
                }
                if (txtPulso.getText().isEmpty()) {
                    txtPulso.setText("0");
                }
                if (txtFCC.getText().isEmpty()) {
                    txtFCC.setText("0");
                }
                if (txtFR.getText().isEmpty()) {
                    txtFR.setText("0");
                }
                if (txtTemperatura.getText().isEmpty()) {
                    txtTemperatura.setText("0");
                }
                if (txtMasa.getText().isEmpty()) {
                    txtMasa.setText("0");
                }
                if (txtTalla.getText().isEmpty()) {
                    txtTalla.setText("0");
                }
                try {
                    //Agregar Una nueva consulta
                    Consulta C = new Consulta();
                    Login login = new Login();
                    SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
                    String spinnerValue = formater.format(this.spFechaConsulta.getValue());
                    Date date = formater.parse(spinnerValue);

                    //Guardamos datos de Consulta
                    C.setExamenFisico(this.txtFisico.getText());
                    C.setFechaConsulta(date);
                    C.setFrecuenciacardiaca(Integer.parseInt(this.txtFCC.getText()));
                    C.setFrecuenciarespiratoria(Integer.parseInt(this.txtFR.getText()));
                    C.setIdPaciente(DatosPaciente);
                    C.setIdUsuario(login.DatosUsuario);
                    C.setImc(Double.parseDouble(this.lblIMC.getText()));
                    C.setMotivo(this.txtMotivoConsulta.getText());
                    C.setObservaciones(this.txtObservacionesIdenti.getText());
                    C.setPeso(Double.parseDouble(this.txtMasa.getText()));
                    C.setPresenteEnfermedad(this.txtPresenteConsulta.getText());
                    C.setPresion(this.txtPresion.getText());
                    C.setPulso(Integer.parseInt(this.txtPulso.getText()));
                    C.setTalla(Double.parseDouble(this.txtTalla.getText()));
                    C.setTemperatura(Double.parseDouble(this.txtTemperatura.getText()));

                    CC.create(C);

                    //Obtiene la ultima consulta del paciente
                    List<Consulta> listconsulta = CC.findbyIdPacienteDESC(DatosPaciente);
                    int idConsulta = listconsulta.get(0).getIdConsulta();
                    DatosConsulta = (Consulta) CC.findConsulta(idConsulta);
                    boolean btnrecet = btnReceta.isEnabled();
                    boolean btndiag = btnDiagnostico.isEnabled();
                    if (btnrecet == false) {
                        //Obtener Numero de Receta creada
                        int idReceta = DatosReceta.getNumReceta();
                        DatosReceta = (Receta) RC.findReceta(idReceta);
                        //Guardar Datos de Receta
                        DatosReceta.setIndicaciones(this.txtExamenes.getText());
                        DatosReceta.setIdConsulta(DatosConsulta);
                        RC.edit(DatosReceta);
                    }
                    if (btndiag == false) {
                        //Obtener Numero de Diagnostico Creado
                        int idDiagnostico = DatosDiagnostico.getIdDiagnostico();
                        DatosDiagnostico = (Diagnostico) DGC.findDiagnostico(idDiagnostico);
                        //Guardar Datos de Diagnostico                   
                        DatosDiagnostico.setIdConsultas(DatosConsulta);
                        DGC.edit(DatosDiagnostico);
                    }

                    JOptionPane.showMessageDialog(null, "Datos de Consulta Guardados Exitosamente");
                    Facturar menu = new Facturar();
                    menu.setVisible(true);
                    this.setVisible(false);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            } else {
                this.PanelPestañas.setSelectedIndex(4);
            }
        } else {
            try {
                //Agregar Una nueva consulta
                Consulta C = new Consulta();
                Login login = new Login();
                SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
                String spinnerValue = formater.format(this.spFechaConsulta.getValue());
                Date date = formater.parse(spinnerValue);

                //Guardamos datos de Consulta
                C.setExamenFisico(this.txtFisico.getText());
                C.setFechaConsulta(date);
                C.setFrecuenciacardiaca(Integer.parseInt(this.txtFCC.getText()));
                C.setFrecuenciarespiratoria(Integer.parseInt(this.txtFR.getText()));
                C.setIdPaciente(DatosPaciente);
                C.setIdUsuario(login.DatosUsuario);
                C.setImc(Double.parseDouble(this.lblIMC.getText()));
                C.setMotivo(this.txtMotivoConsulta.getText());
                C.setObservaciones(this.txtObservacionesIdenti.getText());
                C.setPeso(Double.parseDouble(this.txtMasa.getText()));
                C.setPresenteEnfermedad(this.txtPresenteConsulta.getText());
                C.setPresion(this.txtPresion.getText());
                C.setPulso(Integer.parseInt(this.txtPulso.getText()));
                C.setTalla(Double.parseDouble(this.txtTalla.getText()));
                C.setTemperatura(Double.parseDouble(this.txtTemperatura.getText()));

                CC.create(C);

                //Obtiene la ultima consulta del paciente
                List<Consulta> listconsulta = CC.findbyIdPacienteDESC(DatosPaciente);
                int idConsulta = listconsulta.get(0).getIdConsulta();
                DatosConsulta = (Consulta) CC.findConsulta(idConsulta);
                boolean btnrecet = btnReceta.isEnabled();
                boolean btndiag = btnDiagnostico.isEnabled();
                if (btnrecet == false) {
                    //Obtener Numero de Receta creada
                    int idReceta = DatosReceta.getNumReceta();
                    DatosReceta = (Receta) RC.findReceta(idReceta);
                    //Guardar Datos de Receta
                    DatosReceta.setIndicaciones(this.txtExamenes.getText());
                    DatosReceta.setIdConsulta(DatosConsulta);
                    RC.edit(DatosReceta);
                }
                if (btndiag == false) {
                    //Obtener Numero de Diagnostico Creado
                    int idDiagnostico = DatosDiagnostico.getIdDiagnostico();
                    DatosDiagnostico = (Diagnostico) DGC.findDiagnostico(idDiagnostico);
                    //Guardar Datos de Diagnostico                   
                    DatosDiagnostico.setIdConsultas(DatosConsulta);
                    DGC.edit(DatosDiagnostico);
                }

                JOptionPane.showMessageDialog(null, "Datos de Consulta Guardados Exitosamente");
                Facturar menu = new Facturar();
                menu.setVisible(true);
                this.setVisible(false);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

    }//GEN-LAST:event_btnTerminarActionPerformed

    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed
        try {
            Conexion con = new Conexion();
            Connection conn = con.getConexion();
            int id = DatosPaciente.getIdPaciente();
            String path = "src\\Reportes\\HistorialClinico.jasper";
            JasperReport reporte = null;
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            Map parametro = new HashMap();
            parametro.put("idPaciente", id);
            JasperPrint j = JasperFillManager.fillReport(reporte, parametro, conn);
            JasperViewer jv = new JasperViewer(j, false);
            jv.setTitle("Historial Clinico/ Clinica Arevalo");
            jv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jv.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }//GEN-LAST:event_btnHistorialActionPerformed

    private void btnRecetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecetaActionPerformed
        try {
            Receta R = new Receta();
            RC.create(R);
            //Obtener Numero de Receta creado
            List<Receta> listreceta = RC.findbyIdDESC();
            int idReceta = listreceta.get(0).getNumReceta();
            DatosReceta = (Receta) RC.findReceta(idReceta);
            this.btnAgregarMedicamento.setEnabled(true);
            this.txtDosis.setEnabled(true);
            this.txtCantidad.setEnabled(true);
            this.btnReceta.setEnabled(false);
            txtMedicamentoBusqueda.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnRecetaActionPerformed

    private void btnDiagnosticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagnosticoActionPerformed
        try {
            Diagnostico D = new Diagnostico();
            DGC.create(D);
            //Obtener Numero de Diagnostico Creado
            List<Diagnostico> listreceta = DGC.findbyIdDESC();
            int idDiagnostico = listreceta.get(0).getIdDiagnostico();
            DatosDiagnostico = (Diagnostico) DGC.findDiagnostico(idDiagnostico);
            this.txtEnfermedad.setEnabled(true);
            this.btnDiagnostico.setEnabled(false);
            btnbuscarporCate.setEnabled(true);
            btnBuscarEnfermedad.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnDiagnosticoActionPerformed

    private void txtDUIBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDUIBusquedaKeyTyped
        char car = evt.getKeyChar();
        if (Character.isDigit(car) || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_SPACE) {
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_txtDUIBusquedaKeyTyped

    private void txtNombreBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreBusquedaKeyTyped
        char car = evt.getKeyChar();
        if (Character.isLetter(car) || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_SPACE) {

        } else {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtNombreBusquedaKeyTyped

    private void txtOcupacionPacienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOcupacionPacienteKeyTyped
        char car = evt.getKeyChar();
        if (Character.isLetter(car) || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_SPACE) {
        } else {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtOcupacionPacienteKeyTyped

    private void txtNombrePacienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombrePacienteKeyTyped
        char car = evt.getKeyChar();
        if (Character.isLetter(car) || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_SPACE) {
        } else {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtNombrePacienteKeyTyped

    private void txtApellidoPacienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoPacienteKeyTyped
        char car = evt.getKeyChar();
        if (Character.isLetter(car) || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_SPACE) {
        } else {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtApellidoPacienteKeyTyped

    private void txtSexoPacienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSexoPacienteKeyTyped
        char car = evt.getKeyChar();
        if (Character.isLetter(car) || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_SPACE) {
            try {

                if (txtSexoPaciente.getText().equals("Masculino") || txtSexoPaciente.getText().equals("Femenino")) {
                    lblError.setText("");
                } else {
                    if (txtSexoPaciente.getText().equals("")) {
                        lblError.setText(CamposVacios);
                    } else {
                        lblError.setText(SexoIncorrecto);
                    }

                }
            } catch (Exception e) {

            }
        } else {
            evt.consume();
            getToolkit().beep();
        }


    }//GEN-LAST:event_txtSexoPacienteKeyTyped

    private void txtCivilPacienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCivilPacienteKeyTyped
        char car = evt.getKeyChar();
        if (Character.isLetter(car) || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_SPACE) {
        } else {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtCivilPacienteKeyTyped

    private void txtPresionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPresionKeyTyped
        char car = evt.getKeyChar();
        if ((int) evt.getKeyChar() > 32 && (int) evt.getKeyChar() <= 47 || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE
                || evt.getKeyChar() == KeyEvent.VK_SPACE || Character.isDigit(car)
                || (int) evt.getKeyChar() >= 58 && (int) evt.getKeyChar() <= 64) {
        } else {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtPresionKeyTyped

    private void txtPulsoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPulsoKeyTyped
        char car = evt.getKeyChar();
        if ((int) evt.getKeyChar() > 32 && (int) evt.getKeyChar() <= 47 || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE
                || evt.getKeyChar() == KeyEvent.VK_SPACE || Character.isDigit(car)
                || (int) evt.getKeyChar() >= 58 && (int) evt.getKeyChar() <= 64) {
        } else {
            evt.consume();
            getToolkit().beep();

        }
    }//GEN-LAST:event_txtPulsoKeyTyped

    private void txtFCCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFCCKeyTyped
        char car = evt.getKeyChar();
        if ((int) evt.getKeyChar() > 32 && (int) evt.getKeyChar() <= 47 || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE
                || evt.getKeyChar() == KeyEvent.VK_SPACE || Character.isDigit(car)
                || (int) evt.getKeyChar() >= 58 && (int) evt.getKeyChar() <= 64) {
        } else {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtFCCKeyTyped

    private void txtFRKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFRKeyTyped
        char car = evt.getKeyChar();
        if ((int) evt.getKeyChar() > 32 && (int) evt.getKeyChar() <= 47 || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE
                || evt.getKeyChar() == KeyEvent.VK_SPACE || Character.isDigit(car)
                || (int) evt.getKeyChar() >= 58 && (int) evt.getKeyChar() <= 64) {
        } else {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtFRKeyTyped

    private void txtTemperaturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTemperaturaKeyTyped
        char car = evt.getKeyChar();
        if ((int) evt.getKeyChar() > 32 && (int) evt.getKeyChar() <= 47 || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE
                || evt.getKeyChar() == KeyEvent.VK_SPACE || Character.isDigit(car)
                || (int) evt.getKeyChar() >= 58 && (int) evt.getKeyChar() <= 64) {
        } else {
            evt.consume();
            getToolkit().beep();

        }
    }//GEN-LAST:event_txtTemperaturaKeyTyped

    private void txtMasaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMasaKeyTyped
        char car = evt.getKeyChar();
        if ((int) evt.getKeyChar() > 32 && (int) evt.getKeyChar() <= 47 || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE
                || evt.getKeyChar() == KeyEvent.VK_SPACE || Character.isDigit(car)
                || (int) evt.getKeyChar() >= 58 && (int) evt.getKeyChar() <= 64) {
        } else {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtMasaKeyTyped

    private void txtTallaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaKeyTyped
        char car = evt.getKeyChar();
        if (evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_SPACE || Character.isDigit(car)) {
        } else {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtTallaKeyTyped

    private void txtEnfermedadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnfermedadKeyTyped
        char car = evt.getKeyChar();
        if (Character.isLetter(car) || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_SPACE) {
        } else {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtEnfermedadKeyTyped

    private void txtMedicamentoBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMedicamentoBusquedaKeyTyped
        char car = evt.getKeyChar();
        if (Character.isLetter(car) || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_SPACE) {
        } else {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtMedicamentoBusquedaKeyTyped

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        char car = evt.getKeyChar();
        if (Character.isLetterOrDigit(car) || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_SPACE) {
        } else {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtDosisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDosisKeyTyped
        char car = evt.getKeyChar();
        if (Character.isLetterOrDigit(car) || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_SPACE
                || (int) evt.getKeyChar() >= 40 && (int) evt.getKeyChar() <= 41 || (int) evt.getKeyChar() >= 44 && (int) evt.getKeyChar() >= 47) {
        } else {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtDosisKeyTyped

    private void btnReporteAntecedentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteAntecedentesActionPerformed
        try {
            Conexion con = new Conexion();
            Connection conn = con.getConexion();
            int id = DatosPaciente.getIdPaciente();
            String path = "src\\Reportes\\Paciente.jasper";
            JasperReport reporte = null;
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            Map parametro = new HashMap();
            parametro.put("idPaciente", id);
            JasperPrint j = JasperFillManager.fillReport(reporte, parametro, conn);
            JasperViewer jv = new JasperViewer(j, false);
            jv.setTitle("Paciente / Clinica Arevalo");
            jv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jv.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }//GEN-LAST:event_btnReporteAntecedentesActionPerformed

    private void btnEnfermedadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnfermedadesActionPerformed
        ReporteEnfermedades menu = new ReporteEnfermedades();
        menu.setVisible(true);
    }//GEN-LAST:event_btnEnfermedadesActionPerformed

    private void btnEditarDiagnosticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarDiagnosticoActionPerformed
         int resp = JOptionPane.showConfirmDialog(null, "¿Están todos los datos correctos?", "Editar Diagnostico", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.YES_OPTION == resp) {
            try {
                EditarDetalle = (DetalleDiagnostico) DC.findDetalleDiagnostico(Integer.parseInt(this.tbDiagnostico.getValueAt(tbDiagnostico.getSelectedRow(), 0).toString()));
                EditarDetalle.setDescripcion(this.txtObservacionesDiagnostico.getText());
                DC.edit(EditarDetalle);
                JOptionPane.showMessageDialog(null, "El registro fue editado con Éxito");
                CrearModeloDiagnostico();
                CargarTablaDiagnostico();
                this.btnEliminarDiagnostico.setEnabled(false);
                btnEditarDiagnostico.setEnabled(false);
                txtObservacionesDiagnostico.setText("");
                CrearModeloEnfermedades();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else {
        }
    }//GEN-LAST:event_btnEditarDiagnosticoActionPerformed
    private static int CalcularEdad(String fecha) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int Resta = year - Integer.parseInt(fecha);
        return Resta;
    }

    private static String CalcularIMC(double masa, double talla) {
        double imc = masa / Math.pow((talla / 100), 2);

        return String.format("%.2f", imc);
    }
    DefaultTableModel modeloAntecedentes;

    private void CrearModeloAntecedentes() {
        try {
            modeloAntecedentes = (new DefaultTableModel(
                    null, new String[]{
                        "ID Antecedente", "Nombre Paciente", "Familiar",
                        "Personal"}) {
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
            tbAntecendentes.setModel(modeloAntecedentes);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    private void CargarTablaAntecedentes() {
        try {
            Object o[] = null;
            List<Antecedentes> listAnte = AC.findbyIdPaciente(DatosPaciente);
            for (int i = 0; i < listAnte.size(); i++) {
                modeloAntecedentes.addRow(o);
                modeloAntecedentes.setValueAt(listAnte.get(i).getIdAntecedentes(), i, 0);
                modeloAntecedentes.setValueAt(listAnte.get(i).getIdPaciente().getNombres().concat(", " + listAnte.get(i).getIdPaciente().getApellidos()), i, 1);
                modeloAntecedentes.setValueAt(listAnte.get(i).getFamiliares(), i, 2);
                modeloAntecedentes.setValueAt(listAnte.get(i).getPersonales(), i, 3);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    DefaultTableModel modeloEnfermedades;

    private void CrearModeloEnfermedades() {
        try {
            modeloEnfermedades = (new DefaultTableModel(
                    null, new String[]{
                        "ID", "Enfermedad", "Categoria",
                        "clave"}) {
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
            tbEnfermedades.setModel(modeloEnfermedades);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    private void CargarTablaPorEnfermedad(String nombre) {
        try {
            Object o[] = null;
            List<EnfermedadesCie10> listAnte = EC.findEnfermedadporNombre(nombre);
            for (int i = 0; i < listAnte.size(); i++) {
                modeloEnfermedades.addRow(o);
                modeloEnfermedades.setValueAt(listAnte.get(i).getId(), i, 0);
                modeloEnfermedades.setValueAt(listAnte.get(i).getDescripcion(), i, 1);
                modeloEnfermedades.setValueAt(listAnte.get(i).getIdCategoria().getDescripcion(), i, 2);
                modeloEnfermedades.setValueAt(listAnte.get(i).getClave(), i, 3);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void CargarTablaPorCategoria(String nombre) {
        try {
            Object o[] = null;
            List<EnfermedadesCie10> listAnte = EC.findEnfermedadporCategoria(nombre);
            for (int i = 0; i < listAnte.size(); i++) {
                modeloEnfermedades.addRow(o);
                modeloEnfermedades.setValueAt(listAnte.get(i).getId(), i, 0);
                modeloEnfermedades.setValueAt(listAnte.get(i).getDescripcion(), i, 1);
                modeloEnfermedades.setValueAt(listAnte.get(i).getIdCategoria().getDescripcion(), i, 2);
                modeloEnfermedades.setValueAt(listAnte.get(i).getClave(), i, 3);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    DefaultTableModel modeloDiagnostico;

    private void CrearModeloDiagnostico() {
        try {
            modeloDiagnostico = (new DefaultTableModel(
                    null, new String[]{
                        "ID", "Enfermedad", "Descripcion"}) {
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
            this.tbDiagnostico.setModel(modeloDiagnostico);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    private void CargarTablaDiagnostico() {
        try {
            Object o[] = null;
            List<DetalleDiagnostico> listDiagnostico = DC.findbyDiagnostico(DatosDiagnostico);
            for (int i = 0; i < listDiagnostico.size(); i++) {
                modeloDiagnostico.addRow(o);
                modeloDiagnostico.setValueAt(listDiagnostico.get(i).getIdDetalleDiagnostico(), i, 0);
                modeloDiagnostico.setValueAt(listDiagnostico.get(i).getIdEnfermedad().getDescripcion(), i, 1);
                modeloDiagnostico.setValueAt(listDiagnostico.get(i).getDescripcion(), i, 2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    DefaultTableModel modeloVademecum;

    private void CrearModeloVademecum() {
        try {
            modeloVademecum = (new DefaultTableModel(
                    null, new String[]{
                        "ID", "Principio Activo", "Marcas",}) {
                Class[] types = new Class[]{
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class
                };
                boolean[] canEdit = new boolean[]{
                    false, false, false
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
            this.tbVademecum.setModel(modeloVademecum);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    private void CargarTablaVademecum(String Buscar) {
        try {
            Object o[] = null;
            List<Vademecum> listvademecum = VC.findVademecumporNombre(Buscar);
            for (int i = 0; i < listvademecum.size(); i++) {
                modeloVademecum.addRow(o);
                modeloVademecum.setValueAt(listvademecum.get(i).getIdInsumo(), i, 0);
                modeloVademecum.setValueAt(listvademecum.get(i).getPrincipioactivo(), i, 1);
                modeloVademecum.setValueAt(listvademecum.get(i).getMarcas(), i, 2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    DefaultTableModel modeloReceta;

    private void CrearModeloReceta() {
        try {
            modeloReceta = (new DefaultTableModel(
                    null, new String[]{
                        "ID", "Principio Activo", "Cantidad Recetada", "Dosis", "Num Receta"
                    }) {
                Class[] types = new Class[]{
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class
                };
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false
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
            this.tbReceta.setModel(modeloReceta);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    private void CargarTablaReceta() {
        try {
            Object o[] = null;
            List<DetalleReceta> listvademecum = DRC.findbyNumReceta(DatosReceta);
            for (int i = 0; i < listvademecum.size(); i++) {
                modeloReceta.addRow(o);
                modeloReceta.setValueAt(listvademecum.get(i).getDetalleReceta(), i, 0);
                modeloReceta.setValueAt(listvademecum.get(i).getIdInsumo().getPrincipioactivo(), i, 1);
                modeloReceta.setValueAt(listvademecum.get(i).getCantidad(), i, 2);
                modeloReceta.setValueAt(listvademecum.get(i).getDosis(), i, 3);
                modeloReceta.setValueAt(listvademecum.get(i).getNumReceta().getNumReceta(), i, 4);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void CargarCombo() {
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
            java.util.logging.Logger.getLogger(Consulta_Medica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consulta_Medica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consulta_Medica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consulta_Medica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consulta_Medica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelAntecedentes;
    private javax.swing.JPanel PanelConsulta;
    private javax.swing.JPanel PanelDiagnostico;
    private javax.swing.JPanel PanelExamenes;
    private javax.swing.JPanel PanelExploracion;
    private javax.swing.JPanel PanelIdentificacion;
    private javax.swing.JTabbedPane PanelPestañas;
    private javax.swing.JPanel PanelSeleccion;
    private javax.swing.JPanel PanelTratamiento;
    private javax.swing.JButton btnAgenda;
    private javax.swing.JButton btnAgregarAnte;
    private javax.swing.JButton btnAgregarMedicamento;
    private javax.swing.JButton btnAgregarPaciente;
    private javax.swing.JButton btnBuscarEnfermedad;
    private javax.swing.JButton btnCancelarAnte;
    private javax.swing.JButton btnCancelarCancelar;
    private javax.swing.JButton btnCancelarFisica;
    private javax.swing.JButton btnCancelarIdenti;
    private javax.swing.JButton btnCancelarL2;
    private javax.swing.JButton btnCancelarPaciente;
    private javax.swing.JButton btnDiagnostico;
    private javax.swing.JButton btnEditarAnte;
    private javax.swing.JButton btnEditarDiagnostico;
    private javax.swing.JButton btnEditarMedicamento;
    private javax.swing.JButton btnEliminarDiagnostico;
    private javax.swing.JButton btnEliminarMedicamento;
    private javax.swing.JButton btnElminarAnte;
    private javax.swing.JButton btnEnfermedades;
    private javax.swing.JButton btnHacerCita;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JButton btnMenuAnte;
    private javax.swing.JButton btnMenuConsulta;
    private javax.swing.JButton btnMenuExamen;
    private javax.swing.JButton btnMenuFisica;
    private javax.swing.JButton btnMenuFisica1;
    private javax.swing.JButton btnMenuIdenti;
    private javax.swing.JButton btnMenuPaciente;
    private javax.swing.JButton btnMenuTratamiento;
    private javax.swing.JButton btnNuevoAnte;
    private javax.swing.JButton btnNuevoPaciente;
    private javax.swing.JButton btnReceta;
    private javax.swing.JButton btnReporteAntecedentes;
    private javax.swing.JButton btnSeleccionPaciente;
    private javax.swing.JButton btnSiguienteAnte;
    private javax.swing.JButton btnSiguienteConsulta;
    private javax.swing.JButton btnSiguienteExamen;
    private javax.swing.JButton btnSiguienteFisica;
    private javax.swing.JButton btnSiguienteFisica1;
    private javax.swing.JButton btnSiguienteIdenti;
    private javax.swing.JButton btnTerminar;
    private javax.swing.JButton btnbuscarporCate;
    private javax.swing.JComboBox<Hora> cbHora;
    private com.toedter.calendar.JDateChooser fechapicker;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblApellidoIdenti;
    private javax.swing.JLabel lblCivilIdenti;
    private javax.swing.JLabel lblDireccionIdenti;
    private javax.swing.JLabel lblDuiIdenti;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblFechaIdenti1;
    private javax.swing.JLabel lblFechaUltima;
    private javax.swing.JTextField lblIMC;
    private javax.swing.JLabel lblNombreIdenti;
    private javax.swing.JLabel lblPacienteAnte;
    private javax.swing.JLabel lblPacienteConsulta;
    private javax.swing.JLabel lblPacienteDiagnostico;
    private javax.swing.JLabel lblPacienteExamen;
    private javax.swing.JLabel lblPacienteFisica;
    private javax.swing.JLabel lblPacienteTratamiento;
    private javax.swing.JLabel lblSexoIdenti;
    private javax.swing.JLabel lblTelefonoIdenti;
    private javax.swing.JLabel lblUltimoMotivo;
    private javax.swing.JSpinner spFechaConsulta;
    private javax.swing.JSpinner spFechaPaciente;
    private javax.swing.JTable tbAntecendentes;
    private javax.swing.JTable tbDiagnostico;
    private javax.swing.JTable tbEnfermedades;
    private javax.swing.JTable tbPacientes;
    private javax.swing.JTable tbReceta;
    private javax.swing.JTable tbVademecum;
    private javax.swing.JButton txtAgregarDiagnostico;
    private javax.swing.JTextField txtApellidoPaciente;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCivilPaciente;
    private javax.swing.JTextField txtDUIBusqueda;
    private javax.swing.JFormattedTextField txtDUIPaciente;
    private javax.swing.JTextField txtDireccionPaciente;
    private javax.swing.JTextField txtDosis;
    private javax.swing.JTextField txtEnfermedad;
    private javax.swing.JTextArea txtExamenes;
    private javax.swing.JTextField txtFCC;
    private javax.swing.JTextField txtFR;
    private javax.swing.JTextArea txtFamiliarAnte;
    private javax.swing.JTextArea txtFisico;
    private javax.swing.JTextField txtIdPaciente;
    private javax.swing.JTextField txtMasa;
    private javax.swing.JTextField txtMedicamentoBusqueda;
    private javax.swing.JTextField txtMotivoConsulta;
    private javax.swing.JTextField txtNombreBusqueda;
    private javax.swing.JTextField txtNombrePaciente;
    private javax.swing.JTextArea txtObservacionesDiagnostico;
    private javax.swing.JTextArea txtObservacionesIdenti;
    private javax.swing.JTextField txtOcupacionPaciente;
    private javax.swing.JTextArea txtPersonalAnte;
    private javax.swing.JTextArea txtPresenteConsulta;
    private javax.swing.JTextField txtPresion;
    private javax.swing.JTextField txtPulso;
    private javax.swing.JTextField txtSexoPaciente;
    private javax.swing.JTextField txtTalla;
    private javax.swing.JFormattedTextField txtTelefonoPaciente;
    private javax.swing.JTextField txtTemperatura;
    private javax.swing.JButton txtVademecum;
    // End of variables declaration//GEN-END:variables
}
