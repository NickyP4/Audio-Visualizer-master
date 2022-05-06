import com.jogamp.nativewindow.WindowClosingProtocol;

public class Cube extends Visual {

    public void settings() {
        size(800, 600, P3D);
    }

    public void setup() {
        surface.setResizable(false);
        surface.setLocation(600, 100);
        colorMode(HSB);
        setFrameSize(256);
        startMinim();
        loadAudio("heroplanet.mp3");
        getAudioPlayer().loop();
        if (getGraphics().isGL()) {
            final com.jogamp.newt.Window w = (com.jogamp.newt.Window) getSurface().getNative();
            w.setDefaultCloseOperation(WindowClosingProtocol.WindowClosingMode.DISPOSE_ON_CLOSE);
        }
    }

    float smoothedBoxSize = 0;

    public void draw() {
        calculateAverageAmplitude();
        background(0);
        noFill();
        lights();
        stroke(map(getSmoothedAmplitude(), 0, 1, 0, 1000), 255, 255);
        camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
        translate(0, 0, -250);

        float boxSize = 50 + (getAmplitude() * 300);//map(average, 0, 1, 100, 400);
        smoothedBoxSize = lerp(smoothedBoxSize, boxSize, 0.2f);

        pushMatrix();
        translate(-100, 0, 0);
        rotateY(angle);
        rotateX(angle);
        box(smoothedBoxSize);

        popMatrix();
        pushMatrix();
        translate(100, 0, 0);
        rotateY(angle);
        rotateX(angle);
        strokeWeight(5);
        box(smoothedBoxSize);

        popMatrix();
        pushMatrix();
        translate(0, -100, 0);
        rotateY(angle);
        rotateX(angle);
        strokeWeight(5);
        box(smoothedBoxSize);

        popMatrix();
        pushMatrix();
        translate(0, 100, 0);
        rotateY(angle);
        rotateX(angle);
        strokeWeight(5);
        box(smoothedBoxSize);

        popMatrix();
        rotateY(angle);
        rotateX(angle);
        strokeWeight(5);
        box(smoothedBoxSize);

        angle += 0.01f;
    }

    float angle = 0;

    public void keyPressed() {
        if (key == ' ') {
            if (getAudioPlayer().isPlaying()) {
                getAudioPlayer().pause();
            } else if (getAudioPlayer().position() == getAudioPlayer().length()) {
                getAudioPlayer().rewind();
                getAudioPlayer().play();
            } else {
                getAudioPlayer().play();
            }
        }
    }
} 