package com.example.gsb_android;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Medicament implements Serializable {
    private String depotLegal;
    private String nomCommercial;
    private String codeFamille ;
    private String composition;
    private String effets;
    private String contreIndication;
    private Double prixEchantillon;
    private String typeFamille;

    static List<Medicament> allMedicament;




    Medicament(String NewDepotLegal, String NewNomCommercial, String NewCodeFamille, String NewComposition, String NewEffets,String NewcontreIndication, String NewPrixEchantillon,String NewTypeFamille)
    {
        depotLegal = NewDepotLegal;
        nomCommercial =NewNomCommercial;
        codeFamille=NewCodeFamille;
        composition = NewComposition;
        effets =NewEffets;
        contreIndication = NewcontreIndication;

        Double NewNewPrixEchantillon;

        if (NewPrixEchantillon == null || NewPrixEchantillon == "null"){
            NewNewPrixEchantillon = (double) 0;
        }else{
             NewNewPrixEchantillon = Double.parseDouble(NewPrixEchantillon);
        }
        prixEchantillon = NewNewPrixEchantillon;
        typeFamille = NewTypeFamille;
    }
    public String getDepotLegal(){
        return depotLegal;
    }
    public String getNomCommercial() {
        return nomCommercial;
    }
    public String getCodeFamille() {
        return codeFamille;
    }
    public String getComposition() {
        return composition;
    }
    public String getEffets() {
        return effets;
    }
    public String getcontreIndication() {
        return contreIndication;
    }
    public Double getPrixEchantillon() {
        return prixEchantillon;
    }
    public String getTypeFamille() {
        return typeFamille;
    }



    static List<Medicament> getMedicamentByTypeFamille(String Typefamille){

        List<Medicament> list = new ArrayList<>();;

        for (Medicament M: allMedicament) {
            if(M.typeFamille.equals(Typefamille)){
                list.add(M);
            }
        }
        return list;
    }

    static Medicament getMedicamentByNomComm(String nomMedic){
        Medicament medic = null;

        for (Medicament M: allMedicament
             ) {
            if (M.getNomCommercial().equals(nomMedic)){
                medic = M;
            }

        }
        return medic;
    }


    static List<Medicament> getMedicamentByCode(String code){

        List<Medicament> list = new ArrayList<>();;

        for (Medicament M: allMedicament) {
            if(M.depotLegal.equals(code)){
                list.add(M);
            }
        }

        return list;
    }

    static void setAllMedicament(List<Medicament> list ){
        allMedicament = null;
        allMedicament = list;
    }

    @Override
    public String toString() {
        return codeFamille +" "+typeFamille;
    }



    static public List<String> getAllTypeFamille(){
        List<String>typesFamille = new ArrayList<String>();

        for (Medicament M:
                allMedicament) {
            if (typesFamille.isEmpty()){
                typesFamille.add(M.typeFamille);
            }else{
                if (!typesFamille.contains(M.typeFamille)){
                    typesFamille.add(M.typeFamille);
                }
            }


        }
        return typesFamille;
    }


    static public List<String> getAllCodeD(){
        List<String>codeD = new ArrayList<String>();

        for (Medicament M:
                allMedicament) {
            if (codeD.isEmpty()){
                codeD.add(M.depotLegal);
            }else{
                if (!codeD.contains(M.depotLegal)){
                    codeD.add(M.depotLegal);
                }
            }

        }
        return codeD;
    }

    static public Medicament getMedicamentByCodeD(String codeD){
        Medicament medic = null;

        for (Medicament M:
             allMedicament) {
            if (M.depotLegal == codeD){
                medic = M;
            }
        }

        return medic;
    }

}
