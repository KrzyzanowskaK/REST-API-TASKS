package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloBadgesList {

    @JsonProperty("votes")
    private int votes;

    @JsonProperty("attachmentsByType")
    private TrelloAttachmentsByTypeDto attachmentsByType;
}
