import kotlin.random.Random

fun main() {
    val pistola = ArmaDeFuego("Pistola", 10, 1, "9mm")
    val rifle = ArmaDeFuego("Rifle", 20, 2, "7.62mm")
    val bazooka = ArmaDeFuego("Bazooka", 5, 3, "RPG")

    println("\nMunición extra = ${ArmaDeFuego.cantidadMunicionExtra} para todas las armas de fuego.\n")

    val disparosPistola = (1..3).map { (1..3).random() }
    val disparosRifle = (1..3).map { (1..3).random() }
    val disparosBazooka = (1..3).map { (1..3).random() }

    val disparos = (1..9).map { Random.nextInt(1, 4) }
    val objetosDisparadores = listOf(
        Casa(),
        Coche(),
        Bocadillo()
    )

    pistola.realizarDisparos(disparosPistola)
    rifle.realizarDisparos(disparosRifle)
    bazooka.realizarDisparos(disparosBazooka)

    for ((index, disparo) in disparos.withIndex()) {
        val objetoDisparador = objetosDisparadores.random()
        println("\nDisparo ${index + 1} - ${objetoDisparador::class.simpleName}:")
        for (i in 1..disparo) {
            objetoDisparador.disparar()
        }
    }
}