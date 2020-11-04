package services;

public class Answer {
    private final boolean isSuccessful;
    private String description;

    public Answer(boolean isValidated) {
        this.isSuccessful = isValidated;
        description = "";
    }

    public Answer(boolean isValidated, String description) {
        this.isSuccessful = isValidated;
        this.description = description;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public String getDescription() {
        return description;
    }
}
