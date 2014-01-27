package patientdroid.entities;

public class SMS {
	private int id_sms;
	private String contenu_sms;
	private Patient patient;
	private int idPatient;
	
	//constructeur
	public SMS(String contenu_sms, Patient patient){
		 
		this.contenu_sms=contenu_sms;
		 this.patient=patient;
		 
	}
	
	public String getContenu_sms() {
		return contenu_sms;
	}
	public void setContenu_sms(String contenu_sms) {
		this.contenu_sms = contenu_sms;
	}
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	

	public int getId_sms() {
		return id_sms;
	}

	public void setId_sms(int id_sms) {
		this.id_sms = id_sms;
	}

	public int getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

}
