package patientdroid.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import patientdroid.database.PatientBDD;
import patientdroid.entities.Patient;
import patientdroid.entities.RDV;
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

public class GererRDV extends Activity {
	private ListView listeRdvs;
	private PatientBDD patientBDD;
	private List<RDV> listeRdv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerer_rdv);
		
		patientBDD = new PatientBDD(this);
        patientBDD.openWrite();
        listeRdv = patientBDD.getAllRDV();
        patientBDD.close();
        
      //Récupération de la listview créée dans le fichier main.xml
        listeRdvs = (ListView) findViewById(R.id.listViewRDVs);
        
      //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();        
        //On déclare la HashMap qui contiendra les informations pour un item
        
        Iterator<RDV> it = listeRdv.iterator();
        while(it.hasNext()){
        	RDV rdv = it.next();
        	HashMap<String,String> map = new HashMap<String,String>();
            map.put("idRDV", Integer.toString(rdv.getId_rdv()));
        	map.put("dateRDV", "date : "+rdv.getDateRdv());
        	map.put("heureRDV","heure :"+ rdv.getHeureRdv());
        	map.put("img", String.valueOf(R.drawable.icone_ajouter_rdv));
        	map.put("idPatient", Integer.toString(rdv.getIdPatient()));
        	listItem.add(map);
        }
        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.affichage_rdv,
               new String[] {"img", "dateRDV","heureRDV"}, new int[] {R.id.avatarRDV, R.id.dateRDVAffichage, R.id.heureRDVAffichage});
 
        //On attribut à notre listView l'adapter que l'on vient de créer
        listeRdvs.setAdapter(mSchedule);
        
        //Enfin on met un écouteur d'évènement sur notre listView
        listeRdvs.setOnItemClickListener(new OnItemClickListener() {
			@Override
        	@SuppressWarnings("unchecked")
         	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				//on récupère la HashMap contenant les infos de notre item (titre, description, img)
        		HashMap<String, String> map = (HashMap<String, String>) listeRdvs.getItemAtPosition(position);
        		//on créer une boite de dialogue
        		Intent intent = new Intent(GererRDV.this, ProfilPatient.class);
        		intent.putExtra("idPatient", map.get("idPatient"));
				startActivity(intent);
        	}

        });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gerer_rdv, menu);
		return true;
	}

}
