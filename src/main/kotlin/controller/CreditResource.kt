package controller

import dto.CreditDto
import io.github.agpsl.credit_application_system.service.impl.CreditService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/credit")
class CreditResource(
    private val creditService: CreditService
) {
    @PostMapping
    fun save(@RequestBody creditDto: CreditDto): String {
        val credit = creditService.save(creditDto.toEntity())
        return "Credit ${credit.creditCode} for Customer ${credit.customer?.firstName} saved"
    }

}