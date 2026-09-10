open class Reserva(
    val id: Int,
    val cliente: String,
    val noches: Int
) {
    open fun obtenerDescripcion(): String {
        return "Reserva General ID #$id - Cliente: $cliente - Noches: $noches"
    }
}
