package com.payman.api.dto.provider;

import com.payman.api.dto.client.OverDraft;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymanOverDraft {

    private String status;

    public PaymanOverDraft(OverDraft overDraft) {
        this.status = overDraft.getStatus();
    }
}
