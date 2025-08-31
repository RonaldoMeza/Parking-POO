package parking

// Data class para representar un ticket simple
data class Ticket(val ticketId: String, val licensePlate: String, val entryTime: Long)

class ParkingLot(var capacity: Int = 50) {
    // setter con validación: nunca menor que 0 ni mayor que capacity
    var occupiedSpots: Int = 0
        set(value) {
            field = when {
                value < 0 -> 0
                value > capacity -> capacity
                else -> value
            }
        }

    // getter calculado: capacidad disponible
    val availableSpots: Int
        get() = capacity - occupiedSpots

    val vehicles = mutableListOf<Vehiculo>()
    val tickets = mutableListOf<Ticket>()

    init {
        println("Se creó un Parking con capacidad $capacity")
    }

    fun parkVehicle(vehicle: Vehiculo): Ticket? {
        return if (availableSpots > 0) {
            vehicles.add(vehicle)
            occupiedSpots++                      // usa el setter validado
            val ticket = Ticket("T${System.currentTimeMillis()}", vehicle.licensePlate, System.currentTimeMillis())
            tickets.add(ticket)
            println("Vehículo ${vehicle.licensePlate} estacionado. Spots disponibles: $availableSpots")
            ticket
        } else {
            println("Parking lleno. No se puede estacionar ${vehicle.licensePlate}")
            null
        }
    }

    fun leaveVehicle(licensePlate: String) {
        val v = vehicles.find { it.licensePlate == licensePlate }
        if (v != null) {
            vehicles.remove(v)
            // actualizar occupiedSpots usando setter para que quede en rango
            occupiedSpots = (occupiedSpots - 1).coerceAtLeast(0)
            println("Vehículo $licensePlate salió. Spots disponibles: $availableSpots")
            tickets.removeAll { it.licensePlate == licensePlate }
        } else {
            println("No se encontró vehículo con placa $licensePlate")
        }
    }

    fun showVehicles() {
        println("Vehículos en parking (${vehicles.size}):")
        vehicles.forEach { it.imprimirEstado() }
    }
}
