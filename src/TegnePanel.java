
import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class TegnePanel extends JPanel {

    private final int afstandFraKantTilGraf = 30;
    private final int afstandPladsTilLabel = 30;
    private final Color linjeFarve = Color.BLUE;
    private final Color prikFarve = Color.BLACK;
    private final Color gridFarve = Color.GRAY;
    private final static Stroke GRAPH_STROKE = new BasicStroke(2f);
    private final int prikStr = 4;
    private final int numberYDivisions = 9;
    private ArrayList<Double> data = new ArrayList<Double>();
    private double max;
    private double min;
    private String type = "";
    private DataStorage d;

    public TegnePanel(String t, DataStorage dS) {
        super();

        type = t;
        /*indsætter grænseværdier alt efter hvilken sensor der benyttes. 
        *x-aksen går fra 0-9 og y-aksen fra 0-45 ved temperaturmåling og fra 0-150 ved pulsmåling*/
        if (t == "Temperatur") {
            max = 45.0;
        } else {
            max = 150;
        }
        d = dS;

    }

    @Override
    //*Metoden kaldes og der tegnes en graf over de sidste 10 værdier i databasen.
    protected synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        /*vi henter data ind hver gang vi repainter*/
        data = d.hentData(type);

        double xScale = ((double) getWidth() - (2 * afstandFraKantTilGraf) - afstandPladsTilLabel) / (data.size() - 1);
        double yScale = ((double) getHeight() - 2 * afstandFraKantTilGraf - afstandPladsTilLabel) / (max - min);

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            int x1 = (int) (i * xScale + afstandFraKantTilGraf + afstandPladsTilLabel);
            int y1 = (int) ((max - data.get(i)) * yScale + afstandFraKantTilGraf);
            graphPoints.add(new Point(x1, y1));
        }
        /*Tilføjer hvid baggrund*/
        g2.setColor(Color.WHITE);
        g2.fillRect(afstandFraKantTilGraf + afstandPladsTilLabel, afstandFraKantTilGraf, getWidth() - (2 * afstandFraKantTilGraf) - afstandPladsTilLabel, getHeight() - 2 * afstandFraKantTilGraf - afstandPladsTilLabel);
        g2.setColor(Color.BLACK);

        /*vi tilføjer gitter for y-aksen*/
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = afstandFraKantTilGraf + afstandPladsTilLabel;
            int x1 = prikStr + afstandFraKantTilGraf + afstandPladsTilLabel;
            int y0 = getHeight() - ((i * (getHeight() - afstandFraKantTilGraf * 2 - afstandPladsTilLabel)) / numberYDivisions + afstandFraKantTilGraf + afstandPladsTilLabel);
            int y1 = y0;
            if (data.size() > 0) {
                g2.setColor(gridFarve);
                g2.drawLine(afstandFraKantTilGraf + afstandPladsTilLabel + 1 + prikStr, y0, getWidth() - afstandFraKantTilGraf, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((min + (max - min) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        for (int i = 0; i < data.size(); i++) {
            if (data.size() > 1) {
                int x0 = i * (getWidth() - afstandFraKantTilGraf * 2 - afstandPladsTilLabel) / (data.size() - 1) + afstandFraKantTilGraf + afstandPladsTilLabel;
                int x1 = x0;
                int y0 = getHeight() - afstandFraKantTilGraf - afstandPladsTilLabel;
                int y1 = y0 - prikStr;
                if ((i % ((int) ((data.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridFarve);
                    g2.drawLine(x0, getHeight() - afstandFraKantTilGraf - afstandPladsTilLabel - 1 - prikStr, x1, afstandFraKantTilGraf);
                    g2.setColor(Color.BLACK);
                    String xLabel = i + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }

        /*så opretter vi x- og y-aksen*/
        g2.drawLine(afstandFraKantTilGraf + afstandPladsTilLabel, getHeight() - afstandFraKantTilGraf - afstandPladsTilLabel, afstandFraKantTilGraf + afstandPladsTilLabel, afstandFraKantTilGraf);
        g2.drawLine(afstandFraKantTilGraf + afstandPladsTilLabel, getHeight() - afstandFraKantTilGraf - afstandPladsTilLabel, getWidth() - afstandFraKantTilGraf, getHeight() - afstandFraKantTilGraf - afstandPladsTilLabel);
        /*Tegner prikker og streger ved prikkerne*/
        Stroke oldStroke = g2.getStroke();
        g2.setColor(linjeFarve);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }
        g2.setStroke(oldStroke);
        g2.setColor(prikFarve);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - prikStr / 2;
            int y = graphPoints.get(i).y - prikStr / 2;
            int ovalW = prikStr;
            int ovalH = prikStr;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }
}
