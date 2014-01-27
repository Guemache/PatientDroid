package patientdroid.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDDSQLite extends SQLiteOpenHelper {
	
	////////////////////////
	//Table Patient//////////////////////////
	private static final String TABLE_PATIENT = "table_patient";
	private static final String COL_ID_PATIENT = "id_patient";
	private static final String COL_NOM = "nom";
	private static final String COL_PRENOM = "prenom";
	private static final String COL_ADRESSE = "adresse";
	private static final String COL_TEL = "tel";
	private static final String COL_EMAIL = "email";
	private static final String COL_DATENAISS = "dateNaiss";
	private static final String COL_TYPEMALADIE = "typeMaladie";
	private static final String COL_DATE_DER_CONSULTATION = "dateDerConsultation";
	
	private static final String CREATE_PATIENT = "CREATE TABLE " + TABLE_PATIENT + " ("
			+ COL_ID_PATIENT + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NOM + " TEXT NOT NULL, "
			+ COL_PRENOM + " TEXT NOT NULL, " + COL_ADRESSE + " TEXT NOT NULL, " + COL_TEL + " TEXT NOT NULL,"
			+ COL_EMAIL + " TEXT NOT NULL, " + COL_DATENAISS + " TEXT NOT NULL, " +
			COL_TYPEMALADIE + " TEXT NOT NULL, "+ COL_DATE_DER_CONSULTATION + " TEXT NOT NULL );"; 
	
	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	
	//Table DossierMedical/////////////////////
	
	private static final String TABLE_DOSSIER_MEDICAL = "table_dossierMedical";
	private static final String COL_ID_DOSSIER = "id_dossier";
	private static final String COL_FK_PATIENT_DM = "fk_patient";
	private static final String COL_INTITULE_DOSSIER = "intitule_dossier";
	
	private static final String CREATE_DOSSIER_MEDICAL = "CREATE TABLE " + TABLE_DOSSIER_MEDICAL + " ("
			+ COL_ID_DOSSIER + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_INTITULE_DOSSIER + " TEXT, "
			+ COL_FK_PATIENT_DM + " INTEGER NOT NULL, " +
			" FOREIGN KEY("+COL_FK_PATIENT_DM+") REFERENCES " +TABLE_PATIENT+"("+COL_ID_PATIENT+"));"; 
	
	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	
	//Table FicheSuivi////////////////////////
	
	private static final String TABLE_FICHE_SUIVI = "table_ficheSuivi";
	private static final String COL_ID_FICHE = "id_fiche";
	private static final String COL_INTITULE_FICHE = "intitule_fiche";
	private static final String COL_NOM_MALADIE = "nom_maladie_fiche";
	private static final String COL_REMARQUE = "remarque_fiche";
	private static final String COL_FK_DOSSIER = "fk_dossier";
	
	private static final String CREATE_FICHE_SUIVI = "CREATE TABLE " + TABLE_FICHE_SUIVI + " ("
			+ COL_ID_FICHE + "  INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_INTITULE_FICHE + " TEXT NOT NULL, "
			+ COL_NOM_MALADIE + " TEXT NOT NULL, " + COL_REMARQUE + " TEXT NOT NULL,"
			+ COL_FK_DOSSIER + " INTEGER NOT NULL, " + 
			"FOREIGN KEY("+COL_FK_DOSSIER+") REFERENCES  "+TABLE_DOSSIER_MEDICAL+"("+COL_ID_DOSSIER+"));"; 
	
	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	
	//Table RDV////////////////////////////////
	
	private static final String TABLE_RDV = "table_rdv";
	private static final String COL_ID_RDV = "id_rdv";
	private static final String COL_DATE_RDV = "date_rdv";
	private static final String COL_HEURE_RDV = "heure_rdv";
	private static final String COL_FK_PATIENT_RDV = "fk_patient";
	
	private static final String CREATE_TABLE_RDV = "CREATE TABLE " + TABLE_RDV + " ("
			+ COL_ID_RDV + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_DATE_RDV + " TEXT NOT NULL, "
			+ COL_HEURE_RDV + " TEXT NOT NULL," + COL_FK_PATIENT_RDV + " INTEGER NOT NULL,"+
			" FOREIGN KEY("+COL_FK_PATIENT_RDV+") REFERENCES " +TABLE_PATIENT+"("+COL_ID_PATIENT+"));"; 
	
	
	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

	//Table SMS///////////////////////////////////
	
	private static final String TABLE_SMS = "table_sms";
	private static final String COL_ID_SMS = "id_sms";
	private static final String COL_CONTENU_SMS = "contenu_sms";
	private static final String COL_DATE_SMS = "date_sms";
	private static final String COL_HEURE_SMS = "heure_sms";
	private static final String COL_FK_PATIENT_SMS = "fk_patient";
	
	private static final String CREATE_TABLE_SMS = "CREATE TABLE " + TABLE_SMS + " ("
			+ COL_ID_SMS + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_CONTENU_SMS + " TEXT, "
			+ COL_DATE_SMS + " TEXT NOT NULL," + COL_HEURE_SMS + " TEXT NOT NULL," + 
			COL_FK_PATIENT_SMS + " INTEGER NOT NULL," +
			" FOREIGN KEY("+COL_FK_PATIENT_SMS+") REFERENCES "+TABLE_PATIENT+"("+COL_ID_PATIENT+"));"; 
	
	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	public BaseDDSQLite(Context context, String name, CursorFactory factory,int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_PATIENT);
		db.execSQL(CREATE_DOSSIER_MEDICAL);
		db.execSQL(CREATE_FICHE_SUIVI);
		db.execSQL(CREATE_TABLE_RDV);
		db.execSQL(CREATE_TABLE_SMS);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
