import kotlin.properties.Delegates
class Reactor<T>() {
    // Your compute cell's addCallback method must return an object
    // that implements the Subscription interface.
    interface Subscription {
        fun cancel()
        fun callFunction()
    }
    interface Cell<T> {
        var value: T
        fun addSubscriber(cell: Cell<T>)
    }
    inner class InputCell(value: T) : Cell<T>{
        private val subscribedCells : MutableList<ComputeCell> = mutableListOf()
        override var value: T by Delegates.observable(value) {
                _, _, _ -> subscribedCells.forEach {
            it.update()
        }
        }


        override fun addSubscriber(cell: Cell<T>) {
            subscribedCells.add(cell as ComputeCell)

        }
    }
    inner class ComputeCell(vararg input: Cell<T>, val functionAssociatedToCell: (List<T>) -> T) : Cell<T> {
        private var cellsOnWhichDepends = input
        private val subscribedCells : MutableList<ComputeCell> = mutableListOf()
        val callbacksSubscribed = mutableMapOf<Int, Subscription>() //added to implement correctly the addCallback fun
        init {
            cellsOnWhichDepends.forEach { it.addSubscriber(this) }
        }

        override var value : T by Delegates.observable(functionAssociatedToCell(cellsOnWhichDepends.map { it.value })){
                _, _, _ ->
            run {
                subscribedCells.forEach { it.update() }
                callbacksSubscribed.values.forEach { it.callFunction() } //added to implement correctly the addCallback fun
            }
        }

        override fun addSubscriber(cell: Cell<T>) {
            subscribedCells.add(cell as ComputeCell)
        }

        fun update() {
            value = functionAssociatedToCell(cellsOnWhichDepends.map { it.value })
        }

        fun addCallback(function: (T) -> Unit) : Subscription{
            val subscription = object : Subscription {
                override fun cancel() {
                    callbacksSubscribed.remove(this.hashCode())
                }
                override fun callFunction() {
                    function(value)
                }
            }
            callbacksSubscribed[subscription.hashCode()] = subscription
            return callbacksSubscribed[subscription.hashCode()]!!
        }

    }

}
