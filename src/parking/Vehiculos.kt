package parking

// Interfaz con acción del vehículo
interface AccionVehiculo {
    fun conducir()
}

// Auto: hereda Vehiculo y implementa AccionVehiculo
class Auto(
    override val licensePlate: String,
    override var make: String,
    override var model: String,
    override val color: String = "gris"
) : Vehiculo(licensePlate, make, model), AccionVehiculo {

    // ejemplo de constructor secundario
    constructor(licensePlate: String) : this(licensePlate, "MarcaX", "ModeloX", "gris")

    override val maxSpeed = 200

    override fun conducir() {
        println("El auto $licensePlate conduce por la carretera a $speed km/h")
    }
}

// Motocicleta: otra subclase
class Motocicleta(
    override val licensePlate: String,
    override var make: String,
    override var model: String,
    override val color: String = "rojo"
) : Vehiculo(licensePlate, make, model), AccionVehiculo {

    override val maxSpeed = 160

    override fun conducir() {
        println("La moto $licensePlate esquiva el tráfico a $speed km/h")
    }
}
