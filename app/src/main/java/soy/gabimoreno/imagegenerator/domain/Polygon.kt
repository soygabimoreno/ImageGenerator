package soy.gabimoreno.imagegenerator.domain

enum class Polygon(
    val letter: String,
    val nSides: Int,
    val hsb: Int
) {
    A("A", 3, 0),
    B("B", 4, 14),
    C("C", 5, 28),
    D("D", 6, 42),
    E("E", 7, 55),
    F("F", 8, 69),
    G("G", 9, 83),
    H("H", 10, 98),
    I("I", 11, 111),
    J("J", 12, 125),
    K("K", 13, 138),
    L("L", 14, 152),
    M("M", 15, 166),
    N("N", 16, 180),
    O("O", 17, 194),
    P("P", 18, 208),
    Q("Q", 19, 222),
    R("R", 20, 235),
    S("S", 21, 249),
    T("T", 22, 263),
    U("U", 23, 277),
    V("V", 24, 291),
    W("W", 25, 305),
    X("X", 26, 318),
    Y("Y", 27, 332),
    Z("Z", 28, 346);

    override fun toString(): String {
        return "letter: $letter, nSides: $nSides, hsb: $hsb"
    }
}
