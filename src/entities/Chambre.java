package entities;

public class Chambre {
	@Override
	public String toString() {
		return "Chambre [id=" + id + ", telephone=" + telephone + ", categorie=" + categorie + "]";
	}
	private static int count;
	private int id;
	private String telephone;
	private Categorie categorie;
	
	public Chambre(String telephone, Categorie categorie) {
		this.id = ++count;
		this.telephone = telephone;
		this.categorie = categorie;
	}
	public Chambre(int id, String telephone, Categorie categorie) {
		this.id = id;
		this.telephone = telephone;
		this.categorie = categorie;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
}
