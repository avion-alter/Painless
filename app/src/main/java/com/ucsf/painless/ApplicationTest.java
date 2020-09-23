package com.ucsf.painless;

import android.app.Application;
import android.app.ProgressDialog;
import android.util.Log;

import com.ucsf.painless.model.clipBoard.PatientPreviousMapDatum;
import com.ucsf.painless.network.ConnectionDetector;
import com.ucsf.painless.utils.SessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class ApplicationTest extends Application {
    public ConnectionDetector connectionDetector;
    public SessionManager sessionManager;
    ProgressDialog pDialog;

    String selectedSeverity="";
    private static ApplicationTest singleton;

    public List<PatientPreviousMapDatum> clipBoardData;

    public ArrayList<HashMap<String, String>> selectedSevere;
    public ArrayList<HashMap<String, String>> selectedPartName;
    public ArrayList<HashMap<String, String>> selectedColor;
    public ArrayList<HashMap<String, String>> selectedFrontBack;
    public ArrayList<HashMap<String, String>> selectedRightLeft;
    public static ApplicationTest getInstance(){
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        Log.e("Singleton","In oncreate");
        connectionDetector = new ConnectionDetector(getApplicationContext());
        sessionManager = new SessionManager(getApplicationContext());
        selectedSevere=new ArrayList<>();
        selectedPartName=new ArrayList<>();
        selectedColor=new ArrayList<>();
        selectedFrontBack=new ArrayList<>();
        selectedRightLeft=new ArrayList<>();
        clipBoardData=new ArrayList<>();
    }

    public String currency(String currency){
        double cal= Double.parseDouble(currency);
        if (cal>1){
            return "Lei ";
        }else return "Leu ";

    }


    private static SimpleDateFormat inSDF = new SimpleDateFormat("yyyy-mm-dd");
    private static SimpleDateFormat outSDF = new SimpleDateFormat("dd.mm.yyyy");

    public String formatDate(String inDate) {
        String outDate = "";
        if (inDate != null) {
            try {
                Date date = inSDF.parse(inDate);
                outDate = outSDF.format(date);
            } catch (ParseException ex){
            }
        }
        return outDate;
    }

    public String getSelectedSeverity() {
        return selectedSeverity;
    }

    public void setSelectedSeverity(String selectedSeverity) {
        this.selectedSeverity = selectedSeverity;
    }


    public ArrayList<HashMap<String, String>> getSelectedSevere() {
        return selectedSevere;
    }

    public void setSelectedSevere(ArrayList<HashMap<String, String>> selectedSevere) {
        this.selectedSevere = selectedSevere;
    }

    public ArrayList<HashMap<String, String>> getSelectedPartName() {
        return selectedPartName;
    }

    public void setSelectedPartName(ArrayList<HashMap<String, String>> selectedPartName) {
        this.selectedPartName = selectedPartName;
    }

    public ArrayList<HashMap<String, String>> getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(ArrayList<HashMap<String, String>> selectedColor) {
        this.selectedColor = selectedColor;
    }


    public ArrayList<HashMap<String, String>> getSelectedFrontBack() {
        return selectedFrontBack;
    }

    public void setSelectedFrontBack(ArrayList<HashMap<String, String>> selectedFrontBack) {
        this.selectedFrontBack = selectedFrontBack;
    }

    public ArrayList<HashMap<String, String>> getSelectedRightLeft() {
        return selectedRightLeft;
    }

    public void setSelectedRightLeft(ArrayList<HashMap<String, String>> selectedRightLeft) {
        this.selectedRightLeft = selectedRightLeft;
    }

    public void addInFrontBack(String name, String sever){
        HashMap<String,String> idStringHashMap=new HashMap<>();
        idStringHashMap.put(name,sever);
        selectedFrontBack.add(idStringHashMap);
    }

    public void addInLeftRight(String name, String sever){
        HashMap<String,String> idStringHashMap=new HashMap<>();
        idStringHashMap.put(name,sever);
        selectedRightLeft.add(idStringHashMap);
    }

    public void addInSelected(String name, String sever){
        HashMap<String,String> idStringHashMap=new HashMap<>();
        idStringHashMap.put(name,sever);
        selectedSevere.add(idStringHashMap);
    }

    public void addInSelectedPart(String name, String sever){
        HashMap<String,String> idStringHashMap=new HashMap<>();
        idStringHashMap.put(name,sever);
        selectedPartName.add(idStringHashMap);
    }

    public void addInSelectedColor(String name, String sever){
        HashMap<String,String> idStringHashMap=new HashMap<>();
        idStringHashMap.put(name,sever);
        selectedColor.add(idStringHashMap);
    }

    public void removeSelectedId(){
        selectedSevere.remove(selectedSevere.size()-1);
    }

}
