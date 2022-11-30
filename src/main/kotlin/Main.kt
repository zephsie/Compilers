import java.io.File

fun main() {
    val valid = setOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
        's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9')

    val transitions = mapOf(
        1 to mapOf(valid to 2),
        2 to mapOf(setOf('.') to 1, setOf('@') to 3, valid to 2),
        3 to mapOf(valid to 4),
        4 to mapOf(setOf('.', '-') to 3, valid to 4),
    )

    val validator = Validator(transitions, 4)

    val list = listOf(
        "",
        "a",
        "a@",
        "a@b",
        "a@b.",
        "a@b.c",
        "a@b..c",
        ".a.b.c",
        "a.b.c.",
        "a.b.c@",
        "@gma.f",
        "a.b.c@.",
        "a.b.c@d",
        "a.b.c@d-d.e",
        "a.b.c@dd.e-r-e-e-e",
        "a..c.c@e.com",
        "..a@email.com",
        "a@.com",
        "a@e..com",
        "a@com.com",
        ".a@com.com",
        "a@com.com.",
        "a@com-com.com",
        "a@com--com.com",
        "a@comcom.com-",
        "a@comcom.c-o-m",
        "ema.i.l@e76.m.a457954nv957655979.i.l.co0000ff-m86.c-o-68m.c-o-m87978.c-o890m.c-o-m546657",
        "ema.i.l@e76.m.a457954nv957655979.i.l.co0000ff-m86.c-o-68m.c-o-m87978.c-o890m.c-o-m546657.",
        "ema.i.l@e.m.a457954nv95979.i..l.co0000ff-m.c-o-m.c-o-m.c-om.c-o-m",
        "ema.i.l@@e.m.a457954nv95979.i.l.co0000ff-m.c-o-m.c-o-m.c-om.c-o-m",
        ".ema.i.l@e.m.a457954nv95@979.i.l.co0000ff-m.c-o-m.c-o-m.c-om.c-o-m",
        "ema.i.l@e.m.a457954nv95979.i..l.co0000ff-m.c-o-m.c-o-m.c-om.c-o-m",
        "ema.i.l@e.m.a457954nv95979.i.l.co0000ff-m.c.-o-m.c-o-m.c-om.c-o-m",
        "ema.i.l@e.m.a457954nv95979.i.l.co0000ff-m.c--o-m.c-o-m.c-om.c-o-m",
        "ema.i.l@e.m.a457954nv95979.i.l.co0000ff-m.-c-o-m.c-o-m.c-om.c-o-m",
        "ema.i.l@e.m.a457954nv95979.i.l.co0000ff-m.c-o-m.c-o-m-.c-om.c-o-m"
    )

    val validEmails = File("valid.txt")
    val invalidEmails = File("invalid.txt")

    validEmails.writeText("")
    invalidEmails.writeText("")

    list.forEach {
        if (validator.isValid(it)) {
            validEmails.appendText(it + System.lineSeparator())
        } else {
            invalidEmails.appendText(it + System.lineSeparator())
        }
    }

    val emails = File("emails.txt")

    validator.getValidFromText("...^&*(ema.i.5546546.56.l@e.m.a457954nv95979&%*^i.l.co0000ff-m.c-o-m.c-o-m-.c-om.c-o-m").forEach {
        emails.appendText(it + System.lineSeparator())
    }

    println("Done")
}