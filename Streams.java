package streams;

import java.util.*;
import java.util.stream.*;

class Libro {

    double precio;
    String titulo;
    int paginas;

    Libro(String titulo, double precio, int pags) {
        this.precio = precio;
        this.titulo = titulo;
        this.paginas = pags;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPags(int paginas) {
        this.paginas = paginas;
    }

    double getPrecio() {
        return precio;
    }

    void setPrecio(double precio) {
        this.precio = precio;
    }

    String getTitulo() {
        return titulo;
    }

    void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}

public class Streams {

    static void listarLibros(String label, ArrayList<Libro> alist) {
        Iterator<Libro> iter
                = alist.iterator();
        System.out.println("\n-----\n" + label + "\n");
        while (iter.hasNext()) {
            System.out.print(iter.next().getTitulo() + "; ");
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Libro> libros = new ArrayList<>();
        libros.add(new Libro("Sergio 12€ 3pags", 12, 3));
        libros.add(new Libro("Gabi 123€ 2pags", 123, 2));
        libros.add(new Libro("Nacho 1€ 1pags", 1, 1));
        System.out.println("\n************LISTADOS ASCENDENTES\n");
        libros.sort((p1, p2) -> Double.compare(p1.getPrecio(), p2.getPrecio()));
        listarLibros("Ordenados por precio (real) Double.compare(p1,p2)", libros);
        libros.sort((p1, p2) -> p1.getPaginas() - p2.getPaginas());   // o bien 
        listarLibros("Ordenados por páginas (entero): p1-p2", libros);
        libros.sort((p1, p2) -> Integer.compare(p1.getPaginas(), p2.getPaginas()));
        listarLibros("Ordenados por páginas (entero) Integer.compare(p1, p2)", libros);
        libros.sort((p1, p2) -> Double.compare(p1.getPrecio(), p2.getPrecio()));
        listarLibros("Ordenados por precio Double.compare(p1, p2)", libros);
        libros.sort((p1, p2) -> p1.getTitulo().compareTo(p2.getTitulo()));
        listarLibros("Ordenados por nombre (cadena): p1.compareTo(p2)", libros);
        System.out.println("\n************LISTADOS DESCENDENTES\n");
        libros.sort((p1, p2) -> Double.compare(p2.getPrecio(), p1.getPrecio()));
        listarLibros("Ordenados por precio (real) Double.compare(p2,p1)", libros);
        libros.sort((p1, p2) -> p2.getPaginas() - p1.getPaginas());   // o bien 
        listarLibros("Ordenados por páginas (entero): p2-p1", libros);
        libros.sort((p1, p2) -> Integer.compare(p2.getPaginas(), p1.getPaginas()));
        listarLibros("Ordenados por páginas (entero) Integer.compare(p2, p1)", libros);
        libros.sort((p1, p2) -> Double.compare(p2.getPrecio(), p1.getPrecio()));
        listarLibros("Ordenados por precio Double.compare(p2, p1)", libros);
        libros.sort((p1, p2) -> p2.getTitulo().compareTo(p1.getTitulo()));
        listarLibros("Ordenados por nombre (cadena): p2.compareTo(p1)", libros);

        System.out.println("\n************STREAMS INTERMEDIOS\n");
        // STREAMS INTERMEDIOS: Devuelven Stream
        //      FILTRADO
        Stream<Libro> librosFiltradosOrdenadosPorPrecioAscendente
                = libros.stream()
                        .filter(p -> p.getPrecio() > 1)
                        .sorted((p1, p2) -> Double.compare(p2.getPrecio(), p1.getPrecio()));
        System.out.println("************librosFiltradosOrdenadosPorPrecioAscendente\n");
        librosFiltradosOrdenadosPorPrecioAscendente.forEach(p -> System.out.println(p.getTitulo()));
        //      MAPEADO Y FILTRADO
        Stream<String> tituloDeStreamDeLibrosFiltradosOrdenadosMapeados
                = libros.stream()
                        .filter(p -> p.getPrecio() > 1)
                        .sorted((p1, p2) -> Double.compare(p2.getPrecio(), p1.getPrecio()))
                        .map(p -> p.getTitulo());
        System.out.println("************tituloDeStreamDeLibrosFiltradosOrdenadosMapeados\n");
        tituloDeStreamDeLibrosFiltradosOrdenadosMapeados.forEach(p -> System.out.println(p));
        Stream<Double> precioDeStreamDeLibrosFiltradosOrdenadosMapeados
                = libros.stream()
                        .filter(p -> p.getPrecio() > 1)
                        .sorted((p1, p2) -> Double.compare(p2.getPrecio(), p1.getPrecio()))
                        .map(p -> p.getPrecio());
        System.out.println("************precioDeStreamDeLibrosFiltradosOrdenadosMapeados\n");
        precioDeStreamDeLibrosFiltradosOrdenadosMapeados.forEach(p -> System.out.println(p));
        System.out.println("\n************STREAMS FINALES\n");
        // STREAMS FINALES: Devuelven OTRA COSA que no es un Stream
        // FOR EACH
        System.out.println("************ FOREACH título de los libros caros ordenados por precio \n");
        libros.stream()
                .filter(p -> p.getPrecio() > 1)
                .sorted((p1, p2) -> Double.compare(p2.getPrecio(), p1.getPrecio()))
                .map(p -> p.getTitulo())
                .forEach(p -> System.out.println(p));

        // COLLECT
        List<Double> preciosAltosOrdenados
                = libros.stream()
                        .filter(p -> p.getPrecio() > 1)
                        .sorted((p1, p2) -> Double.compare(p2.getPrecio(), p1.getPrecio()))
                        .map(p -> p.getPrecio())
                        .collect(Collectors.toList());
        System.out.println("************COLLECT preciosAltosOrdenados\n");
        preciosAltosOrdenados.forEach(p -> System.out.println(p));
        // COLLECT JOINING
        String librosPreciosAltosOrdenadosSeparadosComas
                = libros.stream()
                        .filter(p -> p.getPrecio() > 1)
                        .sorted((p1, p2) -> Double.compare(p2.getPrecio(), p1.getPrecio()))
                        .map(p -> p.getTitulo())
                        .collect(Collectors.joining(",", "Texto antes => ", " <= Texto después"));
        System.out.println("************COLLECT JOINING preciosAltosOrdenados\n" + librosPreciosAltosOrdenadosSeparadosComas);

        // MapToInt con Average
        double mediaPaginas
                = libros.stream()
                        .mapToInt(p -> p.getPaginas()).average().getAsDouble();

        System.out.println("************COLLECT MAPTOINT AVERAGE GETASDOUBLE preciosAltosOrdenados\n" + mediaPaginas);
    }
}
