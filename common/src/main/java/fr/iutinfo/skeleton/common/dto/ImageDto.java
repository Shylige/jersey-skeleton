package fr.iutinfo.skeleton.common.dto;

public class ImageDto {
	private int id;
	private int idClient;
	private String url;
	
	public ImageDto() {
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
	
	
}
