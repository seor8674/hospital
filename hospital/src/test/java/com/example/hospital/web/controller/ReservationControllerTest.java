package com.example.hospital.web.controller;

import com.example.hospital.web.reservation.domain.Reservation;
import com.example.hospital.web.reservation.domain.ReservationRepository;
import com.example.hospital.web.reservation.dto.ReservationRequestDto;
import com.example.hospital.web.user.domain.User;
import com.example.hospital.web.user.domain.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@WithMockUser("Test1")
class ReservationControllerTest {

    @MockBean
    ReservationController reservationController;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReservationRepository reservationRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void init()
    {
        mockMvc= MockMvcBuilders.standaloneSetup(reservationController).build();
    }

    @Test
    public void makeReservationTest() throws Exception {

        String 김영수 = objectMapper.writeValueAsString(new ReservationRequestDto(LocalDateTime.now(), "김영수"));

        mockMvc.perform(post("/api/reservation/makeReservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(김영수))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void updateReservationTest() throws Exception{
        String 김영수 = objectMapper.writeValueAsString(new ReservationRequestDto(LocalDateTime.now(), "김영수"));

        mockMvc.perform(put("/api/reservation/4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(김영수))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void deleteReservationTest() throws Exception{

        mockMvc.perform(delete("/api/reservation/4"))
                .andExpect(status().isOk())
                .andDo(print());
    }


}