package me.hwanseok.hwanseok20210225.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GetController.class)        // WebMvc Test Annotation ( GetController 를 테스트)
@AutoConfigureMockMvc                   // MockMvc 자동 설정 Annotation
public class GetControllerTest {

    @Autowired
    private MockMvc mockMvc;            // AutoConfigureMockMvc 으로 자동 주입된 mockMvc


    @Test                               // JUnit Test 설정 Annotation
    @DisplayName("getRequest 테스트")    // 테스트명 미적용시 메소드 이름
    public void getRequestTest() throws Exception {
        // given
        URI uri = UriComponentsBuilder.newInstance()
                .path("/api/getMethod")
                .build()
                .toUri();

        // when
        mockMvc.perform(get(uri))

                // then
                .andExpect(status().isOk())
                .andExpect(content().string("Hi getMethod"))
                .andDo(print());
    }

//    @Test                                 // JUnit Test 설정 Annotation
//    @DisplayName("getParameter 테스트")    // 테스트명 미적용시 메소드 이름
//    public void getParameterTest() throws Exception {
//
//        LinkedMultiValueMap<String, String> queryParameter = new LinkedMultiValueMap<String, String>();
//        queryParameter.put("id", Arrays.asList("id"));
//        queryParameter.put("password", Arrays.asList("password"));
//
//        // given
//        URI uri = UriComponentsBuilder.newInstance()
//                .path("/api/getParameter")
//                .queryParams(queryParameter)
//                .build()
//                .toUri();
//
//        // when
//        mockMvc.perform(get(uri))
//
//                // then
//                .andExpect(status().isOk())
//                .andExpect(content().string("idpassword"))
//                .andDo(print());
//    }

    @Test   // TODO 작성해 보세요
    public void getMultiParameterTest() {

    }

}
