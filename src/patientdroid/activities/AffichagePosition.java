package patientdroid.activities;

import medecin.patient.R;
import medecin.patient.R.layout;
import medecin.patient.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.*;

@SuppressLint("NewApi")
public class AffichagePosition extends Activity  {
	private MapFragment map;
	private String argIntent;
	private String [] tabBesoinCoord;
	private String coord1;
	private String coord2;

	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_affichage_position);
		
		//récupération des attributs
		Intent i = getIntent();
		argIntent = i.getStringExtra("coordonnees");
		tabBesoinCoord = argIntent.split(";");
		String coord = tabBesoinCoord[0];
		String [] tab1 = coord.split("-");
		String nomPrenom = tabBesoinCoord[1];
		coord1 = tab1[0];
		coord2 = tab1[1];
		float c1 = Float.parseFloat(coord1);
		float c2 = Float.parseFloat(coord2);
		
		// Get a handle to the Map Fragment
        GoogleMap map = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapFrag)).getMap();

        LatLng position = new LatLng(c1, c2);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                position, 16));

        // Flat markers will rotate when the map is rotated,
        // and change perspective when the map is tilted.
        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icone_position))
                .position(position).title(nomPrenom).snippet("a besoin d'aide")
                .flat(true)
                .rotation(245));

       
        
      


        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		
			
		
		
		
		
	}
	




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.affichage_position, menu);
		return true;
	}



	

}
