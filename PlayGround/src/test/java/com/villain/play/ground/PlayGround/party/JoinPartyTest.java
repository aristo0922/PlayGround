package com.villain.play.ground.PlayGround.party;


import com.villain.play.ground.PlayGround.reservation.Reservation;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JoinPartyTest {

  private static Long TEST_PARTY_ID = 0l;
  @InjectMocks
  private PartyService service;

  @Mock
  private PartyRepository partyRepository;
  List<Reservation> reservations = new ArrayList<>();

  @BeforeEach
  void init() {
    String[] members = {"건일", "정수", "가온", "오드", "준한", "주연"};
    long partyId = 1L;
    String user = "musk";
    for(String member : members){
      reservations.add(new Reservation(partyId, member, "musk"));
    }
  }



  @DisplayName("기존 파티 참가 요청하기")
  @Test
  void joinParty(){
    for(Reservation reservation: reservations)
      service.join(reservation);
  }
}