package sk.FEI.Kovalak.exceptions;

public class MissingElementException extends Exception {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    private void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public MissingElementException(String errorMessage){
        this.setErrorMessage(errorMessage);
}
}
