package com.example.felipearango.solucionesagropecuarias;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private int a;
    public static boolean calledAlready = false;
    private Button btnP,btnF,btnC, btnShow;

    private ArrayList<Productor> listP = new ArrayList<>();
    private ArrayList<Finca> listF = new ArrayList<>();
    private ArrayList<Cultivo> listC = new ArrayList<>();

    private Productor p = new Productor("Reye@gmailcom","65684984","Fernando","Calderon Mesa"
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
        btnShow = (Button) findViewById(R.id.btnShow);
        btnShow.setOnClickListener(this);
    }


    public void eventPD(String usChildString){
        databaseReference.child(usChildString).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //listUsers.clear();

                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){                    //
                    // Log.e("DATA", objSnapshot.getValue(Productor.class).getApellidos()+"");
                    Productor productorIn = objSnapshot.getValue(Productor.class);
                    // Log.i("USER",userIn.getId());
                     listP.add(productorIn);
                     eventFD("Productores",productorIn);
                    //Log.e("DATA", dataSnapshot.child(p.getIdProductor()).getValue(Productor.class)+"");
                }

                 // Productor uC =  dataSnapshot.child(p.getIdProductor()).getValue(Productor.class);
                // putImage(userIn);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void eventFD(final String usChildString, final Productor p){
        databaseReference.child(usChildString).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapshot:dataSnapshot.child(p.getIdProductor()).child("Fincas").getChildren()){
                    Finca fincaIn = objSnapshot.child(p.getIdProductor()).child("Fincas").getValue(Finca.class);
                    listF.add(fincaIn);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void eventCD(final String usChildString, final Productor p,final Finca f){
        databaseReference.child(usChildString).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //listUsers.clear();
                // Log.e("DATA", dataSnapshot+"");
                Log.e("DATA", dataSnapshot.child(p.getIdProductor()).child(f.getIdFinca()) +"");
                /*for (DataSnapshot objSnapshot:dataSnapshot.child(p.getIdProductor()).
                        child(f.getIdFinca()).child("idCultivos").getChildren()){

                     Log.e("DATA", objSnapshot.child(p.getIdProductor()).child(f.getIdFinca())
                            .child("idCultivos").getValue(Cultivo.class)+"");

                    Cultivo cultivoIn = objSnapshot.child(p.getIdProductor()).child(f.getIdFinca())
                            .child("idCultivos").getValue(Cultivo.class);
                    listC.add(cultivoIn);
                    //Log.e("DATA", dataSnapshot.child(p.getIdProductor()).getValue(Productor.class)+"");
                }*/
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int vista = v.getId();
        switch(vista){
            case R.id.btnP:{
               // insertarP(p);
                break;
            }
            case R.id.btnF:{
                    //insertarF(p,f);
                break;
            }
            case R.id.btnC:{
                insertarC(p,f,c);
                break;
            }
            case R.id.btnShow:{
                eventPD("Productores");
                break;
            }

        }
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

    private void loop(){
        for (int i = 0; i < listP.size() ; i++) {
            Log.e("YO", listP.get(i).getApellidos());
           // String a= listP.get(i).getIdProductor();
           // eventFD("Fincas",listP.get(i));
        }

    }

    private void ensayo(){
        for (int i = 0; i < listF.size() ; i++) {
            Log.e("YO", listF.get(i).getNombreFinca());
        }
    }

    private void loopi(){
        for (int i = 0; i < listP.size() ; i++) {
            for (int j = 0; j < listF.size() ; j++) {
                eventCD("Productor",listP.get(i),listF.get(j));
            }
        }
    }

}
