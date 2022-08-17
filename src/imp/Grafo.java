package imp;
import api.ConjuntosTDA;
import api.GrafoTDA;

public class Grafo implements GrafoTDA {
	
	static int n = 100;
	
	int[][] matriz;
	
	int[] vector;
	
	int cantNodos;
	
	public void InicializarGrafo() {
		matriz = new int[n][n];
		vector = new int[n];
		cantNodos = 0;
	}

	public void AgregarVertice(int vertice) { //lineal
		vector[cantNodos] = vertice;
		
		for (int i = 0; i <= cantNodos; i++) {
			matriz[cantNodos][i] = 0;
			matriz[i][cantNodos] = 0;
		}
		
		cantNodos++;
	}
	
	private int vertice2Indice(int vertice) {
		int i = cantNodos-1;
		
		while (i >= 0 && vector[i] != vertice) i--; //lineal
		
		return i;
	}

	public void EliminarVertice(int vertice) {
		int indice = vertice2Indice(vertice);
		
		for(int i = 0; i < cantNodos; i++) matriz[i][indice] = matriz[i][cantNodos-1];
		
		for (int i = 0; i < cantNodos; i++) matriz[indice][i] = matriz[cantNodos-1][i];
		
		vector[indice] = vector[cantNodos-1];
		
		cantNodos--;
	}

	public void AgregarArista(int verticeUno, int verticeDos, int peso) {
		int origen = vertice2Indice(verticeUno);
		int destino = vertice2Indice(verticeDos);
		
		matriz[origen][destino] = peso;
	}

	public void EliminarArista(int verticeUno, int verticeDos) {
		int origen = vertice2Indice(verticeUno);
		int destino = vertice2Indice(verticeDos);
		
		matriz[origen][destino] = 0;		
	}

	public ConjuntosTDA Vertices() {
		ConjuntosTDA vertices = new Conjunto();
		vertices.InicializarConjunto();
		
		for(int i = 0; i < cantNodos; i++) vertices.Agregar(vector[i]); //cuadratico
		
		return vertices;
	}

	public boolean ExisteArista(int verticeUno, int verticeDos) {
		int origen = vertice2Indice(verticeUno);
		int destino = vertice2Indice(verticeDos);
				
		return matriz[origen][destino] != 0; //cte
	}

	@Override
	public int PesoArista(int verticeUno, int verticeDos) { // lineal
		int origen = vertice2Indice(verticeUno);
		int destino = vertice2Indice(verticeDos); // lineal
				
		return matriz[origen][destino];
	}
	
}
