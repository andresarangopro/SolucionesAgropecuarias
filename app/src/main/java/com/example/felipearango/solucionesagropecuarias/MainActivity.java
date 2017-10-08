package com.example.felipearango.solucionesagropecuarias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private int a;
    public static boolean calledAlready = false;
    private Button btnP,btnF,btnC;

    private Productor p = new Productor("Reyes@gmailcom","65684984","Fernando","Calderon Mesa"
            ,"Hombre","22/08/1995","3052485129","Fernandez@gmail.com","Ninguna",1,0,1,2,0);
    private Finca f = new Finca("18","La Picazón",40,"Hectareas","Entrerrios","El tapon","Alto",12.2F
            ,"KM",30,"Minutos","Regular","Propia",2500,"GPSDIR5545","Quebrado",1);
    private Cultivo c = new Cultivo("12","FRS123",1500,"22/07/2017",150,20,60,25.2F,"Bueno",0.6F,
            "Choco",0,"Producto",3000,0,"Hidro Inno Acuatico",1,0,1,0,"Entierra");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializatedFireBase();
        initComponents();
    }

    private void initComponents(){
        btnP = (Button) findViewById(R.id.btnP);
        btnP.setOnClickListener(this);
        btnF= (Button) findViewById(R.id.btnF);
        btnF.setOnClickListener(this);
        btnC = (Button) findViewById(R.id.btnC);
        btnC.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int vista = v.getId();
        switch(vista){
            case R.id.btnP:{
                insertarP(p);
                break;
            }
            case R.id.btnF:{
                    insertarF(p,f);
                break;
            }
            case R.id.btnC:{
                insertarC(p,f,c);
                break;
            }
        }
    }

    /**
     * Meotodo para insertar productor.
     *
     * @param p productor
     */
    private void insertarP(Productor p){
        databaseReference.child("Productores").child(p.getIdProductor()).setValue(p);
    }

    /**
     * Metodo para insertar Finca a un productor P
     * @param p prodcutor a insertar finca
     * @param f Finca a insertar
     */
    private void insertarF(Productor p,Finca f){
        databaseReference.child("Productores").child(p.getIdProductor()).
                child("Fincas").child(f.getIdFinca()).setValue(f);
    }

    /**
     * Metodo para insertar cultivo a una finca f la cual pertenece a un productor p
     * @param p productor de la finca a la cual se le insertará cultivo
     * @param f finca a la cual se le insertará cultivo
     * @param c cultivo a insertar
     */
    private void insertarC(Productor p,Finca f, Cultivo c){
        databaseReference.child("Productores").child(p.getIdProductor()).
                child("Fincas").child(f.getIdFinca()).child("idCultivos").child(c.getIdCultivo()).setValue(c);
    }


    protected void inicializatedFireBase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        if (!calledAlready) {
            firebaseDatabase.setPersistenceEnabled(true);
            calledAlready = true;
        }
        databaseReference = firebaseDatabase.getReference();
    }

}
