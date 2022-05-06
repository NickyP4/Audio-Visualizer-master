import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Spiral extends PApplet {

    Minim minim;
    AudioPlayer player;

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        surface.setResizable(false);
        surface.setLocation(600, 100);
        minim = new Minim(this);
        player = minim.loadFile("heroplanet.mp3");
        player.loop();
    }

    public void draw() {
        noStroke();
        rect(0, 0, width, height);
        noFill();
        fill(-1, 10);

        int bufferSize = player.bufferSize();
        for (int i = 0; i < bufferSize - 1; i++) {

            translate(map(i, 0, bufferSize, 0, width), map(i, 0, bufferSize, 0, height));
            float x1 = (player.left.get(i))*cos(i*2*PI/bufferSize);
            float y1 = (player.left.get(i))*sin(i*2*PI/bufferSize);
            float x2 = (player.left.get(i) + player.left.get(i)*400)*cos(i*2*PI/bufferSize);
            float y2 = (player.left.get(i) + player.left.get(i)*800)*sin(i*2*PI/bufferSize);
            stroke(random(0,255),random(0,255),random(0,255));
            strokeWeight(2);
            ellipse(x1, y1, x2, y2);
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