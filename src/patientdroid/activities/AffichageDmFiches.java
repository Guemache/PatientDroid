package patientdroid.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import patientdroid.database.PatientBDD;
import patientdroid.entities.FicheSuivi;

import medecin.patient.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class AffichageDmFiches extends Activity {
	private ListView listeFiches;
	private PatientBDD patientBDD;
	private List<FicheSuivi> listeF;
	/////
	private String argIntent;
	private String [] tabBesoinDM;
	private String nomPatient;
	private String prenomPatient;
	private String idDossier;
	private Button b_ajouterFiche;
	private TextView nomPrenomPatient;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_affichage_dm_fiches);
		Intent i = getIntent();
		argIntent = i.getStringExtra("besoinDM");
		tabBesoinDM = argIntent.split("-");
		nomPatient = tabBesoinDM[0];
		prenomPatient = tabBesoinDM[1];
		idDossier = tabBesoinDM[2];
		
		nomPrenomPatient = (TextView) this.findViewById(R.id.textViewNomPrenomPatientAffDM);
		nomPrenomPatient.setText(nomPatient+" "+prenomPatient);
		//récupérer les boutons
		b_ajouterFiche = (Button) this.findViewById(R.id.buttonAjouterFicheSuivi);
		
		patientBDD = new PatientBDD(this);
        patientBDD.openWrite();
        listeF = patientBDD.getFichesSuiviByIdDossier(Integer.parseInt(idDossier));
        patientBDD.close();
        
        //Récupération de la listview créée dans le fichier main.xml
        listeFiches = (ListView) findViewById(R.id.listViewFichesSuivi);
        
        ///////////
      //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();        
        //On déclare la HashMap qui contiendra les informations pour un item
        
        Iterator<FicheSuivi> it = listeF.iterator();
        while(it.hasNext()){
        	FicheSuivi fiche = it.next();
        	HashMap<String,String> map = new HashMap<String,String>();
            map.put("idFiche", Integer.toString(fiche.getId_ficheSuivi()));
        	map.put("intituleFiche", fiche.getIntitule());
        	map.put("img", String.valueOf(R.drawable.iconefiche));
        	listItem.add(map);
        }
        
      //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.affichage_fiche,
               new String[] {"img", "intituleFiche"}, new int[] {R.id.avatarFiche, R.id.intituleFicheAffichage});
 
        //On attribut à notre listView l'adapter que l'on vient de créer
        listeFiches.setAdapter(mSchedule);
        
        //cliquer sur les items de la listeView
      //Enfin on met un écouteur d'évènement sur notre listView
        listeFiches.setOnItemClickListener(new OnItemClickListener() {
			@Override
        	@SuppressWarnings("unchecked")
         	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				//on récupère la HashMap contenant les infos de notre item (titre, description, img)
        		HashMap<String, String> map = (HashMap<String, String>) listeFiches.getItemAtPosition(position);
        		//on créer une boite de dialogue
        		Intent intent = new Intent(AffichageDmFiches.this, AffichageFiche.class);
        		intent.putExtra("idFiche", map.get("idFiche"));
				startActivity(intent);
        	}

        });
        
      //bouton ajouter fiche
        b_ajouterFiche.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AffichageDmFiches.this, AjouterFicheSuivi.class);
        		intent.putExtra("idDossier", idDossier);
				startActivity(intent);
				
			}
		});
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.affichage_dm_fiches, menu);
		return true;
	}

}
