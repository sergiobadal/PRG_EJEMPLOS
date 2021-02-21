package anonimas;
// Una IF es una interfaz (todos los métodos deben ser abstractos) con un único 
//  método y este es abstracto
// Creamos IF (IFMensaje) con un método abstracto "imprimir" que no hace nada 
//  (es abstracto)
// CASO A:  SIN CLASE ANÓNIMA, CON IMPLEMENTS
//          Implementamos esa IF en una subclase (MensajeImplementado)
//          Creamos una instancia de esa subclase
//          Llamamos al método "imprimir": imprime UNA vez el texto recibido 
// CASO B:  CON CLASE ANÓNIMA              
//          Creamos una instancia de esa subclase
//          Al crear la instancia definimos el método abstracto con @Override
//          Llamamos al método "imprimir": imprime DOS veces el texto recibido 
// CASO C:  CON FUNCIÓN LAMBDA
//          Creamos una instancia de esa subclase
//          Al crear la instancia definimos el método abstracto con UNA LAMBDA
//          Llamamos al método "imprimir": imprime TRES veces el texto recibido 

public class Main {
    public static void main(String[] args) {
        // Sin clase anónima (con implements en una subclase): imprime 1 vez
        MensajeImplementado m1 = new MensajeImplementado();
        System.out.println(m1.imprime("SinClaseAnonima"));
        // Con clase anónima: imprime 2 veces
        IFMensaje m2 = new IFMensaje() {
            //@Override
            public String imprime(String t) {
                return t + t;
            }
        };
        System.out.println(m2.imprime("ClaseAnonima"));
        // Con función anónima lambda: imprime 3 veces
        // !! Las expresiones lambda solo sirven con INTERFACES FUNCIONALES
        IFMensaje m3 = (t) -> (t + t + t);
        System.out.println(m3.imprime("FuncionAnonima"));
    }
}
