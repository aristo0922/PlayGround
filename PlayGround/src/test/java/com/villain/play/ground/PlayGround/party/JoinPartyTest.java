package com.villain.play.ground.PlayGround.party;


import static org.mockito.Mockito.when;

import com.villain.play.ground.PlayGround.album.Album;
import com.villain.play.ground.PlayGround.constant.Artist;
import com.villain.play.ground.PlayGround.reservation.ReservationDTO;
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
  List<ReservationDTO> reservationDTO = new ArrayList<>();

  private Party party;
  Long targetId = 2L;
  ReservationDTO leaderReservationDTO;

  @BeforeEach
  void init() {
    String[] members = {"건일", "정수", "가온", "오드", "준한", "주연"};
    long partyId = targetId;
    String user = "musk";
    String leader = "ant";
    for(String member : members){
      reservationDTO.add(new ReservationDTO(partyId, member, user));
    }

    Album album = new Album("Live and Fall", Artist.XH);
    party = new Party(targetId, "Sound Wave", album, 1L, 1L, 6, new ArrayList<>());
    leaderReservationDTO = new ReservationDTO(targetId, members[0], leader);
    party.addReservation(leaderReservationDTO);
  }

  @DisplayName("기존에 존재하는 ant 파티 참가하기")
  @Test
  void join(){
    when(partyRepository.findPartyById(targetId)).thenReturn(party);
    ReservationDTO newReservationDTO = reservationDTO.get(2);

    service.join(newReservationDTO);

    Party result = service.getParty(targetId);
    List<ReservationDTO> reservationDTOList = result.getReservationDTOS();

    Assertions.assertEquals(leaderReservationDTO, reservationDTOList.get(0));
    Assertions.assertEquals(newReservationDTO, reservationDTOList.get(1));
  }

  @DisplayName("이미 선점된 멤버로 파티 참가 불가")
  @Test
  void fail_join(){
    when(partyRepository.findPartyById(targetId)).thenReturn(party);
    ReservationDTO newReservationDTO = reservationDTO.get(2);

    service.join(newReservationDTO);
    Assertions.assertThrows(IllegalArgumentException.class, () -> service.join(newReservationDTO));
  }

  @DisplayName("존재하지 않는 멤버는 선점할 수 없다.")
  @Test
  void fail_join2(){
    when(partyRepository.findPartyById(targetId)).thenReturn(party);
    ReservationDTO newReservationDTO = reservationDTO.get(2);
    newReservationDTO.setMember("아령");

    Assertions.assertThrows(IllegalArgumentException.class, () -> service.join(newReservationDTO));
  }


}