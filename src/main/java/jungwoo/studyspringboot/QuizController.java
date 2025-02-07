package jungwoo.studyspringboot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {
    @GetMapping("/quiz")
    public ResponseEntity<String> quiz1(@RequestParam("code") int code) { //code 라는 키에 매핑된 int 타입의 변수 code 에 할당된 값에 따라 반환값 결정
        switch (code) {
            case 1:
                return ResponseEntity.created(null).body("Created!");
            case 2:
                return ResponseEntity.badRequest().body("Bad Request!");
            default:
                return ResponseEntity.ok("OK!");
        }
    }

    @PostMapping("/quiz")
    // @RequestBody 가 붙어 있으면, Spring 이 요청 본문을 JSON 데이터로 가정하고 Java 객체로 변환함
    public ResponseEntity<String> quiz2(@RequestBody Code code) { // Code 라는 객체의 값에 따라 반환값 결정
        switch (code.value()) {
            case 1:
                return ResponseEntity.status(403).body("Forbidden!");
            default:
                return ResponseEntity.ok("OK!");
        }
    }
}

record Code(int value) {} // 데이터 전달을 목적으로 하는 객체, 즉 코드의 값을 빠르게 매핑해주기 위해 레코드 선언