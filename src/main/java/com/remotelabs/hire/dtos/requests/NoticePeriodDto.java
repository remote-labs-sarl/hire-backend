package com.remotelabs.hire.dtos.requests;

import com.remotelabs.hire.enums.NoticePeriodInterval;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticePeriodDto {

    private int amount;
    private NoticePeriodInterval noticePeriodInterval;
}
