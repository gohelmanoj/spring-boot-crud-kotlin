package com.sample.mydemo.person.exceptions

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.time.LocalDateTime

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class ErrorDetails(var timeStamp: LocalDateTime, var message: String, var path: String, var errorCode: String) {

}
