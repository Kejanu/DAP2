package Blatt04L.KevinSolution;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.LinkedList;

public class Visualization extends JFrame {

    Point[] points;
    LinkedList<Point> pointList;
    int width;


    public Visualization(Point[] points, LinkedList<Point> pointList, int width, int height) {
        createWindow("YEET", width, height);
        this.points = points;
        this.pointList = pointList;
        this.width = width;
    }

    public void createWindow(String title, int width, int height) {
        // set title, visibility, size and default close operation
        setTitle(title);
        setVisible(true);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        for (Point p : this.points) {
            //g2.fill(new Ellipse2D.Double(p.getX() * 10, p.getY() * 10, 3, 3));
            g2.fill(new Ellipse2D.Double(p.getX() * 10, p.getY() * 10, 6, 6));
        }

        Point p1;
        Iterator it = pointList.iterator();
        p1 = (Point) it.next();

        while (it.hasNext()) {
            Point p2 = (Point) it.next();
            Line2D line = new Line2D.Double(p1.getX() * 10, p1.getY()* 10, p2.getX()* 10, p2.getY()* 10);
            p1 = p2;
            g2.draw(line);
        }
    }

    public double betterX(double x) {
        return x * 10 - 40;
    }

    public double betterY(double y) {
        return width - y * 10 + 40;
    }

    public static void main(String[] args) {
        //Visualization s = new Visualization(null, null);
    }
}
