package com.villain.play.ground.PlayGround.party;

import com.villain.play.ground.PlayGround.party.Party.PartyBuilder;
import com.villain.play.ground.PlayGround.request.NewParty;
import com.villain.play.ground.PlayGround.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PartyService {

  private final PartyRepository partyRepository;
  public void join(Reservation request){
    Long partyId = request.getPartyId();
    Party party = partyRepository.findPartyById(partyId);

//    if(party.isFull()){
//      throw new IllegalStateException("Cannot Access to this party.");
//    }
//    if(party.isReservedMember(request.getMember())){
//      throw new IllegalArgumentException("Cannot Access to this party.");
//    }
//    party.addReservation(request);
    // todo 선입금 여부 선택 했는가 - reservation
    // todo 존재하는 파티인가 -> jpa 가 데이터가 없을 때 반환하는 게 뭔지 봐야할
  }

  public Party save(NewParty newParty){
    if (newParty.hasNullField()) throw new IllegalArgumentException("[ ERROR ] There are any initialized fields.");
    Party party = new Party.PartyBuilder().platform(newParty.getPlatform()).leader(newParty.getLeader()).recruit(newParty.getRecruit()).maximum(
        newParty.getMaximum()).build();
    return partyRepository.save(party);
  }

  public Party getParty(Long id){
    return partyRepository.findPartyById(id);
  }
  public Party detail(Long id) {
    return partyRepository.findPartyById(id);
//    Party party = partyRepository.findPartyById(id);
//    if (party == null)
//      throw new IllegalArgumentException();
//    return party;
  }

  public void deleteAllReservations(Long id){
    Party party = partyRepository.findPartyById(id);
    party.deleteAllReservations();
  }
}
