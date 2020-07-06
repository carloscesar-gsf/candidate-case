package br.com.carloscesargsf.candidatecase.exceptions;

public class NotAuthorizedException extends BaseException {

    private static final long serialVersionUID = -2598554523688861224L;

    public NotAuthorizedException(String message) {
        super(message);
    }

    public NotAuthorizedException(String message, Object... params) {
        super(message, params);
    }

}
