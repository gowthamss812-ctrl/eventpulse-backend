package com.gs.eventpulse.validation;

import com.gs.eventpulse.model.Event;
import com.gs.eventpulse.model.EventType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Map;
import java.util.Set;

/**
 * The actual logic behind @ValidEventMetadata.
 * Checks that an Event's metadata map contains the required keys for
 * its eventType. Each event type's required fields are defined once,
 * in REQUIRED_FIELDS below — add a new event type by adding one line here.
 */
public class EventMetadataValidator implements ConstraintValidator<ValidEventMetadata, Event> {

    private static final Map<EventType, Set<String>> REQUIRED_FIELDS = Map.of(
            EventType.LOGIN_SUCCESS, Set.of(),
            EventType.LOGIN_FAILURE, Set.of("reason"),
            EventType.PAGE_VIEW, Set.of("page"),
            EventType.VIDEO_PLAY, Set.of("videoId", "videoTitle"),
            EventType.BANNER_CLICK, Set.of("offerId", "offerTitle"),
            EventType.SCROLL_DEPTH, Set.of("page", "depthPercent")
    );

    @Override
    public boolean isValid(Event event, ConstraintValidatorContext context) {
        if (event == null || event.getEventType() == null) {
            // let @NotNull on eventType handle this case separately
            return true;
        }

        Set<String> required = REQUIRED_FIELDS.get(event.getEventType());
        if (required == null || required.isEmpty()) {
            return true;
        }

        Map<String, Object> metadata = event.getMetadata();
        if (metadata == null) {
            return buildFailure(context, event.getEventType(), required);
        }

        for (String field : required) {
            if (!metadata.containsKey(field) || metadata.get(field) == null) {
                return buildFailure(context, event.getEventType(), required);
            }
        }

        return true;
    }

    private boolean buildFailure(ConstraintValidatorContext context, EventType type, Set<String> required) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                "metadata for eventType " + type + " must include: " + required
        ).addConstraintViolation();
        return false;
    }
}