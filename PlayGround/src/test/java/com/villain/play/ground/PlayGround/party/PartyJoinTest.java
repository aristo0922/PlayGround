package com.villain.play.ground.PlayGround.party;

import static org.mockito.Mockito.when;

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

  Party party;
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
    Reservation request = new Reservation.Builder().member("오드").user("아령").party("0").build();
    Assertions.assertThrows(IllegalStateException.class, () -> {service.join(request);});
  }

  @Test
  @DisplayName("이미 선점한 멤버로 파티 가입")
  void noSeatTest(){
    Reservation request = new Reservation.Builder().member("오드").user("아령").party("0").build();
// 1. party 가져온다.
//    2. party 멤버 선점 여부 확인한다
//    2-1. 자리 잇으면 -> 3. 파티 가입 승인
//    2-2. 자리 없으면 -> 3. 파티 가입 거절
  }

  @Test
  @DisplayName("선점 멤버 선택 안하고 파티 가입")
  void failTest(){
    Reservation request = new Reservation.Builder().user("아령").party("0").build();
    // 1. 입력값 검증(Service 책임: 비어있는 입력값이 있는가)
    // 2. (빈 입력값 존재) - error response entity 처리? 에러 메시지: 재시도 요청하기?
  }

  @Test
  @DisplayName("선입금 여부 선택 안하고 파티 가입")
  void failTest2(){
    Reservation request = new Reservation.Builder().member("오드").user("아령").party("0").build();
    // 1. 입력값 검증(Service 책임: 비어있는 입력값이 있는가)
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