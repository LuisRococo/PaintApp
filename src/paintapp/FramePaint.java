package paintapp;

import Logica.ControlPaint;
import Logica.IO.ControlGuardado;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FramePaint extends javax.swing.JFrame implements IClienteColorChoser {

    private int lastX = -1;
    private int lastY = -1;
    private final ControlPaint control;
    private boolean inicializado = false;
    private final PanelLienzo lienzo;

    private final Cursor cursorDibujo = new Cursor(Cursor.CROSSHAIR_CURSOR);
    private final Cursor cursorMover = new Cursor(Cursor.HAND_CURSOR);

    public FramePaint(int ancho, int alto) {
        initComponents();
        setTitle("PaintApp");
        lienzo = new PanelLienzo();
        EventosRaton eventos = new EventosRaton(this);
        lienzo.addMouseListener(eventos);
        lienzo.addMouseMotionListener(eventos);
        lienzo.addMouseWheelListener(eventos);
        lienzo.setBackground(Color.WHITE);
        lienzo.setSize(PnlWrapper.getSize());
        lienzo.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        PnlWrapper.setLayout(new BorderLayout());
        PnlWrapper.add(lienzo, BorderLayout.CENTER);
        control = new ControlPaint(lienzo, this, ancho, alto);
        lienzo.setControl(control);
        cambiarColor(control.getColorPincel());
        inicializado = true;
        inicializarBrochas();
        inicializarModos();
        jSlider1.setValue(control.getPorcentageActual());
        cambioTamanoLienzo();
    }
    
    public void setImagenGuardada(Color[][] img, File archivo){
        control.setArchivo(archivo);
        control.setImagenCargada(img);
    }

    private void inicializarBrochas() {
        String brochas[] = control.getNombrePinceles();
        for (String brocha : brochas) {
            CmbHerramienta.addItem(brocha);
        }
    }

    private void inicializarModos() {
        String modos[] = control.getModosTrabajo();
        for (String modo : modos) {
            CmbModo.addItem(modo);
        }
    }

    public void avisoCambioDeModo() {
        String modo = control.getModoActual();
        int i = 0;
        String modoCmb = CmbModo.getItemAt(i);
        while (modoCmb != null) {
            if (modoCmb.equals(modo)) {
                CmbModo.setSelectedIndex(i);
                break;
            }
            i++;
            modoCmb = CmbModo.getItemAt(i);
        }
    }

    void atenderClick(int x, int y) {
        control.accion(x, y);
    }

    void avisarClickTerminado() {
        lienzo.setCursor(cursorDibujo);
        control.accionTerminada();
        lastX = -1;
        lastY = -1;
    }

    void avisarHover(int x, int y) {
        control.hover(x, y);
    }

    void cambioTamanoLienzo() {
        control.cambioTamanoPanel(PnlWrapper.getWidth(), PnlWrapper.getHeight());
    }

    void moverLienzo(int x, int y) {
        lienzo.setCursor(cursorMover);
        if (lastX != -1 && lastY != -1) {
            int nuevaPosX = lastX - x;
            int nuevaPosY = lastY - y;
            control.cambiarPosLienzo(nuevaPosX, nuevaPosY);
        }
        lastX = x;
        lastY = y;
    }

    void hoverFuera() {
        control.hover(-200, -200);
    }

    void cambioZoom(int unidades) {
        unidades = unidades * 5;
        jSlider1.setValue(jSlider1.getValue() - unidades);
    }

    public void cambiarColor(Color cl) {
        this.pnlColor.setBackground(cl);
        control.cambiarColor(cl);
    }

    public void guardarNuevo() {
        String nombreArchivo = JOptionPane.showInputDialog(this, "Nombre del archivo");
        if (nombreArchivo == null || nombreArchivo.length() < 1) {
            JOptionPane.showMessageDialog(this, "Ponga un nombre valido");
            return;
        }
        nombreArchivo += ".png";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            selectedFile = new File(selectedFile, nombreArchivo);
            if (ControlGuardado.isExisteArchivo(selectedFile)) {
                int aux = JOptionPane.showConfirmDialog(this, "Existe un archivo con el mismo nombre\n"
                        + "¿Desea sobreescribir?", "Sobreescribir archivo", JOptionPane.YES_NO_OPTION);
                if (aux == JOptionPane.NO_OPTION) {
                    return;
                }
            }
            if (ControlGuardado.guardarImagen(control.getImagenArray(), selectedFile)) {
                JOptionPane.showMessageDialog(this, "Archivo Guardado");
                control.setArchivo(selectedFile);
            } else {
                JOptionPane.showMessageDialog(this, "Hubo un error al guardar");
            }
        }
    }

    public void guardarSobreescribir() {
        File archivo = control.getArchivo();
        if (archivo != null) {
            if (ControlGuardado.guardarImagen(control.getImagenArray(), archivo)) {
                JOptionPane.showMessageDialog(this, "Archivo Guardado");
                control.setArchivo(archivo);
            } else {
                JOptionPane.showMessageDialog(this, "Hubo un error al guardar");
            }
        } else {
            guardarNuevo();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        CmbModo = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        pnlColor = new javax.swing.JPanel();
        CmbHerramienta = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jSlider1 = new javax.swing.JSlider();
        jPanel10 = new javax.swing.JPanel();
        btnHistRegresar = new javax.swing.JButton();
        btnHistAvanzar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        PnlWrapper = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuCambiarTamano = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        menu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Modo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        CmbModo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbModoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CmbModo, 0, 219, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CmbModo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pincel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jButton3.setText("Color");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        pnlColor.setBackground(new java.awt.Color(102, 0, 204));
        pnlColor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlColorLayout = new javax.swing.GroupLayout(pnlColor);
        pnlColor.setLayout(pnlColorLayout);
        pnlColorLayout.setHorizontalGroup(
            pnlColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 86, Short.MAX_VALUE)
        );
        pnlColorLayout.setVerticalGroup(
            pnlColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        CmbHerramienta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbHerramientaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CmbHerramienta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CmbHerramienta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Zoom", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jSlider1.setMaximum(500);
        jSlider1.setMinimum(25);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Historial", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        btnHistRegresar.setText("<<");
        btnHistRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistRegresarActionPerformed(evt);
            }
        });

        btnHistAvanzar.setText(">>");
        btnHistAvanzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistAvanzarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHistRegresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHistAvanzar)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHistRegresar)
                    .addComponent(btnHistAvanzar))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Arrastra haciendo click derecho para moverte");

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Use scroll para zoom");

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Otros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jButton1.setText("Reiniciar Posicion Lienzo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3);

        PnlWrapper.setBackground(new java.awt.Color(255, 255, 255));
        PnlWrapper.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                PnlWrapperComponentResized(evt);
            }
        });

        javax.swing.GroupLayout PnlWrapperLayout = new javax.swing.GroupLayout(PnlWrapper);
        PnlWrapper.setLayout(PnlWrapperLayout);
        PnlWrapperLayout.setHorizontalGroup(
            PnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
        );
        PnlWrapperLayout.setVerticalGroup(
            PnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.add(PnlWrapper);

        jMenu1.setText("Archivo");

        jMenuItem4.setText("Nuevo");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("Guardar");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem6.setText("Guardar Como");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Proyecto");

        menuCambiarTamano.setText("Tamano Lienzo");
        menuCambiarTamano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCambiarTamanoActionPerformed(evt);
            }
        });
        jMenu2.add(menuCambiarTamano);

        jMenuItem2.setText("Limpiar Lienzo");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        menu2.setText("PaintApp");

        jMenuItem1.setText("Cuadricula y Lieneas Guias");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menu2.add(jMenuItem1);

        jMenuItem3.setText("Configuracion General");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menu2.add(jMenuItem3);

        jMenuBar1.add(menu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PnlWrapperComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_PnlWrapperComponentResized
        if (inicializado) {
            cambioTamanoLienzo();
        }
    }//GEN-LAST:event_PnlWrapperComponentResized

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        control.cambiarZoom(jSlider1.getValue());
    }//GEN-LAST:event_jSlider1StateChanged

    private void menuCambiarTamanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCambiarTamanoActionPerformed
        JDialog dialogoCambTam = new FrameCmbTamano(this, control);
        dialogoCambTam.setLocationRelativeTo(this);
        dialogoCambTam.setVisible(true);
    }//GEN-LAST:event_menuCambiarTamanoActionPerformed

    private void CmbHerramientaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbHerramientaActionPerformed
        if (inicializado) {
            control.setPincel(CmbHerramienta.getSelectedItem().toString(), true);
        }
    }//GEN-LAST:event_CmbHerramientaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        FrameColorChoser dialog = new FrameColorChoser(control.getColorPincel(), this);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        FrameCuadricula dialog = new FrameCuadricula(this, control);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnHistRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistRegresarActionPerformed
        control.retrocederCambio();
    }//GEN-LAST:event_btnHistRegresarActionPerformed

    private void btnHistAvanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistAvanzarActionPerformed
        control.avanzarCambio();
    }//GEN-LAST:event_btnHistAvanzarActionPerformed

    private void CmbModoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbModoActionPerformed
        if (inicializado) {
            control.setModoTrabajo(CmbModo.getSelectedItem().toString());
        }
    }//GEN-LAST:event_CmbModoActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        FrameComponentes frame = new FrameComponentes(this, control);
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Seguro que desea limpiar el lienzo?", "Limpiar Lienzo", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            control.limpiarImagen();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        control.reiniciarPosicionLienzo();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        FrameNuevoProyecto frame = new FrameNuevoProyecto();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        guardarSobreescribir();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        guardarNuevo();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CmbHerramienta;
    private javax.swing.JComboBox<String> CmbModo;
    private javax.swing.JPanel PnlWrapper;
    private javax.swing.JButton btnHistAvanzar;
    private javax.swing.JButton btnHistAvanzar1;
    private javax.swing.JButton btnHistRegresar;
    private javax.swing.JButton btnHistRegresar1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JMenu menu2;
    private javax.swing.JMenuItem menuCambiarTamano;
    private javax.swing.JPanel pnlColor;
    // End of variables declaration//GEN-END:variables

    @Override
    public void recibirColor(Color cl) {
        cambiarColor(cl);
    }
}
