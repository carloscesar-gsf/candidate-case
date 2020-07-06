package br.com.carloscesargsf.candidatecase.exceptions;

import static br.com.carloscesargsf.candidatecase.constants.ExceptionConstants.RESOURCE_NOT_FOUND;

public class ResourceNotFoundException extends BaseException {

    private static final long serialVersionUID = 6744002122172891647L;

    public ResourceNotFoundException() {
        super(RESOURCE_NOT_FOUND);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Object... params) {
        super(message, params);
    }

}
