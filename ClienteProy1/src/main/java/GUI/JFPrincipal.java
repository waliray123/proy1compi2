/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Analizadores.Compilar.LexerCompilar;
import Analizadores.Compilar.ParserCompilar;
import Analizadores.Comunicacion.LexerComun;
import Analizadores.Comunicacion.ParserComun;
import Analizadores.ErrorCom;
import Analizadores.Pintar.LexerPintar;
import Analizadores.ValExpr.LexerValExpr;
import Analizadores.ValExpr.ParserValExpr;
import ControlBack.ControlCrearPista;
import ControlBack.ControlDuracion;
import ControlBack.ControlGuardado;
import ControlBack.ControlReproducir;
import ControlBack.ControlSemantico;
import ControlBack.ControlServidor;
import ControlBack.Nota;
import javax.swing.ImageIcon;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import ControlGUI.NumeroLinea;
import Objetos.BinGuard;
import Objetos.Comunicacion.Solicitud;
import Objetos.ControlCrear.ExprVal;
import Objetos.Lista;
import Objetos.Pista;
import Objetos.PistaGuard;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Utilities;

/**
 *
 * @author user-ubunto
 */
public class JFPrincipal extends javax.swing.JFrame {

    private NumeroLinea numeroLinea;
    private List<Nota> notasPistaRep;
    private List<String> mensajes;
    private int durPista;
    private int caretAct;
    private boolean pistaIniciada;
    private boolean codigoCargado;
    private List<String> pistasEnLista;
    private boolean detenerLista;

    /**
     * Creates new form JFPrincipal
     */
    public JFPrincipal() {
        initComponents();
        this.jButton10.setText("");
        this.notasPistaRep = new ArrayList<>();
        this.pistasEnLista = new ArrayList<>();
        this.mensajes = new ArrayList<>();
        durPista = 0;
        this.pistaIniciada = false;
        MostrarIconoPlay();
        agregarCarretParaMostrarLinCol();
        agregarNumeroLineaTextPane();
        cargarPistas();
        cargarListas();
        codigoCargado = false;
        detenerLista = true;
    }

    private void MostrarIconoPlay() {
        ImageIcon iconLogo = new ImageIcon("Images/tocar.png");
        jButton10.setIcon(iconLogo);
    }

    private void MostrarIconoPausar() {
        ImageIcon iconLogo = new ImageIcon("Images/boton-de-pausa.png");
        jButton10.setIcon(iconLogo);
    }

    private void agregarCarretParaMostrarLinCol() {
        this.jTextPane2.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                try {
                    int caretPos = jTextPane2.getCaretPosition();
                    int row = (caretPos == 0) ? 1 : 0;
                    for (int offset = caretPos; offset > 0;) {
                        offset = Utilities.getRowStart(jTextPane2, offset) - 1;
                        row++;
                    }
                    int offset = Utilities.getRowStart(jTextPane2, caretPos);
                    int col = caretPos - offset + 1;
                    jLabelLin.setText(String.valueOf(row));
                    jLabelCol.setText(String.valueOf(col));
                } catch (Exception exc) {
                    System.out.println(exc);
                }
            }
        });
    }

    private void agregarNumeroLineaTextPane() {
        this.numeroLinea = new NumeroLinea(this.jTextPane2);
        this.jScrollPane6.setRowHeaderView(this.numeroLinea);
    }

    private void escanear() {
        try {
            LexerPintar lex = new LexerPintar(new BufferedReader(new StringReader(this.jTextPane2.getText())));
            lex.pintar.darEstilo(this.jTextPane2.getText());
            this.jTextPane2.setDocument(lex.pintar.caja2.getDocument());
            lex.scanear();
        } catch (Exception e) {

        }
    }

    private void cargarPistas() {
        ControlGuardado controlG = new ControlGuardado();
        controlG.obtenerBinario();
        BinGuard binarioG = controlG.getBinarioGuard();
        List<PistaGuard> pistasGuardadas = binarioG.getPistasGuardadas();

        DefaultTableModel model = (DefaultTableModel) this.jTableCanciones.getModel();
        int rowCount = model.getRowCount();
        //Eliminar todas las filas del modelo
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        for (PistaGuard pistaG : pistasGuardadas) {
            model.addRow(new Object[]{pistaG.getNombre(), pistaG.getDuracion()});
        }
    }

    private void cargarListas() {
        try {
            ControlGuardado controlG = new ControlGuardado();
            controlG.obtenerBinario();
            BinGuard binarioG = controlG.getBinarioGuard();
            List<Lista> listas = binarioG.getListasGuardadas();
            DefaultListModel modelo = new DefaultListModel();
            for (Lista lista : listas) {
                modelo.addElement(lista.getNombre());
            }
            this.jList2.setModel(modelo);
        } catch (Exception e) {

        }
    }

    private void Compilar() {
        try {
            String entrada = this.jTextPane2.getText();
            List<Pista> pistas = null;
            Lista lista = null;
            List<ErrorCom> errores = new ArrayList<>();
            StringReader reader = new StringReader(entrada);
            LexerCompilar lexico = new LexerCompilar(reader);
            ParserCompilar parser = new ParserCompilar(lexico);
            boolean esLista = false;
            try {
                parser.parse();
                errores = parser.getErroresCom();
                lista = parser.getLista();
                if (lista != null) {
                    esLista = true;
                }
                pistas = parser.getPistas();
            } catch (Exception ex) {
                System.out.println("Error irrecuperable");
                System.out.println("Causa: " + ex.getCause());
                System.out.println("Causa2: " + ex.toString());
            }
            if (errores.size() > 0) {
                JFReporteErrores jfReporteErrores = new JFReporteErrores(errores);
                jfReporteErrores.setVisible(true);
            } else {
                if (esLista) {
                    //Validar lista y guardar la pista
                    ControlSemantico controlSem = new ControlSemantico();
                    boolean valdList = controlSem.validarLista(lista);
                    if (valdList) {
                        //guardar lista
                        ControlGuardado controlG = new ControlGuardado();
                        if (codigoCargado == false) {
                            lista.setCodigo(entrada);
                            controlG.guardarLista(lista);
                            JOptionPane.showMessageDialog(this, "Lista guardada correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            //Modificar Lista
                            lista.setCodigo(entrada);
                            modificarLista(lista);
                            JOptionPane.showMessageDialog(this, "Lista modificada correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }
                } else {
                    for (Pista pista : pistas) {
                        ControlCrearPista crearPista = new ControlCrearPista(pista);
                        crearPista.initPista();
                        this.notasPistaRep = crearPista.getNotas();
                        this.mensajes = crearPista.getMensajes();
                        escribirMensajes();
                        //Obtener Duracion
                        ControlDuracion controlD = new ControlDuracion(this.notasPistaRep);
                        controlD.generarDuracion();
                        int duracion = controlD.getDuracion();
                        //Guardar Pista
                        if (this.codigoCargado == false) {
                            guardarPistaEnBinario(entrada, pista.getNombre(), duracion);
                            JOptionPane.showMessageDialog(this, "Pista guardada correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            modificarPistaEnBinario(entrada, pista.getNombre(), duracion);
                            JOptionPane.showMessageDialog(this, "Pista modificada correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
                        }
                        System.out.println("Pista: " + pista.getNombre());
                        List<String> ext = pista.getExtensiones();
                        for (String string : ext) {
                            System.out.println("Extiende: " + string);
                        }
                    }
                }
                System.out.println("Compilado Correctamente");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void guardarPistaEnBinario(String codigo, String nombre, int duracion) {
        ControlGuardado controlG = new ControlGuardado();
        controlG.guardarPista(codigo, nombre, duracion);
    }

    private void modificarPistaEnBinario(String codigo, String nombre, int duracion) {
        this.codigoCargado = false;
        ControlGuardado controlG = new ControlGuardado();
        controlG.modificarPista(codigo, nombre, duracion);
    }

    private void escribirMensajes() {
        String men = "";
        for (String mensaje : this.mensajes) {
            men += mensaje + "\n";
        }
        this.jTextArea1.setText(men);
    }

    private void controlLoad(String path) {
        String texto = "";
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader br = new BufferedReader(fileReader);
            String line;
            while ((line = br.readLine()) != null) {
                texto += line + "\n";
            }
            this.jTextPane2.setText(texto);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String getSeleccionado(String nombre) {
        String valR = "";
        ControlGuardado controlG = new ControlGuardado();
        controlG.obtenerBinario();
        BinGuard binarioGuard = controlG.getBinarioGuard();
        List<PistaGuard> pistasGuardadas = binarioGuard.getPistasGuardadas();
        PistaGuard pistaG = null;
        for (PistaGuard pistaGuardada : pistasGuardadas) {
            String nomP = pistaGuardada.getNombre();
            if (nomP.equals(nombre)) {
                pistaG = pistaGuardada;
                break;
            }
        }
        if (pistaG == null) {
            valR = "No se encontro la pista";
        } else {
            try {
                String entrada = pistaG.getCodigo();
                List<Pista> pistas = null;
                List<ErrorCom> errores = new ArrayList<>();
                StringReader reader = new StringReader(entrada);
                LexerCompilar lexico = new LexerCompilar(reader);
                ParserCompilar parser = new ParserCompilar(lexico);
                try {
                    parser.parse();
                    errores = parser.getErroresCom();
                    pistas = parser.getPistas();
                } catch (Exception ex) {
                    System.out.println("Error irrecuperable");
                    System.out.println("Causa: " + ex.getCause());
                    System.out.println("Causa2: " + ex.toString());
                }
                if (errores.size() > 0) {
                    JFReporteErrores jfReporteErrores = new JFReporteErrores(errores);
                    jfReporteErrores.setVisible(true);
                } else {
                    for (Pista pista : pistas) {
                        ControlCrearPista crearPista = new ControlCrearPista(pista);
                        crearPista.initPista();
                        this.notasPistaRep = crearPista.getNotas();
                        //Obtener Duracion
                        ControlDuracion controlD = new ControlDuracion(this.notasPistaRep);
                        controlD.generarDuracion();
                        this.durPista = controlD.getDuracion();

                    }
                    valR = "Cargado Correctamente";
                    System.out.println("Cargado Correctamente");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return valR;
    }

    private String getModificar(String nombre) {
        String valR = "";
        ControlGuardado controlG = new ControlGuardado();
        controlG.obtenerBinario();
        BinGuard binarioGuard = controlG.getBinarioGuard();
        List<PistaGuard> pistasGuardadas = binarioGuard.getPistasGuardadas();
        PistaGuard pistaG = null;
        for (PistaGuard pistaGuardada : pistasGuardadas) {
            String nomP = pistaGuardada.getNombre();
            if (nomP.equals(nombre)) {
                pistaG = pistaGuardada;
                break;
            }
        }
        if (pistaG == null) {
            valR = "No se encontro la pista";
        } else {
            valR = pistaG.getCodigo();
            System.out.println("Cargado Correctamente");
        }
        return valR;
    }

    private void eliminarPista() {
        try {
            DefaultTableModel tm = (DefaultTableModel) this.jTableCanciones.getModel();
            int rowIndex = this.jTableCanciones.getSelectedRow();
            String cancion = String.valueOf(tm.getValueAt(rowIndex, 0));
            ControlGuardado controlG = new ControlGuardado();
            boolean valR = controlG.eliminarPista(cancion);
            if (valR) {
                //Eliminar pista                 
                JOptionPane.showMessageDialog(this, "Pista eliminada correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Pista:" + cancion + "no encontrada", "Info", JOptionPane.ERROR_MESSAGE);
            }
            cargarPistas();
        } catch (Exception e) {
        }
    }

    private void eliminarLista() {
        try {
            String value = this.jList2.getSelectedValue();
            ControlGuardado controlG = new ControlGuardado();
            boolean valR = controlG.eliminarLista(value);
            if (valR) {
                //Eliminar pista                 
                JOptionPane.showMessageDialog(this, "Lista eliminada correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Lista:" + value + "no encontrada", "Info", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
        }
    }

    private void modificarLista(Lista lista) {
        ControlGuardado controlG = new ControlGuardado();
        controlG.modificarLista(lista);
    }

    private void mostrarPistasEnLista() {
        DefaultListModel modelo = new DefaultListModel();
        for (String string : pistasEnLista) {
            modelo.addElement(string);
        }
        this.jList3.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelBiblioteca = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableCanciones = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelCol = new javax.swing.JLabel();
        jLabelLin = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jPanelReproduccion = new javax.swing.JPanel();
        jPanelFrecuencia = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuArchivo = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Canciones");

        jScrollPane2.setViewportView(jList2);

        jLabel2.setText("Listas de Reproduccion");

        jLabel3.setText("Pistas en lista de reproduccion");

        jScrollPane3.setViewportView(jList3);

        jTableCanciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Duracion "
            }
        ));
        jScrollPane4.setViewportView(jTableCanciones);

        jButton2.setText("Modificar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Reproducir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Eliminar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Modificar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Eliminar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton1.setText("Ver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton8.setText("Reproducir lista");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBibliotecaLayout = new javax.swing.GroupLayout(jPanelBiblioteca);
        jPanelBiblioteca.setLayout(jPanelBibliotecaLayout);
        jPanelBibliotecaLayout.setHorizontalGroup(
            jPanelBibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBibliotecaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBibliotecaLayout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelBibliotecaLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(333, 358, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
                .addGroup(jPanelBibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBibliotecaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelBibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelBibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addGroup(jPanelBibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton8)
                                    .addGroup(jPanelBibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))))
                            .addGroup(jPanelBibliotecaLayout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 25, Short.MAX_VALUE))
                    .addGroup(jPanelBibliotecaLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanelBibliotecaLayout.setVerticalGroup(
            jPanelBibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBibliotecaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelBibliotecaLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelBibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(jButton6)
                            .addComponent(jButton1))
                        .addGap(19, 19, 19)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelBibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton8))
                .addContainerGap(129, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Biblioteca", jPanelBiblioteca);

        jLabel4.setText("Texto Entrada");

        jButton7.setText("Compilar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel5.setText("Linea");

        jLabel6.setText("Columna");

        jLabelCol.setText("0");

        jLabelLin.setText("0");

        jTextPane2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextPane2KeyTyped(evt);
            }
        });
        jScrollPane6.setViewportView(jTextPane2);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel9.setText("Log");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLin)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelCol)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 433, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabelCol)
                    .addComponent(jLabelLin)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Editor", jPanel1);

        jPanelReproduccion.setToolTipText("Reproductor");
        jPanelReproduccion.setName(""); // NOI18N

        javax.swing.GroupLayout jPanelFrecuenciaLayout = new javax.swing.GroupLayout(jPanelFrecuencia);
        jPanelFrecuencia.setLayout(jPanelFrecuenciaLayout);
        jPanelFrecuenciaLayout.setHorizontalGroup(
            jPanelFrecuenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 345, Short.MAX_VALUE)
        );
        jPanelFrecuenciaLayout.setVerticalGroup(
            jPanelFrecuenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 241, Short.MAX_VALUE)
        );

        jLabel7.setText("4000Hz");

        jLabel8.setText("0Hz");

        jLabel10.setText("Progreso");

        jLabel11.setText("Tiempo");

        jLabel12.setText("00:00");

        jButton10.setText("jButton10");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelReproduccionLayout = new javax.swing.GroupLayout(jPanelReproduccion);
        jPanelReproduccion.setLayout(jPanelReproduccionLayout);
        jPanelReproduccionLayout.setHorizontalGroup(
            jPanelReproduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReproduccionLayout.createSequentialGroup()
                .addGroup(jPanelReproduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelReproduccionLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addGap(61, 61, 61))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelReproduccionLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelReproduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelFrecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelReproduccionLayout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanelReproduccionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelReproduccionLayout.setVerticalGroup(
            jPanelReproduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReproduccionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelReproduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelReproduccionLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addComponent(jPanelFrecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jLabel13)
                .addGap(11, 11, 11)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelReproduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        jMenuArchivo.setText("Archivo");

        jMenuItem1.setText("Guardar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItem1);

        jMenuItem2.setText("Cargar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItem2);

        jMenuBar1.add(jMenuArchivo);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelReproduccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelReproduccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Compilar();
        cargarPistas();
        cargarListas();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextPane2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPane2KeyTyped
        int caretPos = jTextPane2.getCaretPosition();
        escanear();
        jTextPane2.setCaretPosition(caretPos);

    }//GEN-LAST:event_jTextPane2KeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //TODO Modificar
        //Traer el codigo de la pista y mostrarlo en el edit text
        try {
            DefaultTableModel tm = (DefaultTableModel) this.jTableCanciones.getModel();
            int rowIndex = this.jTableCanciones.getSelectedRow();
            String cancion = String.valueOf(tm.getValueAt(rowIndex, 0));
            String codigoPista = getModificar(cancion);
            if (codigoPista.equals("No se encontro la pista") == false) {
                this.jTextPane2.setText("");
                this.jTextPane2.setText(codigoPista);
                this.codigoCargado = true;
                JOptionPane.showMessageDialog(this, "El codigo ha sido cargado", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, codigoPista, "Info", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Archivo de Texto", "txt"));
            fileChooser.setAcceptAllFileFilterUsed(false);
            int seleccion = fileChooser.showOpenDialog(this);
            if (seleccion == APPROVE_OPTION) {
                controlLoad(fileChooser.getSelectedFile().getPath());
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Info", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //Reproducir : traer la pista seleccionada y transformarla en notas
        try {
            DefaultTableModel tm = (DefaultTableModel) this.jTableCanciones.getModel();
            int rowIndex = this.jTableCanciones.getSelectedRow();
            String cancion = String.valueOf(tm.getValueAt(rowIndex, 0));
            String val = getSeleccionado(cancion);
            if (val.equals("Cargado Correctamente")) {
                this.jLabel12.setText(String.valueOf(this.durPista));
                this.jLabel13.setText(cancion);
            }
        } catch (Exception e) {
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    ControlReproducir controlR1;
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        //Boton reproducir pista
        try {
            //Reproducir la pista
            Avanzando avanzando = null;
            Thread hilo1 = null;
            Thread hilo2 = null;
            ReprodPista repPista = null;
            if (pistaIniciada == false) {
                this.pistaIniciada = true;
                //Empezar a reproducir pista
                MostrarIconoPausar();
                controlR1 = new ControlReproducir(this.notasPistaRep);
                repPista = new ReprodPista();

                avanzando = new Avanzando(this.durPista);
                avanzando.setBar(this.jProgressBar1);
                hilo1 = new Thread(avanzando);
                hilo1.start();
                hilo2 = new Thread(repPista);
                hilo2.start();
                //controlR1.reproducir();

            } else {
                //Detener reproduccion pista
                this.pistaIniciada = false;
                MostrarIconoPlay();
                if (hilo1 != null) {
                    hilo1.stop();
                }
                if (hilo2 != null) {
                    hilo2.stop();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        //Eliminar pista seleccionada
        if (JOptionPane.showConfirmDialog(rootPane, "Se eliminará la pista, ¿desea continuar?",
                "Eliminar Pista", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            eliminarPista();
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //Eliminar lista
        if (JOptionPane.showConfirmDialog(rootPane, "Se eliminará la lista, ¿desea continuar?",
                "Eliminar Lista", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            eliminarLista();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // Modificar Lista
        try {
            String value = this.jList2.getSelectedValue();
            ControlGuardado controlG = new ControlGuardado();
            controlG.obtenerBinario();
            BinGuard binario = controlG.getBinarioGuard();
            List<Lista> listas = binario.getListasGuardadas();
            Lista listaM = null;
            for (Lista lista : listas) {
                String nom = lista.getNombre().replace("\"", "");
                if (nom.equals(value)) {
                    listaM = lista;
                    break;
                }
            }
            if (listaM == null) {
                JOptionPane.showMessageDialog(this, "La lista no se encuentra", "Info", JOptionPane.ERROR_MESSAGE);
            } else {
                this.jTextPane2.setText("");
                this.jTextPane2.setText(listaM.getCodigo());
                this.codigoCargado = true;
                JOptionPane.showMessageDialog(this, "El codigo ha sido cargado", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Ver pistas en lista
        try {
            String value = this.jList2.getSelectedValue();
            ControlGuardado controlG = new ControlGuardado();
            controlG.obtenerBinario();
            BinGuard binario = controlG.getBinarioGuard();
            List<Lista> listas = binario.getListasGuardadas();
            Lista listaM = null;
            for (Lista lista : listas) {
                String nom = lista.getNombre().replace("\"", "");
                if (nom.equals(value)) {
                    listaM = lista;
                    break;
                }
            }
            if (listaM == null) {
                JOptionPane.showMessageDialog(this, "La lista no se encuentra", "Info", JOptionPane.ERROR_MESSAGE);
            } else {
                this.pistasEnLista = listaM.getPistas();
                mostrarPistasEnLista();
                JOptionPane.showMessageDialog(this, "El codigo ha sido cargado", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:        
        if (this.pistasEnLista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La lista no cargada", "Info", JOptionPane.ERROR_MESSAGE);
        } else {
            this.detenerLista = false;
            //Reproducir lista
            try {
                Thread hilo1 = null;
                if (detenerLista == false) {
                    ReprodLista repL = new ReprodLista();
                    hilo1 = new Thread(repL);
                    hilo1.start();
                } else {
                    hilo1.stop();
                }
            } catch (Exception e) {

            }
        }

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:Guardar un archivo
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    class ReprodLista implements Runnable {

        @Override
        public void run() {
            try {
                for (String string : pistasEnLista) {
                    Avanzando avanzando = null;
                    Thread hilo1 = null;
                    Thread hilo2 = null;
                    ReprodPista repPista = null;
                    if (detenerLista) {
                        if (hilo1 != null) {
                            hilo1.stop();
                        }
                        if (hilo2 != null) {
                            hilo2.stop();
                        }
                        break;
                    } else {                        
                        getSeleccionado(string);
                        controlR1 = new ControlReproducir(notasPistaRep);
                        repPista = new ReprodPista();
                        avanzando = new Avanzando(durPista);
                        avanzando.setBar(jProgressBar1);
                        hilo1 = new Thread(avanzando);
                        hilo1.start();
                        hilo2 = new Thread(repPista);
                        hilo2.start();
                    }
                }
            } catch (Exception e) {

            }
        }

    }

    class ReprodPista implements Runnable {

        private Sequencer sequencer;

        @Override
        public void run() {
            try {
                this.sequencer = MidiSystem.getSequencer();
                sequencer.open();
                Sequence sequence = null;
                try {
                    sequence = controlR1.crearSecuencia();
                } catch (Throwable ex) {
                    Logger.getLogger(ControlReproducir.class.getName()).log(Level.SEVERE, null, ex);
                }
                sequencer.setSequence(sequence);
                sequencer.start();
                while (sequencer.isRunning()) {
                    Thread.sleep(1000L);
                }
                sequencer.stop();
            } catch (Exception e) {

            }
        }

    }

    class Avanzando implements Runnable {

        JProgressBar bar;

        private int tiempo;

        public Avanzando(int tiempo) {
            this.tiempo = tiempo;
        }

        @Override
        public void run() {
            for (int i = 1000; i <= tiempo;) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Avanzando.class.getName()).log(Level.SEVERE, null, ex);
                }

                double time = (i * 100 / tiempo);
                int time2 = (int) Math.round(time);
                this.getBar().setValue(time2);
                System.out.println((time2));

                if (this.getBar().getValue() >= tiempo || pistaIniciada == false) {
                    pistaIniciada = false;
                    break;
                    //JOptionPane.showMessageDialog(new Index(), "Ha terminado el Jbar No: " + num_bar);
                }
                i += 1000;
            }
            MostrarIconoPlay();
        }

        public void setBar(JProgressBar bar) {
            this.bar = bar;
        }

        public JProgressBar getBar() {
            return bar;
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
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
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCol;
    private javax.swing.JLabel jLabelLin;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBiblioteca;
    private javax.swing.JPanel jPanelFrecuencia;
    private javax.swing.JPanel jPanelReproduccion;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableCanciones;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextPane jTextPane2;
    // End of variables declaration//GEN-END:variables
}
