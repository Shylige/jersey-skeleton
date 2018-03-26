package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.CommandeDto;
import fr.iutinfo.skeleton.common.dto.UserDto;

public class Commande {
	private int id;
	private int idClient;
	private int idProduit;
	private String prenom;
	private int idImage;
	private boolean regard; // true gauche false droite
	private String couleur;
	private String typo;
	private boolean format; // true A3 false A4
	
	public Commande() {
	}
	
	public Commande(int id, int idClient, int idProduit, String prenom, int idImage, boolean regard,String couleur, String typo,
			boolean format) {
		this.id = id;
		this.idClient = idClient;
		this.idProduit = idProduit;
		this.prenom = prenom;
		this.idImage = idImage;
		this.regard = regard;
		this.typo = typo;
		this.format = format;
		this.couleur=couleur;
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

	public void initFromDto(CommandeDto dto) {
        this.setId(dto.getId());
        this.setCouleur(dto.getCouleur());
        this.setFormat(dto.isFormat());
        this.setIdProduit(dto.getIdProduit());
        this.setIdClient(dto.getIdClient());
        this.setRegard(dto.isRegard());
        this.setTypo(dto.getTypo());
        this.setIdImage(dto.getIdImage());
        this.setPrenom(dto.getPrenom());
        
    }

    public CommandeDto convertToDto() {
        CommandeDto dto = new CommandeDto();
        
        dto.setId(this.getId());
        dto.setCouleur(this.getCouleur());
        dto.setFormat(this.isFormat());
        dto.setIdProduit(this.getIdProduit());
        dto.setIdClient(this.getIdClient());
        dto.setRegard(this.isRegard());
        dto.setTypo(this.getTypo());
        dto.setIdImage(this.getIdImage());
        dto.setPrenom(this.getPrenom());
        return dto;
    }
}
