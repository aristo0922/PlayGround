package com.villain.play.ground.PlayGround.album;

import com.villain.play.ground.PlayGround.party.Party;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class AlbumDto {

  private String albumName;
  private String artist;
  private List<Party> partyList;
}
