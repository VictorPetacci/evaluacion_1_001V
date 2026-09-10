sealed class ResultadoRegistro {
    data class Exito(val mensaje: String, val reserva: ReservaAlojamiento) : ResultadoRegistro()
    data class Error(val motivo: String) : ResultadoRegistro()
}
