package com.example.felipearango.solucionesagropecuarias;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.felipearango.solucionesagropecuarias.MainActivity.calledAlready;

public class RegistroFinca extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegistrar;
    protected DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private EditText etLote,etArea, etSector, etCabecera, etTipoCab,etAltitudF,etUbicacion;
    private Spinner spHectareas,spVereda, spMunicipio, spKM,spMin, spAcceso,spAlq, spTerr;
    private CheckBox cbCultivoA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_finca);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firebaseAuth = FirebaseAuth.getInstance();
        inicializatedFireBase();
        initComponents();
        putTextSpinners();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    protected void inicializatedFireBase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        if (!calledAlready) {
            firebaseDatabase.setPersistenceEnabled(true);
            calledAlready = true;
        }
        databaseReference = firebaseDatabase.getReference();
    }

    private void initComponents(){
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(this);

        etLote = (EditText) findViewById(R.id.etLote);
        etArea = (EditText) findViewById(R.id.etArea);
        etSector = (EditText) findViewById(R.id.etSector);
        etCabecera = (EditText) findViewById(R.id.etCabecera);
        etTipoCab = (EditText) findViewById(R.id.etTipoCab);
        etAltitudF = (EditText) findViewById(R.id.etAltitudF);
        etUbicacion= (EditText) findViewById(R.id.etUbicación);

        spHectareas = (Spinner) findViewById(R.id.spHectáreas);
        spVereda = (Spinner) findViewById(R.id.spVereda);
        spMunicipio = (Spinner) findViewById(R.id.spMunicipio);
        spKM = (Spinner) findViewById(R.id.spKM);
        spMin = (Spinner) findViewById(R.id.spMin);
        spAcceso= (Spinner) findViewById(R.id.spAcceso);
        spAlq= (Spinner) findViewById(R.id.spAlq);
        spTerr = (Spinner) findViewById(R.id.spTerr);

    }

    private void spinnerPut(String[] str, Spinner sp){
        ArrayAdapter<String> comboAdapter;
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, str);
        //Implemento el adapter con el contexto, layout, listaFrutas
        comboAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, list);
        //Cargo el spinner con los datos
        sp.setAdapter(comboAdapter);
    }

    private void putTextSpinners(){
        String[] strareaCampo = new String[]{"Hectáreas","Cuadras","Metros"};
        String[] strdistCab = new String[]{"Kilómetros","Metros"};
        String[] strtimeCab = new String[]{"Horas","Minutos"};
        String[] strviaAcc = new String[]{"Bueno","Regular", "Malo"};
        String[] strfincaL = new String[]{"Propia","Alquilada","Comodato","Otra"};
        String[] strterreno = new String[]{"Plano", "Ondulado", "Quebrado", "Empinado", "Montañoso", "Otros"};

        spinnerPut(strareaCampo,spHectareas);
        spinnerPut(strdistCab,spKM);
        spinnerPut(strtimeCab,spMin);
        spinnerPut(strviaAcc,spAcceso);
        spinnerPut(strfincaL,spAlq);
        spinnerPut(strterreno,spTerr);
    }


    private String getTxt(EditText txt){
        return txt.getText().toString();
    }
    private float getTxtToF(EditText txt){
        return Float.parseFloat(txt.getText().toString());
    }

    private String getIdPr(String aRemplazar){
        String remplazado=aRemplazar.replace(".", "");
        Log.e("Message",remplazado);
        return remplazado;
    }

    private Finca getData(){
        String lote = getTxt(etLote), areaMed ="" , municipio ="", vereda = "",
                sector = getTxt(etSector), distanciaCabeceraMed = "", tiempoCabeceraMed = "",
                viaDeacceso = "", alquilada ="", ubicacion ="",terreno = "";
        float areNum = getTxtToF(etArea),distanciaCabeceraNum = getTxtToF(etCabecera),
               tiempoCabeceraNum = getTxtToF(etTipoCab),altitud = getTxtToF(etAltitudF);
        int  cultivoAloe =0;
        areaMed = spHectareas.getItemAtPosition(spHectareas.getSelectedItemPosition()).toString()+" ";
        distanciaCabeceraMed = spKM.getItemAtPosition(spKM.getSelectedItemPosition()).toString()+" ";
        viaDeacceso = spAcceso.getItemAtPosition(spAcceso.getSelectedItemPosition()).toString()+" ";
        alquilada = spAlq.getItemAtPosition(spAlq.getSelectedItemPosition()).toString()+" ";
        terreno = spTerr.getItemAtPosition(spTerr.getSelectedItemPosition()).toString()+" ";
        String idFin =  databaseReference.push().getKey();
        Finca finca = new Finca(idFin,lote,areNum,areaMed,municipio,vereda,sector,distanciaCabeceraNum
                              ,distanciaCabeceraMed,tiempoCabeceraNum,tiempoCabeceraMed,viaDeacceso,
                                alquilada, altitud, ubicacion,terreno, cultivoAloe);
        return finca;
    }

    private void insertarFinca(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String prid = getIdPr(user.getEmail());
        insertarFToFireB(prid,getData());
    }

    /**
     * Metodo para insertar Finca a un productor P
     * @param p prodcutor a insertar finca
     * @param f Finca a insertar
     */
    private void insertarFToFireB(String p,Finca f){
        databaseReference.child("Productores").child(p).
                child("Fincas").child(f.getIdFinca()).setValue(f);

        startActivity(new Intent(RegistroFinca.this, Registro_Cultivo.class));
    }

    @Override
    public void onClick(View v) {
        if(v == btnRegistrar){
            insertarFinca();

        }
    }
}
