import java.util.List;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lenovo
 */
public class Gui extends javax.swing.JPanel {

    private static boolean begin = false;
    private static double maxGr;
    private static double minGr;
    private static boolean isMax = false;
    private static boolean isMin = false;
    private static JFrame grafFrame = null;
    List<Double> data;
    /**
     * Creates new form Gui
     */
    /*konstruktør der bl.a. skjuler stop og graf knap*/
    public Gui() {
        initComponents();
        graf.setVisible(false);
        stopKnap.setVisible(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        min = new javax.swing.JTextField();
        max = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        graf = new javax.swing.JButton();
        tempLabel = new javax.swing.JLabel();
        startKnap = new javax.swing.JButton();
        stopKnap = new javax.swing.JButton();
        alarmLabel = new javax.swing.JLabel();
        maxLabel = new javax.swing.JLabel();
        minLabel = new javax.swing.JLabel();
        infoLabel = new javax.swing.JLabel();

        min.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minActionPerformed(evt);
            }
        });

        max.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxActionPerformed(evt);
            }
        });

        jLabel1.setText("Max temp");

        jLabel2.setText("Min temp");

        graf.setText("Vis Graf");
        graf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grafActionPerformed(evt);
            }
        });

        tempLabel.setText("Temperatur nu:");

        startKnap.setText("Start Måling");
        startKnap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startKnapActionPerformed(evt);
            }
        });

        stopKnap.setText("Stop Måling");
        stopKnap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopKnapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(stopKnap)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(startKnap)
                                    .addComponent(graf))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 244, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(min, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(max, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel1))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(alarmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(maxLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                                    .addComponent(minLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(57, 57, 57)))
                        .addGap(49, 49, 49))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(infoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addComponent(tempLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(min, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(max, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(graf))
                        .addGap(14, 14, 14)
                        .addComponent(startKnap)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stopKnap)
                .addGap(13, 13, 13)
                .addComponent(tempLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(alarmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(maxLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(minLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void minActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minActionPerformed

    private void grafActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grafActionPerformed
        /*ved tryk på graf knappen åbnes nyt vindue der tegner grafen vha. TegnePanel*/
        grafFrame = new JFrame("Temperatur Graf");
        grafFrame.setVisible(true);
        grafFrame.setSize(600, 400);
        grafFrame.add(new TegnePanel());
    }//GEN-LAST:event_grafActionPerformed

    private void maxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maxActionPerformed

    private void startKnapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startKnapActionPerformed
        /*ved tryk på startnappen: skjules startknappen og graf + stop knap vises*/
        graf.setVisible(true);
        stopKnap.setVisible(true);
        startKnap.setVisible(false);
        /*vi tester hvorvidt der indtastet grænseværdier, hvis ikke informerer vi brugeren
        *og grænseværdierne sættes til standardværdier: 39 og 35*/
        try {
            maxGr = Double.parseDouble(max.getText());
        } catch (NumberFormatException n) {
            maxGr = 39.0;
            maxLabel.setText("De har ikke sat en maxværdi. standardværdi benyttes: " + maxGr + " C");
            infoLabel.setText("De kan sætte en grænseværdi, ved at stoppe målingen og skrive en værdi i feltet udfra max eller min");
        }
        try {
            minGr = Double.parseDouble(min.getText());
        } catch (NumberFormatException n) {
            minGr = 35.0;
            minLabel.setText("De har ikke sat en minværdi. standardværdi benyttes: " + minGr + " C");
            infoLabel.setText("De kan sætte en grænseværdi, ved at stoppe målingen og skrive en værdi i feltet udfra max eller min");

        }
        /*den boolske værdi der fortæller hvorvidt der er trykket på starknappen sættes til sand
        *så ved Examination at målingen er startet*/
        setStartet(true);
    }//GEN-LAST:event_startKnapActionPerformed

    private void stopKnapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopKnapActionPerformed
        /*ved tryk på stopknappen: skjules stop og graf knappen + startknappen vises igen*/
        startKnap.setVisible(true);
        stopKnap.setVisible(false);
        graf.setVisible(false);
        /*den boolske værdi der fortæller hvorvidt der er trykket på starknappen sættes til falsk
        *så ved Examination at målingen er stoppet*/
        setStartet(false);
        /*labels der vises hvis der mangler grænseværdier nulstilles*/
        maxLabel.setText("");
        minLabel.setText("");
        infoLabel.setText("");
    }//GEN-LAST:event_stopKnapActionPerformed

    /*de følgende syv metoder, er metoder vi laver for at kunne ændre på gui fra Examination*/
    
    /*metode, der opdaterer temperaturværdien på gui og kalder repaint på grafen således at måling også vises på grafen*/
    public void setTemp(double temp) {
        tempLabel.setText("" + temp + " C");
        if (grafFrame != null) {

            grafFrame.repaint();
        }
    }
    /*metode, der nustiller alarm beskeden*/
    public void resetAlarm() {
        alarmLabel.setText("");
    }
    
    /*metode, der viser alarm besked på gui*/
    public void setAlarm() {
        alarmLabel.setText("ALARM: grænseværdier overskredet");
    }
    /*metode der sætter den boolsk værdi, der fortæller om vi er startet eller ej*/
    public void setStartet(boolean value) {
        begin = value;
    }
    
    /*metode der via en boolsk værdi fortæller om vi er startet*/
    public boolean isStartet() {
        return begin;
    }
    
    /*metode der returnerer maximumsværdien*/
    public double getMax() {
        return maxGr;
    }
    
    /*metode, der returnerer minimumsværdien*/
    public double getMin() {
        return minGr;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alarmLabel;
    private javax.swing.JButton graf;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField max;
    private javax.swing.JLabel maxLabel;
    private javax.swing.JTextField min;
    private javax.swing.JLabel minLabel;
    private javax.swing.JButton startKnap;
    private javax.swing.JButton stopKnap;
    private javax.swing.JLabel tempLabel;
    // End of variables declaration//GEN-END:variables
}