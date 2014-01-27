package patientdroid.activities;

import patientdroid.database.PatientBDD;
import patientdroid.entities.DossierMedical;
import patientdroid.entities.FicheSuivi;
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

public class AjouterFicheSuivi extends Activity {
	private EditText  intituleFicheEdit;
	private EditText  nomMaladieFicheEdit;
	private EditText  remarqueFicheEdit;
	private PatientBDD patientBDD;
	private TextView  textStatutAjout;
	private String idDossier;
	private DossierMedical dm;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajouter_fiche_suivi);
		Intent i = getIntent();
		idDossier = i.getStringExtra("idDossier");
		
		Button boutonValider = (Button) this.findViewById(R.id.buttonValiderFormAjoutFiche);
		@SuppressWarnings("unused")
		Button boutonReinitialiser = (Button) this.findViewById(R.id.buttonReinitialiserFormAjoutFiche);
		intituleFicheEdit = (EditText) this.findViewById(R.id.editTextIntituleFiche);
		nomMaladieFicheEdit = (EditText) this.findViewById(R.id.editTextPNomMaladie);
		remarqueFicheEdit = (EditText) this.findViewById(R.id.editTextRemarqueFiche);
		textStatutAjout = (TextView) this.findViewById(R.id.textViewStatutAjoutFiche);
		
		//création d'une instance de PatientBDD
	 	 patientBDD = new PatientBDD(this);
	 	 //////
	 	 
	 	dm = new DossierMedical();
		dm.setId_DossierMedical(Integer.parseInt(idDossier));
		
		boutonValider.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String intituleFiche = intituleFicheEdit.getText().toString();
				String maladieFiche = nomMaladieFicheEdit.getText().toString();
				String remarqueFiche = remarqueFicheEdit.getText().toString();
				
				
				if((!intituleFiche.isEmpty())&&(!maladieFiche.isEmpty())&&(!remarqueFiche.isEmpty())){
					
				    FicheSuivi fs = new FicheSuivi(intituleFiche,maladieFiche,remarqueFiche,dm);
				   //On ouvre la base de données pour écrire dedans
					patientBDD.openWrite();
					//insertion du patient dans la base données
					long i1 = patientBDD.insertFicheSuivi(fs);
					//fermer la base de données après insetion
					patientBDD.close();
					
					 if(i1!=-1){
						    
					    	textStatutAjout.setText("La fiche a été ajoutée");
					    	
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
		getMenuInflater().inflate(R.menu.ajouter_fiche_suivi, menu);
		return true;
	}

}
