package Testing

class People {
    var fio: String = ""
    var color: MutableList<String> = mutableListOf()

    constructor(fio: String, color: List<String>) {
        this.fio = fio
        color.forEach {
            this.color.add(it.toLowerCase())
        }
    }

    fun isSimColor(other: People): Boolean {
        if (color.size != other.color.size) return false
        for (c in color) {
            if (c !in other.color) return false
        }
        return true
    }

    override fun toString(): String = "$fio : ${color.joinToString(separator = ", ")}"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as People

        if (fio != other.fio) return false
        if (color != other.color) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fio.hashCode()
        result = 31 * result + color.hashCode()
        return result
    }


}

fun getUnicColor(people: List<String>): List<People> {
    val inpList = mutableListOf<People>()
    val res = mutableListOf<People>()
    for (man in people) {
        val p = man.split(":")
        inpList.add(People(p[0], p[1].split(", ")))
    }
    for (p in inpList) {
        var res1 = true
        for (o in inpList.filter { it != p }) {
            if (p.isSimColor(o)) {
                res1 = false
                break
            }
        }
        if (res1) res.add(p)
    }
    return res
}

class Compl {
    var dest = 0
    var irr = 0

    constructor(d: Int, i: Int) {
        dest = d
        irr = i
    }

    constructor(str: String) {
        val wr = str.split(" ")
        when (wr.size) {
            1 -> if (wr[0].contains('i')) {
                dest = 0
                irr = if (wr[0].replace("i", "").isEmpty()) 1
                else wr[0].replace("i", "").toInt()
            } else {
                dest = wr[0].toInt()
                irr = 0
            }
            2 ->
                if (wr[0] == "-") {
                    dest = 0
                    irr = if (wr[1].replace("i", "").isEmpty()) -1
                    else wr[1].replace("i", "").toInt() * -1
                }
            else -> {
                dest = wr[0].toInt()
                if (wr[1] == "-") {
                    irr = if (wr[2].replace("i", "").isEmpty()) -1
                    else wr[2].replace("i", "").toInt() * -1
                } else {
                    irr = if (wr[2].replace("i", "").isEmpty()) 1
                    else wr[2].replace("i", "").toInt()
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean = other is Compl && dest == other.dest && irr == other.irr

    override fun hashCode(): Int {
        var result = dest
        result = 31 * result + irr
        return result
    }

    override fun toString(): String {
        val res = StringBuilder()
        if (dest != 0) res.append(dest)
        if (irr < 0) {
            res.append(" - ")
            res.append(irr * -1)
        } else {
            res.append(" + ")
            res.append(irr)
        }
        res.append("i")
        return res.toString()
    }

    operator fun times(other: Compl): Compl {
        val res: Compl
        res = if (other.irr == 0 || irr == 0) {
            if (other.irr == 0)
                Compl(dest * other.dest, irr * other.dest)
            else
                Compl(dest * other.dest, dest * other.irr)
        } else {
            Compl(dest * other.dest - irr * other.irr,
                    dest * other.irr + irr * other.dest)
        }
        return res
    }
}

fun mulKompl(str: String): Compl {
    val wr = str.split(";").filter { it.isNotEmpty() }
    var res = Compl(wr[0])
    for (i in 1 until wr.size) {
        res *= Compl(wr[i])
    }
    return res
}
