package patientdroid.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import patientdroid.database.PatientBDD;
import patientdroid.entities.Patient;

import medecin.patient.R;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

@SuppressLint("ResourceAsColor")
public class ListePatients extends Activity {

	private ListView listePatients;
	private PatientBDD patientBDD;
	private List<Patient> listePat;
	 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_patients);
        patientBDD = new PatientBDD(this);
        patientBDD.openWrite();
        listePat = patientBDD.getAllPatients();
        patientBDD.close();
        
        
        //Récupération de la listview créée dans le fichier main.xml
        listePatients = (ListView) findViewById(R.id.listViewPatients);
 
        //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();        
        //On déclare la HashMap qui contiendra les informations pour un item
        
        Iterator<Patient> it = listePat.iterator();
        while(it.hasNext()){
        	Patient pat = it.next();
        	HashMap<String,String> map = new HashMap<String,String>();
            map.put("idPatient", Integer.toString(pat.getId()));
        	map.put("nomPatient", pat.getNom());
        	map.put("prenomPatient", pat.getPrenom());
        	map.put("img", String.valueOf(R.drawable.iconepatient));
        	listItem.add(map);
        }
        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.affichage_patient,
               new String[] {"img", "nomPatient","prenomPatient"}, new int[] {R.id.avatarPatient, R.id.nomPatientAffichage, R.id.prenomPatientAffichage});
 
        //On attribut à notre listView l'adapter que l'on vient de créer
        listePatients.setAdapter(mSchedule);
 
       
       
       
       
        //Enfin on met un écouteur d'évènement sur notre listView
        listePatients.setOnItemClickListener(new OnItemClickListener() {
			@Override
        	@SuppressWarnings("unchecked")
         	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				//on récupère la HashMap contenant les infos de notre item (titre, description, img)
        		HashMap<String, String> map = (HashMap<String, String>) listePatients.getItemAtPosition(position);
        		//on créer une boite de dialogue
        		Intent intent = new Intent(ListePatients.this, ProfilPatient.class);
        		intent.putExtra("idPatient", map.get("idPatient"));
				startActivity(intent);
        	}

        });
 	
    }

}
