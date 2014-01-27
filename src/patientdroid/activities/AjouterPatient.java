package patientdroid.activities;

import patientdroid.database.PatientBDD;
import patientdroid.entities.Patient;
import medecin.patient.R;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

@SuppressLint({ "NewApi", "ResourceAsColor" })
public class AjouterPatient extends Activity {
	private EditText  nomPatientEdit;
	private EditText  prenomPatientEdit;
	private EditText  adressePatientEdit;
	private EditText  telPatientEdit;
	private EditText  mailPatientEdit;
	private EditText  dateNaissPatientEdit;
	private Spinner   typeMaladiePatient;
	private EditText  dateDerConsPatientEdit;
	private PatientBDD patientBDD;
	private TextView  textStatutAjout;
	
	
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajouter_patient);
		
		 Button boutonValider = (Button) this.findViewById(R.id.buttonValiderFormAjoutPatient);
		 @SuppressWarnings("unused")
		Button boutonReinitialiser = (Button) this.findViewById(R.id.buttonReinitialiserFormAjoutPatient);
	     nomPatientEdit = (EditText) this.findViewById(R.id.editTextNomPatient);
	     prenomPatientEdit = (EditText) this.findViewById(R.id.editTextPrenomPatient);
	     adressePatientEdit = (EditText) this.findViewById(R.id.editTextAdressePatient);
	 	 telPatientEdit = (EditText) this.findViewById(R.id.editTextNumTel);
	 	 mailPatientEdit = (EditText) this.findViewById(R.id.editTextMail);
	 	 dateNaissPatientEdit = (EditText) this.findViewById(R.id.editTextDateNaiss);
	 	 typeMaladiePatient = (Spinner) this.findViewById(R.id.spinnerTypeMaladie);
	 	 dateDerConsPatientEdit = (EditText) this.findViewById(R.id.editTextDateDerCons);
	 	 textStatutAjout = (TextView) this.findViewById(R.id.textViewStatutAjout);
	 	 
	 	 //création d'une instance de PatientBDD
	 	 patientBDD = new PatientBDD(this);
	 	 //////
	 	boutonValider.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			String nomPatient = nomPatientEdit.getText().toString();
			String prenomPatient = prenomPatientEdit.getText().toString();
			String adressePatient = adressePatientEdit.getText().toString();
			String telPatient = telPatientEdit.getText().toString();
			String mailPatient = mailPatientEdit.getText().toString();
			String dateNaiss = dateNaissPatientEdit.getText().toString();
			String typeMaladie = typeMaladiePatient.getSelectedItem().toString();
			String dateDerCons = dateDerConsPatientEdit.getText().toString();
		    if((!nomPatient.isEmpty())&&(!prenomPatient.isEmpty())&&(!adressePatient.isEmpty())&&
		    	(!telPatient.isEmpty())&&(!mailPatient.isEmpty())&&(!dateNaiss.isEmpty())){
		    Patient patient = new Patient(nomPatient,prenomPatient,adressePatient,telPatient,mailPatient,dateNaiss,typeMaladie,dateDerCons);
		    
		   //On ouvre la base de données pour écrire dedans
			patientBDD.openWrite();
			//insertion du patient dans la base données
			long i = patientBDD.insertPatient(patient);
			//fermer la base de données après insetion
			patientBDD.close();
		    
		    if(i!=-1){
		    
		    	textStatutAjout.setText("Le patient a été ajouté");
		    	
		    }else{
		    
		    	textStatutAjout.setText("Problème avec l'ajout, veuillez réessayer");
		    	
		    }
		    
		 }else{
			
			 textStatutAjout.setText("champs obligatoires, sauf date dernière consultation");
			
		 }
			
				
			}
		});
	 	 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ajouter_patient, menu);
		return true;
	}

}
