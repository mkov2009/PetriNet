package sk.FEI.Kovalak.exceptions;

public class WrongTypeElementException extends Exception {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    private void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public WrongTypeElementException(String errorMessage){
        this.setErrorMessage(errorMessage);
    }
}
