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
