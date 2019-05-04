package sk.FEI.Kovalak.petrinet;

public class TransitionNotRunnableException extends Exception{
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    private void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public TransitionNotRunnableException(String errorMessage){
        this.setErrorMessage(errorMessage);
    }
}
