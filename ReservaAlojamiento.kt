
data class ReservaAlojamiento(
    val idReserva: Int,
    val nombreCliente: String,
    val cantidadNoches: Int,
    val valorPorNoche: Double,
    val cantidadPersonas: Int,
    val tipoAlojamiento: TipoAlojamiento
) : Reserva(idReserva, nombreCliente, cantidadNoches) {

    fun calcularMontoTotal(): Double {
        return valorPorNoche * cantidadNoches
    }

    override fun obtenerDescripcion(): String {
        return "Reserva de Alojamiento [#$idReserva] | Cliente: $nombreCliente | " +
                "Tipo: ${tipoAlojamiento.descripcion} | Total: \$${calcularMontoTotal()}"
    }
}
