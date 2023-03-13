package service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;  
 
import java.util.List;

import dao.IDAO;
import entities.Categorie;
import entities.Chambre;
import entities.Client;
import entities.Reservation;

public class ReservationService implements IDAO<Reservation>{
	List<Reservation> reservations;

	public ReservationService() {
		reservations = new ArrayList<Reservation>();
	}

	@Override
	public Boolean create(Reservation object) {
		String query = "insert into reservation values (null,?,?,?,?);";
		if(this.findAll() == null) {
			
		}else {
			try {
				PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
				preparedStatement.setInt(1, object.getChambre().getId());
				preparedStatement.setInt(2, object.getClient().getId());
				preparedStatement.setDate(3, new Date(object.getDatedebut().getTime()));
				preparedStatement.setDate(4, new Date(object.getDatefin().getTime()));
				return preparedStatement.executeUpdate()>0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
		
	}

	@Override
	public String toString() {
		return "ReservationService [reservations=" + reservations + "]";
	}

	@Override
	public Boolean update(Reservation object) {
		String query = "UPDATE reservation SET id_chambre=?,id_client=?,date_debut=?,date_fin=? where id=?;";
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			preparedStatement.setInt(1, object.getChambre().getId());
			preparedStatement.setInt(2, object.getClient().getId());
			preparedStatement.setDate(3, new Date(object.getDatedebut().getTime()));
			preparedStatement.setDate(4, new Date(object.getDatefin().getTime()));
			preparedStatement.setInt(5, object.getId());
			return preparedStatement.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Boolean delete(Reservation object) {
		String query = "delete from reservation where id=?";
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
	public Reservation findById(int id) {
		ChambreService chambreService = new ChambreService();
		ClientService clientService = new ClientService();
		String query = "select * from reservation where id=?;";
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return new Reservation(resultSet.getInt("id"), chambreService.findById(resultSet.getInt("id_chambre")),clientService.findById(resultSet.getInt("id_client")),resultSet.getDate("date_debut"),resultSet.getDate("date_fin"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reservation> findAll() {
		ChambreService chambreService = new ChambreService();
		ClientService clientService = new ClientService();
		String query = "select * from reservation;";
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				reservations.add(new Reservation(resultSet.getInt("id"), chambreService.findById(resultSet.getInt("id_chambre")),clientService.findById(resultSet.getInt("id_client")),resultSet.getDate("date_debut"),resultSet.getDate("date_fin")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reservations;
	}


}
