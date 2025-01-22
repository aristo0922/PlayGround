package com.villain.play.ground.PlayGround.party;

import com.villain.play.ground.PlayGround.request.NewParty;
import com.villain.play.ground.PlayGround.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/party")
public class PartyController {

  private final PartyService partyService;

  @PostMapping
  public void create(@RequestBody NewParty party){
    partyService.save(party);
  }

  @GetMapping("/{partyId}")
  public Party read(@PathVariable("partyId") long partyId){
    return partyService.getParty(partyId);
  }

  @PostMapping("/{partyId}/join")
  public String join(@PathVariable("partyId") int partyId, @RequestBody Reservation reservation){
//    partyService.join(reservation, partyId);
    try{
      reservation.setPartyId(partyId);
      return reservation.toString() + " -> reservation";
    }catch (IllegalArgumentException e){
      System.out.println("[IllegalArgumentException] member or user is null");
    }
    return reservation.toString() + " -> builder";
  }
}
