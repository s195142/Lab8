package it.polito.tdp.metrodeparis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.metrodeparis.model.Fermata;

public class MetroDAO {

	public List<Fermata> getFermate() {
		
		final String sql = "SELECT id_fermata, nome, coordx, coordy FROM fermata ORDER BY nome ASC ";
		
		List<Fermata> fermate = new LinkedList<Fermata>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("id_fermata");
				String nome = rs.getString("nome");
				double x = rs.getDouble("coordX");
				double y = rs.getDouble("coordY");

				Fermata f = new Fermata(id, nome, x , y);
				fermate.add(f);
			}

			return fermate;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public void popolaGrafo(Graph<Fermata, DefaultEdge> grafo) {
		
//		SELECT f1.id_fermata AS id1, f1.nome AS n1, f1.coordX AS x1, f1.coordY AS y1, 
//		f2.id_fermata AS id2, f2.nome AS n2, f2.coordX AS x2, f2.coordY AS y2
//		FROM fermata AS f1, fermata AS f2, connessione AS cn
		
	}
	
	public boolean esisteConnessione(Fermata partenza, Fermata arrivo) {
		
		String sql = "SELECT COUNT(*) AS cnt FROM connessione WHERE id_stazP=? AND id_stazA=? ";
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, partenza.getId());
			st.setInt(2, arrivo.getId());
			
			ResultSet rs = st.executeQuery();
			rs.next(); // mi posiziono sulla prima e unica riga
			
			int numero = rs.getInt("cnt");
			
			conn.close();

			return (numero>0);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Fermata> stazioniArrivo(Fermata partenza) {
		// TODO Auto-generated method stub
		String sql = "SELECT id_stazA FROM connessione WHERE id_stazP=? ";
		
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, partenza.getId());
			ResultSet rs = st.executeQuery();
						
			List<Fermata> result = new ArrayList<>();
			
			while(rs.next()) {
				result.add(new Fermata(rs.getInt("id_stazA")));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}