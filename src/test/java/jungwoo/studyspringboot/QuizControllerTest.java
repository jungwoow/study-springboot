package jungwoo.studyspringboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class QuizControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper; // 객체를 JSON 으로 변환해주는 역할, 객체 직렬화라고 하기도 함

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @DisplayName("quiz(): GET /quiz?code=1 이면 응답 코드는 201, 응답 본문은 Created! 반환")
    @Test
    public void getQuiz1() throws Exception {
        // given
        final String url = "/quiz";

        // when
        final ResultActions result = mockMvc.perform(get(url).param("code", "1")); // code 키에 1이 매핑된 경우

        // then
        result
                .andExpect(status().isCreated())
                .andExpect(content().string("Created!"));
    }

    @DisplayName("quiz(): GET /quiz?code=2 이면 응답 코드는 400, 응답 본문은 Bad Request! 반환")
    @Test
    public void getQuiz2() throws Exception {
        // given
        final String url = "/quiz";

        //when
        final ResultActions result = mockMvc.perform(get(url).param("code", "2")); // code 키에 2가 매핑된 경우

        // then
        result
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Bad Request!"));
    }

    @DisplayName("quiz(): POST /quiz 에 요청 본문이 {\"value\":1} 이면 응답 코드는 403, 응답 본문은 Forbidden! 반환")
    @Test
    public void postQuiz1() throws Exception {
        // given
        final String url = "/quiz";
        Code code = new Code(1); // 요청에 보낼 Code 객체 생성

        //when
        final ResultActions result = mockMvc.perform(post(url)
                .content(objectMapper.writeValueAsString(code)) // Code 객체를 JSON 으로 변환 (객체 직렬화) 후 요청 본문으로 전송
                .contentType(MediaType.APPLICATION_JSON)); // JSON 타입으로 요청을 보내기 위해 덧붙이는 메서드

        // then
        result
                .andExpect(status().isForbidden())
                .andExpect(content().string("Forbidden!"));
    }

    @DisplayName("quiz(): POST /quiz 에 요청 본문이 {\"value\":13} 이면 응답 코드는 200, 응답 본문은 OK! 반환")
    @Test
    public void postQuiz2() throws Exception {
        // given
        final String url = "/quiz";
        Code code = new Code(13); // 요청에 보낼 Code 객체 생성

        //when
        final ResultActions result = mockMvc.perform(post(url)
                .content(objectMapper.writeValueAsString(code)) // Code 객체를 JSON 으로 변환 (객체 직렬화) 후 요청 본문으로 전송
                .contentType(MediaType.APPLICATION_JSON)); // JSON 타입으로 요청을 보내기 위해 덧붙이는 메서드

        // then
        result
                .andExpect(status().isOk())
                .andExpect(content().string("OK!"));
    }
}
