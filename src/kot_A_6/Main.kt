/*
Свободная касса. В ресторане быстрого обслуживания есть несколько
касс. Посетители стоят в очереди в конкретную кассу, но могут перейти
в другую очередь при уменьшении или исчезновении там очереди.
 */
package kot_A_6

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() = coroutineScope {
    val semaphore = java.util.concurrent.Semaphore(3, false)
    for (i in 0..5) {
        launch {
            CashReg(semaphore, i).run()
            delay(400)
        }
    }
}