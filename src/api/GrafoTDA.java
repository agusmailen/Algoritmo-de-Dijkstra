package api;

public interface GrafoTDA {
	
	void InicializarGrafo(); // Sin precondiciones.

	void AgregarVertice(int vertice); // Grafo inicializado y no existe nodo V.
	
	void EliminarVertice(int vertice); // Grafo inicializado y existe nodo V.
	
	void AgregarArista(int verticeUno, int verticeDos, int peso); // Grafo inicializado y no existe arista.
	
	void EliminarArista(int verticeUno, int verticeDos); // Grafo inicializado y existe arista.
	
	ConjuntosTDA Vertices(); // Grafo inicializado.
	
	boolean ExisteArista(int verticeUno, int verticeDos); // Grafo inicializado y existe nodo v1 y v2.
	
	int PesoArista(int verticeUno, int verticeDos); // Grafo inicizalizado y existe arista.

}
