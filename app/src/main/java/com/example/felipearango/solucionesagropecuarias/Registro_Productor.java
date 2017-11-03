package com.example.felipearango.solucionesagropecuarias;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import static com.example.felipearango.solucionesagropecuarias.MainActivity.calledAlready;

public class Registro_Productor extends AppCompatActivity  implements View.OnClickListener{

    private Button btnRegistrar;
    protected DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private EditText etCedula,etNombre, etApellido, etCelular, etEmail, etPass;
    private RadioGroup rgSexo;
    private RadioButton rBHombre;
    private CheckBox chIngresos, cbEmpleado, cbPensionado, cbCasado;
    private Spinner spDia,spMes,spAno,spEtnia,spPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__productor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        startupComponents();
        firebaseAuth = FirebaseAuth.getInstance();
        inicializatedFireBase();
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

    private void putTextSpinners(){
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String formattedDate = df.format(calendar.getTime());
        String[] strDia = makeListDate(0,31);
        String[] strMes = makeListDate(0,12);
        String[] strYear = makeListDate(1900,Integer.parseInt(formattedDate));
        String[] strEtnia = new String[] {"Ninguna","Indígena","Afrodescendiente","Desplazado","Otra"};
        spinnerPut(strEtnia,spEtnia);
        spinnerPut(strDia,spDia);
        spinnerPut(strMes,spMes);
        spinnerPut(strYear,spAno);

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

    private void startupComponents(){
        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(this);

        etCedula = (EditText) findViewById(R.id.etCedula);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellido = (EditText) findViewById(R.id.etApellido);
        etCelular = (EditText) findViewById(R.id.etCelular);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);

        rgSexo =(RadioGroup) findViewById(R.id.rgSexo);

        chIngresos = (CheckBox) findViewById(R.id.chIngresos);
        cbEmpleado = (CheckBox) findViewById(R.id.cbEmpleado);
        cbPensionado = (CheckBox) findViewById(R.id.cbPensionado);
        cbCasado = (CheckBox) findViewById(R.id.cbCasado);

        spDia = (Spinner) findViewById(R.id.spDia);
        spMes = (Spinner) findViewById(R.id.spMes);
        spAno = (Spinner) findViewById(R.id.spAno);
        spPersonas = (Spinner) findViewById(R.id.spPersonas);
        spEtnia = (Spinner) findViewById(R.id.spEtnia);

        rBHombre = (RadioButton) findViewById(R.id.rBHombre);
    }

    private Productor getData(){
        Log.e("PRO","OBTENER DATOS");
        String idProd = getId(etEmail), cedula = getTxt(etCedula), nombre = getTxt(etNombre), apellido = getTxt(etCelular),
                sexo = "" ,fecha = "", celular  = getTxt(etCelular), email = getTxt(etEmail), etnia = "";
        int ingresos =  (chIngresos.isChecked() ? 1 : 0), empleados = (cbEmpleado.isChecked() ? 1 : 0),
                pensionado = (cbPensionado.isChecked() ? 1 : 0),persoCrgo = 0, casado = (cbCasado.isChecked() ? 1 : 0);
        etnia = spEtnia.getItemAtPosition(spEtnia.getSelectedItemPosition()).toString()+" ";
        sexo =  (rBHombre.isChecked() ? "Hombre" : "Mujer");
        Productor pr = new Productor(idProd, cedula, nombre, apellido, sexo,fecha, celular, email
                , etnia, ingresos, empleados, pensionado,persoCrgo, casado);
        Log.e("PRO","DATOS OBTENIDO"+idProd);
        return pr ;
    }

   private void registerUser(String mail, String pass) {
        final String mail1 = mail;
        final String pass1 = pass;
       Log.e("PROM","REGISTRANDO USUARIO");
       firebaseAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(
                Registro_Productor.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Registro_Productor.this, "REGISTER SUCCESFULLY", Toast.LENGTH_SHORT).show();
                    loginUser(mail1,pass1);
                } else {
                    Toast.makeText(Registro_Productor.this, "COULD NOT REGISTER. PLEASE TRY AGAIN", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private void loginUser(String mail, String pass){
        Log.e("PRO","LOGUEANDO USUARIO");
        firebaseAuth.signInWithEmailAndPassword(mail,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.e("PRO","USUARIO LOGUEADO");
                            insertarP(getData());
                        }else{
                            Toast.makeText(Registro_Productor.this,"Datos errados",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    private String getTxt(EditText txt){
        return txt.getText().toString();
    }

    private String getId(EditText txt){
        String aRemplazar = txt.getText().toString();
        String remplazado=aRemplazar.replace(".", "");
        return remplazado;
    }

    /**
     * Metodo para insertar productor.
     * @param p productor
     */
    private void insertarP(Productor p){
        databaseReference.child("Productores").child(p.getIdProductor()).setValue(p);
        startActivity(new Intent(getApplicationContext(), RegistroFinca.class));
    }


    protected void inicializatedFireBase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        if (!calledAlready) {
            firebaseDatabase.setPersistenceEnabled(true);
            calledAlready = true;
        }
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public void onClick(View v) {
        int vista = v.getId();
        switch(vista) {
            case  R.id.btnRegistrar: {

                String email = getTxt(etEmail), pass = getTxt(etPass);
                Toast.makeText(getBaseContext(),email+" - " +pass, Toast.LENGTH_LONG).show();
                Log.e("PRO","BTN REGISTRO");
                registerUser(email, pass);
                break;
            }
        }

    }

}
