package patientdroid.activities;

import patientdroid.database.PatientBDD;
import patientdroid.entities.DossierMedical;
import patientdroid.entities.FicheSuivi;
import patientdroid.entities.Patient;
import patientdroid.entities.RDV;
import medecin.patient.R;
import medecin.patient.R.layout;
import medecin.patient.R.menu;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("NewApi")
public class AjouterRDV extends Activity {
	private String idPatient;
	private EditText  dateRDVEdit;
	private EditText  heureRDVEdit;
	private PatientBDD patientBDD;
	private TextView  textStatutAjout;
	private Button boutonValider;
	private Button boutonReinitialiser;
	private RDV rdv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajouter_rdv);
		//récupérer l'intent
		Intent i = getIntent();
		idPatient = i.getStringExtra("idPatient");
		
		//récupération des champs de texte
		Button boutonValider = (Button) this.findViewById(R.id.buttonValiderFormAjoutRDV);
		@SuppressWarnings("unused")
		Button boutonReinitialiser = (Button) this.findViewById(R.id.buttonReinitialiserFormAjoutRDV);
		dateRDVEdit = (EditText) this.findViewById(R.id.editTextDateRDV);
		heureRDVEdit = (EditText) this.findViewById(R.id.editTextHeureRDV);
		textStatutAjout = (TextView) this.findViewById(R.id.textViewStatutAjoutRDV);
		
		//création d'une instance de PatientBDD
	 	 patientBDD = new PatientBDD(this);
	 	 //////
	 	 
	 	rdv = new RDV();
		rdv.setId_rdv(Integer.parseInt(idPatient)); 
		
        boutonValider.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String dateRDV = dateRDVEdit.getText().toString();
				String heureRDV = heureRDVEdit.getText().toString();
				
				
				if((!dateRDV.isEmpty())&&(!heureRDV.isEmpty())){
					Patient pat = new Patient();
					pat.setId(Integer.parseInt(idPatient));
				    rdv.setDateRdv(dateRDV);
				    rdv.setHeureRdv(heureRDV);
				    rdv.setPatient(pat);
				   //On ouvre la base de données pour écrire dedans
					patientBDD.openWrite();
					//insertion du patient dans la base données
					long i1 = patientBDD.insertRDV(rdv);
					//fermer la base de données après insetion
					patientBDD.close();
					
					 if(i1!=-1){
						    
					    	textStatutAjout.setText("Le rdv a été ajoutée");
					    	
					    }else{
					    
					    	textStatutAjout.setText("Problème avec l'ajout, veuillez réessayer");
					    	
					    }
					    
					 }else{
						
						 textStatutAjout.setText("champs obligatoires");
						
					 }
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ajouter_rdv, menu);
		return true;
	}

}
