package Blatt04L.KevinSolution;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Iterator;
import java.util.LinkedList;

import Blatt04L.Interfaces.UniversalPoint;


public class ConvexVisualization extends JFrame {

    UniversalPoint[] points;
    LinkedList<UniversalPoint> pointList;
    int width;
    int factor;


    //implement interface UniversalPoint to use this

    public ConvexVisualization(UniversalPoint[] points, LinkedList<UniversalPoint> pointList, int width, int height, int factor) {
        this.points = points;
        this.pointList = pointList;
        this.width = width;
        this.factor=factor;
        createWindow("YEET", width, height);

    }

    public void createWindow(String title, int width, int height) {
        // set title, visibility, size and default close operation
        setTitle(title);
        setVisible(true);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(37, 44, 76));
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);

        for (UniversalPoint p : this.points) {
            // -3 cause ellipse expansion
            g2.fill(new Ellipse2D.Double(betterX(p.getX(),factor) - 3, betterY(p.getY(),factor) - 3, 6, 6));
        }

        UniversalPoint p1;
        Iterator it = pointList.iterator();
        p1 = (UniversalPoint) it.next();
        g2.setStroke(new BasicStroke(2f));

        g2.setColor(new Color(0, 192, 57));
        while (it.hasNext()) {
            UniversalPoint p2 = (UniversalPoint) it.next();
            Line2D line = new Line2D.Double(betterX(p1.getX(),factor), betterY(p1.getY(),factor), betterX(p2.getX(),factor), betterY(p2.getY(),factor));
            p1 = p2;
            g2.draw(line);
        }
    }

    public double betterX(double x, int factor) {
        return x * factor - 40;
    }

    public double betterY(double y, int factor) {
        return width - y * factor + 40;
    }

    public static void main(String[] args) {
        //Visualization s = new Visualization(null, null);
    }
}
