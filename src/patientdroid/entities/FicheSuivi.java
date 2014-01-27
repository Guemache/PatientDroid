package patientdroid.entities;

public class FicheSuivi {
	private int id_ficheSuivi;
	private String intitule;
	private String nomMaladie;
	private String remarque;
	private DossierMedical dossierM;
	
	//
	public FicheSuivi(){
		
	}
	
	//constructeur
	public FicheSuivi(String intitule,String nomMaladie,String remarque,DossierMedical dossierM){
		this.intitule=intitule;
		this.nomMaladie=nomMaladie;
		this.remarque=remarque;
		this.dossierM=dossierM;
		
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	public String getNomMaladie() {
		return nomMaladie;
	}
	public void setNomMaladie(String nomMaladie) {
		this.nomMaladie = nomMaladie;
	}
	public String getRemarque() {
		return remarque;
	}
	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}
	public DossierMedical getDossierM() {
		return dossierM;
	}
	public void setDossierM(DossierMedical dossierM) {
		this.dossierM = dossierM;
	}
	
	public int getId_ficheSuivi() {
		return id_ficheSuivi;
	}
	public void setId_ficheSuivi(int id_ficheSuivi) {
		this.id_ficheSuivi = id_ficheSuivi;
	}
}
