package com.gs.eventpulse.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Class-level validation: checks that an Event's `metadata` map contains
 * the required fields for its `eventType`.
 * Example: a VIDEO_PLAY event must have `videoId` in metadata.
 * This runs automatically wherever @Valid is used on an Event — same as
 * the built-in @NotBlank / @NotNull annotations — so no one can forget
 * to call it manually.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EventMetadataValidator.class)
@Documented
public @interface ValidEventMetadata {

    String message() default "metadata is missing required fields for this eventType";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}