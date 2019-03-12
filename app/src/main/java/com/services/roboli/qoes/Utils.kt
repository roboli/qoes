package com.services.roboli.qoes

const val MOVISTAR = "MOVISTAR"
const val CLARO = "CLARO"
const val TIGO = "TIGO"
const val UNKNOWN = "UNKNOWN"
const val UNITEL = UNKNOWN

data class Range(val name: String, val start: Int, val end: Int)

val ranges = listOf<com.services.roboli.qoes.Range>(
    Range(TIGO, 3000, 3228),
    Range(CLARO, 3229, 3229),
    Range(TIGO, 3230, 3309),
    Range(MOVISTAR, 3400, 3449),
    Range(TIGO, 4000, 4099),
    Range(CLARO, 4100, 4299),
    Range(MOVISTAR, 4300, 4475),
    Range(TIGO, 4476, 4699),
    Range(CLARO, 4700, 4772),
    Range(TIGO, 4773, 4819),
    Range(UNITEL, 4820, 4821),
    Range(TIGO, 4822, 5009),
    Range(CLARO, 5010, 5019),
    Range(MOVISTAR, 5020, 5029),
    Range(TIGO, 5030, 5069),
    Range(MOVISTAR, 5070, 5109),
    Range(CLARO, 5110, 5139),
    Range(MOVISTAR, 5140, 5149),
    Range(TIGO, 5150, 5199),
    Range(TIGO, 5200, 5209),
    Range(MOVISTAR, 5210, 5299),
    Range(TIGO, 5300, 5309),
    Range(CLARO, 5310, 5311),
    Range(MOVISTAR, 5312, 5313),
    Range(TIGO, 5314, 5389),
    Range(MOVISTAR, 5390, 5409),
    Range(CLARO, 5410, 5499),
    Range(MOVISTAR, 5500, 5509),
    Range(CLARO, 5510, 5517),
    Range(MOVISTAR, 5518, 5519),
    Range(TIGO, 5521, 5529),
    Range(CLARO, 5531, 5539),
    Range(MOVISTAR, 5540, 5542),
    Range(CLARO, 5543, 5544),
    Range(MOVISTAR, 5545, 5549),
    Range(TIGO, 5550, 5553),
    Range(CLARO, 5554, 5579),
    Range(TIGO, 5580, 5581),
    Range(CLARO, 5582, 5599),
    Range(MOVISTAR, 5600, 5608),
    Range(CLARO, 5610, 5639),
    Range(MOVISTAR, 5640, 5689),
    Range(CLARO, 5690, 5699),
    Range(TIGO, 5700, 5709),
    Range(CLARO, 5710, 5718),
    Range(TIGO, 5719, 5789),
    Range(MOVISTAR, 5790, 5799),
    Range(TIGO, 5800, 5809),
    Range(CLARO, 5810, 5818),
    Range(TIGO, 5819, 5819),
    Range(CLARO, 5820, 5879),
    Range(TIGO, 5880, 5909),
    Range(CLARO, 5910, 5914),
    Range(MOVISTAR, 5915, 5917),
    Range(TIGO, 5918, 5919),
    Range(CLARO, 5920, 5989),
    Range(TIGO, 5990, 5999)
)

fun identifyOp(phone: Int): String {
    val result: Range? = ranges.find { it.start <= phone && phone <= it.end }
    return result?.name ?: UNKNOWN
}