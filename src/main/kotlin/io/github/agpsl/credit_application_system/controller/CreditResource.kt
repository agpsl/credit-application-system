package io.github.agpsl.credit_application_system.controller

import io.github.agpsl.credit_application_system.dto.CreditDto
import io.github.agpsl.credit_application_system.dto.CreditView
import io.github.agpsl.credit_application_system.dto.CreditViewList
import io.github.agpsl.credit_application_system.service.impl.CreditService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credit")
class CreditResource(
    private val creditService: CreditService
) {
    @PostMapping
    fun save(@RequestBody creditDto: CreditDto): ResponseEntity<String> {
        val credit = creditService.save(creditDto.toEntity())
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("Credit ${credit.creditCode} for Customer ${credit.customer?.firstName} saved")
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): ResponseEntity<List<CreditViewList>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                creditService.findAllByCustomerId(customerId)
                    .stream()
                    .map { credit -> CreditViewList(credit) }
                    .collect(Collectors.toList())
            )
    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(@RequestParam(value = "customerId") customerId: Long,
                         @PathVariable creditCode: UUID): ResponseEntity<CreditView> {
        val credit = creditService.findByCreditCode(customerId, creditCode)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(CreditView(credit))
    }
}