class MantenedorReservas {
    private val reservas: MutableList<ReservaAlojamiento> = mutableListOf()

    fun registrarReserva(reserva: ReservaAlojamiento): ResultadoRegistro {
        return try {
            when {
                reserva.id <= 0 ->
                    ResultadoRegistro.Error("El ID (${reserva.id}) debe ser mayor a 0.")
                reserva.nombreCliente.isBlank() ->
                    ResultadoRegistro.Error("El nombre del cliente no puede estar vacío.")
                reserva.cantidadNoches <= 0 ->
                    ResultadoRegistro.Error("La cantidad de noches debe ser mayor a 0.")
                reserva.valorPorNoche <= 0 ->
                    ResultadoRegistro.Error("El valor por noche debe ser mayor a 0.")
                reserva.cantidadPersonas <= 0 ->
                    ResultadoRegistro.Error("La cantidad de personas debe ser mayor a 0.")
                reservas.any { it.id == reserva.id } ->
                    ResultadoRegistro.Error("Ya existe una reserva registrada con el ID #${reserva.id}.")
                else -> {
                    reservas.add(reserva)
                    ResultadoRegistro.Exito("Reserva de ${reserva.nombreCliente} registrada exitosamente.", reserva)
                }
            }
        } catch (e: Exception) {
            ResultadoRegistro.Error("Error inesperado en el sistema: ${e.message}")
        }
    }

    fun obtenerReservas(): List<ReservaAlojamiento> {
        return reservas.toList()
    }
}
