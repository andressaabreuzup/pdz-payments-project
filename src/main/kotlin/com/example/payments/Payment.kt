package com.example.payments

import java.time.LocalDateTime

/**
 * @author Finzi
 */

data class Payment(
    val id: String?,
    val id_customer: String,
    val id_subscription: String,
    val value: Float,
    val start_processing_date: String? = LocalDateTime.now().toString(),
    val end_processing_date: String? = null,
    val status: Int? = 0
    ) {
}