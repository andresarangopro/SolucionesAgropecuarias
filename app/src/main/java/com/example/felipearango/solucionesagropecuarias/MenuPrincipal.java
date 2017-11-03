package com.example.felipearango.solucionesagropecuarias;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

public class MenuPrincipal extends AppCompatActivity {


    ImageButton iBtnArribaIzq,iBtnArribaDer, iBtnCentroIzq,iBtnCentroDer, iBtnAbajoIzq,iBtnAbajoDer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        initComponents();
    }

    private void initComponents(){
        iBtnArribaIzq = (ImageButton) findViewById(R.id.iBtnArribaIzq);
        iBtnArribaDer = (ImageButton) findViewById(R.id.iBtnArribaDer);
        iBtnCentroIzq = (ImageButton) findViewById(R.id.iBtnCentroIzq);
        iBtnCentroDer = (ImageButton) findViewById(R.id.iBtnCentroDer);
        iBtnAbajoIzq= (ImageButton) findViewById(R.id.iBtnAbajoIzq);
        iBtnAbajoDer= (ImageButton) findViewById(R.id.iBtnAbajoDer);
    }
}
