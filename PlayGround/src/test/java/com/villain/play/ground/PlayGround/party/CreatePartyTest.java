//package com.villain.play.ground.PlayGround.party;
//
//import static org.awaitility.Awaitility.given;
//import static org.mockito.ArgumentMatchers.any;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.api.extension.Extension;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.util.ReflectionTestUtils;
//
//@ExtendWith(MockitoExtension.class)
//public class CreatePartyTest {
//
//  List<Party> listToCreate = new ArrayList<>();
//
//  @InjectMocks
//  PartyService partyService;
//
//  @Mock
//  private PartyRepository partyRepository;
//
//  Party party1 = new Party.PartyBuilder().platform("JYP shop").album("HELLO WORLD").id(0).recruit(0L)
//      .maximum(6).build();
//  Party party2 = new Party.PartyBuilder().platform("SoundWave").album("HELLO WORLD").id(0)
//      .recruit(0L).maximum(6).build();
//  Party party3 = new Party.PartyBuilder().platform("MyMusic").album("HELLO WORLD").id(0).recruit(0L)
//      .maximum(6).build();
//  Party party4 = new Party.PartyBuilder().platform("Hanter").album("HELLO WORLD").id(0).recruit(0L)
//      .maximum(6).build();
//  Party party5 = new Party.PartyBuilder().platform("Spotify").album("HELLO WORLD").id(0).recruit(0L)
//      .maximum(6).build();
//  Party party6 = new Party.PartyBuilder().platform("Melon").album("HELLO WORLD").id(0).recruit(0L)
//      .maximum(6).build();
//
//  @BeforeEach
//  void setUp() {
//    listToCreate.add(party1);
//    listToCreate.add(party2);
//    listToCreate.add(party3);
//    listToCreate.add(party4);
//    listToCreate.add(party5);
//    listToCreate.add(party6);
//  }
////
////  @DisplayName("파티_저장하기")
////  @Test
////  void save() {
////    long i = 0L;
////    for (Party party : listToCreate) {
////      given(partyRepository.save(party)).willReturn(party);
////      partyService.save(party);
////      Party inserted = partyService.getParty(i++);
////      Assertions.assertEquals(party.getPlatform(), inserted.getPlatform());
////    }
////  }
////
////  @Test
////  void test(){
////    Long fakePartyId = 1L;
////    ReflectionTestUtils.setField(party1, "id", fakePartyId);
////
////    given(partyRepository.save(any())).willReturn(party1);
////    given(partyRepository.findPartyById(fakePartyId)).willReturn(Optional.ofNullable(party1));
////
////  }
//}
