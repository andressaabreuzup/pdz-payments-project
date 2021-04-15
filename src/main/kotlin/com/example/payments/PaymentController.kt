package com.example.payments

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException
import java.time.LocalDateTime
import java.util.*

/**
 * @author Finzi
 */
@RestController
@RequestMapping("payment")
class PaymentController(val repository: PaymentRepository) {

    @PostMapping
    fun create(@RequestBody payment: Payment) = ResponseEntity.ok(repository.save(payment))

    @GetMapping("{id}")
    fun read(@PathVariable id: String) = ResponseEntity.ok(repository.findById(id))

    @PutMapping("{id}")
    fun update(@PathVariable id: String, @RequestBody payment: Payment): ResponseEntity<Payment> {
        val paymentDBOptional = repository.findById(id)
        val paymentDB = paymentDBOptional
            .orElseThrow{RuntimeException("Payment: $id not found")}
            .copy(status = payment.status, end_processing_date = LocalDateTime.now().toString())
        return  ResponseEntity.ok(paymentDB)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: String) = repository
        .findById(id)
        .ifPresent{ repository.delete(it)}
}