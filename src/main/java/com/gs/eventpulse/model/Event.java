package com.gs.eventpulse.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.eventpulse.validation.ValidEventMetadata;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

/**
 * The shared "envelope" shape for every event EventPulse tracks.
 * Event-specific data (videoId, offerId, depthPercent, etc.) lives inside
 * `metadata` rather than as separate fields on this class — this is what
 * lets us support new event types later without changing this class.
 * metadata is Map<String, Object> (not Map<String, String>) so numeric
 * fields like depthPercent stay as actual numbers, matching the natural
 * shape of the incoming JSON instead of forcing everything through
 * string parsing.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidEventMetadata
public class Event {

    @NotBlank(message = "eventId is required")
    private String eventId;

    @NotBlank(message = "userId is required")
    private String userId;

    @NotNull(message = "eventType is required")
    private EventType eventType;

    @NotNull(message = "timestamp is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant timestamp;

    private Map<String, Object> metadata;
}