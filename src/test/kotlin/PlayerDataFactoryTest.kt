package ru.joutak.template.data

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.joutak.template.item.CustomType
import java.time.Instant

class PlayerDataFactoryTest {

    @Test
    fun `creates correct player data`() {
        val factory = PlayerDataFactory()

        val states = mapOf(
            CustomType.ARMOR to true,
            CustomType.HIT to false
        )

        val start = Instant.EPOCH
        val now = Instant.EPOCH.plusSeconds(600)

        val result = factory.create(
            health = 20.0,
            states = states,
            startTime = start,
            now = now
        )

        assertEquals(20.0, result.health)
        assertTrue(result.items.contains("ARMOR"))
        assertEquals(10, result.time)
    }
}