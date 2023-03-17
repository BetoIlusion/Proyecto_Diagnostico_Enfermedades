package com.unlockdev.diagnosticodeenfermedades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class Ventana_Resultado extends AppCompatActivity {
    TextView msj;
    public void Reintentar(View view){
        Activity ActivityPreguntas=this;
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("¿Desea Volver a Empezar?").setPositiveButton("Si",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        Intent i=new Intent(ActivityPreguntas, Ventana_Preguntas.class);
                        startActivity(i);
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
    public void Salir(View view){
        Activity ActivityPreguntas=this;
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("¿Desea salir del Diagnostico de Enfermedadades?").setPositiveButton("Si",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        Intent intent=new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_resultado);
        msj=findViewById(R.id.txt_Respuesta);
        String resultado=getIntent().getStringExtra("respuesta");
        msj.setText(resultado);
        msj.setMovementMethod(new ScrollingMovementMethod());
 }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}