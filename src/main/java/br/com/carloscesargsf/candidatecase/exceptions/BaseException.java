package br.com.carloscesargsf.candidatecase.exceptions;

public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = -7459333181519833493L;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Object... params) {
        this(params.length > 0 ? String.format(message, params) : message);
    }

}
