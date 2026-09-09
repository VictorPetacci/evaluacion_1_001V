import kotlin.concurrent.thread

// ==========================================
// 11. Operación Asíncrona
// Simula consulta de disponibilidad con retardo usando un hilo aparte.
// No requiere librerías externas (viene en la librería estándar de Kotlin).
// ==========================================
fun consultarDisponibilidad(): Boolean {
    var resultado = false
    println("Consultando disponibilidad de alojamientos en segundo plano...")

    val hilo = thread {
        Thread.sleep(1000) // Simulación de 1 segundo de espera
        resultado = true
    }
    hilo.join() // El programa principal espera a que el hilo termine antes de continuar

    return resultado
}

// ==========================================
// Función Modular de Salida / Visualización
// ==========================================
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
