package sk.FEI.Kovalak.petrinet;

public class IdAlreadyUsedException extends Exception {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    private void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public IdAlreadyUsedException(String errorMessage){
        this.setErrorMessage(errorMessage);
    }
}
