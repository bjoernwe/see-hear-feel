package dev.upaya.shf.data.auth

import org.junit.Assert.*

import org.junit.Test

class AuthUtilsKtTest {

    @Test
    fun generateNonce_isRandom() {
        // GIVEN the function
        // WHEN two nonces are generated
        val nonce1 = generateNonce()
        val nonce2 = generateNonce()

        // THEN they are different
        assertNotEquals(nonce1, nonce2)
    }
}
