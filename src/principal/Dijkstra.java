package principal;

import api.ConjuntosTDA;
import api.GrafoTDA;
import imp.Conjunto;
import imp.Grafo;

public class Dijkstra {
	
	public static ConjuntosTDA copiarConjunto(ConjuntosTDA conjunto) {
		ConjuntosTDA conjuntoCopy = new Conjunto();
		conjuntoCopy.InicializarConjunto();
		ConjuntosTDA conjuntoAux = new Conjunto();
		conjuntoAux.InicializarConjunto();
		
		while(!conjunto.ConjuntoVacio()) {
			int value = conjunto.Elegir();
			conjuntoAux.Agregar(value);
			conjunto.Sacar(value);
		}
		
		while(!conjuntoAux.ConjuntoVacio()) {
			int value = conjuntoAux.Elegir();
			conjunto.Agregar(value);
			conjuntoCopy.Agregar(value);
			conjuntoAux.Sacar(value);
		}
		return conjuntoCopy;
	}
	
	public static void mostrarGrafo(GrafoTDA grafo) {
		ConjuntosTDA vertices = grafo.Vertices();
		
		while(!vertices.ConjuntoVacio()) {
			int vertice = vertices.Elegir();
			ConjuntosTDA vertices2 = grafo.Vertices();
			while(!vertices2.ConjuntoVacio()) {
				int vertice2 = vertices2.Elegir();
				if (grafo.ExisteArista(vertice, vertice2)) {
					int peso = grafo.PesoArista(vertice, vertice2);
					System.out.print("(" + vertice + "," + vertice2 + "," + peso + ")");
				}
				vertices2.Sacar(vertice2);
			}
			vertices.Sacar(vertice);
		}
	}
	
	
	public static int[][] armarMatriz(GrafoTDA grafo, int origen) {
		
		ConjuntosTDA vertices = grafo.Vertices();
		int cantidadVertices = 0;
		
		while(!vertices.ConjuntoVacio()) {
			int vertice = vertices.Elegir();
			vertices.Sacar(vertice);
			cantidadVertices++;
		}
		
		int[][] matriz = new int[cantidadVertices][cantidadVertices];
		
		for(int i=0; i < cantidadVertices; i++) {
			if(i+1 != origen) {
				matriz[0][i] = (int) Double.POSITIVE_INFINITY;
			}
		}
		return matriz;
	}
	
	public static int[][] completarDistancias(int[][] array, int inicial, GrafoTDA grafo) {
		
		ConjuntosTDA vertices =  grafo.Vertices();
		vertices.Sacar(inicial);
		
		while(!vertices.ConjuntoVacio()) {
			
			int vertice = vertices.Elegir();
			
			if(grafo.ExisteArista(inicial, vertice)) {
				array[0][vertice-1] = grafo.PesoArista(inicial, vertice);
				array[1][vertice-1] = inicial;
			}
			vertices.Sacar(vertice);
		}
		return array;
	}
	
	public static int obtenerCercano(int[][] array, ConjuntosTDA yaVisitados) {
		
		int menor = (int) Double.POSITIVE_INFINITY;
		int posicion = 0;
		
		for(int i = 0; i < array.length; i++) {
			if(array[0][i] != 0 && array[0][i] < menor && !yaVisitados.Pertenece(i+1)) {
				menor = array[0][i];
				posicion = i;				
			}
		}
		return (posicion+1);
	}
	
	
	public static int[][] modificarDistancias(int[][] array, GrafoTDA grafo, int vertice, ConjuntosTDA conjuntoPendientes) {
		
		ConjuntosTDA candidatos = copiarConjunto(conjuntoPendientes);
		
		while(!candidatos.ConjuntoVacio()) {
			
			int posibleAdyacente = candidatos.Elegir();
			
			candidatos.Sacar(posibleAdyacente);
			
			if(grafo.ExisteArista(vertice, posibleAdyacente) && conjuntoPendientes.Pertenece(posibleAdyacente)) {
				if(grafo.PesoArista(vertice, posibleAdyacente) + array[0][vertice - 1] < array[0][posibleAdyacente - 1]) {
					array[0][posibleAdyacente - 1] = grafo.PesoArista(vertice, posibleAdyacente) + array[0][vertice - 1];
					array[1][posibleAdyacente-1] = vertice;
				}
			}
		}
		
		return array;
	}
	
	public static GrafoTDA armarGrafo(int[][] verticeInicial, GrafoTDA original) {
		 
		 GrafoTDA grafo = new Grafo();
		 grafo.InicializarGrafo();
		 
		 ConjuntosTDA v = original.Vertices();
		 
		 while(!v.ConjuntoVacio()) {
			 int vertice = v.Elegir();
			 grafo.AgregarVertice(vertice);
			 v.Sacar(vertice);
		 }
		 
		 ConjuntosTDA vertices = original.Vertices();
		 
		 while(!vertices.ConjuntoVacio()) {
			 
			 int vertice = vertices.Elegir();
			 int verticeOrigen = verticeInicial [1][vertice-1];
			 
			 if(verticeOrigen != 0) {		 
				 int pesoArista = original.PesoArista(verticeOrigen, vertice);
				 grafo.AgregarArista(verticeOrigen, vertice, pesoArista);
			 }
			 
			vertices.Sacar(vertice);
		 }
		 return grafo;
	 }
	
	public static GrafoTDA Dijkstra(GrafoTDA grafo, int inicial) {
		
		ConjuntosTDA visitados = new Conjunto(); 
		visitados.InicializarConjunto();
		visitados.Agregar(inicial);
		
		int[][] matriz = armarMatriz(grafo, inicial);
		
		matriz = completarDistancias(matriz, inicial, grafo); //cargamos las distancias del nodo principal a sus adyacentes.
		
		ConjuntosTDA pendientes = grafo.Vertices();
		pendientes.Sacar(inicial);
		
		while(!pendientes.ConjuntoVacio()) {
			
			int verticeCercano = obtenerCercano(matriz, visitados); //Obtenes el vertice mas cercano al inicial
			
			visitados.Agregar(verticeCercano); 
			pendientes.Sacar(verticeCercano);
			
			matriz = modificarDistancias(matriz, grafo, verticeCercano, pendientes); //obtenemos los adyacentes y 		
		}
		GrafoTDA grafoFinal = armarGrafo(matriz, grafo);
		return grafoFinal;	
	}

	public static void main(String[] args) {
		
		GrafoTDA grafo = new Grafo();
		grafo.InicializarGrafo();
		grafo.AgregarVertice(1);
		grafo.AgregarVertice(2);
		grafo.AgregarVertice(3);
		grafo.AgregarVertice(4);

		grafo.AgregarArista(1, 2, 12);
		grafo.AgregarArista(1, 3, 21);
		grafo.AgregarArista(2, 1, 10);
		grafo.AgregarArista(3, 4, 32);

		mostrarGrafo(grafo);
		
		System.out.println("");
		
		GrafoTDA caminosMinimos = Dijkstra(grafo, 1);
		mostrarGrafo(caminosMinimos);

	}

}
