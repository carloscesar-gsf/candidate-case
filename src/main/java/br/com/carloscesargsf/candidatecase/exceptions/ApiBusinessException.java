package br.com.carloscesargsf.candidatecase.exceptions;

public class ApiBusinessException extends BaseException {

    private static final long serialVersionUID = 5743348830245315569L;

    public ApiBusinessException(String message) {
        super(message);
    }

    public ApiBusinessException(String message, Object... params) {
        super(message, params);
    }

}
