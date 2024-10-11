package io.github.agpsl.credit_application_system.exception

import java.time.LocalDateTime

data class ExceptionDetail(
    val title: String,
    val timestamp: LocalDateTime,
    val status: Int,
    val details: MutableMap<String, String?>
){
}