package fr.iutinfo.skeleton.api;

public class Image {
	private int id;
	private int idUser;
	private String url;
	
	public Image() {
    }
	
	public Image(int id, int idUser, String url) {
		this.id = id;
		this.url = url;
		this.idUser=idUser;
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

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	
}
