package main.java.ejercicios.ejercicio3;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class HeuristicEjercicio3 {

    /**
     * Suma el beneficio de todos los productos que aún no han sido analizados.
     *
     * @param source el vértice origen.
     * @param goal   restricción que indica que no quedan más vértices por analizar.
     * @param target el vértice destino.
     * @return el beneficio de los productos de los p`roductos que aún no se han analizado.
     */
    public static Double heuristic(VertexEjercicio3 source, Predicate<VertexEjercicio3> goal, VertexEjercicio3 target) {
        return (Objects.equals(source.id(), DataEjercicio3.getNumProductos())) ? 0. :
                IntStream.range(source.id(), DataEjercicio3.getNumProductos()).boxed()
                        .mapToDouble(i -> DataEjercicio3.beneficioProductos(i, source.tiempoProduccionRestante(), source.tiempoManualRestante()))
                        .sum();
    }

    private HeuristicEjercicio3() {
    }
}
