import kotlin.random.Random

class ArmaDeFuego(private val nombre: String, private var municion: Int, private val municionARestar: Int, private val tipoDeMunicion: String) {
    companion object {
        var cantidadMunicionExtra: Int = Random.nextInt(5, 16)
    }

    var danio: Int = 0
        private set

    var radio: String = ""
        private set

    init {
        when (nombre) {
            "Pistola" -> {
                this.danio = Random.nextInt(1, 6)
                this.radio = if (Random.nextBoolean()) "Reducido" else "Corto"
            }
            "Rifle" -> {
                this.danio = Random.nextInt(5, 11)
                this.radio = if (Random.nextBoolean()) "Corto" else "Intermedio"
            }
            "Bazooka" -> {
                this.danio = Random.nextInt(10, 31)
                this.radio = when (Random.nextInt(3)) {
                    0 -> "Intermedio"
                    1 -> "Amplio"
                    else -> "Enorme"
                }
            }
        }
    }

    private fun dispara() {
        if (municion >= municionARestar) {
            municion -= municionARestar
            println("$nombre disparada. Munición restante: $municion")
        } else {
            recarga()
            if (municion >= municionARestar) {
                municion -= municionARestar
                println("$nombre disparada. Munición restante: $municion")
            } else {
                println("No hay suficiente munición para disparar.")
            }
        }
    }

    private fun recarga() {
        if (cantidadMunicionExtra >= municionARestar * 2) {
            municion += municionARestar * 2
            cantidadMunicionExtra -= municionARestar * 2
            println("$nombre recargada. Munición actual: $municion")
        } else if (cantidadMunicionExtra >= municionARestar) {
            municion += municionARestar
            cantidadMunicionExtra -= municionARestar
            println("$nombre recargada. Munición actual: $municion")
        } else {
            println("No hay suficiente munición extra para recargar.")
        }
    }

    private fun mostrarInfo() {
        println("Nombre: $nombre, Munición: $municion, Tipo de Munición: $tipoDeMunicion, Daño: $danio, Radio: $radio")
    }

    fun realizarDisparos(disparos: List<Int>) {
        for ((index, disparo) in disparos.withIndex()) {
            println("\nDisparo ${index + 1} - $nombre (${disparo} disparos):")
            for (i in 1..disparo) {
                dispara()
                mostrarInfo()
            }
        }
    }
}