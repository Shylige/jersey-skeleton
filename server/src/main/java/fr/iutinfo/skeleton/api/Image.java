package fr.iutinfo.skeleton.api;

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
	
	
}
