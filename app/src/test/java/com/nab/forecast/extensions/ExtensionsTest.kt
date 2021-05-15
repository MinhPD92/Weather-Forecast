package com.nab.forecast.extensions

import org.junit.Test
import java.util.*

class ExtensionsTest {


    @Test
    fun `verify get time at beginning of day correctly`() {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.MONTH, 1)
            set(Calendar.YEAR, 2021)
            set(Calendar.DAY_OF_MONTH, 10)
            set(Calendar.HOUR, 10)
            set(Calendar.MINUTE, 10)
            set(Calendar.SECOND, 10)
        }

        val expectCalendar: Calendar = calendar.clone().run {
            (this as Calendar).also {
                set(Calendar.HOUR, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
        }

        val actualResult = calendar.getTimeAtBeginningOfDay()

        val expectResult = expectCalendar.timeInMillis // Feb 10th, 2021 00:00:00 UTC+7

        assert(expectResult == actualResult)
    }
}