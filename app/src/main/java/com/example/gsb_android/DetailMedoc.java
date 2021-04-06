package com.example.gsb_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DetailMedoc extends AppCompatActivity {
    Medicament medicament = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_medoc);
        String NomMedicament =this.getIntent().getExtras().getString("medicament");
        medicament =  Medicament.getMedicamentByNomComm(NomMedicament);

        TextView CodeDepotMedic = (TextView) findViewById((R.id.CodeDepotMedic));
        TextView NomComMedic = (TextView) findViewById((R.id.NomComMedic));
        TextView FamilleMedoc = (TextView) findViewById((R.id.FamilleMedoc));
        TextView CompositionMedic = (TextView) findViewById((R.id.CompositionMedic));
        TextView effetsMedic = (TextView) findViewById((R.id.effetsMedic));
        TextView ContreIndicationMedic = (TextView) findViewById((R.id.ContreIndicationMedic));

        CodeDepotMedic.setText(medicament.getDepotLegal());
        NomComMedic.setText(medicament.getNomCommercial());
        FamilleMedoc.setText(medicament.getTypeFamille());
        CompositionMedic.setText(medicament.getComposition());
        effetsMedic.setText(medicament.getEffets());
        ContreIndicationMedic.setText(medicament.getcontreIndication());


    }
}
