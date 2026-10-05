package mx.edu.utez.proyecto2D.exception;

public class PaqueteNoValidoException extends RuntimeException {
    public PaqueteNoValidoException(String mensaje) {
        super(mensaje);
    }
}