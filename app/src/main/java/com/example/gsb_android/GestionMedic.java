package com.example.gsb_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GestionMedic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_medic);

        final Button btnConsultParFam = (Button) findViewById(R.id.consultMedicParFam);
        btnConsultParFam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentJeu = new Intent( GestionMedic.this,MedocParFamille.class);
                GestionMedic.this.startActivityForResult( intentJeu,10);
            }


        });

        final Button btnConsultParCode = (Button) findViewById(R.id.consultMedicParCodeD);
        btnConsultParCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentJeu = new Intent( GestionMedic.this, MedocParCodeD.class);
                GestionMedic.this.startActivityForResult( intentJeu,10);
            }


        });
    }
}
