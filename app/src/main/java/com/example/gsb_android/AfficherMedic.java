package com.example.gsb_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AfficherMedic extends AppCompatActivity {
    List<Medicament> listMedic = new ArrayList<Medicament>();
    String typeRecherche;
    String valeurRecherche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_medic);
        ListView myList = (ListView) findViewById((R.id.ListMedic));

        // On recupere les extra
        typeRecherche = this.getIntent().getExtras().getString("TypeRecherche");
        valeurRecherche =this.getIntent().getExtras().getString("ValeurType");

        List<Medicament> lesMedicAffiche = new ArrayList<Medicament>();
        final List<String> lesNomsMedicAffiche = new ArrayList<String>();

        // On verifie quelle type de medicament on souhaite afficher ( type famill ou par code de depot)
        if(typeRecherche.equals("typeFamille")){
            lesMedicAffiche = Medicament.getMedicamentByTypeFamille(valeurRecherche);
        }else if(typeRecherche.equals("codeD")){
            lesMedicAffiche = Medicament.getMedicamentByCode(valeurRecherche);
        }

        for (Medicament M: lesMedicAffiche
             ) {
            lesNomsMedicAffiche.add(M.getNomCommercial());
        }

        // On affiche les resultats dans la liste
        final ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(AfficherMedic.this,R.layout.support_simple_spinner_dropdown_item, lesNomsMedicAffiche);
        myList.setAdapter(adapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // on envoie le nom du medicament sur l'activity DetailMedoc
                Intent intent = new Intent( AfficherMedic.this, DetailMedoc.class);
                intent.putExtra("medicament", lesNomsMedicAffiche.get(+position));
                AfficherMedic.this.startActivityForResult( intent, 11);


            }
        });





    }
}
