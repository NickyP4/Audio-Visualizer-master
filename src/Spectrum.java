import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Spectrum extends PApplet {

    Minim minim;
    AudioPlayer player;

    int radius = 150;

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
        translate(width/2, height/2);
        noFill();
        fill(-1, 10);


        int bufferSize = player.bufferSize();
        for (int i = 0; i < bufferSize - 1; i+=15) {
            float x1 = (radius)*cos(i*2*PI/bufferSize) * 5;
            float y1 = (radius)*sin(i*2*PI/bufferSize) * 5;
            float x2 = (radius + player.left.get(i)*100)*cos(i*2*PI/bufferSize);
            float y2 = (radius + player.left.get(i)*100)*sin(i*2*PI/bufferSize);
            stroke(random(0,255),random(0,255),random(0,255));
            strokeWeight(6);
            line(x1, y1, x2, y2);
        }
        fill(0);

        beginShape();

        for (int i = 0; i < bufferSize; i+=30) {
            float x2 = (radius + player.left.get(i)*100)*cos(i*2*PI/bufferSize);
            float y2 = (radius + player.left.get(i)*100)*sin(i*2*PI/bufferSize);

            strokeWeight(2);
            vertex(x2, y2);
            pushStyle();
            stroke(-1);
            strokeWeight(20);
            point(x2, y2);
            popStyle();
        }
        endShape();
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
