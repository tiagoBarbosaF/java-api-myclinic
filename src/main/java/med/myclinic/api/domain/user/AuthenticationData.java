package med.myclinic.api.domain.user;

public record AuthenticationData(
        String login,
        String password
) {
}
