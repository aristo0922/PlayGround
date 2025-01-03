package com.villain.play.ground.PlayGround.party;

import com.villain.play.ground.PlayGround.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartyService {

  private final PartyRepository partyRepository;
  public void join(Reservation request){
    if(true)
      throw new IllegalStateException();
  }

  public Party detail(int id) {
    Party party = partyRepository.getPartyById(id);
    if (party == null)
      throw new IllegalArgumentException();
    return party;
  }
}
