package patientdroid.activities;


import medecin.patient.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EnvoyerSMS extends Activity {
	private String argIntent;
	private String [] tabBesoinSMS;
	private String nomPatient;
	private String prenomPatient;
	private String telPatient;
	private Button b_envoyerSMS;
	private Button b_effacerSMS;
	private TextView nomPrenomPatient;
	private EditText smsContenu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_envoyer_sms);
		Intent i = getIntent();
		argIntent = i.getStringExtra("besoinSMS");
		tabBesoinSMS = argIntent.split("-");
		nomPatient = tabBesoinSMS[0];
		prenomPatient = tabBesoinSMS[1];
		telPatient = tabBesoinSMS[2];
		
		nomPrenomPatient = (TextView) this.findViewById(R.id.textViewNomPrenomSMS);
		nomPrenomPatient.setText(nomPatient+" "+prenomPatient+" : "+telPatient);
		//récupérer les boutons
		b_envoyerSMS = (Button) this.findViewById(R.id.buttonEnvoyerSMS);
		b_effacerSMS = (Button) this.findViewById(R.id.buttonEffacerSMS);
		smsContenu = (EditText) this.findViewById(R.id.editTextEnvoiSMS);
		
		b_envoyerSMS.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			String msg = smsContenu.getText().toString();
			if(telPatient.length()>= 4 && msg.length() > 0){
				//Grâce à l'objet de gestion de SMS (SmsManager) que l'on récupère via la méthode static getDefault()
				//On envoie le SMS à l'aide de la méthode sendTextMessage
				SmsManager.getDefault().sendTextMessage(telPatient, null, msg, null, null);
				//On efface les deux EditText
				smsContenu.setText("Votre Message a été envoyé!");
			}else{
				//On affiche un petit message d'erreur dans un Toast
				smsContenu.setText("Message non envoyé! Le numéro de téléphone n'est pas valide ou le message est court!");
			}
				
			}
		});
		/**
		 * 
		 * effacer sms
		 */
		b_effacerSMS.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				smsContenu.setText("");
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.envoyer_sm, menu);
		return true;
	}

}
