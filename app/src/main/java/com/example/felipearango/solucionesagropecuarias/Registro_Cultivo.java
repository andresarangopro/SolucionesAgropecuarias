package com.example.felipearango.solucionesagropecuarias;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import static com.example.felipearango.solucionesagropecuarias.MainActivity.calledAlready;

public class Registro_Cultivo extends AppCompatActivity implements View.OnClickListener{

    private Button btnRegistrar;
    FloatingActionButton fab;
    private EditText etPlantas,etLote,etDistancia,etLargo,etAncho,etPeso,etGrado
            ,etOrigen,etCantidadPlanta,etEspecie;
    private CheckBox chOrganico,chCultivoAsociado,chRiesgo,chDeshoja,chFloral,chHijuelos;
    private Spinner spDia,spMes,spAno,spEstCultivo,spPEnferma;
    private DatabaseReference databaseReferences;
    private FirebaseDatabase firebaseDatabases;
    private FirebaseAuth firebaseAuths;
    private  Finca fincaR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__cultivo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firebaseAuths = FirebaseAuth.getInstance();
        inicializatedFireBase();
        initComponents();
        putTextSpinners();
        /*
        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Cultivo_1.class));
            }
        });*/
    }

    private void inicializatedFireBase(){
        firebaseDatabases = FirebaseDatabase.getInstance();
        if (!calledAlready) {
            firebaseDatabases.setPersistenceEnabled(true);
            calledAlready = true;
        }
        databaseReferences = firebaseDatabases.getReference();
    }

    private void insertarCultivo(){
        FirebaseUser user = firebaseAuths.getCurrentUser();
        String prid = getIdPr(user.getEmail());
        Cultivo c = getData();
        //finish();
        //startActivity(new Intent(Registro_Cultivo.this, MenuPrincipal.class));
        eventFDAS("Productores",prid, c);
    }

    private String getIdPr(String aRemplazar){
        String remplazado=aRemplazar.replace(".", "");
        Log.e("Message",remplazado);
        return remplazado;
    }

    private void initComponents(){
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(this);
        etPlantas = (EditText) findViewById(R.id.etPlantas);
        etLote = (EditText) findViewById(R.id.etLote);
        etDistancia = (EditText) findViewById(R.id.etDistancia);
        etLargo = (EditText) findViewById(R.id.etLargo);
        etAncho= (EditText) findViewById(R.id.etAncho);
        etPeso = (EditText) findViewById(R.id.etPeso);
        etGrado = (EditText) findViewById(R.id.etGrado);
        etOrigen = (EditText) findViewById(R.id.etOrigen);
        etCantidadPlanta = (EditText) findViewById(R.id.etCantidadPlanta);
        etEspecie = (EditText) findViewById(R.id.etEspecie);

        chOrganico = (CheckBox)findViewById(R.id.chOrganico);
        chCultivoAsociado = (CheckBox) findViewById(R.id.chCultivoAsociado);
        chRiesgo = (CheckBox) findViewById(R.id.chRiesgo);
        chDeshoja = (CheckBox) findViewById(R.id.chDeshoja);
        chFloral = (CheckBox) findViewById(R.id.chFloral);
        chHijuelos = (CheckBox) findViewById(R.id.chHijuelos);

        spDia = (Spinner) findViewById(R.id.spDia);
        spMes = (Spinner) findViewById(R.id.spMes);
        spAno = (Spinner) findViewById(R.id.spAno);
        spEstCultivo = (Spinner) findViewById(R.id. spEstCultivo);
        spPEnferma = (Spinner) findViewById(R.id. spPEnferma);
    }

    @Override
    public void onClick(View v) {
        int vista = v.getId();
        switch(vista) {
            case  R.id.btnRegistrar: {
                // insertarP(p);
                insertarCultivo();
                break;
            }
        }
    }

    public void eventFDAS(final String usChildString, final String p , final Cultivo cultivo){
        databaseReferences.child(usChildString).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             //   Log.e("MAS","ENTRANDO A OBTENER DATASNAP");
                Log.e("MAS","ENTRANDO A OBTENER DATASNAP");
                Log.e("MAS", dataSnapshot.child(p).child("Fincas").getChildren()+"");
                for (DataSnapshot objSnapshot:dataSnapshot.child(p).child("Fincas").getChildren()){
                 fincaR = objSnapshot.getValue(Finca.class);
                    Log.e("MAS",fincaR.getIdFinca());
                }

                insertarCToFireB(p,fincaR.getIdFinca(),cultivo);
                Log.e("MAS","SALIENDO");
                finish();
                startActivity(new Intent(Registro_Cultivo.this, MenuPrincipal.class));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Metodo para insertar Finca a un productor P
     * @param p prodcutor a insertar finca
     * @param f Finca a insertar
     */
    private void insertarCToFireB(String p,String f,Cultivo c){
        databaseReferences.child("Productores").child(p).
                child("Fincas").child(f).child("Cultivos").child(c.getIdCultivo()).setValue(c);
        //startActivity(new Intent(Registro_Cultivo.this, MenuPrincipal.class));
        //finish();
    }



    private Cultivo getData(){
        String idCultivo,numeroLote =getTxt(etLote), fechaSiembra = "", estadoCultivo = ""
                ,origen = getTxt(etOrigen), productos = "",especie = getTxt(etEspecie),plantaEnferma = "";

        float distanciaEntrePlantas = getTxtToF(etDistancia),largoHojas = getTxtToF(etLargo),
                anchoHojas= getTxtToF(etLargo), pesoHojas = getTxtToF(etPeso),
                gradoBrix = getTxtToF(etGrado), cantidadXPlanta = getTxtToF(etCantidadPlanta);

        int manejoRiego = (chRiesgo.isChecked() ? 1 : 0),haceDesahoje = (chDeshoja.isChecked() ? 1 : 0)
                ,retiraEscapeFloral = (chFloral.isChecked() ? 1 : 0),
                retiraHijuelos = (chHijuelos.isChecked() ? 1 : 0),
                manejoOrganicoint = (chOrganico.isChecked() ? 1 : 0), totalPlanta = getTxtToInt(etPlantas)
                , cultivoAsociado = (chCultivoAsociado.isChecked() ? 1 : 0);

        fechaSiembra = spDia.getItemAtPosition(spDia.getSelectedItemPosition()).toString()+" - ";
        fechaSiembra += spMes.getItemAtPosition(spMes.getSelectedItemPosition()).toString()+" - ";
        fechaSiembra +=  spAno.getItemAtPosition(spAno.getSelectedItemPosition()).toString()+" ";
        estadoCultivo = spEstCultivo.getItemAtPosition(spEstCultivo.getSelectedItemPosition()).toString()+" ";
        plantaEnferma = spPEnferma.getItemAtPosition(spPEnferma.getSelectedItemPosition()).toString()+" ";
        idCultivo =  databaseReferences.push().getKey();
        Log.e("MAS"," CREANDO CULTIVO ");
       Cultivo c = new Cultivo(idCultivo,numeroLote,totalPlanta,fechaSiembra,distanciaEntrePlantas,
               largoHojas,anchoHojas,pesoHojas,estadoCultivo,gradoBrix,origen,manejoOrganicoint,
               productos,cantidadXPlanta,cultivoAsociado,especie,manejoRiego,haceDesahoje,retiraEscapeFloral,
               retiraHijuelos,plantaEnferma);
        Log.e("MAS"," CREO CULTIVO ");
        return c;
    }



    private String getTxt(EditText txt){
        return txt.getText().toString();
    }
    private float getTxtToF(EditText txt){
        return Float.parseFloat(txt.getText().toString());
    }
    private int getTxtToInt(EditText txt){
        return Integer.parseInt(txt.getText().toString());
    }

    private String[] makeListDate( int from, int to){
        int de = from;
        int hasta = to;
        int sum = 1;
        if(from > 0){
            de = 0;
            hasta =(to - from)+1;
            sum = 1900;
        }
        String[] date = new String[hasta];
        for (int i = de; i < hasta; i++) {
            date[i] = i+sum+"";
        }
        return date;
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
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String formattedDate = df.format(calendar.getTime());
        String[] strDia = makeListDate(0,31);
        String[] strMes = makeListDate(0,12);
        String[] strYear = makeListDate(1900,Integer.parseInt(formattedDate));
        String[] strEstadoCu = new String[]{"Bueno", "Regular"," Malo"};
        String[] strPlantaEnf = new String[]{"Entierra"," Retira "};
        spinnerPut( strEstadoCu,spEstCultivo);
        spinnerPut( strPlantaEnf,spPEnferma);
        spinnerPut(strDia,spDia);
        spinnerPut(strMes,spMes);
        spinnerPut(strYear,spAno);
    }

}
