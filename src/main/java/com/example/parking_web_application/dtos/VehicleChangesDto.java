package com.example.parking_web_application.dtos;

import java.math.BigDecimal;

public class VehicleChangesDto {
    private BigDecimal changes;

    private boolean canProceed;

    private String message;

    public VehicleChangesDto(){}

    public VehicleChangesDto(BigDecimal changes, boolean canProceed, String message) {
        this.changes = changes;
        this.canProceed = canProceed;
        this.message = message;
    }

    public BigDecimal getChanges() {
        return changes;
    }

    public void setChanges(BigDecimal changes) {
        this.changes = changes;
    }

    public boolean isCanProceed() {
        return canProceed;
    }

    public void setCanProceed(boolean canProceed) {
        this.canProceed = canProceed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
