package parking

fun demoParkingScenario() {
    // parking con capacidad pequeña para forzar límites
    val parking = ParkingLot(capacity = 3)

    // crear vehículos
    val auto1 = Auto("ABC-123", "Toyota", "Corolla")
    val moto1 = Motocicleta("MOTO-1", "Yamaha", "YZF")

    // probar setter de speed (clamp): intentamos asignar más que el max
    auto1.speed = 220   // se debe ajustar a maxSpeed = 200
    moto1.speed = 80

    // aparcar
    parking.parkVehicle(auto1)   // spots available -> 2
    parking.parkVehicle(moto1)   // spots available -> 1

    parking.showVehicles()

    // acciones (interfaz)
    auto1.conducir()
    moto1.conducir()

    // salir
    parking.leaveVehicle("ABC-123")
    parking.showVehicles()

    // intentar rellenar el parking
    val auto2 = Auto("DEF-456", "Honda", "Civic")
    val auto3 = Auto("GHI-789", "Mazda", "3")
    val auto4 = Auto("JKL-000", "Ford", "Fiesta")
    parking.parkVehicle(auto2) // ok
    parking.parkVehicle(auto3) // ok (parking ahora lleno si capacity=3)
    parking.parkVehicle(auto4) // debe mostrar "Parking lleno..."
}

fun main() {
    println("=== Simulación Parking ===")
    demoParkingScenario()
}
