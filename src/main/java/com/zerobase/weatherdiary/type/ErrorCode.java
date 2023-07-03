package com.zerobase.weatherdiary.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    EMPTY_DIARY_LIST("해당 날짜에 일기가 없습니다."),
    TOO_FAR_FUTURE("너무 먼 미래입니다.");
    private final String description;
}
