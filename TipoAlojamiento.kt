
sealed class TipoAlojamiento(val descripcion: String) {
    object Habitacion : TipoAlojamiento("Habitación")
    object Cabana : TipoAlojamiento("Cabaña")
    object Departamento : TipoAlojamiento("Departamento")
}
