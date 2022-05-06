package main.java.ejercicios.ejercicio4;

import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record VertexEjercicio4(Integer indice,
                               List<Integer> capacidadRestante) implements VirtualVertex<VertexEjercicio4, EdgeEjercicio4, Integer> {

    public static VertexEjercicio4 initialVertex() {
        return of(0, IntStream.range(0, DataEjercicio4.getNumContenedores()).boxed().map(DataEjercicio4::getCapacidadContenedor).toList());
    }

    public static VertexEjercicio4 of(Integer indice, List<Integer> capacidadRestante) {
        return new VertexEjercicio4(indice, capacidadRestante);
    }

    public static Predicate<VertexEjercicio4> goal() {
        return v -> Objects.equals(v.indice, DataEjercicio4.getNumElementos());
    }

    /**
     * Obtiene el número de contenedores que ya están llenos.
     *
     * @return número de contenedores llenos.
     */
    public Double weight() {
        return capacidadRestante().stream().filter(capacidad -> capacidad == 0).count() * 1.0;
    }

    public static Predicate<VertexEjercicio4> constraint() {
        // Comprueba que la solución tenga el contenedor lleno o no tenga ningún elemento en su interior.
        return v -> IntStream.range(0, DataEjercicio4.getNumContenedores()).boxed()
                .allMatch(contenedor -> v.capacidadRestante.get(contenedor) == 0
                        || Objects.equals(v.capacidadRestante.get(contenedor), DataEjercicio4.getCapacidadContenedor(contenedor)));
    }

    @Override
    public List<Integer> actions() {
        // Si estamos en el último elemento, no se puede realizar ninguna acción.
        if (Objects.equals(indice, DataEjercicio4.getNumElementos()))
            return List2.empty();

        List<Integer> acciones = IntStream.range(0, capacidadRestante.size())
                // Para cada elemento y para cada contenedor, sólo se puede ubicar en caso de que esté permitido acorde a sus tipos.
                .filter(contenedor -> DataEjercicio4.elementoEnContenedor(indice, contenedor, capacidadRestante))
                .boxed().collect(Collectors.toList());
        // El elemento puede no ser almacenado en un contenedor.
        acciones.add(DataEjercicio4.getNumContenedores());
        return acciones;
    }

    @Override
    public VertexEjercicio4 neighbor(Integer a) {
        List<Integer> auxCapacidadRestante = List2.copy(capacidadRestante);
        // Comprobamos que el elemento se ha colocado en un contenedor y si lo está, disminuimos la capacidad del contenedor correspondiente..
        if (!Objects.equals(a, DataEjercicio4.getNumContenedores()))
            auxCapacidadRestante.set(a, capacidadRestante.get(a) - DataEjercicio4.getTamanoElemento(indice));
        return of(indice + 1, auxCapacidadRestante);
    }

    @Override
    public EdgeEjercicio4 edge(Integer a) {
        return EdgeEjercicio4.of(this, neighbor(a), a);
    }
}
