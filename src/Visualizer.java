import processing.core.PApplet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Visualizer {

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Audio Visualizer");
        mainFrame.setResizable(false);
        mainFrame.setSize(400, 600);
        mainFrame.setLocation(200, 100);
        mainFrame.setLayout(new GridLayout(3, 2));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        JButton line = new JButton("Line");
        JButton cube = new JButton("Cube");
        JButton circle = new JButton("Circle");
        JButton rotatingBands = new JButton("Rotating Bands");
        JButton spectrum = new JButton("Spectrum");
        JButton spiral = new JButton("Spiral");

        mainFrame.add(line);
        mainFrame.add(cube);
        mainFrame.add(circle);
        mainFrame.add(rotatingBands);
        mainFrame.add(spectrum);
        mainFrame.add(spiral);

        mainFrame.setVisible(true);

        line.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PApplet.main("Line");
            }
        });

        cube.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PApplet.main("Cube");
            }
        });

        circle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PApplet.main("Circle");
            }
        });

        rotatingBands.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PApplet.main("RotatingBands");
            }
        });

        spectrum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PApplet.main("Spectrum");
            }
        });

        spiral.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PApplet.main("Spiral");
            }
        });
    }
}
