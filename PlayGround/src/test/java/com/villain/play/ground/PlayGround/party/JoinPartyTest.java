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
class JoinPartyTest {

  private static Long TEST_PARTY_ID = 0l;
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

//  todo test 시나리오 보강
//  @Test
//  @DisplayName("풀파티 참가 요청 실패")
//  void fullPartyTest() {
//    service.deleteAllReservations(TEST_PARTY_ID);
//    for(Reservation reservation: fullReservation){
//      service.join(reservation, 0l);
//    }
//    Reservation request = new Reservation.Builder().member("오드").user("아령").build();
//    Assertions.assertThrows(IllegalStateException.class, () -> {service.join(request, 0l);});
//  }

//  todo test 시나리오 보강
//  @Test
//  @DisplayName("이미 선점한 멤버로 파티 가입 요청 실패")
//  void noSeatTest(){
//    service.deleteAllReservations(TEST_PARTY_ID);
//    long recruitId = 0L;
//    Party party = new Party("sound cloud", "live and fall", 0L, recruitId, 6, 6);
//    Reservation request1 = new Reservation.Builder().member("오드").user("아령").build();
//    Reservation request2 = new Reservation.Builder().member("오드").user("아령").build();
//    service.join(request1, 0l);
//    Assertions.assertThrows(IllegalArgumentException.class, () -> {service.join(request2, 0l);});
//  }
}