import com.jogamp.nativewindow.WindowClosingProtocol;

public class RotatingBands extends Visual {


    public void settings()
    {
        size(800, 600, P3D);
    }

    public void setup()
    {
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

    float[] radius = {100, 200, 300, 400};
    float rot = 0;

    public void draw(){
        calculateAverageAmplitude();
        try {
            calculateFFT();
        }
        catch(VisualException e) {
            e.printStackTrace();
        }
        calculateFrequencyBands();
        background(0);
        noFill();
        stroke(255);
        lights();
        stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        camera(0, -500, 500, 0, 0, 0, 0, 1, 0);

        rot += getAmplitude() / 20.0f;

        float[] bands = getSmoothedBands();
        for (int r = 0; r < 4; r++) {
            rotateY(rot * -1 * (r + 1));
            for(int i = 0 ; i < bands.length ; i ++){
                float theta = map(i, 0, bands.length, 0, TWO_PI);

                stroke(map(i, 0, bands.length, 0, 255), 255, 255);
                float x = sin(theta) * radius[r];
                float z = cos(theta) * radius[r];
                float h = bands[i];
                pushMatrix();
                translate(x, - h / 2 , z);
                rotateY(theta);
                strokeWeight(8);
                box(50, h, 50);
                popMatrix();
            }
        }
    }

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