package soy.gabimoreno.imagegenerator.domain

fun Float.mapNumber(inMin: Float, inMax: Float, outMin: Float, outMax: Float): Float {
    return ((this - inMin) * (outMax - outMin) / (inMax - inMin) + outMin)
}
