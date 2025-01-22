package com.villain.play.ground.PlayGround.party;

import com.villain.play.ground.PlayGround.request.NewParty;
import com.villain.play.ground.PlayGround.reservation.Reservation;
import lombok.RequiredArgsConstructor;
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
  }

  public Party save(NewParty newParty){
    if (newParty.hasNullField()) throw new IllegalArgumentException("[ ERROR ] There are any initialized fields.");
    Party party = new Party.PartyBuilder().platform(newParty.getPlatform()).leader(newParty.getLeader()).recruit(newParty.getRecruit()).maximum(
        newParty.getMaximum()).build();
    return partyRepository.save(party);
  }

  public Party getParty(Long id){
    Party party =partyRepository.findPartyById(id);
    if(party == null)
      throw new IllegalArgumentException("[ ERROR ] 존재하지 않는 파티 정보 입니다.");
    return party;
  }

  public void deleteAllReservations(Long id){
    Party party = partyRepository.findPartyById(id);
    party.deleteAllReservations();
  }
}
