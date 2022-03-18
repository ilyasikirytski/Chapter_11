package kot_A_6

import java.util.*
import java.util.concurrent.Semaphore
import java.util.concurrent.TimeUnit

class CashReg(var semaphore: Semaphore, var customerIndex: Int) {
    fun run() {
        println("покупатель $customerIndex встал в очередь.")
        try {
            if (semaphore.tryAcquire(1, 2000, TimeUnit.MILLISECONDS)) {
                semaphore.acquire()
                println("покупатель $customerIndex выбирает товары")
                Thread.sleep(Random().nextInt(10000).toLong())
                semaphore.release(2)
                println("покупатель $customerIndex сделал покупку и ушёл")
            } else {
                println("покупатель $customerIndex не дождался обслуживания и перешёл в другую очередь")
                Thread.sleep(Random().nextInt(20000).toLong())
                run()
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}