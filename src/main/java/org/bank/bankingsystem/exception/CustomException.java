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

    public static class InsufficientFundsException extends CustomException{
        public InsufficientFundsException(String message) { super(message); }
    }

    public static class RemoveAdminException extends CustomException{
        public RemoveAdminException(String message) { super(message); }
    }

    public static class UnauthorizedTransfer extends CustomException{
        public UnauthorizedTransfer(String message) { super(message);}
    }

    public static class InvalidUserDetails extends CustomException{
        public InvalidUserDetails(String message){
            super(message);
        }
    }
}
