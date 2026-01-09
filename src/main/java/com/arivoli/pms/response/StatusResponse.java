package com.arivoli.pms.response;

import java.time.LocalDateTime;

public record StatusResponse (String applicationName,
                              String status,
                              LocalDateTime timestamp) {
}
