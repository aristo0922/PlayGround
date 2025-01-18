package com.villain.play.ground.PlayGround.party;

import com.villain.play.ground.PlayGround.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PartyService {

  private final PartyRepository partyRepository;
  public void join(Reservation request, Long partyId){
    Party party = partyRepository.findPartyById(partyId);

    if(party.isFull()){
      throw new IllegalStateException("Cannot Access to this party.");
    }
    if(party.isReservedMember(request.getMember())){
      throw new IllegalArgumentException("Cannot Access to this party.");
    }
    party.addReservation(request);
  }

  public Party save(Party party){
    return partyRepository.save(party);
  }

  public Party getParty(Long id){
    return partyRepository.findPartyById(id);
  }
  public Party detail(Long id) {
    return partyRepository.findPartyById(id);
  }

  public void deleteAllReservations(Long id){
    Party party = partyRepository.findPartyById(id);
    party.deleteAllReservations();
  }
}
