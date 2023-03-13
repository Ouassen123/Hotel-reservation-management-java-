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

public class ClientService implements IDAO<Client>{
	List<Client> clients;
	public ClientService() {
		
	}

	@Override
	public Boolean create(Client object) {
		String query = "insert into client values (null,?,?,?,?);";
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			preparedStatement.setString(1, object.getNom());
			preparedStatement.setString(2, object.getPrenom());
			preparedStatement.setString(3, object.getTelephone());
			preparedStatement.setString(4, object.getEmail());
			return preparedStatement.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String toString() {
		return "ClientService [clients=" + clients + "]";
	}

	@Override
	public Boolean update(Client object) {
		String query = "UPDATE client SET nom=?,prenom=?,telephone=?,email=? where id=?;";
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			preparedStatement.setString(1, object.getNom());
			preparedStatement.setString(2, object.getPrenom());
			preparedStatement.setString(3, object.getTelephone());
			preparedStatement.setString(4, object.getEmail());
			preparedStatement.setInt(5, object.getId());
			return preparedStatement.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Boolean delete(Client object) {
		String query = "delete from client where id=?";
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
	public Client findById(int id) {
		String query = "select * from client where id=?;";
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return new Client(resultSet.getInt("id"), resultSet.getString("nom"),resultSet.getString("prenom"),resultSet.getString("telephone"),resultSet.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Client> findAll() {
		String req="select * from client";
		List<Client> clients =new ArrayList<Client>();
		PreparedStatement stmt;
		try {
			stmt = connexion.Connexion.getConnexion().prepareStatement(req);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				clients.add(new Client(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("telephone"),rs.getString("email")));
			}
			return clients;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
