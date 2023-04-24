package com.study.server1.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    MockMvc mvc;

    private static final String ERROR_ID = "jhl";
    private static final String USER_ID = "abc";

    @Test
    public void 한번_호출() throws Exception {
        mvc.perform(get("/server-1/user/" + USER_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(USER_ID)))
                .andExpect(jsonPath("$.point", is(notNullValue())));
    }

    @Test
    public void 에러_한번_호출() throws Exception {
        mvc.perform(get("/server-1/user/" + ERROR_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(ERROR_ID)))
                .andExpect(jsonPath("$.point", is(nullValue())));
    }

    @Test
    public void NO_ERROR() throws Exception {
        for (int i = 0; i < 20; i++) {
            mvc.perform(get("/server-1/user/" + USER_ID))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(USER_ID)))
                    .andExpect(jsonPath("$.point", is(notNullValue())));
        }
    }

    @Test
    public void 서킷오픈() throws Exception {
        // 성공
        mvc.perform(get("/server-1/user/" + USER_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(USER_ID)))
                .andExpect(jsonPath("$.point", is(notNullValue())));


        for (int i = 0; i < 15; i++) {
            mvc.perform(get("/server-1/user/" + ERROR_ID))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(ERROR_ID)))
                    .andExpect(jsonPath("$.point", is(nullValue())));
        }

        // 실패
        mvc.perform(get("/server-1/user/" + USER_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(USER_ID)))
                .andExpect(jsonPath("$.point", is(nullValue())));


        log.info("--------- sleep ---------");
        Thread.sleep(10000L);   // 10초


        // 성공
        mvc.perform(get("/server-1/user/" + USER_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(USER_ID)))
                .andExpect(jsonPath("$.point", is(notNullValue())));
    }

}