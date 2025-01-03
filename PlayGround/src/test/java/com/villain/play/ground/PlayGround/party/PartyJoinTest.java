package com.villain.play.ground.PlayGround.party;

import static org.mockito.Mockito.when;

import com.villain.play.ground.PlayGround.reservation.Reservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PartyJoinTest {

  @InjectMocks
  PartyService service;

  @Mock
  PartyRepository partyRepository;

  private Party fullParty;
  private Party joinableParty;

  @BeforeEach
  void init() {
    this.fullParty = new Party(0, "sound cloud", "live and fall", "villain", 0, 6, 6);
    this.joinableParty = new Party(1, "sound cloud", "live and fall", "villain musk", 1, 6, 1);
  }

  @Test
  @DisplayName("풀파티 참가 요청 실패")
  void fullPartyTest() {
    Reservation request = new Reservation.Builder().member("오드").user("아령").party(0).build();
    Assertions.assertThrows(IllegalStateException.class, () -> {service.join(request);});
  }

  @Test
  @DisplayName("이미 선점한 멤버로 파티 가입 요청 실패")
  void noSeatTest(){
    int recruitId = 0;
    Party party = new Party(0, "sound cloud", "live and fall", "villain", 0, 6, 6);
    Reservation request = new Reservation.Builder().member("오드").user("아령").party(0).build();
    Assertions.assertThrows(IllegalArgumentException.class, () -> {service.join(request);});

//    when(reservationRepository.getListByPartyId(request.getParty())).thenReturn(list);
    // 1. party 가져온다. (-> reservation service 에 파티 id, 선점 멤버로 조인 가능 여부 확인)
//    2. 선점 관련 서비스 연결, 파티 아이디, 선점 멤버가 동일한 레코드가 이미 존재하는지 확인
//      : party 멤버 선점 여부 확인한다
//    2-1. 자리 잇으면 -> 3. 파티 가입 승인
//    2-2. 자리 없으면 -> IllegalStateException -> 3. 파티 가입 거절
  }

  @Test
  @DisplayName("선점 멤버 선택 안하고 파티 가입")
  void failTest(){
    Reservation request = new Reservation.Builder().user("아령").party(0).build();
    Assertions.assertThrows(IllegalArgumentException.class, () -> {service.join(request);});

    // 1. 입력값 검증(Service 책임: 비어있는 입력값이 있는가) -> IllegalArgumentException
    // 2. (빈 입력값 존재) - error response entity 처리? 에러 메시지: 재시도 요청하기?
  }

  @Test
  @DisplayName("선입금 여부 선택 안하고 파티 가입")
  void failTest2(){
    Reservation request = new Reservation.Builder().member("오드").user("아령").party(0).build();
    Assertions.assertThrows(IllegalArgumentException.class, () -> {service.join(request);});

    // 1. 입력값 검증(Service 책임: 비어있는 입력값이 있는가) -> IllegalArgumentException
    // 2. (빈 입력값 존재) - error response entity 처리? 에러 메시지: 재시도 요청하기?
  }

  @Test
  @DisplayName("존재하지 않는 파티 조회 요청")
  void party_test() {
    int requestId = 99999;

    when(partyRepository.getPartyById(requestId)).thenReturn(null);

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      service.detail(requestId);
    });
  }


}