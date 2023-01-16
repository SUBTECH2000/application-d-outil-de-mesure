package com.example.outildemesure.controleur;

import android.view.View;
import android.widget.LinearLayout;

public class threadfleche extends Thread{

    private boolean imgXR = false;
    private boolean imgXL = false;
    private boolean imgYT = false;
    private boolean imgYB = false;

    private LinearLayout LIXL = null;
    private LinearLayout LIXR = null;
    private LinearLayout LIYB = null;
    private LinearLayout LIYT = null;

    public void setImgXR(boolean imgXR) {
        this.imgXR = imgXR;
    }

    public void setImgXL(boolean imgXL) {
        this.imgXL = imgXL;
    }

    public void setImgYT(boolean imgYT) {
        this.imgYT = imgYT;
    }

    public void setImgYB(boolean imgYB) {
        this.imgYB = imgYB;
    }

    public void setLIXL(LinearLayout LIXL) {
        this.LIXL = LIXL;
    }

    public void setLIXR(LinearLayout LIXR) {
        this.LIXR = LIXR;
    }

    public void setLIYB(LinearLayout LIYB) {
        this.LIYB = LIYB;
    }

    public void setLIYT(LinearLayout LIYT) {
        this.LIYT = LIYT;
    }

    public threadfleche(LinearLayout LIXL, LinearLayout LIXR, LinearLayout LIYB, LinearLayout LIYT) {
        this.LIXL = LIXL;
        this.LIXR = LIXR;
        this.LIYB = LIYB;
        this.LIYT = LIYT;
    }
    private boolean state1 = false;
    private boolean state2 = false;
    private boolean state3 = false;
    private boolean state4 = false;

    void onoff1(){
        if(imgXL == true){
            if(state1 == true){
                LIXL.setVisibility(View.VISIBLE);
                state1 = !state1;
            }
            else{
                LIXL.setVisibility(View.INVISIBLE);
                state1 = !state1;
            }

        }
        else{LIXL.setVisibility(View.VISIBLE);}
    }
    void onoff2(){
        if(imgXR == true){
            if(state2 == true){
                LIXR.setVisibility(View.VISIBLE);
                state2 = !state2;
            }
            else{
                LIXR.setVisibility(View.INVISIBLE);
                state2 = !state2;
            }

        }
        else{LIXR.setVisibility(View.VISIBLE);}
    }
    void onoff3(){
        if(imgYT == true){

            if(state3 == true){
                LIYT.setVisibility(View.VISIBLE);
                state3 = !state3;
            }
            else{
                LIYT.setVisibility(View.INVISIBLE);
                state3 = !state3;
            }

        }
        else{LIYT.setVisibility(View.VISIBLE);}
    }
    void onoff4(){
        if(imgYB == true){
            if(state4 == true){
                LIYB.setVisibility(View.VISIBLE);
                state4 = !state4;
            }
            else{
                LIYB.setVisibility(View.INVISIBLE);
                state4 = !state4;
            }

        }
        else{LIYB.setVisibility(View.VISIBLE);}
    }


    private boolean running = false;
    private final static int SKIP_TICKS = 200;

    // défini l'état du thread : true ou false
    public void setRunning(boolean run) {
        running = run;
    }

    // démarrage du thread
    @Override
    public void run()
    {
        // déclaration des temps de départ et de pause
        long startTime;
        long sleepTime;

        // boucle tant que running est vrai
        // il devient faux par setRunning(false), notamment lors de l'arrêt de l'application
        // cf : surfaceDestroyed() dans GameView.java
        while (running)
        {
            // horodatage actuel
            startTime = System.currentTimeMillis();

            try {
                onoff1();
                onoff2();
                onoff3();
                onoff4();
            }
            catch (Exception e) {}

            // Calcul du temps de pause, et pause si nécessaire
            // afin de ne réaliser le travail ci-dessus que X fois par secondes
            sleepTime = SKIP_TICKS-(System.currentTimeMillis() - startTime);
            try {
                if (sleepTime >= 0) {sleep(sleepTime);}
            }
            catch (Exception e) {}
        } // boucle while (running)
    } // public void run()
}
