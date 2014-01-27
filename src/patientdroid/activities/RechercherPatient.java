package patientdroid.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import patientdroid.database.PatientBDD;
import patientdroid.entities.Patient;
import medecin.patient.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class RechercherPatient extends Activity {
	private PatientBDD patientBDD;
	private EditText  recherchePatientEdit;
	private Button b_rechercherPatient;
	private List<Patient> listePat;
	private ListView listePatients;
	private static Context ctx;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rechercher_patient);
		recherchePatientEdit = (EditText) this.findViewById(R.id.editTextNomPatientRecherche);
		b_rechercherPatient = (Button) this.findViewById(R.id.buttonRechercherPatient);
		patientBDD = new PatientBDD(this);
		      
        //On déclare la HashMap qui contiendra les informations pour un item
		ctx = this.getBaseContext();
		b_rechercherPatient.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Récupération de la listview créée dans le fichier main.xml
		     listePatients = (ListView) findViewById(R.id.listViewPatientsResultatsRecherche);
			 String nomPatientRech = recherchePatientEdit.getText().toString();
			 patientBDD.openWrite();
			 listePat = patientBDD.getPatientWithNom(nomPatientRech);
			 patientBDD.close();
			 ///////Vider la liste
		    // listePatients.setAdapter(null);
			 ////////////
		     //Création de la ArrayList qui nous permettra de remplire la listView
			 ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();  
			 if(listePat!=null){
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
			        SimpleAdapter mSchedule = new SimpleAdapter (ctx, listItem, R.layout.affichage_patient_recherche,
			               new String[] {"img", "nomPatient","prenomPatient"}, new int[] {R.id.avatarPatientRecherche, R.id.nomPatientAffichageRecherche, R.id.prenomPatientAffichageRecherche});
			        	
			        //On attribut à notre listView l'adapter que l'on vient de créer
			        listePatients.setAdapter(mSchedule);
			 
			        }
			 
			 ////////cliquer les items
			 
			//Enfin on met un écouteur d'évènement sur notre listView
		        listePatients.setOnItemClickListener(new OnItemClickListener() {
					@Override
		        	@SuppressWarnings("unchecked")
		         	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
						//on récupère la HashMap contenant les infos de notre item (titre, description, img)
		        		HashMap<String, String> map = (HashMap<String, String>) listePatients.getItemAtPosition(position);
		        		//on créer une boite de dialogue
		        		Intent intent = new Intent(RechercherPatient.this, ProfilPatient.class);
		        		intent.putExtra("idPatient", map.get("idPatient"));
						startActivity(intent);
		        	}

		        });
			 
			 
			 
			 /////////////
			}
		});
		
		

        
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rechercher_patient, menu);
		return true;
	}

}
