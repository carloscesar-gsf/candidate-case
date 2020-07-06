package br.com.carloscesargsf.candidatecase.exceptions;

public class NotAuthenticatedException extends BaseException {

    private static final long serialVersionUID = 1423619445648169150L;

    public NotAuthenticatedException(String message) {
        super(message);
    }

    public NotAuthenticatedException(String message, Object... params) {
        super(message, params);
    }

}
