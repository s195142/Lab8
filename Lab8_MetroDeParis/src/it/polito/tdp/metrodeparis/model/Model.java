package it.polito.tdp.metrodeparis.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.metrodeparis.dao.MetroDAO;

public class Model {
	
	private Graph<Fermata, DefaultEdge> grafo; // non simple xk è uguale -.-
	private List<Fermata> fermate;
	private Map<Integer, Fermata> fermateIdMap;
	
	public void creaGrafo() {
		// crea oggetto grafo
		this.grafo = new SimpleDirectedGraph<>(DefaultEdge.class);

		//aggiungi vertici
		MetroDAO dao = new MetroDAO();
		this.fermate = dao.getFermate();
		Graphs.addAllVertices(this.grafo, this.fermate);
		
		//aggiungi archi (opzione 1)
		for(Fermata partenza : this.grafo.vertexSet()) {
			for(Fermata arrivo : this.grafo.vertexSet()) {
				if(dao.esisteConnessione(partenza, arrivo)) {
					this.grafo.addEdge(partenza, arrivo);
				}
			}
		}
		
		//aggiungi archi (opzione 2)
		for(Fermata partenza : this.grafo.vertexSet()) {
			List<Fermata> arrivi = dao.stazioniArrivo(partenza);
			
			for(Fermata arrivo : arrivi) {
				this.grafo.addEdge(partenza, arrivo);
			}
		}
		
		//aggiungi archi (opzione 3)

	}

	public List<Fermata> fermateRaggiungibili(Fermata source){
		
		List<Fermata> result = new ArrayList<Fermata>();
		
		GraphIterator<Fermata, DefaultEdge> it = new BreadthFirstIterator<>(this.grafo, source);
		//  parametro source x dire all'iteratore da dove partire
		while(it.hasNext()) {
			result.add(it.next());
		}
		return result;
	}
	

	public static List<Fermata> getAllFermate() {
		MetroDAO dao = new MetroDAO();
		List<Fermata> fermate = new LinkedList<Fermata>(dao.getFermate());
		return fermate;
	}

	public String calcolaPercorso(Fermata p, Fermata a) {
		String res = "";
		MetroDAO dao = new MetroDAO();
		dao.popolaGrafo(grafo);
		return null;
	}


	public Graph<Fermata, DefaultEdge> getGrafo() {
		return grafo;
	}

}
