package patientdroid.activities;


import medecin.patient.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class AccueilActivity extends Activity {
	
	private Button b_ajouterPatient;
	private Button b_rechercherPatient;
	private Button b_listerRDV;
	private Button b_listerMessages;
	private Button b_listerPatients;
	private Button b_quitter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accueil);
		b_ajouterPatient = (Button) this.findViewById(R.id.buttonAccueilAjouterPatient);
		b_rechercherPatient = (Button) this.findViewById(R.id.buttonAccueilChercherPatient);
		b_listerRDV = (Button) this.findViewById(R.id.buttonAccueilListeRDV);
		b_listerMessages = (Button) this.findViewById(R.id.buttonAccueilListeMessages);
		b_listerPatients = (Button) this.findViewById(R.id.buttonAccueilListePatients);
		b_quitter = (Button) this.findViewById(R.id.buttonAccueilShutdown);
		
		b_ajouterPatient.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AccueilActivity.this, AjouterPatient.class);
				startActivity(intent);
			}
			
		});
		
		b_rechercherPatient.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AccueilActivity.this, RechercherPatient.class);
				startActivity(intent);
				
			}
			
		});
		
		b_listerRDV.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AccueilActivity.this, GererRDV.class);
				startActivity(intent);
				
			}
			
		});
		
		b_listerMessages.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		b_listerPatients.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AccueilActivity.this, ListePatients.class);
				startActivity(intent);
				
			}
			
		});
		
		b_quitter.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
	            System.exit(0);
			}
			
		});
		
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.accueil, menu);
		return true;
	}

}
