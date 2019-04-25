package Blatt04L.KevinSolution;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Visualization extends JFrame {

    Point[] points;
    LinkedList<Point> pointList;
    int width;


    public Visualization(Point[] points, LinkedList<Point> pointList, int width, int height) {
        this.points = points;
        this.pointList = pointList;
        this.width = width;
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

        for (Point p : this.points) {
            // -3 cause ellipse expansion
            g2.fill(new Ellipse2D.Double(betterX(p.getX()) - 3, betterY(p.getY()) - 3, 6, 6));
        }

        Point p1;
        Iterator it = pointList.iterator();
        p1 = (Point) it.next();
        g2.setStroke(new BasicStroke(2f));

        g2.setColor(new Color(0, 192, 57));
        while (it.hasNext()) {
            Point p2 = (Point) it.next();
            Line2D line = new Line2D.Double(betterX(p1.getX()), betterY(p1.getY()), betterX(p2.getX()), betterY(p2.getY()));
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
