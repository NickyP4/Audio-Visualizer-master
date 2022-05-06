import com.jogamp.nativewindow.WindowClosingProtocol;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Line extends PApplet {

    Minim minim;
    AudioPlayer player;

    public void settings() {
        size(800, 600, P3D);
    }

    public void setup() {
        surface.setResizable(false);
        surface.setLocation(600, 100);
        minim = new Minim(this);
        player = minim.loadFile("heroplanet.mp3");
        colorMode(HSB);
        player.loop();
        if (getGraphics().isGL()) {
            final com.jogamp.newt.Window w = (com.jogamp.newt.Window) getSurface().getNative();
            w.setDefaultCloseOperation(WindowClosingProtocol.WindowClosingMode.DISPOSE_ON_CLOSE);
        }
    }
    float average = 0;

    public void draw() {
        background(0);
        stroke(255);

        pushMatrix();
        translate(0, 200, 0);
        for (int j = 0; j < 2; j+=1) {
            float centerY = height / 2;
            float sum = 0;
            for (int i = 0; i < player.bufferSize(); i+=10) {
                stroke(map(i, 0, player.bufferSize(), 0, 255), 255, 255);
                line(i, centerY, i, centerY + player.left.get(i) * centerY);
                sum += abs(player.left.get(i));
            }
            average = sum / player.bufferSize();

            float ellipseWidth = average * 5000;
            noStroke();
            fill(map(average, 0, 1, 0, 255), 255, 255);
            ellipse(width / 4, centerY, ellipseWidth, ellipseWidth);
            ellipse(3 * width / 4, centerY, ellipseWidth, ellipseWidth);
            translate(0, -400, 0);
        }

        popMatrix();
        rotate((float) (PI/2.0));
        translate(0, -600, 0);
        for (int j = 0; j < 2; j+=1) {
            float centerY = height / 2;
            float sum = 0;
            for (int i = 0; i < player.bufferSize(); i+=10) {
                stroke(map(i, 0, player.bufferSize(), 0, 255), 255, 255);
                line(i, centerY, i, centerY + player.left.get(i) * centerY);
                sum += abs(player.left.get(i));
            }
            average = sum / player.bufferSize();

            float ellipseWidth = average * 5000;
            noStroke();
            fill(map(average, 0, 1, 0, 255), 255, 255);
            ellipse(width / 4, centerY, ellipseWidth, ellipseWidth);
            ellipse(3 * width / 4, centerY, ellipseWidth, ellipseWidth);
            translate(0, -600, 0);
        }

    }

    public void exit() {
        dispose();
    }

    public void keyPressed() {
        if (key == ' ') {
            if (player.isPlaying()) {
                player.pause();
            } else if (player.position() == player.length()) {
                player.rewind();
                player.play();
            } else {
                player.play();
            }
        }
    }
}