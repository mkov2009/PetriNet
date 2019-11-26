package sk.FEI.Kovalak.exceptions;

public class SameTypeElementException extends Exception{
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    private void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public SameTypeElementException(String errorMessage){
        this.setErrorMessage(errorMessage);
    }
}
