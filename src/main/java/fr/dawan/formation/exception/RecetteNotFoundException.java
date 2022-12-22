package fr.dawan.formation.exception;

public class RecetteNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RecetteNotFoundException(String message) {
        super(message);
    }

}
