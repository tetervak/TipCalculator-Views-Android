package ca.tetervak.tipcalculator.model

class TipCalculator {

    private var costOfService: Double = 0.0
    private var serviceQuality: ServiceQuality = ServiceQuality.OK
    private var roundUpTip: Boolean = true

    fun calculate (
        costOfService: Double,
        serviceQuality: ServiceQuality,
        roundUpTip: Boolean
    ): TipData {
        this.costOfService = costOfService
        this.serviceQuality = serviceQuality
        this.roundUpTip = roundUpTip
        return tipData
    }

    private val tipPercentage: Double
        get() = when (serviceQuality) {
            ServiceQuality.OK -> 0.15
            ServiceQuality.GOOD -> 0.18
            ServiceQuality.AMAZING -> 0.20
        }

    private val tipAmount: Double
        get() {
            var tip = tipPercentage * costOfService
            if (roundUpTip) tip = kotlin.math.ceil(tip)
            return tip
        }

    private val tipData: TipData
        get() = TipData(
            costOfService = costOfService,
            serviceQuality = serviceQuality,
            roundUpTip = roundUpTip,
            tipAmount = tipAmount,
            billTotal = costOfService + tipAmount
        )
}