package edu.eci.arsw.primefinder;

public class Main {

    public static void main(String[] args) {
        Control control = Control.newControl();
        
        control.start();

    }
/*Modifícalo para que cada t milisegundos:
Se pausen todos los hilos trabajadores.
Se muestre cuántos números primos se han encontrado.
El programa espere ENTER para reanudar.
La sincronización debe usar synchronized, wait(), notify() / notifyAll() sobre el mismo monitor (sin busy-waiting).
Entrega en el reporte de laboratorio las observaciones y/o comentarios explicando tu diseño de sincronización (qué lock, 
qué condición, cómo evitas lost wakeups). */
}
