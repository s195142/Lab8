package it.polito.tdp.metrodeparis.model;


public class TestModel {

	public static void main(String[] args) {
		
		Model m = new Model();
		m.creaGrafo();
		//System.out.println(m.getGrafo());

		System.out.format("Creati %d vertici e %d archi\n", m.getGrafo().vertexSet().size(), m.getGrafo().edgeSet().size());
		
//		List<Fermata> raggiungibili = m.fermateRaggiungibili(source);
	}

}
