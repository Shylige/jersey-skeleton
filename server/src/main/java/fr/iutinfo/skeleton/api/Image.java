package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.ImageDto;

public class Image {
	private int id;
	private int idClient;
	private String url;
	
	public Image() {
    }
	
	public Image(int id, int idClient, String url) {
		this.id = id;
		this.url = url;
		this.idClient=idClient;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	
	public void initFromDto(ImageDto dto) {
		this.setId(dto.getId());
		this.setIdClient(dto.getIdClient());
		this.setUrl(dto.getUrl());
    }

    public ImageDto convertToDto() {
        ImageDto dto = new ImageDto();
        dto.setId(this.getId());
        dto.setIdClient(this.getIdClient());
        dto.setUrl(this.getUrl());
        
        return dto;
    }
	
	
}
