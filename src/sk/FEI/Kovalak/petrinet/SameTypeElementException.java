package sk.FEI.Kovalak.petrinet;

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
