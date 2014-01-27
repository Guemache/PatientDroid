package patientdroid.activities;

import patientdroid.database.PatientBDD;
import patientdroid.entities.FicheSuivi;
import medecin.patient.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class AffichageFiche extends Activity {
	private String idFiche;
	private TextView intituleFicheAff;
	private TextView nomMaladieAff;
	private TextView remarqueAff;
	private PatientBDD patientBDD;
	private FicheSuivi fiche;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_affichage_fiche);
		//récupérer l'argument de l'intent
		Intent i = getIntent();
		idFiche = i.getStringExtra("idFiche");
		/////
		intituleFicheAff = (TextView) this.findViewById(R.id.textViewAffIntitueFichePage);
		nomMaladieAff = (TextView) this.findViewById(R.id.textViewAffNomMaldiePage);
		remarqueAff = (TextView) this.findViewById(R.id.textViewAffRemarquePage);
		//récupération du patient de la base de données
	    patientBDD = new PatientBDD(this);
		patientBDD.openWrite();
		fiche = patientBDD.getFicheSuiviById((Integer.parseInt(idFiche)));
		patientBDD.close();
		
		intituleFicheAff.setText("Intitulé Fiche : "+fiche.getIntitule());
		nomMaladieAff.setText("Nom Maladie : "+fiche.getNomMaladie());
		remarqueAff.setText("Remarque : "+fiche.getRemarque());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.affichage_fiche, menu);
		return true;
	}

}
