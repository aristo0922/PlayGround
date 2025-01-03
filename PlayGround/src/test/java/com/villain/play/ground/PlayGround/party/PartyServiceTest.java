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
class PartyServiceTest {
  Party party ;
  @InjectMocks
  PartyService service;

  @Mock
  PartyRepository partyRepository;

  @BeforeEach
  void init(){
    this.party = new Party();
  }

//  @Test
//  @DisplayName("인원수_초과_상태_파티_가입")
//  void 인원수_초과_상태_파티_가입(){
//    // Given
//    Party mockParty = Mockito.mock(Party.class);
//    when(mockParty.isFull()).thenReturn(true); // 파티가 이미 가득 찬 상태
//
//    Reservation request = new Reservation.Builder().member("오드").user("아령").party("0").build();
//
//    Assertions.assertThrows(IllegalStateException.class, () -> {service.join(request);});
//  }

  @Test
  @DisplayName("존재하지 않는 파티 조회 요청")
  void party_test(){
    int requestId= 99999;

    when(partyRepository.getPartyById(requestId)).thenReturn(null);

    Assertions.assertThrows(IllegalArgumentException.class, () -> {service.detail(requestId);});
  }


}