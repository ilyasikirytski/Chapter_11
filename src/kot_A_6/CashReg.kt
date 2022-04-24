package kot_A_6

import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Semaphore

//class CashReg(var semaphore: kotlinx.coroutines.sync.Semaphore, var customerIndex: Int) {
//    suspend fun run() {
//        println("покупатель $customerIndex встал в очередь.")
//        semaphore.withPermit {
//            println("покупатель $customerIndex выбирает товары")
//            delay(3000)
//            println("покупатель $customerIndex сделал покупку и ушёл")
//        }
//    }
//}
class CashReg(private val semaphore: Semaphore, private val customerIndex: Int) {
    suspend fun run() {
        println("покупатель $customerIndex встал в очередь.")
            semaphore.tryAcquire(1000, {
                println("покупатель $customerIndex выбирает товары")
                delay(1000)
                semaphore.release()
                println("покупатель $customerIndex сделал покупку и ушёл")
            } , {
                println("покупатель $customerIndex не дождался обслуживания и перешёл в другую очередь")
                delay(1000)
                run()
            })
        }
    }


suspend fun Semaphore.tryAcquire(
    timeout: Long,
    onAcquire: suspend () -> Unit,
    onError: suspend () -> Unit
) {
    return if (this.tryAcquire()) {
        onAcquire.invoke()
    } else {
        delay(timeout)
        if (this.tryAcquire()) {
            onAcquire.invoke()
        } else {
            onError.invoke()
        }
    }
}