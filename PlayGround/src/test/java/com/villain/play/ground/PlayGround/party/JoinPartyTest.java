package com.villain.play.ground.PlayGround.party;


import static org.mockito.Mockito.when;

import com.villain.play.ground.PlayGround.reservation.Reservation;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JoinPartyTest {

  @InjectMocks
  private PartyService service;

  @Mock
  private PartyRepository partyRepository;
  List<Reservation> reservations = new ArrayList<>();

  private Party party;
  Long targetId = 2L;
  Reservation leaderReservation;

  @BeforeEach
  void init() {
    String[] members = {"건일", "정수", "가온", "오드", "준한", "주연"};
    long partyId = targetId;
    String user = "musk";
    String leader = "ant";
    for(String member : members){
      reservations.add(new Reservation(partyId, member, user));
    }

    party = new Party(targetId, "Sound Wave", "Live and Fall", 1L, 1L, 6, new ArrayList<>());
    leaderReservation = new Reservation(targetId, members[0], leader);
    party.addReservation(leaderReservation);
  }

  @DisplayName("기존에 존재하는 ant 파티 참가하기")
  @Test
  void join(){
    when(partyRepository.findPartyById(targetId)).thenReturn(party);
    Reservation newReservation = reservations.get(2);

    service.join(newReservation);

    Party result = service.getParty(targetId);
    List<Reservation> reservationList = result.getReservationList();

    Assertions.assertEquals(leaderReservation, reservationList.get(0));
    Assertions.assertEquals(newReservation, reservationList.get(1));
  }

  @DisplayName("이미 선점된 멤버로 파티 참가 불가")
  @Test
  void fail_join(){
    when(partyRepository.findPartyById(targetId)).thenReturn(party);
    Reservation newReservation = reservations.get(2);

    service.join(newReservation);
    Assertions.assertThrows(IllegalArgumentException.class, () -> service.join(newReservation));
  }
}