package com.remotelabs.hire.dtos.requests;

import com.remotelabs.hire.enums.ActiveOrInactive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCountryStatusDto {

    private Long countryId;
    private ActiveOrInactive status;
}
