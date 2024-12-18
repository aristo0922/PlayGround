package com.villain.play.ground.PlayGround.party;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JoinServiceTest {
  Party party ;
  @InjectMocks
  JoinService service;

  @BeforeEach
  void init(){
    this.party = new Party();
  }

  @Test
  @DisplayName("인원수_초과_상태_파티_가입")
  void 인원수_초과_상태_파티_가입(){
    // Given
    Party mockParty = Mockito.mock(Party.class);
    when(mockParty.isFull()).thenReturn(true); // 파티가 이미 가득 찬 상태

    Reservation request = new Reservation.Builder().member("오드").user("아령").party("0").build();

    Assertions.assertThrows(IllegalStateException.class, () -> {service.join(request);});
  }

}