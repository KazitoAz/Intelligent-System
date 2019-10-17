package gui;
/* Code from https://gist.github.com/roooodcastro/6325153 */
/* http://stackoverflow.com/questions/8693342/drawing-a-simple-line-graph-in-java */
/* http://blog.stackoverflow.com/2009/06/attribution-required/ */
/* https://creativecommons.org/licenses/by-sa/3.0/ */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Rodrigo
 */
public class GraphPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int padding = 25;
    private int labelPadding = 25;
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color pointColor = new Color(100, 100, 100, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int pointWidth = 4;
    private int numberYDivisions = 10;
    private double[] scores;
    private double currentTime = 0;
    private double startTime = 0;
    private double endTime = 0;

    public GraphPanel(double[] scores) {
        this.scores = scores;
    }
    
    private String getDayStringFromHour(int hour)
    {
    	if (hour < 24) return "MON";
    	if (hour < 24*2) return "TUE";
    	if (hour < 24*3) return "WED";
    	if (hour < 24*4) return "THU";
    	if (hour < 24*5) return "FRI";
    	if (hour < 24*6) return "SAT";
    	return "SUN";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (scores.length - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < scores.length; i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - scores[i]) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }

        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        // create hatch marks and grid lines for y axis.
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (scores.length > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMinScore() + (getMaxScore() - getMinScore()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < scores.length; i++) {
            if (scores.length > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (scores.length - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((scores.length / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = getDayStringFromHour(i);//i + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }

        // create x and y axes 
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

      
        
        {
        	g2.setColor(Color.BLACK);
        	int x1 = (int) (startTime * xScale + padding + labelPadding);
        	int y1 = (int) (padding);
        	
        	int y2 = (int) ((getMaxScore() - getMinScore()) * yScale + padding);
        	g2.drawLine(x1, y1, x1, y2);
        }
        
        {
        	g2.setColor(Color.BLACK);
        	int x1 = (int) (endTime * xScale + padding + labelPadding);
        	int y1 = (int) (padding);
        	
        	int y2 = (int) ((getMaxScore() - getMinScore()) * yScale + padding);
        	g2.drawLine(x1, y1, x1, y2);
        }
        
        {
        	g2.setColor(Color.gray);
        	int x1 = (int) (currentTime * xScale + padding + labelPadding);
        	int y1 = (int) (padding);
        	
        	int y2 = (int) ((getMaxScore() - getMinScore()) * yScale + padding);
        	g2.drawLine(x1, y1, x1, y2);
        }
        
        Color colorLookup[] = new Color[8];
        colorLookup[0] = Color.red;
        colorLookup[1] = Color.orange;
        colorLookup[2] = Color.yellow;
        colorLookup[3] = Color.green;
        colorLookup[4] = Color.cyan;
        colorLookup[5] = Color.blue;
        colorLookup[6] = Color.magenta;
        colorLookup[7] = Color.lightGray;
        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
        	g2.setColor(colorLookup[i/24]);
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }
        
        

        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    private double getMinScore() {
        double minScore = Double.MAX_VALUE;
        for (Double score : scores) {
            minScore = Math.min(minScore, score);
        }
        return minScore < 0 ? minScore : 0;
    }

    private double getMaxScore() {
        double maxScore = Double.MIN_VALUE;
        for (Double score : scores) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }

    public void setScores(double[] scores) {
        this.scores = scores;
        invalidate();
        this.repaint();
    }
    
    public void setCurrentTime(double time)
    {
    	currentTime = time;
    }
    
    public void setStartEndTimes(double start, double end)
    {
    	/* Oh my god this is a hack but I can't brain properly right now */
    	if (endTime >= 24*7)
    	{
    		startTime = start;
    		endTime = end;
    	}
    	else
    	{
    		if (start >= endTime)
    		{
    			startTime = start;
        		endTime = end;
    		}
    	}
    }

    public double[] getScores() {
        return scores;
    }
}
