package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IDAO;
import entities.Categorie;
import entities.Chambre;
import entities.Client;
import java.util.Date;
public class ChambreService implements IDAO<Chambre>{
	@Override
	public String toString() {
		return "ChambreService [chambres=" + chambres + "]";
	}

	List<Chambre> chambres;
	public ChambreService() {
		chambres = new ArrayList<Chambre>();
	}

	@Override
	public Boolean create(Chambre object) {
		String query = " insert into chambre values (null,?,?);";
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			preparedStatement.setString(1, object.getTelephone());
			preparedStatement.setInt(2, object.getCategorie().getId());
			if(preparedStatement.executeUpdate()==1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public Boolean update(Chambre object) {
		String query = "UPDATE chambre SET telephone=?,id_categorie=? where id=?;";
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			preparedStatement.setString(1, object.getTelephone());
			preparedStatement.setInt(2, object.getCategorie().getId());
			preparedStatement.setInt(3, object.getId());
			return preparedStatement.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Boolean delete(Chambre object) {
		String query = "delete from chambre where id=?";
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			preparedStatement.setInt(1, object.getId());
			return preparedStatement.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Chambre findById(int id) {
		CategorieService categorieService = new CategorieService();
		String query = "select * from chambre where id=?;";
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return new Chambre(resultSet.getInt("id"),resultSet.getString("telephone"),new Categorie(resultSet.getInt("id_categorie"),categorieService.findById(resultSet.getInt("id_categorie")).getCode(),categorieService.findById(resultSet.getInt("id_categorie")).getCode()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Chambre> findAll() {
		CategorieService categorieService = new CategorieService();
		String query = "select * from categorie;";
		List<Chambre> chambres = new ArrayList<Chambre>();
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				 chambres.add(new Chambre(resultSet.getInt("id"),resultSet.getString("telephone"),new Categorie(resultSet.getInt("id_categorie"),categorieService.findById(resultSet.getInt("id_categorie")).getCode(),categorieService.findById(resultSet.getInt("id_categorie")).getCode())));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chambres;
	}
	
	public List<Chambre> findChambreBetweenDates (Client client, Date dateDebut,Date datefin){
		
		return null;
	}
	
	public List<Chambre> findChambreByCategorie(Categorie categorie){
		List<Chambre> listChambre;
		
		return null;
	}

}
