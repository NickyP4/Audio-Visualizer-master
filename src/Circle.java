import com.jogamp.nativewindow.WindowClosingProtocol;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Circle extends PApplet {

    Minim minim;
    AudioPlayer player;

    int radius = 200;
    float rad = 60;

    public void settings() {
        size(800, 600, P3D);
    }

    public void setup() {
        surface.setResizable(false);
        surface.setLocation(600, 100);
        minim = new Minim(this);
        player = minim.loadFile("heroplanet.mp3");
        
        player.loop();
        if (getGraphics().isGL()) {
            final com.jogamp.newt.Window w = (com.jogamp.newt.Window) getSurface().getNative();
            w.setDefaultCloseOperation(WindowClosingProtocol.WindowClosingMode.DISPOSE_ON_CLOSE);
        }
    }

    public void draw() {
        noStroke();
        rect(0, 0, width, height);
        translate(width / 2, height / 2);
        noFill();

        int bufferSize = player.bufferSize();
        for (int i = 0; i < bufferSize - 1; i += 20) {
            rad = rad + 0.9f * player.left.get(i);
            ellipse(0, 0, 2 * rad, 2 * rad);

            float x1 = (radius) * cos(i * 2 * PI / bufferSize);
            float y1 = (radius) * sin(i * 2 * PI / bufferSize);
            float x2 = (radius + player.left.get(i) * 400) * cos(i * 2 * PI / bufferSize);
            float y2 = (radius + player.left.get(i) * 400) * sin(i * 2 * PI / bufferSize);
            strokeWeight(9);
            stroke(random(0, 255), random(0, 255), random(0, 255));
            line(x1, y1, x2, y2);
        }


        fill(0);
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
