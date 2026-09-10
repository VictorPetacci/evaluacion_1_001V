import kotlin.concurrent.thread

fun consultarDisponibilidad(): Boolean {
    var resultado = false
    println("Consultando disponibilidad de alojamientos en segundo plano...")

    val hilo = thread {
        Thread.sleep(1000)
        resultado = true
    }
    hilo.join()

    return resultado
}

fun mostrarDetalleReserva(reserva: ReservaAlojamiento) {
    println("--------------------------------------------------")
    println("ID: ${reserva.id}")
    println("Cliente: ${reserva.nombreCliente}")
    println("Noches: ${reserva.cantidadNoches}")
    println("Valor por noche: \$${reserva.valorPorNoche}")
    println("Cantidad de personas: ${reserva.cantidadPersonas}")
    println("Tipo de Alojamiento: ${reserva.tipoAlojamiento.descripcion}")
    println("Valor Total: \$${reserva.calcularMontoTotal()}")
    println("Descripción: ${reserva.obtenerDescripcion()}")
}
