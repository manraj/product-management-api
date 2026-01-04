package com.arivoli.pms.model;

import java.time.LocalDateTime;

public record StatusResponse (String applicationName,
                              String status,
                              LocalDateTime timestamp) {
}
