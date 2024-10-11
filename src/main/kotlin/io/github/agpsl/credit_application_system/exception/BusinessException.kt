package io.github.agpsl.credit_application_system.exception

import java.lang.RuntimeException

data class BusinessException(override val message: String): RuntimeException(message)
