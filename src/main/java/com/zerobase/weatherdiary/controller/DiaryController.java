package com.zerobase.weatherdiary.controller;

import com.zerobase.weatherdiary.domain.Diary;
import com.zerobase.weatherdiary.service.DiaryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @ApiOperation(value = "일기 텍스트와 날씨를 이용해서 DB에 일기 저장합니다.")
    @PostMapping("/create/diary")
    void createDiary(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
                     @ApiParam(value = "날짜 형식 : yyyy-mm-dd", example = "20XX-05-XX") LocalDate date,
                     @RequestBody String text) {
        diaryService.createDiary(date, text);
    }

    @GetMapping("/read/diary")
    @ApiOperation(value = "입력받은 날짜의 첫번째 일기를 읽습니다.")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
                          @ApiParam(value = "날짜 형식 : yyyy-mm-dd", example = "20XX-05-XX")LocalDate date) {
        return diaryService.readDiary(date);
    }

    @GetMapping("/read/diaries")
    @ApiOperation(value = "startDate부터 endDate까지 입력되어있는 일기를 DB에서 가져옵니다.")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
                            @ApiParam(value = "날짜 형식 : yyyy-mm-dd", example = "20XX-05-XX") LocalDate startDate,
                            @RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
                            @ApiParam(value = "날짜 형식 : yyyy-mm-dd", example = "20XX-05-XX") LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @PutMapping("/update/diary")
    @ApiOperation(value = "일기 내용을 수정합니다.")
    void updateDiary(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
                     @ApiParam(value = "날짜 형식 : yyyy-mm-dd", example = "20XX-05-XX")LocalDate date,
                     @RequestBody String text) {
        diaryService.updateDiary(date, text);
    }

    @DeleteMapping("/delete/diary")
    @ApiOperation(value = "일기를 삭제합니다.")
    void deleteDiary(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
                     @ApiParam(value = "날짜 형식 : yyyy-mm-dd", example = "20XX-05-XX")LocalDate date){
        diaryService.deleteDiary(date);
    }
}
