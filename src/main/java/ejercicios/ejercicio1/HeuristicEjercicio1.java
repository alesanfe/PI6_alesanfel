package main.java.ejercicios.ejercicio1;

import java.util.function.Predicate;

public class HeuristicEjercicio1 {

    /**
     * Cuenta el número de ficheros que quedan por ser analizados desde el vértice origen.
     *
     * @param source el vértice origen.
     * @param goal   restricción que indica que no quedan más vértices por analizar.
     * @param target el vértice destino.
     * @return un valor entre 0. y el número de ficheros.
     */
    public static Double heuristic(VertexEjercicio1 source, Predicate<VertexEjercicio1> goal, VertexEjercicio1 target) {
        return (DataEjercicio1.getNumFichero() - source.id()) * 1.0;
    }

    private HeuristicEjercicio1() {
    }
}
