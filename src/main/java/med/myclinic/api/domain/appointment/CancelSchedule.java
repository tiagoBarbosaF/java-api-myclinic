package med.myclinic.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record CancelSchedule(
        @NotNull @JsonAlias("consulta_id") Long idAppointment,
        @NotNull @JsonAlias("motivo_cancelamento") CancellationReason cancellationReason
) {
}
