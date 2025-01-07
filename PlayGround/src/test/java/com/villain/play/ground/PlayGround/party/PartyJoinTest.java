package com.villain.play.ground.PlayGround.party;


import com.villain.play.ground.PlayGround.reservation.Reservation;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PartyJoinTest {

  private static int TEST_PARTY_ID = 0;
  @InjectMocks
  PartyService service;

  List<Reservation> fullReservation = new ArrayList<>();

  @BeforeEach
  void init() {
    String[] members = {"건일", "정수", "가온", "오드", "준한", "주연"};
    for(String member : members){
      fullReservation.add(new Reservation.Builder().member(member).user("아령").build());
    }
  }

  @Test
  @DisplayName("풀파티 참가 요청 실패")
  void fullPartyTest() {
    service.deleteAllReservations(TEST_PARTY_ID);
    for(Reservation reservation: fullReservation){
      service.join(reservation, 0);
    }
    Reservation request = new Reservation.Builder().member("오드").user("아령").build();
    Assertions.assertThrows(IllegalStateException.class, () -> {service.join(request, 0);});
  }

  @Test
  @DisplayName("이미 선점한 멤버로 파티 가입 요청 실패")
  void noSeatTest(){
    service.deleteAllReservations(TEST_PARTY_ID);
    int recruitId = 0;
    Party party = new Party(0, "sound cloud", "live and fall", "villain", 0, 6, 6);
    Reservation request1 = new Reservation.Builder().member("오드").user("아령").build();
    Reservation request2 = new Reservation.Builder().member("오드").user("아령").build();
    service.join(request1, 0);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {service.join(request2, 0);});
  }

//  @Test
//  @DisplayName("선점 멤버 선택 안하고 파티 가입")
//  void failTest(){
//    Reservation request = new Reservation.Builder().user("아령").build();
//    Assertions.assertThrows(IllegalArgumentException.class, () -> {service.join(request);});
//
//    // 1. 입력값 검증(Service 책임: 비어있는 입력값이 있는가) -> IllegalArgumentException
//    // 2. (빈 입력값 존재) - error response entity 처리? 에러 메시지: 재시도 요청하기?
//  }

//  @Test
//  @DisplayName("선입금 여부 선택 안하고 파티 가입")
//  void failTest2(){
//    Reservation request = new Reservation.Builder().member("오드").user("아령").build();
//    Assertions.assertThrows(IllegalArgumentException.class, () -> {service.join(request);});
//
//    // 1. 입력값 검증(Service 책임: 비어있는 입력값이 있는가) -> IllegalArgumentException
//    // 2. (빈 입력값 존재) - error response entity 처리? 에러 메시지: 재시도 요청하기?
//  }

//  @Test
//  @DisplayName("존재하지 않는 파티 조회 요청")
//  void party_test() {
//    int requestId = 99999;
//
//    when(partyRepository.getPartyById(requestId)).thenReturn(null);
//
//    Assertions.assertThrows(IllegalArgumentException.class, () -> {
//      service.detail(requestId);
//    });
//  }


}