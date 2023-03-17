package com.unlockdev.diagnosticodeenfermedades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Objects;

public class Ventana_Preguntas extends AppCompatActivity {
    Arbol a1;
    String sintoma;
    TextView msjTextView;

    //metodo para mostrar el resultado del diagnostico
    public void RespFinal(){
        Intent i=new Intent(Ventana_Preguntas.this, Ventana_Resultado.class);
        i.putExtra("respuesta",a1.mostrarTodasLasEnfermedades());
        //i.putExtra("respuesta","Tiene la enfermedad "+a1.EnfermedadMasProbableSTRING()+" con una probabilidad del "+a1.enfMasProbableFLOAT()+"%");
        startActivity(i);



    }

    //metodo para el boton en caso de responder SI
    public void PressbtnSI(View view){
        msjTextView = findViewById(R.id.txt_Pregunta);
        a1.insertarSintomaArbol(sintoma, 0);        //se inserta el sintoma en el arbol
        sintoma=a1.recogerSintoma();                            //recoge el siguiente sintoma
        if (!Objects.equals(sintoma, "vacio" ) && a1.PosibleEnfermedad()==null){
            msjTextView.setText("Usted tiene "+sintoma+"?");
        }else {
            RespFinal();                        //se dirige a la ventana final mostrando el resultado;
        }
    }
    public void PressbtnNO(View view){
        msjTextView = findViewById(R.id.txt_Pregunta);
        a1.insertarSintomaArbol(sintoma, 1);
        sintoma=a1.recogerSintoma();
        if (!Objects.equals(sintoma, "vacio") && a1.PosibleEnfermedad()==null){
            msjTextView.setText("Usted tiene "+sintoma+"?");
        }else {
            RespFinal();
        }
    }

    //reinicia una Activity
    public static void reiniciarActivity(Activity actividad){
        Intent intent=new Intent();
        intent.setClass(actividad, actividad.getClass());
        //llamamos a la actividad
        actividad.startActivity(intent);
        //finalizamos la actividad actual
        actividad.finish();
    }

    //metodo para el boton de reinicio
    public void PressbtnReiniciar(View view){
        Activity ActivityPreguntas=this;
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("¿Desea Reiniciar las Preguntas?").setPositiveButton("Si",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        reiniciarActivity(ActivityPreguntas);
                    }
                })
                .setNegativeButton("Cancelar",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int which){
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    //PROCEDIMIENTO POR DEFECTO PARA INICIAR ESTE ACTIVITY,
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_preguntas);

        //creamos el arbol una vez se inicia la aplicacion
        a1=new Arbol();

        //ENFERMEDAD #1
        a1.insertarInformacion("Covid", "fiebre", 5);
        a1.insertarInformacion("Covid", "tos", 5);
        a1.insertarInformacion("Covid", "dificultad de respirar", 4);
        a1.insertarInformacion("Covid", "fatiga", 4);
        a1.insertarInformacion("Covid", "dolor muscular", 5);
        a1.insertarInformacion("Covid", "dolor de cabeza", 5);
        a1.insertarInformacion("Covid", "dolor de garganta", 4);
        a1.insertarInformacion("Covid", "nauseas", 1);
        a1.insertarInformacion("Covid", "diarrea", 4);
        a1.insertarInformacion("Covid", "perdida del olfato", 5);
        a1.insertarInformacion("Covid", "perdida del gusto", 5);
        //ENFERMEDAD #2
        a1.insertarInformacion("Dengue", "nauseas",2);
        a1.insertarInformacion("Dengue", "fiebre",5);
        a1.insertarInformacion("Dengue", "sarpullido",1);
        a1.insertarInformacion("Dengue", "dolor muscular",3);
        a1.insertarInformacion("Dengue", "dolor de cabeza",4);
        a1.insertarInformacion("Dengue", "dolor de ojos",3);
        //ENFERMEDAD #3
        a1.insertarInformacion("Zika", "fiebre", 5);
        a1.insertarInformacion("Zika", "sarpullido", 2);
        a1.insertarInformacion("Zika", "dolor de cabeza", 4);
        a1.insertarInformacion("Zika", "dolor articular", 3);
        a1.insertarInformacion("Zika", "conjutivitis", 1);
        a1.insertarInformacion("Zika", "dolor muscular", 3);
        //ENFERMEDAD #4
        a1.insertarInformacion("Chikungunya", "fiebre", 5);
        a1.insertarInformacion("Chikungunya", "dolor articular", 2);
        a1.insertarInformacion("Chikungunya", "dolor muscular", 2);
        a1.insertarInformacion("Chikungunya", "sarpullido", 1);
        a1.insertarInformacion("Chikungunya", "fatiga", 3);
        //ENFERMEDAD #5
        a1.insertarInformacion("Gripe", "fiebre", 5);
        a1.insertarInformacion("Gripe", "escalofrio", 4);
        a1.insertarInformacion("Gripe", "dolor de cabeza", 3);
        a1.insertarInformacion("Gripe", "tos", 1);
        a1.insertarInformacion("Gripe", "estornudo", 4);
        a1.insertarInformacion("Gripe", "dolor de garganta", 2);
        a1.insertarInformacion("Gripe", "dolor muscular", 3);
        a1.insertarInformacion("Gripe", "perdida de apetito", 3);
        a1.insertarInformacion("Gripe", "fatiga", 5);
        //ENFERMEDAD #6
        a1.insertarInformacion("Asma", "dificultad de respirar", 3);
        a1.insertarInformacion("Asma", "dolor de pecho", 2);
        a1.insertarInformacion("Asma", "tos", 3);
        a1.insertarInformacion("Asma", "silbido al respirar", 3);
        a1.insertarInformacion("Asma", "incapacidad de hablar", 3);
        //ENFERMEDAD #7
        a1.insertarInformacion("Fiebre amarilla", "fiebre", 2);
        a1.insertarInformacion("Fiebre amarilla", "delirio", 5);
        a1.insertarInformacion("Fiebre amarilla", "dolor muscular", 3);
        a1.insertarInformacion("Fiebre amarilla", "dolor de cabeza", 2);
        a1.insertarInformacion("Fiebre amarilla", "escalofrios", 3);
        a1.insertarInformacion("Fiebre amarilla", "fatiga", 3);
        a1.insertarInformacion("Fiebre amarilla", "nauseas", 3);
        a1.insertarInformacion("Fiebre amarilla", "perdida de apetito", 2);
        a1.insertarInformacion("Fiebre amarilla", "ojos amarillos", 5);
        a1.insertarInformacion("Fiebre amarilla", "sangrado en encias/orina", 5);
        a1.insertarInformacion("Fiebre amarilla", "vomito", 3);
        //ENFERMEDAD #8
        a1.insertarInformacion("Chagas", "mareos", 3);
        a1.insertarInformacion("Chagas", "desmayos", 5);
        a1.insertarInformacion("Chagas", "palpitaciones", 4);
        a1.insertarInformacion("Chagas", "dolor de pecho", 5);
        a1.insertarInformacion("Chagas", "fatiga", 1);
        a1.insertarInformacion("Chagas", "estreñimiento", 2);
        a1.insertarInformacion("Chagas", "dificultad para tragar", 2);
        //ENFERMEDAD #9
        a1.insertarInformacion("Dengue hemorragico", "nauseas", 2);
        a1.insertarInformacion("Dengue hemorragico", "fiebre", 3);
        a1.insertarInformacion("Dengue hemorragico", "sarpullido", 1);
        a1.insertarInformacion("Dengue hemorragico", "dolor muscular", 3);
        a1.insertarInformacion("Dengue hemorragico", "dolor de cabeza", 4);
        a1.insertarInformacion("Dengue hemorragico", "dolor de ojos", 3);
        a1.insertarInformacion("Dengue hemorragico", "dolor de abdomen", 4);
        a1.insertarInformacion("Dengue hemorragico", "dificultad para respirar", 5);
        a1.insertarInformacion("Dengue hemorragico", "piel palida", 2);
        a1.insertarInformacion("Dengue hemorragico", "sangrado encias/nariz", 5);
        a1.insertarInformacion("Dengue hemorragico", "sed excesiva", 3);
        //ENFERMEDAD #10
        a1.insertarInformacion("Enfermedad renal", "fatiga", 3);
        a1.insertarInformacion("Enfermedad renal", "dolor de huesos", 5);
        a1.insertarInformacion("Enfermedad renal", "piel seca", 2);
        a1.insertarInformacion("Enfermedad renal", "nauseas", 1);
        a1.insertarInformacion("Enfermedad renal", "perdida de peso", 3);
        a1.insertarInformacion("Enfermedad renal", "sed excesiva", 2);
        a1.insertarInformacion("Enfermedad renal", "dolor de espalda", 4);
        a1.insertarInformacion("Enfermedad renal", "entumecimiento de mano o pies", 5);
        //ENFERMEDAD #11
        a1.insertarInformacion("Colecistitis", "nauseas", 2);
        a1.insertarInformacion("Colecistitis", "vomito", 3);
        a1.insertarInformacion("Colecistitis", "dolor de abdomen", 4);
        a1.insertarInformacion("Colecistitis", "fiebre", 3);
        a1.insertarInformacion("Colecistitis", "dolor al respirar", 3);
        a1.insertarInformacion("Colecistitis", "dolor despues de comer", 5);
        a1.insertarInformacion("Colecistitis", "dolor nocturno", 4);
        //ENFERMEDAD #12
        a1.insertarInformacion("Mayaro", "diarrea", 3);
        a1.insertarInformacion("Mayaro", "dolor de cabeza", 2);
        a1.insertarInformacion("Mayaro", "dolor de ojos", 5);
        a1.insertarInformacion("Mayaro", "erupcion cutanea", 2);
        a1.insertarInformacion("Mayaro", "fiebre", 2);
        a1.insertarInformacion("Mayaro", "mareos", 5);
        a1.insertarInformacion("Mayaro", "nauseas", 3);
        a1.insertarInformacion("Mayaro", "vomito", 3);
        a1.insertarInformacion("Mayaro", "perdida de peso", 5);

        //recogemos el primer sintoma y lo mandamos a preguntar
        sintoma=a1.recogerSintoma();
        msjTextView = findViewById(R.id.txt_Pregunta); //ENLAZAMOS LA VARIABLE "msjTextView" AL TEXTVIEW DEL ACTIVITY
        msjTextView.setText("Usted tiene "+sintoma+"?");             //SE ENVIRA A IMPRIMIR EL PRIMER ELEMENTO DE VECTOR, LA PRIMERA PREGUNTA
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}