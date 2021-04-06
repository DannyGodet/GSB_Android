package com.example.gsb_android;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ListView;
        import android.widget.TextView;

        import androidx.appcompat.app.AppCompatActivity;

        import com.squareup.okhttp.OkHttpClient;
        import com.squareup.okhttp.Request;
        import com.squareup.okhttp.Response;
        import android.widget.ArrayAdapter;
        import android.widget.Toast;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.IOException;
        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.List;

public class MedocParCodeD extends AppCompatActivity {

    private ListView myList;
    private TextView textViewDonnees;
    //données figurant dans la requete sql
    private static final String MED_depotLegal = "MED_DEPOTLEGAL";
    private static final String MED_nomCommercial = "MED_NOMCOMMERCIAL";
    private static final String MED_codeFamille = "FAM_CODE";
    private static final String MED_effets = "MED_EFFETS";
    private static final String MED_composition = "MED_COMPOSITION";
    private static final String MED_contreIndication = "MED_CONTREINDIC";
    private static final String MED_prixEchantillon = "MED_PRIXECHANTILLON";
    private static final String MED_typeFamille = "FAM_LIBELLE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_medic_par_code_d);
        new JSONAsynchrone().execute();

    }

    private class JSONAsynchrone extends AsyncTask<String, String, String> {
        //mise en place d’une fenêtre de dialogue

        private ProgressDialog pDialog;
        private ListView myList = (ListView) findViewById((R.id.ListeViewMedicParCodeD));
        private String[] medicaments;

        ArrayList<Medicament> oslist = new ArrayList<Medicament>();


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MedocParCodeD.this);
            pDialog.setMessage("Chargement en cours ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("http://192.168.206.246/PPE_Android/tousMedicaments.php").build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String json) {
            pDialog.dismiss();


            JSONObject c = null;
            try {
                // Getting JSON Array from URL
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    c = jsonArray.getJSONObject(i);

                    String depotLegal = c.getString(MED_depotLegal);
                    String nomCommercial = c.getString(MED_nomCommercial);
                    String code = c.getString(MED_codeFamille);
                    String composition = c.getString(MED_composition);
                    String effets = c.getString(MED_effets);
                    String contreIndication = c.getString(MED_contreIndication);
                    String prixEchantillon = c.getString(MED_prixEchantillon);
                    String typeFamille = c.getString(MED_typeFamille);
                    Medicament med = new Medicament(depotLegal, nomCommercial, code, composition,effets ,contreIndication, prixEchantillon, typeFamille);
                    oslist.add(med);
                }

                Medicament.setAllMedicament(oslist);
                List<String> typesFamille = null;
                typesFamille = Medicament.getAllCodeD();

                final ArrayAdapter<String> adapter = new
                        ArrayAdapter<String>(MedocParCodeD.this,R.layout.support_simple_spinner_dropdown_item, typesFamille);
                myList.setAdapter(adapter);

                final List<String> finalTypesFamille = typesFamille;
                myList.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int
                            position, long id) {
                        //Toast.makeText(MedocParFamille.this, "Vous avez cliqué sur un medicament du type "+oslist.get(+position).typeFamille, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent( MedocParCodeD.this, DetailMedoc.class);
                        //intent.putExtra("ListMedic", oslist);

                        List<Medicament> list = new ArrayList<Medicament>();
                        list = (ArrayList<Medicament>)oslist;
                        Medicament m =  Medicament.getMedicamentByCodeD(finalTypesFamille.get(+position));

                        intent.putExtra("medicament", m.getNomCommercial());
                        MedocParCodeD.this.startActivityForResult( intent, 11);

                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}

