package com.villain.play.ground.PlayGround.party;

import com.villain.play.ground.PlayGround.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartyService {

  private final PartyRepository partyRepository;
  public void join(Reservation request, int partyId){
    Party party = PartyRepository.getPartyById(partyId);

    if(party.isFull()){
      throw new IllegalStateException("Cannot Access to this party.");
    }
    if(party.isReservedMember(request.getMember())){
      throw new IllegalArgumentException("Cannot Access to this party.");
    }
    party.addReservation(request);
    // todo 선입금 여부 선택 했는가 - reservation
    // todo 존재하는 파티인가 -> jpa 가 데이터가 없을 때 반환하는 게 뭔지 봐야할
  }

  public Party detail(int id) {
    Party party = partyRepository.getPartyById(id);
    if (party == null)
      throw new IllegalArgumentException();
    return party;
  }

  public void deleteAllReservations(int id){
    Party party = partyRepository.getPartyById(id);
    party.deleteAllReservations();
  }
}
