package live.senya.garrely.entity.mapper

interface Mapper<IN, OUT> {
    fun map(element: IN): OUT
}

interface Mapper2<IN1, IN2, OUT> {
    fun map(item1: IN1, item2: IN2): OUT
}