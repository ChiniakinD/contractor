package com.chiniakin.listener;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class RabbitTask {

    @JsonProperty("contractor_id")
    private String contractorId;

    @JsonProperty("active_main_borrower")
    private Boolean activeMainBorrower;

    public static RabbitTask of(String contractorId, Boolean activeMainBorrower) {
        return new RabbitTask()
                .setContractorId(contractorId)
                .setActiveMainBorrower(activeMainBorrower);
    }

}
