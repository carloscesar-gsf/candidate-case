package br.com.carloscesargsf.candidatecase.utils;

public final class UserUtils {

    private UserUtils() {
        // $COVERAGE-IGNORE$
    }

    // public static UsuarioDTO getPrincipal() {
    //     return (UsuarioDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    // }

    public static boolean checkIfHasPermission(String authority) {
        // return getPrincipal().hasAuthority(authority);
        return true;
    }

}
