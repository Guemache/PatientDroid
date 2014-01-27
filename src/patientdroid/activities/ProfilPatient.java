package patientdroid.activities;

import patientdroid.database.PatientBDD;
import patientdroid.entities.DossierMedical;
import patientdroid.entities.Patient;
import patientdroid.entities.RDV;
import medecin.patient.R;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfilPatient extends Activity {
	private Button boutonAppeler;
	private Button boutonEnvoyerSMS;
	private Button boutonDossierMedical;
	private Button boutonSupprimer;
	private Patient patient; 
	private RDV rdv;
	private TextView nomPatientAff;
	private TextView prenomPatientAff;
	private TextView telPatientAff;
	private TextView dateNaissPatientAff;
	private TextView courrielPatientAff;
	private TextView typeMaladiePatientAff;
	private TextView dateDerConsPatientAff;
	private TextView adressePatientAff;
	private TextView dernierRDVPatient;
	private PatientBDD patientBDD;
	private String idPatient;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profil_patient);
		Intent i = getIntent();
		idPatient = i.getStringExtra("idPatient");
		//récupération des textes views
		nomPatientAff = (TextView) this.findViewById(R.id.textViewAffPatientNom);
		prenomPatientAff = (TextView) this.findViewById(R.id.textViewAffPatientPrenom);
		telPatientAff = (TextView) this.findViewById(R.id.textViewAffPatientTel);
		dateNaissPatientAff = (TextView) this.findViewById(R.id.textViewAffPatientDatNaiss);
		courrielPatientAff = (TextView) this.findViewById(R.id.textViewCourrielAff);
		typeMaladiePatientAff = (TextView) this.findViewById(R.id.textViewTypeMaladieAff);
		dateDerConsPatientAff = (TextView) this.findViewById(R.id.textViewDerConsultationAff);
		adressePatientAff = (TextView) this.findViewById(R.id.textViewAdressePatientAff);
		dernierRDVPatient = (TextView) this.findViewById(R.id.textViewRDVPatientAff);
		//récupération du patient de la base de données
		patientBDD = new PatientBDD(this);
        patientBDD.openWrite();
        patient = patientBDD.getPatientWithId(Integer.parseInt(idPatient));
        rdv = patientBDD.getRDVByIdPatient(Integer.parseInt(idPatient));
        patientBDD.close();
        //mettre les valeurs dans les champs
		nomPatientAff.setText("Nom : "+patient.getNom());
		prenomPatientAff.setText("Prénom : "+patient.getPrenom());
		telPatientAff.setText("Tel : "+patient.getNumTel());
		dateNaissPatientAff.setText("Né le :"+patient.getDateNaissance());
		courrielPatientAff.setText("Courriel : "+patient.getEmail());
		typeMaladiePatientAff.setText("Type de Maladie : "+patient.getDateDerniereConsultation());
		dateDerConsPatientAff.setText("Date dernière consultation : "+patient.getTypeMaladie());
		if(rdv!=null){
		dernierRDVPatient.setText("Dernier RDV :"+rdv.getDateRdv()+" - "+rdv.getHeureRdv());
		}else{
		dernierRDVPatient.setText("Dernier RDV : Pas de rdv");
		}
		adressePatientAff.setText("Adresse du Patient : "+patient.getAdresse());
		/**
		 * 
		 * Les boutons
		 */
		//bouton de supprimer patient
		boutonSupprimer = (Button) this.findViewById(R.id.buttonSupprimerPatient);
        boutonSupprimer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		   //On ouvre la base de données pour écrire dedans
			patientBDD.openWrite();
			//insertion du patient dans la base données
			patientBDD.removeRDVWithIdPatient(Integer.parseInt(idPatient));
			patientBDD.removePatientWithID(Integer.parseInt(idPatient));
			//fermer la base de données après insetion
			patientBDD.close();
		    Intent intent = new Intent(ProfilPatient.this,ListePatients.class);
		    startActivity(intent);
		   
			}});
        
        //bouton Appeler
        boutonAppeler = (Button) this.findViewById(R.id.buttonAppelerPatient);
        boutonAppeler.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String numTel = "tel:"+patient.getNumTel();
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(numTel));
				startActivity(intent);	
			}
		});
        
        //bouton SMS
        boutonEnvoyerSMS = (Button) this.findViewById(R.id.buttonEnvoyerSmsPatient);
        patientBDD.openWrite();
		final DossierMedical dm = patientBDD.getDossierMedicalWithPatientId(Integer.parseInt(idPatient));
		patientBDD.close();
        
        boutonEnvoyerSMS.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				Intent intent = new Intent(ProfilPatient.this, EnvoyerSMS.class);
				String smsIntentArg = patient.getNom()+"-"+patient.getPrenom()+"-"+patient.getNumTel();
				intent.putExtra("besoinSMS", smsIntentArg);
				startActivity(intent);
				
			}
		});
        
        //bouton Dossier Médical
        boutonDossierMedical = (Button) this.findViewById(R.id.buttonDossierMedicalAff);
        boutonDossierMedical.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(dm!=null){
				int idDossier = dm.getId_DossierMedical();
				Intent intent = new Intent(ProfilPatient.this, AffichageDmFiches.class);
				String dmIntentArg = patient.getNom()+"-"+patient.getPrenom()+"-"+idDossier;
				intent.putExtra("besoinDM", dmIntentArg);
				startActivity(intent);
				}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.patient_profil, menu);
		return true;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch(item.getItemId())
	    {
		case R.id.itemRetournerAccueil :
			Intent intent1 = new Intent(ProfilPatient.this, AccueilActivity.class);
			startActivity(intent1);
			break;
			
		case R.id.itemAjoutDossierMedical:
			Intent intent2 = new Intent(ProfilPatient.this, AjouterDossierMedical.class);
			String dmIntentArg = patient.getNom()+"-"+patient.getPrenom()+"-"+patient.getId();
			intent2.putExtra("besoinDM", dmIntentArg);
			startActivity(intent2);
			break;
		
		case R.id.itemAjoutPhoto:
			break;
			
		case R.id.itemAjouterRDV:
			Intent intent3 = new Intent(ProfilPatient.this, AjouterRDV.class);
			intent3.putExtra("idPatient", idPatient);
			startActivity(intent3);
			
			break;
	    }
		return true;
		
	}
}
