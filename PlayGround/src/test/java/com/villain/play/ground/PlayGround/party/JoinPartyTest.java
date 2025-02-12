package com.villain.play.ground.PlayGround.party;


import static org.mockito.Mockito.when;

import com.villain.play.ground.PlayGround.album.Album;
import com.villain.play.ground.PlayGround.constant.Artist;
import com.villain.play.ground.PlayGround.reservation.Reservation;
import com.villain.play.ground.PlayGround.reservation.ReservationDTO;
import com.villain.play.ground.PlayGround.user.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
  private List<ReservationDTO> reservationDTOList;

  private Party party;
  private static final Long targetId = 2L;
  private static final String leader = "ant";
  private static final String user = "musk";
  private static Reservation initReservation;

  private static User userLeader;

  @BeforeEach
  void init() {
    // given: 파티 및 예약 데이터 초기화
    String[] members = {"건일", "정수", "가온", "오드", "준한", "주연"};

    reservationDTOList = new ArrayList<>();
    for(String member : members){
      reservationDTOList.add(new ReservationDTO(targetId, member, user));
    }

    userLeader = new User("name", "email");

    Album album = new Album("Live and Fall", Artist.XDINARY_HEROES);
    party = new Party(targetId, "Sound Wave", album, userLeader, 1L, 6, new ArrayList<>());

    initReservation = new Reservation(party, members[0], leader);
    party.addReservation(initReservation);
  }

  @DisplayName("기존에 존재하는 ant 파티 참가하기")
  @Test
  void joinPartySuccessfully(){
    // given
    when(partyRepository.findPartyById(targetId)).thenReturn(Optional.of(party));
    ReservationDTO newReservationDTO = reservationDTOList.get(2);

    // when
    service.join(newReservationDTO);
    Party result = service.getParty(targetId);
    List<Reservation> reservations = result.getReservations();

    // then
    Assertions.assertEquals(2, reservations.size());
    Assertions.assertEquals(initReservation, reservations.get(0));
    Assertions.assertEquals(newReservationDTO.getMember(), reservations.get(1).getMember());
    Assertions.assertEquals(newReservationDTO.getUser(), reservations.get(1).getUser());
    Assertions.assertEquals(newReservationDTO.getPartyId(), reservations.get(1).getParty());
  }

  @DisplayName("이미 선점된 멤버로 파티 참가 불가")
  @Test
  void failToJoinWithExistingMember(){
    // given
    when(partyRepository.findPartyById(targetId)).thenReturn(Optional.of(party));
    ReservationDTO newReservationDTO = reservationDTOList.get(0); // 이미 예약된 멤버

    Assertions.assertThrows(IllegalArgumentException.class, () -> service.join(newReservationDTO));
  }

  @DisplayName("존재하지 않는 멤버는 선점할 수 없다.")
  @Test
  void failToJoinWithInvalidMember(){
    // given
    when(partyRepository.findPartyById(targetId)).thenReturn(Optional.of(party));
    ReservationDTO newReservationDTO = new ReservationDTO(targetId, "아령", "musk");
    newReservationDTO.setMember("아령");

    // when & then
    Assertions.assertThrows(IllegalArgumentException.class, () -> service.join(newReservationDTO));
  }
}