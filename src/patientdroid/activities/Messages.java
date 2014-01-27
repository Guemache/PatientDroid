package patientdroid.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import patientdroid.database.PatientBDD;
import patientdroid.entities.Patient;
import patientdroid.entities.SMS;
import medecin.patient.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class Messages extends Activity {
	private String argIntent;
	private String [] tabBesoinSMS;
	private String [] tabBesoinLocalisation;
	private String cord1;
	private String cord2;
	private String telPatient;
	private String message;
	private PatientBDD patientBDD;
	private SMS sms;
	private Patient patientId;
	private Patient patient;
	private ListView listeMessages;
	private String nomPatient;
	private String prenomPatient;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_messages);
		Intent i = getIntent();
		argIntent = i.getStringExtra("msgArg");
		tabBesoinSMS = argIntent.split(";");
		telPatient = tabBesoinSMS[0];
		message = tabBesoinSMS[1];
		tabBesoinLocalisation = message.split("-");
		cord1 = tabBesoinLocalisation[1];
		cord2 = tabBesoinLocalisation[2];
		patientBDD = new PatientBDD(this);
		patientBDD.openWrite();
		patientId = patientBDD.getPatientWithTel(telPatient);
		if(patientId != null){
		int idPatient = patientId.getId();
		patient = patientBDD.getPatientWithId(idPatient);
		nomPatient = patient.getNom();
		prenomPatient = patient.getPrenom();
		}
		patientBDD.close();
		
		/**
		 * 
		 */
		 //Récupération de la listview créée dans le fichier main.xml
		listeMessages = (ListView) findViewById(R.id.listViewNotificationsSOS);
 
        //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();        
        //On déclare la HashMap qui contiendra les informations pour un item


        	HashMap<String,String> map = new HashMap<String,String>();
        	map.put("coordonnees", cord1+"-"+cord2);
        	map.put("message", "[SOS]");
        	map.put("nomPatient", nomPatient+" "+prenomPatient );
        	map.put("img", String.valueOf(R.drawable.listemessages));

         	listItem.add(map);

        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.affichage_patient,
               new String[] {"img", "message","nomPatient"}, new int[] {R.id.avatarPatient, R.id.nomPatientAffichage, R.id.prenomPatientAffichage});
 
        //On attribut à notre listView l'adapter que l'on vient de créer
        listeMessages.setAdapter(mSchedule);
        
        
      //Enfin on met un écouteur d'évènement sur notre listView
        listeMessages.setOnItemClickListener(new OnItemClickListener() {
			@Override
        	@SuppressWarnings("unchecked")
         	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				//on récupère la HashMap contenant les infos de notre item (titre, description, img)
        		HashMap<String, String> map = (HashMap<String, String>) listeMessages.getItemAtPosition(position);
        		//on créer une boite de dialogue
        		Intent intent = new Intent(Messages.this, AffichagePosition.class);
        		String nomPrenom = nomPatient+" "+prenomPatient;
        		intent.putExtra("coordonnees", map.get("coordonnees")+";"+nomPrenom);
				startActivity(intent);
        	}

        });
		 
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.messages, menu);
		return true;
	}

}
