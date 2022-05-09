package org.bank.bankingsystem.exception;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }

    public static class NotFoundException extends CustomException{
        public NotFoundException(String message){
            super(message);
        }
    }

    public static class AlreadyExistsException extends CustomException{
        public AlreadyExistsException(String message){
            super(message);
        }
    }

    public static class InsufficientStorage extends CustomException{
        public InsufficientStorage(String message) { super(message); }
    }

    public static class UnauthorizedTransfer extends CustomException{
        public UnauthorizedTransfer(String message) { super(message);}
    }
}
