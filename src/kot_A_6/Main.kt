/*
Свободная касса. В ресторане быстрого обслуживания есть несколько
касс. Посетители стоят в очереди в конкретную кассу, но могут перейти
в другую очередь при уменьшении или исчезновении там очереди.
 */
package kot_A_6

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore

suspend fun main() = coroutineScope {
    val semaphore = Semaphore(1)
    for (i in 0..10) {
        launch {
            CashReg(semaphore, i).run()
            delay(400)
        }
    }
}