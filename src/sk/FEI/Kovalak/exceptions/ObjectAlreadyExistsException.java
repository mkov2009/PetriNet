package sk.FEI.Kovalak.exceptions;

public class ObjectAlreadyExistsException extends Exception {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    private void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ObjectAlreadyExistsException(String errorMessage){
        this.setErrorMessage(errorMessage);
    }
}
