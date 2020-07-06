package br.com.carloscesargsf.candidatecase.exceptions;

public class ParamFormatException extends BaseException {

    private static final long serialVersionUID = -2598554523688861224L;

    public ParamFormatException(String message) {
        super(message);
    }

    public ParamFormatException(String message, Object... params) {
        super(message, params);
    }

}
