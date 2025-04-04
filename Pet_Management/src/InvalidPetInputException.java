// Custom exception for invalid pet name or text input
class InvalidPetInputException extends Exception {
    public InvalidPetInputException(String message) {
        super(message);
    }
}