// ==========================================
// 12. Programa Principal
// ==========================================
fun main() {
    println("=== MANTENEDOR DE RESERVAS DE ALOJAMIENTO ===\n")

    // 1. Simular consulta inicial de disponibilidad (Operación asíncrona)
    val disponible = consultarDisponibilidad()
    if (disponible) {
        println("Disponibilidad confirmada. Iniciando el sistema...\n")
    }

    val mantenedor = MantenedorReservas()

    // Creación de datos de prueba (Válidos e Inválidos)
    val intentosDeRegistro = listOf(
        // 2. Tres reservas válidas
        ReservaAlojamiento(1, "Ana Pérez", 3, 45000.0, 2, TipoAlojamiento.Habitacion),
        ReservaAlojamiento(2, "Carlos Gómez", 5, 60000.0, 4, TipoAlojamiento.Cabana),
        ReservaAlojamiento(3, "María Torres", 2, 80000.0, 3, TipoAlojamiento.Departamento),

        // 3 y 10. Casos con errores / no válidos
        ReservaAlojamiento(1, "Pedro Soto", 1, 30000.0, 1, TipoAlojamiento.Habitacion), // ID duplicado
        ReservaAlojamiento(4, "", 4, 50000.0, 2, TipoAlojamiento.Cabana),                 // Nombre vacío
        ReservaAlojamiento(-5, "Juan Silva", 2, 40000.0, 2, TipoAlojamiento.Habitacion),  // ID negativo
        ReservaAlojamiento(5, "Laura Rojas", 0, 50000.0, 2, TipoAlojamiento.Departamento) // Noches en 0
    )

    // 4, 5 y 10. Procesar registros y mostrar resultados exitosos y fallidos
    println("--- REGISTRO DE RESERVAS ---")
    intentosDeRegistro.forEach { reserva ->
        when (val resultado = mantenedor.registrarReserva(reserva)) {
            is ResultadoRegistro.Exito -> println("[ÉXITO] ${resultado.mensaje}")
            is ResultadoRegistro.Error -> println("[ERROR AL REGISTRAR] ${resultado.motivo}")
        }
    }

    // 6, 7 y 8. Obtener, recorrer y visualizar reservas
    println("\n--- LISTADO DE RESERVAS REGISTRADAS ---")
    val listaReservas = mantenedor.obtenerReservas()

    listaReservas.forEach { reserva ->
        mostrarDetalleReserva(reserva)
    }

    // 6. Total acumulado de todas las reservas
    val recaudacionTotal = listaReservas.sumOf { it.calcularMontoTotal() }
    println("\n--------------------------------------------------")
    println("RECAUDACIÓN TOTAL DE TODAS LAS RESERVAS: \$$recaudacionTotal")
    println("--------------------------------------------------")

    // 7. Consulta o transformación sobre la colección mediante funciones de orden superior
    println("\n--- CONSULTAS DE COLECCIÓN (ORDEN SUPERIOR) ---")

    // Consulta A: Filtrar reservas con monto superior a 150.000
    val reservasAltas = listaReservas.filter { it.calcularMontoTotal() > 150000.0 }
    println("\nReservas con total superior a \$150.000:")
    reservasAltas.forEach { println(" - ${it.nombreCliente} (\$${it.calcularMontoTotal()})") }

    // Consulta B: Transformar la colección para obtener solamente los nombres de los clientes (map)
    val nombresClientes = listaReservas.map { it.nombreCliente }
    println("\nLista de nombres de clientes registrados:")
    println(nombresClientes)

    // 9 y 12.11. Demostración de Polimorfismo (Tratar ReservaAlojamiento como Reserva general)
    println("\n--- DEMOSTRACIÓN DE HERENCIA Y POLIMORFISMO ---")
    val reservaPadre: Reserva = listaReservas.first() // Se asigna como la clase base 'Reserva'
    println("Reserva tratada como tipo 'Reserva' base:")
    println("Descripción ejecutada (dinámica): ${reservaPadre.obtenerDescripcion()}")

    println("\n=== FIN DE LA EJECUCIÓN ===")
}
