package fr.dawan.formation.exception;

public class CategorieNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CategorieNotFoundException(String message) {
        super(message);
    }
}
