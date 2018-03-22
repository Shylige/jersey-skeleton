package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.ImageDto;
import fr.iutinfo.skeleton.common.dto.ProduitsDto;

public class Produits {
	private int id;
	private String nom;
	private double prix;
	private String image;
	private String description;
	
	
	public Produits() {
		
	}
	
	public Produits(int id, String nom, double prix, String image, String description) {
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.image = image;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public void initFromDto(ProduitsDto dto) {
		this.setId(dto.getId());
		this.setNom(dto.getNom());
		this.setImage(dto.getImage());
		this.setPrix(dto.getPrix());
		this.setDescription(dto.getDescription());
    }

    public ProduitsDto convertToDto() {
        ProduitsDto dto = new ProduitsDto();
        dto.setId(this.getId());
        dto.setImage(this.getImage());
        dto.setNom(this.getNom());
        dto.setPrix(this.getPrix());
        dto.setDescription(this.getDescription());
        return dto;
    }
}
