package nl.hu.team.ntrapplication.database;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

import nl.hu.team.ntrapplication.objects.Research;
import nl.hu.team.ntrapplication.database.DatabaseHandler;

/**
 * Created by Tinus on 13-5-2015.
 */
public class TijdelijkeNaam {
    private Context context;
    public TijdelijkeNaam(Context context){
        this.context = context;
    }
    //getResearches
    public ArrayList<Research> getResearches(){
        DatabaseHandler databaseHandler = new DatabaseHandler(context);
        ArrayList<Research> allResearches = new ArrayList<Research>(databaseHandler.getAllResearch());
        ArrayList<Research> currentResearches = new ArrayList<Research>();
        //TODO: datum aanpassen zodat alleen naar de dag gekeken wordt en niet naar uren en minuten etc
        Date today = new Date();
        for( Research research: allResearches){
           if(research.getBEGIN_DATE().before(today) && research.getEND_DATE().after(today)){
               currentResearches.add(research);

           }
        }
        return currentResearches;
    }
}
