package patientdroid.activities;

import patientdroid.database.PatientBDD;
import patientdroid.entities.DossierMedical;
import patientdroid.entities.Patient;
import medecin.patient.R;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AjouterDossierMedical extends Activity {
	private String argIntent;
	private String [] tabBesoinDM;
	private String nomPatient;
	private String prenomPatient;
	private String idPatient;
	private Button b_ajouterDM;
	@SuppressWarnings("unused")
	private Button b_effacerDM;
	private TextView nomPrenomPatient;
	private EditText editIntituleDossier;
	private PatientBDD patientBDD;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajouter_dossier_medical);
		Intent i = getIntent();
		argIntent = i.getStringExtra("besoinDM");
		tabBesoinDM = argIntent.split("-");
		nomPatient = tabBesoinDM[0];
		prenomPatient = tabBesoinDM[1];
		idPatient = tabBesoinDM[2];
		
		nomPrenomPatient = (TextView) this.findViewById(R.id.textViewNomPrenomDM);
		nomPrenomPatient.setText(nomPatient+" "+prenomPatient);
		
		//récupérer les boutons
	    b_ajouterDM = (Button) this.findViewById(R.id.buttonAjouterDM);
	    b_effacerDM = (Button) this.findViewById(R.id.buttonEffacerDM);
	    editIntituleDossier = (EditText) this.findViewById(R.id.editTextIntituleAjoutDM);		
	    
	  //création d'une instance de PatientBDD
	 	 patientBDD = new PatientBDD(this);
	 	 //////
	    b_ajouterDM.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			String intituleDM = editIntituleDossier.getText().toString();
			if(!intituleDM.isEmpty()){
				Patient patient = new Patient();
				patient.setId(Integer.parseInt(idPatient));
				DossierMedical dm = new DossierMedical(intituleDM,patient);
				
				//On ouvre la base de données pour écrire dedans
				patientBDD.openWrite();
				//insertion du patient dans la base données
				long i = patientBDD.insertDossierMedical(dm);
				//fermer la base de données après insetion
				patientBDD.close();
			    
			    if(i!=-1){
			    
			    	editIntituleDossier.setText("Le Dossier médical a été ajouté");
			    	
			    }else{
			    
			    	editIntituleDossier.setText("Problème avec l'ajout, veuillez réessayer");
			    	
			    }
			}else{
				
				editIntituleDossier.setText("champs obligatoire");
			}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ajouter_dossier_medical, menu);
		return true;
	}

}
