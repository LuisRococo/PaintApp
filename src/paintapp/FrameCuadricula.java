
package paintapp;

import Logica.CompGraficos.Componentes.DatosCuadricula;
import Logica.ControlPaint;
import java.awt.Color;
import javax.swing.JOptionPane;

public class FrameCuadricula extends javax.swing.JDialog implements IClienteColorChoser{

    private final ControlPaint control;
    private Color aux;
    private final DatosCuadricula dtsLnGuias;
    private final DatosCuadricula dtsCuadricula;
    public FrameCuadricula(java.awt.Frame parent, ControlPaint control) {
        super(parent, true);
        initComponents();
        this.control=control;
        this.setTitle("Cuadricula y Lienas Guias");
        dtsCuadricula=control.getDatosCuadricula();
        dtsLnGuias=control.getDatosLnGuias();
        cargarConfigCuadricula();
        cargarConfigLineasGuias();
    }
    
    private void cargarConfigLineasGuias(){
        txtXLn.setText(String.valueOf(dtsLnGuias.getxOffset()));
        txtYLn.setText(String.valueOf(dtsLnGuias.getyOffset()));
        txtGrosorLn.setText(String.valueOf(dtsLnGuias.getGrosor()));
        txtAlto.setText(String.valueOf(dtsLnGuias.getTamAltura()));
        txtAncho.setText(String.valueOf(dtsLnGuias.getTamAncho()));
        pnlLn.setBackground(dtsLnGuias.getColor());
        cbActivarLn.setSelected(control.isCompGraficoActivo("LINEAS_GUIAS"));
    }
    
    private void cargarConfigCuadricula(){
        txtGrosorCd.setText(String.valueOf(dtsCuadricula.getGrosor()));
        pnlCd.setBackground(dtsCuadricula.getColor());
        cbActivarCuadricula.setSelected(control.isCompGraficoActivo("CUADRICULA"));
    }
    
    private boolean aplicarCambiosCuadricula (){
        try{
            int grosor=Integer.valueOf(txtGrosorCd.getText());
            Color color=pnlCd.getBackground();
            control.setDatosCuadricula(color, grosor);
            if (cbActivarCuadricula.isSelected()){
                control.activarComponenteGrafico("CUADRICULA");
            } else {
                control.desacComponenteGrafico("CUADRICULA");
            }
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
    
    private boolean aplicarCambiosLnGuias (){
        try{
            int grosor=Integer.valueOf(txtGrosorLn.getText());
            int ancho=Integer.valueOf(txtAncho.getText());
            int alt=Integer.valueOf(txtAlto.getText());
            int xOffset=corregirOffset(Integer.valueOf(txtXLn.getText()), ancho);
            int yOffset=corregirOffset(Integer.valueOf(txtYLn.getText()), alt);
            Color color=pnlLn.getBackground();
            control.setDatosLineasGuias(color, grosor, xOffset, yOffset, ancho, alt);
            if (cbActivarLn.isSelected()){
                control.activarComponenteGrafico("LINEAS_GUIAS");
            } else {
                control.desacComponenteGrafico("LINEAS_GUIAS");
            }
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
    
    private int corregirOffset(int offset, int tamano){
        offset=Math.abs(offset);
        int aux;
        if (offset>=tamano){
            aux=offset%tamano;
            offset=aux;
        }
        return offset;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbActivarLn = new javax.swing.JCheckBox();
        txtXLn = new javax.swing.JTextField();
        txtYLn = new javax.swing.JTextField();
        pnlLn = new javax.swing.JPanel();
        btnCambiarLn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtGrosorLn = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtAncho = new javax.swing.JTextField();
        txtAlto = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cbActivarCuadricula = new javax.swing.JCheckBox();
        pnlCd = new javax.swing.JPanel();
        btnCambiarCuadricula1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtGrosorCd = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Cuadricula y Lineas Guias");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Lineas Guias"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Offset X: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Offset Y: ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Color: ");

        cbActivarLn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cbActivarLn.setText("Activar");

        pnlLn.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlLnLayout = new javax.swing.GroupLayout(pnlLn);
        pnlLn.setLayout(pnlLnLayout);
        pnlLnLayout.setHorizontalGroup(
            pnlLnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 67, Short.MAX_VALUE)
        );
        pnlLnLayout.setVerticalGroup(
            pnlLnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnCambiarLn.setText("Cambiar");
        btnCambiarLn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarLnActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Grosor:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Ancho: ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Alto: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cbActivarLn))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8)
                            .addComponent(jLabel2))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtXLn)
                            .addComponent(txtGrosorLn)
                            .addComponent(txtYLn)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel4))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnCambiarLn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pnlLn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtAncho)
                            .addComponent(txtAlto))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtXLn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtYLn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtGrosorLn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtAncho, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtAlto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlLn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(btnCambiarLn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbActivarLn)
                .addContainerGap())
        );

        jButton1.setText("Aplicar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Cuadricula"));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Color: ");

        cbActivarCuadricula.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cbActivarCuadricula.setText("Activar");

        pnlCd.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlCdLayout = new javax.swing.GroupLayout(pnlCd);
        pnlCd.setLayout(pnlCdLayout);
        pnlCdLayout.setHorizontalGroup(
            pnlCdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlCdLayout.setVerticalGroup(
            pnlCdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnCambiarCuadricula1.setText("Cambiar");
        btnCambiarCuadricula1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarCuadricula1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Grosor:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel7))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnCambiarCuadricula1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlCd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbActivarCuadricula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(txtGrosorCd))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtGrosorCd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlCd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(btnCambiarCuadricula1)))
                .addGap(15, 15, 15)
                .addComponent(cbActivarCuadricula)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 74, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (aplicarCambiosCuadricula() && aplicarCambiosLnGuias()){
            dispose();
        } else {
            JOptionPane.showConfirmDialog(this, "No fue posible aplicar cambios");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCambiarLnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarLnActionPerformed
        aux=null;
        FrameColorChoser frame=new FrameColorChoser(pnlLn.getBackground(), this);
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
        if (aux!=null){
            pnlLn.setBackground(aux);
        }
    }//GEN-LAST:event_btnCambiarLnActionPerformed

    private void btnCambiarCuadricula1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarCuadricula1ActionPerformed
        aux=null;
        FrameColorChoser frame=new FrameColorChoser(pnlCd.getBackground(), this);
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
        if (aux!=null){
            pnlCd.setBackground(aux);
        }
    }//GEN-LAST:event_btnCambiarCuadricula1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiarCuadricula1;
    private javax.swing.JButton btnCambiarLn;
    private javax.swing.JCheckBox cbActivarCuadricula;
    private javax.swing.JCheckBox cbActivarLn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel pnlCd;
    private javax.swing.JPanel pnlLn;
    private javax.swing.JTextField txtAlto;
    private javax.swing.JTextField txtAncho;
    private javax.swing.JTextField txtGrosorCd;
    private javax.swing.JTextField txtGrosorLn;
    private javax.swing.JTextField txtXLn;
    private javax.swing.JTextField txtYLn;
    // End of variables declaration//GEN-END:variables

    @Override
    public void recibirColor(Color cl) {
        aux=cl;
    }
}
