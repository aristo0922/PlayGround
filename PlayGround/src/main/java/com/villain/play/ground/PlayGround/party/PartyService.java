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

  public void join(Reservation request, int partyId){
    Party party = PartyRepository.getPartyById(partyId);
    // 풀파티인가 - 파티
    // 이미 선점된 멤버인가 - 파티
    // 선점할 멤버를 선택했는가 - reservation
    // 선입금 여부 선택 했는가 - reservation
    // 존재하는 파티인가 -> jpa 가 데이터가 없을 때 반환하는 게 뭔지 봐야할
  }

  public Party detail(int id) {
    Party party = partyRepository.getPartyById(id);
    if (party == null)
      throw new IllegalArgumentException();
    return party;
  }
}
