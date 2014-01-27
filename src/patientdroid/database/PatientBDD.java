package patientdroid.database;


import java.util.ArrayList;
import java.util.List;

import patientdroid.entities.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import patientdroid.database.BaseDDSQLite;;

public class PatientBDD {
	
	
	//utilitaire bdd
		private static final int VERSION_BDD = 1;
		private static final String NOM_BDD = "PatientDroidBDD.bd";
		
		private SQLiteDatabase bdd;
		private BaseDDSQLite maBaseSQLite;
		
		public PatientBDD(Context context){
			//Pour créer la BDD et sa table
			maBaseSQLite = new BaseDDSQLite(context, NOM_BDD, null, VERSION_BDD);
		  	}
		
		public void openWrite(){
			//on ouvre la BDD en écriture
			bdd = maBaseSQLite.getWritableDatabase();
			}
		public void openRead(){
			//on ouvre la BDD en écriture
			bdd = maBaseSQLite.getReadableDatabase();
			}
	 
		public void close(){
			//on ferme l'accès à la BDD
			bdd.close();
			}
		
		public SQLiteDatabase getBDD(){
			return bdd;
			}
		////////////////////////
		////////////////////////
		
		
		/**
		 * traitement de la table PATIENT
		 * 
		 * 
		 */
	
	private static final String TABLE_PATIENT = "table_patient";
	private static final String COL_ID_PATIENT = "id_patient";
	private static final int NUM_COL_ID_PATIENT = 0;
	private static final String COL_NOM = "nom";
	private static final int NUM_COL_NOM = 1;
	private static final String COL_PRENOM = "prenom";
	private static final int NUM_COL_PRENOM = 2;
	private static final String COL_ADRESSE = "adresse";
	private static final int NUM_COL_ADRESSE = 3;
	private static final String COL_TEL = "tel";
	private static final int NUM_COL_TEL = 4;
	private static final String COL_EMAIL = "email";
	private static final int NUM_COL_EMAIL = 5;
	private static final String COL_DATENAISS = "dateNaiss";
	private static final int NUM_COL_DATENAISS = 6;
	private static final String COL_TYPEMALADIE = "typeMaladie";
	private static final int NUM_COL_TYPEMALADIE = 6;
	private static final String COL_DATE_DER_CONSULTATION = "dateDerConsultation";
	private static final int NUM_COL_DATE_DER_CONSULTATION = 7;
	
	
	
	
	
	
	//inserer un patient
	public long insertPatient(Patient patient){
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(COL_NOM, patient.getNom());
		values.put(COL_PRENOM, patient.getPrenom());
		values.put(COL_ADRESSE, patient.getAdresse());
		values.put(COL_TEL, patient.getNumTel());
		values.put(COL_EMAIL, patient.getEmail());
		values.put(COL_DATENAISS, patient.getDateNaissance());
		values.put(COL_TYPEMALADIE, patient.getTypeMaladie());
		values.put(COL_DATE_DER_CONSULTATION, patient.getDateDerniereConsultation());
		return bdd.insert(TABLE_PATIENT, null, values);
		}
	
	//Mise à jour d'un patient
	public int updatePatient(int idPatient, Patient patient){
		//La mise à jour d'un patient dans la BDD fonctionne plus ou moins comme une insertion
		//il faut simple préciser quelle patient on doit mettre à jour grâce à l'ID
		ContentValues values = new ContentValues();
		values.put(COL_NOM, patient.getNom());
		values.put(COL_PRENOM, patient.getPrenom());
		values.put(COL_ADRESSE, patient.getAdresse());
		values.put(COL_TEL, patient.getNumTel());
		values.put(COL_EMAIL, patient.getEmail());
		values.put(COL_DATENAISS, patient.getDateNaissance());
		values.put(COL_TYPEMALADIE, patient.getTypeMaladie());
		values.put(COL_DATE_DER_CONSULTATION, patient.getDateDerniereConsultation());
		return bdd.update(TABLE_PATIENT, values, COL_ID_PATIENT + " = " +idPatient, null);
		 
		
		}
	
	//Supprimer un patient
	public int removePatientWithID(int idPatient){
		//Suppression d'un patient de la BDD grâce à l'ID
		return bdd.delete(TABLE_PATIENT, COL_ID_PATIENT + " = " +idPatient, null);
		}
	////////////////
	////////////////
	/////////////
	//Récupérer tous les patients
	public List<Patient> getAllPatients(){
		List<Patient> patients = new ArrayList<Patient>();
		Cursor c = bdd.query(TABLE_PATIENT, new String[] {COL_ID_PATIENT,COL_NOM, COL_PRENOM},null, null, null, null, null);
		c.moveToFirst();
	    while (!c.isAfterLast()) {
	      Patient pat = cursorToPatient(c);
	      patients.add(pat);
	      c.moveToNext();
	    }
	    // make sure to close the cursor
	    c.close();
	    return patients;
	  }
	
	
   //Rechercher un patient par nom
	public List<Patient> getPatientWithNom(String nom){
		//Récupère dans un Cursor les valeur correspondant à un patient contenu dans la BDD (ici on sélectionne le patient grâce à son nom)
		List<Patient> patients = new ArrayList<Patient>();
		Cursor c = bdd.query(TABLE_PATIENT, new String[] {COL_ID_PATIENT,COL_NOM, COL_PRENOM},COL_NOM + " LIKE \"" + nom +"\"", null, null, null, null);
		c.moveToFirst();
	    while (!c.isAfterLast()) {
	      Patient pat = cursorToPatient(c);
	      patients.add(pat);
	      c.moveToNext();
	    }
	    // make sure to close the cursor
	    c.close();
	    return patients;
	}
	
	//Rechercher un patient par type de maladie
	
	public ArrayList<Patient> getPatientWithTypeMaladie(String typeMaladie){
		//Récupère dans un Cursor les valeur correspondant à un patient contenu dans la BDD (selectionne le patient selon le type de maladie)
		Cursor c = bdd.query(TABLE_PATIENT, new String[] {COL_NOM, COL_PRENOM, COL_TYPEMALADIE}, COL_TYPEMALADIE + " LIKE \"" + typeMaladie +"\"", null, null, null, null);
		return cursorToPatientMaladie(c);
	}
	
	//Rechercher un patient par date derniere consultation
	
	public ArrayList<Patient> getPatientWithDateDerConsulation(String dateDerConsultation){
		//Récupère dans un Cursor les valeur correspondant à un patient contenu dans la BDD (selectionne patient grace à date de consultation)
		Cursor c = bdd.query(TABLE_PATIENT, new String[] {COL_NOM, COL_PRENOM, COL_DATE_DER_CONSULTATION}, COL_DATE_DER_CONSULTATION + " LIKE \"" + dateDerConsultation +"\"", null, null, null, null);
		return cursorToPatientConsultation(c);
	}
   //Récupérer un patient par son id
	public Patient getPatientWithId(int id){
		//Récupère dans un Cursor les valeur correspondant à un patient contenu dans la BDD (selectionne patient grace à date de consultation)
		Cursor c = bdd.query(TABLE_PATIENT, new String[] {COL_ID_PATIENT,COL_NOM, COL_PRENOM, COL_ADRESSE,COL_TEL,COL_EMAIL,COL_DATENAISS,COL_TYPEMALADIE,COL_DATE_DER_CONSULTATION}, COL_ID_PATIENT + " LIKE \"" + id +"\"", null, null, null, null);
		return cursorToPatientId(c);
	}
	//répcupérer un patient avec numéro de tel
	public Patient getPatientWithTel(String tel){
		//Récupère dans un Cursor les valeur correspondant à un patient contenu dans la BDD (selectionne patient grace à date de consultation)
		Cursor c = bdd.query(TABLE_PATIENT, new String[] {COL_ID_PATIENT}, COL_TEL + " LIKE \"" + tel +"\"", null, null, null, null);
		return cursorToPatientTel(c);
		
	}
	//cursor patient tel
	private Patient cursorToPatientTel(Cursor c){
		if (c.getCount() == 0)
			return null;
 
		//Sinon on se place sur le premier élément
		c.moveToFirst();
		Patient patient = new Patient();
		//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
		patient.setId(c.getInt(NUM_COL_ID_PATIENT));
		c.close();
		//On retourne le livre
		return patient;
	}
	
  //Conversion du curseur
	private Patient cursorToPatientId(Cursor c){
		if (c.getCount() == 0)
			return null;
 
		//Sinon on se place sur le premier élément
		c.moveToFirst();
		Patient patient = new Patient();
		//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
		patient.setId(c.getInt(NUM_COL_ID_PATIENT));
		patient.setNom(c.getString(NUM_COL_NOM));
		patient.setPrenom(c.getString(NUM_COL_PRENOM));
		patient.setAdresse(c.getString(NUM_COL_ADRESSE));
		patient.setNumTel(c.getString(NUM_COL_TEL));
		patient.setEmail(c.getString(NUM_COL_EMAIL));
		patient.setDateNaissance(c.getString(NUM_COL_DATENAISS));
		patient.setTypeMaladie(c.getString(NUM_COL_TYPEMALADIE));
		patient.setDateDerniereConsultation(c.getString(NUM_COL_DATE_DER_CONSULTATION));
		//c.close();
		//On retourne le livre
		return patient;
		
	}
	
    /////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
	//Convertir le cursor en patient getAllPatients
	//Cette méthode permet de convertir un cursor en un Patient 
			private Patient cursorToPatient(Cursor c){
				Patient patient = new Patient();
				//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
				patient.setId(c.getInt(NUM_COL_ID_PATIENT));
				patient.setNom(c.getString(NUM_COL_NOM));
				patient.setPrenom(c.getString(NUM_COL_PRENOM));
		 
				//On retourne le livre
				return patient;
			}
	
	//Cette méthode permet de convertir un cursor en un Patient 
			private ArrayList<Patient> cursorToPatientNom(Cursor c){
				//si aucun élément n'a été retourné dans la requête, on renvoie null
				if (c.getCount() == 0)
					return null;
		 
				//Sinon on se place sur le premier élément
				c.moveToFirst();
				Patient patient;
				ArrayList<Patient> listePatient = new ArrayList<Patient>();
				while(c.moveToNext()!=false){
				//On créé un patient
				patient = new Patient();
				//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
			   //patient.setId(c.getInt(NUM_COL_ID_PATIENT));
				patient.setNom(c.getString(NUM_COL_NOM));
				patient.setPrenom(c.getString(NUM_COL_PRENOM));
				listePatient.add(patient);
				}
				//On ferme le cursor
				c.close();
				//On retourne le livre
				return listePatient;
			}
			
/////////////////////////////////////////////////
/////////////////////////////////////////////////
//Cette méthode permet de convertir un cursor en un Patient 
      private ArrayList<Patient> cursorToPatientMaladie(Cursor c){
       //si aucun élément n'a été retourné dans la requête, on renvoie null
       if (c.getCount() == 0)
          return null;

       //Sinon on se place sur le premier élément
       c.moveToFirst();
       Patient patient;
       ArrayList<Patient> listePatient = new ArrayList<Patient>();
       while(c.moveToNext()!=false){
        //On créé un patient
        patient = new Patient();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        //	patient.setId(c.getInt(NUM_COL_ID_PATIENT));
        patient.setNom(c.getString(NUM_COL_NOM));
         patient.setPrenom(c.getString(NUM_COL_PRENOM));
         patient.setTypeMaladie(c.getString(NUM_COL_TYPEMALADIE));
          listePatient.add(patient);
       }
       //On ferme le cursor
         c.close();
       //On retourne la liste
         return listePatient;
}
      
      
    //Cette méthode permet de convertir un cursor en un Patient 
      private ArrayList<Patient> cursorToPatientConsultation(Cursor c){
       //si aucun élément n'a été retourné dans la requête, on renvoie null
       if (c.getCount() == 0)
          return null;

       //Sinon on se place sur le premier élément
       c.moveToFirst();
       Patient patient;
       ArrayList<Patient> listePatient = new ArrayList<Patient>();
       while(c.moveToNext()!=false){
        //On créé un patient
        patient = new Patient();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        //patient.setId(c.getInt(NUM_COL_ID_PATIENT));
        patient.setNom(c.getString(NUM_COL_NOM));
         patient.setPrenom(c.getString(NUM_COL_PRENOM));
         patient.setDateDerniereConsultation(c.getString(NUM_COL_DATE_DER_CONSULTATION));
         listePatient.add(patient);
       }
       //On ferme le cursor
         c.close();
       //On retourne la liste
         return listePatient;
         
 
    }
      
      
      /**
       * 
       * Traitement de la table DossierMedical
       * 
       * 
       * 
       */
      
    private static final String TABLE_DOSSIER_MEDICAL = "table_dossierMedical";
  	private static final String COL_ID_DOSSIER = "id_dossier";
  	private static final int NUM_COL_ID_DOSSIER = 0;
  	private static final String COL_INTITULE_DOSSIER = "intitule_dossier";
  	private static final int NUM_COL_INTITULE_DOSSIER = 1;
  	private static final String COL_FK_PATIENT_DM = "fk_patient";
  	private static final int NUM_COL_FK_PATIENT_DM = 2;
  	
  	 
  	
  		//inserer un dossierMedical
  		public long insertDossierMedical(DossierMedical dossierMedical){
  			//Création d'un ContentValues (fonctionne comme une HashMap)
  			ContentValues values = new ContentValues();
  			//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
  			values.put(COL_INTITULE_DOSSIER, dossierMedical.getIntitule_dossier());
  			values.put(COL_FK_PATIENT_DM, dossierMedical.getPatient().getId());
  			//on insère l'objet dans la BDD via le ContentValues
  			return bdd.insert(TABLE_DOSSIER_MEDICAL, null, values);
  			}
  		
  		//Mise à jour d'un dossier Medical
  		public int updateDossierMedical(int idDossierM, DossierMedical dossierMedical){
  			//La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
  			//il faut simple préciser quelle livre on doit mettre à jour grâce à l'ID
  			ContentValues values = new ContentValues();
  			values.put(COL_INTITULE_DOSSIER, dossierMedical.getIntitule_dossier());
  			values.put(COL_FK_PATIENT_DM, dossierMedical.getPatient().getId());
  			return bdd.update(TABLE_DOSSIER_MEDICAL, values, COL_ID_DOSSIER + " = " +idDossierM, null);
  			}
  		
  		//Supprimer un DossierMedical
  		public int removeDossierMedicalWithID(int idDossierM){
  			//Suppression d'un livre de la BDD grâce à l'ID
  			return bdd.delete(TABLE_DOSSIER_MEDICAL, COL_ID_DOSSIER+ " = " +idDossierM, null);
  			}
  		
  	   //Rechercher un dossier médical par id patient
  		public DossierMedical getDossierMedicalWithPatientId(int idPatient){
  			//Récupère dans un Cursor les valeur correspondant à un dossierMedical contenu dans la BDD (ici on sélectionne le dossierMedical grâce à l'id du patient)
  			Cursor c = bdd.query(TABLE_DOSSIER_MEDICAL, new String[] {COL_ID_DOSSIER,COL_INTITULE_DOSSIER }, COL_FK_PATIENT_DM + " LIKE \"" + idPatient +"\"", null, null, null, null);
  			return cursorToDM(c);
  		}
  		
  		 /////////////////////////////////////////////////
        /////////////////////////////////////////////////
  /////////////////////////////////////////////////
  /////////////////////////////////////////////////
       //Cette méthode permet de convertir un cursor en un dossierMedical 
         private DossierMedical cursorToDM(Cursor c){

         //si aucun élément n'a été retourné dans la requête, on renvoie null
  		if (c.getCount() == 0)
  			return null;

  		//Sinon on se place sur le premier élément
  		c.moveToFirst();
  		//On créé un livre
  		DossierMedical dm = new DossierMedical();
  		//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
  		dm.setId_DossierMedical(c.getInt(NUM_COL_ID_DOSSIER));
  		dm.setIntitule_dossier(c.getString(NUM_COL_INTITULE_DOSSIER));
  		//On ferme le cursor
  		c.close();

  		//On retourne le livre
  		return dm;
  }
         
         /**
          * 
          * 
          * Traitement de la table FicheSuivi
          * 
          */
        private static final String TABLE_FICHE_SUIVI = "table_ficheSuivi";
    	private static final String COL_ID_FICHE = "id_fiche";
    	private static final int NUM_COL_ID_FICHE = 0;
    	private static final String COL_INTITULE_FICHE = "intitule_fiche";
    	private static final int NUM_COL_INTITULE_FICHE = 1;
    	private static final String COL_NOM_MALADIE = "nom_maladie_fiche";
    	private static final int NUM_COL_NOM_MALADIE = 2;
    	private static final String COL_REMARQUE = "remarque_fiche";
    	private static final int NUM_COL_REMARQUE = 3;
    	private static final String COL_FK_DOSSIER = "fk_dossier";
    	private static final int NUM_COL_FK_DOSSIER = 4;
    	
         
       //inserer une fiche de suivi
   		public long insertFicheSuivi(FicheSuivi fiche){
   			//Création d'un ContentValues (fonctionne comme une HashMap)
   			ContentValues values = new ContentValues();
   			//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
   			values.put(COL_INTITULE_FICHE, fiche.getIntitule());
   			values.put(COL_NOM_MALADIE, fiche.getNomMaladie());
   			values.put(COL_REMARQUE, fiche.getRemarque());
   			values.put(COL_FK_DOSSIER , fiche.getDossierM().getId_DossierMedical());
   			//on insère l'objet dans la BDD via le ContentValues
   			return bdd.insert(TABLE_FICHE_SUIVI, null, values);
   			}
   		
   		//récupérer les fiches suivi d'un patient
   	    //Cette méthode permet de convertir un cursor
		private FicheSuivi cursorToFicheSuivi(Cursor c){
			FicheSuivi fiche = new FicheSuivi();
			//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
			fiche.setId_ficheSuivi(c.getInt(NUM_COL_ID_FICHE));
			fiche.setIntitule(c.getString(NUM_COL_INTITULE_FICHE));
			fiche.setNomMaladie(c.getString(NUM_COL_NOM_MALADIE));
			fiche.setRemarque(c.getString(NUM_COL_REMARQUE));
	 
			//On retourne le livre
			return fiche;
		}
         
	//récupérer les fiches de suivi par id dossier
		public List<FicheSuivi> getFichesSuiviByIdDossier(int idDossier){
			List<FicheSuivi> fiches = new ArrayList<FicheSuivi>();
			Cursor c = bdd.query(TABLE_FICHE_SUIVI, new String[] {COL_ID_FICHE,COL_INTITULE_FICHE,COL_NOM_MALADIE, COL_REMARQUE,COL_FK_DOSSIER},COL_FK_DOSSIER  +" LIKE \"" + idDossier +"\"", null, null, null, null);
			c.moveToFirst();
		    while (!c.isAfterLast()) {
		      FicheSuivi f = cursorToFicheSuivi(c);
		      fiches.add(f);
		      c.moveToNext();
		    }
		    // make sure to close the cursor
		    c.close();
		    return fiches;
		  }
	
		
	//récupérer une fiche de suivi par idFiche
		public FicheSuivi getFicheSuiviById(int idFiche){
			//Récupère dans un Cursor les valeur correspondant à un patient contenu dans la BDD (selectionne patient grace à date de consultation)
			Cursor c = bdd.query(TABLE_FICHE_SUIVI, new String[] {COL_ID_FICHE,COL_INTITULE_FICHE,COL_NOM_MALADIE, COL_REMARQUE}, COL_ID_FICHE + " LIKE \"" + idFiche +"\"", null, null, null, null);
			return cursorToFicheSuiviId(c);
			
		}
		
		//cursor to fiche suivi byId
		private FicheSuivi cursorToFicheSuiviId(Cursor c){
			if (c.getCount() == 0)
				return null;
	 
			//Sinon on se place sur le premier élément
			c.moveToFirst();
			FicheSuivi fiche = new FicheSuivi();
			//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
			fiche.setId_ficheSuivi(c.getInt(NUM_COL_ID_FICHE));
			fiche.setIntitule(c.getString(NUM_COL_INTITULE_FICHE));
			fiche.setNomMaladie(c.getString(NUM_COL_NOM_MALADIE));
			fiche.setRemarque(c.getString(NUM_COL_REMARQUE));
			//c.close();
			//On retourne le livre
			return fiche;
			
		}
		
		/**
		 * 
		 * 
		 * Traitement de la table RDV
		 * 
		 * 
		 */
		
		//variables de la table RDV
		private static final String TABLE_RDV= "table_rdv";
    	private static final String COL_ID_RDV = "id_rdv";
    	private static final int NUM_COL_ID_RDV = 0;
    	private static final String COL_DATE_RDV = "date_rdv";
    	private static final int NUM_COL_DATE_RDV = 1;
    	private static final String COL_HEURE_RDV = "heure_rdv";
    	private static final int NUM_COL_HEURE_RDV = 2;
    	private static final String COL_FK_PATIENT_RDV = "fk_patient";
    	private static final int NUM_COL_FK_PATIENT_RDV = 3;
		
		//inserer une fiche de suivi
   		public long insertRDV(RDV rdv){
   			//Création d'un ContentValues (fonctionne comme une HashMap)
   			ContentValues values = new ContentValues();
   			//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
   			values.put(COL_DATE_RDV, rdv.getDateRdv());
   			values.put(COL_HEURE_RDV , rdv.getHeureRdv());
   			values.put(COL_FK_PATIENT_RDV , rdv.getPatient().getId());
   			//on insère l'objet dans la BDD via le ContentValues
   			return bdd.insert(TABLE_RDV, null, values);
   			}
   		//convertur un cursor en RDV
   		private RDV cursorToRDV(Cursor c){
			RDV rdv = new RDV();
			//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
			rdv.setId_rdv(c.getInt(NUM_COL_ID_RDV));
			rdv.setDateRdv(c.getString(NUM_COL_DATE_RDV));
			rdv.setHeureRdv(c.getString(NUM_COL_HEURE_RDV));
			rdv.setIdPatient(c.getInt(NUM_COL_FK_PATIENT_RDV));

	 
			//On retourne le livre
			return rdv;
		}
   		
   		//récupérer tous les rdvs
   	//Récupérer tous les patients
   		public List<RDV> getAllRDV(){
   			List<RDV> rdvs = new ArrayList<RDV>();
   			Cursor c = bdd.query(TABLE_RDV, new String[] {COL_ID_RDV,COL_DATE_RDV, COL_HEURE_RDV,COL_FK_PATIENT_RDV},null, null, null, null, null);
   			c.moveToFirst();
   		    while (!c.isAfterLast()) {
   		      RDV rdv = cursorToRDV(c);
   		      rdvs.add(rdv);
   		      c.moveToNext();
   		    }
   		    // make sure to close the cursor
   		    c.close();
   		    return rdvs;
   		  }
   		
   		public RDV getRDVByIdPatient(int idPatient){
   			
   		//Récupère dans un Cursor les valeur correspondant à un patient contenu dans la BDD (selectionne patient grace à date de consultation)
			Cursor c = bdd.query(TABLE_RDV, new String[] {COL_ID_RDV,COL_DATE_RDV,COL_HEURE_RDV}, COL_FK_PATIENT_RDV + " LIKE \"" + idPatient +"\"", null, null, null, null);
			return cursorToRDVIdPatient(c);
   		}
   		
   		//convetir cursor to rdv by id
   		private RDV cursorToRDVIdPatient(Cursor c){
			if (c.getCount() == 0)
				return null;
	 
			//Sinon on se place sur le premier élément
			c.moveToFirst();
			RDV rdv = new RDV();
			//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
			rdv.setId_rdv(c.getInt(NUM_COL_ID_RDV));
			rdv.setDateRdv(c.getString(NUM_COL_DATE_RDV));
			rdv.setHeureRdv(c.getString(NUM_COL_HEURE_RDV));
			
			//c.close();
			//On retourne le livre
			return rdv;
			
		}
   		
   	//Supprimer un patient
   		public int removeRDVWithIdPatient(int idPatient){
   			//Suppression d'un patient de la BDD grâce à l'ID
   			return bdd.delete(TABLE_RDV, COL_FK_PATIENT_RDV + " =  " +idPatient, null);
   			}
		
		
	
}
