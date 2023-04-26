package com.payman.api.dto.client;

import com.payman.api.dto.client.Contract;
import com.payman.api.dto.provider.PaymanOverDraft;
import com.payman.api.dto.provider.response.PaymanTraceCreationResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OverDraft {

    private String status;

    public OverDraft(PaymanOverDraft paymanOverDraft) {
        this.status = paymanOverDraft.getStatus();
    }
}
