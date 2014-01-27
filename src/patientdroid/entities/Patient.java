package patientdroid.entities;

public class Patient {
	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private String numTel;
	private String email;
	private String dateNaissance;
	private String typeMaladie;
	private String dateDerniereConsultation;
	private DossierMedical dm;
	//constructeur
	public Patient(){
		
	}
	
	public Patient(String nom, String prenom,String adresse,String numTel,String email,String dateNaissance,String typeMaladie,String dateDerniereConsultation){
		this.nom=nom;
		this.prenom=prenom;
		this.adresse=adresse;
		this.numTel=numTel;
		this.email=email;
		this.dateNaissance=dateNaissance;
		this.typeMaladie=typeMaladie;
		this.dateDerniereConsultation=dateDerniereConsultation;
		
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getNumTel() {
		return numTel;
	}
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeMaladie() {
		return typeMaladie;
	}
	public void setTypeMaladie(String typeMaladie) {
		this.typeMaladie = typeMaladie;
	}

	public String getDateDerniereConsultation() {
		return dateDerniereConsultation;
	}

	public void setDateDerniereConsultation(String dateDerniereConsultation) {
		this.dateDerniereConsultation = dateDerniereConsultation;
	}

	public DossierMedical getDm() {
		return dm;
	}

	public void setDm(DossierMedical dm) {
		this.dm = dm;
	}

}
