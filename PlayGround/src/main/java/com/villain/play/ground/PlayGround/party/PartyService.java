package com.villain.play.ground.PlayGround.party;

import com.villain.play.ground.PlayGround.request.NewParty;
import com.villain.play.ground.PlayGround.reservation.Reservation;
import com.villain.play.ground.PlayGround.reservation.ReservationDTO;
import com.villain.play.ground.PlayGround.user.User;
import com.villain.play.ground.PlayGround.user.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PartyService {

  private final PartyRepository partyRepository;
  private final UserService userService;

  public void join(ReservationDTO request){
    Long partyId = request.getPartyId();
    Party party = partyRepository.findPartyById(partyId).get();

    String member = request.getMember();
    if(party.isReservedMember(member)) throw new IllegalArgumentException("[Error] Already reserved member. Please choose other one.");
    if(party.isArtist(member) == false)throw new IllegalArgumentException("[Error] Please choose this album's artist.");

    Reservation reservation = new Reservation(party, member, request.getUser());
    party.addReservation(reservation);
  }

  public Party save(NewParty newParty){
    if (newParty.hasNullField()) throw new IllegalArgumentException("[ ERROR ] There are any initialized fields.");
    User user = userService.findById(newParty.getLeader());
    Party party = new Party.PartyBuilder().platform(newParty.getPlatform()).leader(user).recruit(newParty.getRecruit()).maximum(
        newParty.getMaximum()).build();
    return partyRepository.save(party);
  }

  public Party getParty(Long id){
    Optional<Party> result =partyRepository.findPartyById(id);
    if(result.isEmpty())
      throw new IllegalArgumentException("[ ERROR ] 존재하지 않는 파티 정보 입니다.");
    return result.get();
  }

  public void deleteAllReservations(Long id){
    Optional<Party> result =partyRepository.findPartyById(id);
    if(result.isEmpty())
      throw new IllegalArgumentException("[ ERROR ] 존재하지 않는 파티 정보 입니다.");
    result.get().deleteAllReservations();
  }
}
