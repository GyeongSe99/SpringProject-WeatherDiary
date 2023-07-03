package com.zerobase.weatherdiary.service;


import com.zerobase.weatherdiary.domain.Diary;
import com.zerobase.weatherdiary.error.DiaryException;
import com.zerobase.weatherdiary.repository.DiaryRepository;
import com.zerobase.weatherdiary.type.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class DiaryServiceTest {

    @Autowired
    @Mock
    DiaryService diaryService;

    @Autowired
    @Mock
    DiaryRepository diaryRepository;

    @Test
    @DisplayName("createDiary Success")
    void createDiarySuccessTest() {
        String strDate = "2021-04-06";
        LocalDate date = LocalDate.parse(strDate, DateTimeFormatter.ISO_DATE);
        String str = "Happy~~";

        diaryService.createDiary(date, str);
    }
    
    @Test
    void readDiaryTest_Success() {
        // given
        String strDate = "2021-04-06";
        LocalDate date = LocalDate.parse(strDate, DateTimeFormatter.ISO_DATE);
        String str = "Happy~~";

        diaryService.createDiary(date, str);
        
        // when
        List<Diary> diaries = diaryService.readDiary(date);

        // then
        assertEquals(diaries.get(0).getDate(), date);
        assertEquals(diaries.get(0).getText(), str);
    }

    @Test
    @DisplayName("너무 먼 미래의 일기")
    void readDiaryTooFarFuture_failed() {
        // given
        String strDate = "2250-04-06";
        LocalDate date = LocalDate.parse(strDate, DateTimeFormatter.ISO_DATE);

        // when
        DiaryException exception = assertThrows(DiaryException.class,
                () -> diaryService.readDiary(date));

        // then
        assertEquals(ErrorCode.TOO_FAR_FUTURE, exception.getErrorCode());
    }

    @Test
    @DisplayName("일기 내용이 없을 때")
    void readDiaryEmptyDiary_failed() {
        // given
        LocalDate currentDate = LocalDate.now();

        // when
        DiaryException exception = assertThrows(DiaryException.class,
                () -> diaryService.readDiary(currentDate));

        // then
        assertEquals(ErrorCode.EMPTY_DIARY_LIST, exception.getErrorCode());
    }



}