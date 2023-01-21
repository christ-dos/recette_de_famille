package fr.dawan.formation.exception;

public class CategorieAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CategorieAlreadyExistException(String message) {
        super(message);
    }

}
