package fr.iutinfo.skeleton.common.dto;

public class CommandeDto {
	private int id;
	private int idClient;
	private int idProduit;
	private String prenom;
	private int idImage;
	private boolean regard; // true gauche false droite
	private String couleur;
	private String typo;
	private boolean format; // true A3 false A4
	
	public CommandeDto() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public int getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getIdImage() {
		return idImage;
	}
	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}
	public boolean isRegard() {
		return regard;
	}
	public void setRegard(boolean regard) {
		this.regard = regard;
	}
	public String getTypo() {
		return typo;
	}
	public void setTypo(String typo) {
		this.typo = typo;
	}
	public boolean isFormat() {
		return format;
	}
	public void setFormat(boolean format) {
		this.format = format;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	
	
	
	
}
