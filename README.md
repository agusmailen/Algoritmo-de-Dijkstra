# Algoritmo-de-Dijkstra

Dado un grafo y un vértice desde el cual comenzar, el algoritmo da
como resultado el costo del camino más corto entre ese nodo y todos los demás,
siempre que exista un camino. 

Se utilizó una implementación estática para implementar un TDA grafo y se desarrolló el Algortmo de Dijkstra siguiendo la misma estrategia.

Al comenzar, el algoritmo solo cuenta con la información del vértice inicial y lasdistancias hacia sus vértices adyacentes y en cada 
iteración determinará cual es el mejor candidato, es decir, el que posee la menor distancia hacia el nodo inicial, paramarcarlo como 
visitado. Luego de marcar como visitado un nodo se evaluará, si esque posee adyacentes, si utilizándolo como puente se puede mejorar el 
camino entre el origen y el resto de los vértices pendientes por visitar.

Bibliografía

- Scar, Melanie. Camino Mínimos en Grafos. Facultad de Ciencias Exactas y Naturales.
Universidad de Buenos Aires. Nacional OIA 2016. http://www.oia.unsam.edu.ar/wpcontent/uploads/2017/11/dijkstra-prim.pdf

- Figueroa, A. G. Jhosimar. Camino más corto: Algoritmo de Dijkstra. Algorithms and More, 2012.
https://jariasf.wordpress.com/2012/03/19/camino-mas-corto-algoritmo-de-dijkstra/
