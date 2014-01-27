package patientdroid.entities;

import java.util.ArrayList;

public class DossierMedical {
	private int id_DossierMedical;
	private ArrayList<FicheSuivi> fichesMalade;
	private Patient patient;
	private String intitule_dossier;
	
	//constructeur
	public DossierMedical(){
		
	}
	
	public DossierMedical(ArrayList<FicheSuivi> fichesMalade,Patient patient){
		this.fichesMalade=fichesMalade;
		this.patient=patient;
	}
	
	public DossierMedical(String intituleDM, Patient patient){
		this.intitule_dossier=intituleDM;
		this.patient=patient;
	}
	public ArrayList<FicheSuivi> getFichesMalade() {
		return fichesMalade;
	}
	public void setFichesMalade(ArrayList<FicheSuivi> fichesMalade) {
		this.fichesMalade = fichesMalade;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public int getId_DossierMedical() {
		return id_DossierMedical;
	}
	public void setId_DossierMedical(int id_DossierMedical) {
		this.id_DossierMedical = id_DossierMedical;
	}

	public String getIntitule_dossier() {
		return intitule_dossier;
	}

	public void setIntitule_dossier(String intitule_dossier) {
		this.intitule_dossier = intitule_dossier;
	}
}
