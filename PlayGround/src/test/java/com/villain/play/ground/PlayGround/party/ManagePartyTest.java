package com.villain.play.ground.PlayGround.party;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ManagePartyTest {

  @InjectMocks
  private PartyService service;

  @Mock
  private PartyRepository partyRepository;

//  @BeforeEach
//  void set_up(){
//    partyRepository
//  }


  @DisplayName("파티를 생성할 수 있다.")
  @Test
  void create(){
    service.create(new Party.PartyBuilder().platform("sound wave").album("Live and Fall").leader(1L).recruit(1L).maximum(6).build());
  }

}
