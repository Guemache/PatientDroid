package patientdroid.entities;



public class RDV {
	private int id_rdv;
	private String dateRdv;
	private String heureRdv;
	private Patient patient;
	private  int idPatient; 
	
	
	//
	public RDV(){
		
	}
	//constructeur
	public RDV(String dateRdv,String heureRdv,Patient patient){
		this.dateRdv=dateRdv;
		this.heureRdv=heureRdv;
		this.patient=patient;
	}
	
	public String getDateRdv() {
		return dateRdv;
	}
	public void setDateRdv(String dateRdv) {
		this.dateRdv = dateRdv;
	}
	public String getHeureRdv() {
		return heureRdv;
	}
	public void setHeureRdv(String heureRdv) {
		this.heureRdv = heureRdv;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public int getId_rdv() {
		return id_rdv;
	}

	public void setId_rdv(int id_rdv) {
		this.id_rdv = id_rdv;
	}
	public int getIdPatient() {
		return idPatient;
	}
	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}
	

}
