package sk.FEI.Kovalak.exceptions;

public class WrongMarkingException extends Exception {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    private void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public WrongMarkingException(String errorMessage){
        this.setErrorMessage(errorMessage);
    }

}
