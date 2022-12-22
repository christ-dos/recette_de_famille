package fr.dawan.formation.exception;

public class IngredientNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IngredientNotFoundException(String message) {
        super(message);
    }

}
