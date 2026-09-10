
fun main() {
    println("=== MANTENEDOR DE RESERVAS DE ALOJAMIENTO ===\n")

    val disponible = consultarDisponibilidad()
    if (disponible) {
        println("Disponibilidad confirmada. Iniciando el sistema...\n")
    }

    val mantenedor = MantenedorReservas()

    val intentosDeRegistro = listOf(
        ReservaAlojamiento(1, "Ana Pérez", 3, 45000.0, 2, TipoAlojamiento.Habitacion),
        ReservaAlojamiento(2, "Carlos Gómez", 5, 60000.0, 4, TipoAlojamiento.Cabana),
        ReservaAlojamiento(3, "María Torres", 2, 80000.0, 3, TipoAlojamiento.Departamento),

        ReservaAlojamiento(1, "Pedro Soto", 1, 30000.0, 1, TipoAlojamiento.Habitacion),
        ReservaAlojamiento(4, "", 4, 50000.0, 2, TipoAlojamiento.Cabana),
        ReservaAlojamiento(-5, "Juan Silva", 2, 40000.0, 2, TipoAlojamiento.Habitacion),
        ReservaAlojamiento(5, "Laura Rojas", 0, 50000.0, 2, TipoAlojamiento.Departamento)
    )

    println("--- REGISTRO DE RESERVAS ---")
    intentosDeRegistro.forEach { reserva ->
        when (val resultado = mantenedor.registrarReserva(reserva)) {
            is ResultadoRegistro.Exito -> println("[ÉXITO] ${resultado.mensaje}")
            is ResultadoRegistro.Error -> println("[ERROR AL REGISTRAR] ${resultado.motivo}")
        }
    }

    println("\n--- LISTADO DE RESERVAS REGISTRADAS ---")
    val listaReservas = mantenedor.obtenerReservas()

    listaReservas.forEach { reserva ->
        mostrarDetalleReserva(reserva)
    }

    val recaudacionTotal = listaReservas.sumOf { it.calcularMontoTotal() }
    println("\n--------------------------------------------------")
    println("RECAUDACIÓN TOTAL DE TODAS LAS RESERVAS: \$$recaudacionTotal")
    println("--------------------------------------------------")

    println("\n--- CONSULTAS DE COLECCIÓN (ORDEN SUPERIOR) ---")

    val reservasAltas = listaReservas.filter { it.calcularMontoTotal() > 150000.0 }
    println("\nReservas con total superior a \$150.000:")
    reservasAltas.forEach { println(" - ${it.nombreCliente} (\$${it.calcularMontoTotal()})") }

    val nombresClientes = listaReservas.map { it.nombreCliente }
    println("\nLista de nombres de clientes registrados:")
    println(nombresClientes)

    println("\n--- DEMOSTRACIÓN DE HERENCIA Y POLIMORFISMO ---")
    val reservaPadre: Reserva = listaReservas.first()
    println("Reserva tratada como tipo 'Reserva' base:")
    println("Descripción ejecutada (dinámica): ${reservaPadre.obtenerDescripcion()}")

    println("\n=== FIN DE LA EJECUCIÓN ===")
}
