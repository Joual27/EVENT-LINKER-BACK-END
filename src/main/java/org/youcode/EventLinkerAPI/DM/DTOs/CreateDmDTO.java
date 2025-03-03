package org.youcode.EventLinkerAPI.DM.DTOs;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateDmDTO(@NotNull List<Long> userIds) {
}
