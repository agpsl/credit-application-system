package controller

import dto.CreditDto
import dto.CreditView
import dto.CreditViewList
import io.github.agpsl.credit_application_system.service.impl.CreditService
import org.springframework.web.bind.annotation.GetMapping
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
    fun save(@RequestBody creditDto: CreditDto): String {
        val credit = creditService.save(creditDto.toEntity())
        return "Credit ${credit.creditCode} for Customer ${credit.customer?.firstName} saved"
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): List<CreditViewList> {
        return creditService.findAllByCustomerId(customerId)
            .stream()
            .map { credit -> CreditViewList(credit) }
            .collect(Collectors.toList())
    }

    @GetMapping
    fun findByCreditCode(@RequestParam(value = "customerId") customerId: Long,
                         @RequestParam(value = "creditCode") creditCode: UUID): CreditView {
        val credit = creditService.findByCreditCode(customerId, creditCode)
        return CreditView(credit)
    }
}