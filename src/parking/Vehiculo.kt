package parking

// Clase abstracta: obliga a declarar 'color' en las subclases
abstract class Vehiculo(
    open val licensePlate: String,
    open var make: String,
    open var model: String
) {
    abstract val color: String

    // Propiedad con setter personalizado (clamp) y getter por defecto
    open var speed: Int = 0
        set(value) {
            field = when {
                value < 0 -> 0
                value > maxSpeed -> maxSpeed
                else -> value
            }
        }

    // velocidad máxima que pueden redefinir las subclases
    open val maxSpeed: Int = 180

    // propiedad calculada (getter) que depende de 'speed'
    val isMoving: Boolean
        get() = speed > 0

    init {
        println("Vehículo $licensePlate creado: $make $model")
    }

    fun imprimirEstado() {
        println("[$licensePlate] $make $model - color: $color - velocidad: $speed km/h - en movimiento: $isMoving")
    }
}
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