package com.gs.eventpulse.catalog;

/**
 * A single catalog entry — either a video or an offer/banner.
 * Using a Java `record` here (not a regular class) because this is pure,
 * immutable data with no behavior — records exist exactly for this case
 * and generate the constructor/getters/equals/toString automatically,
 * with even less boilerplate than Lombok's @Data.
 */
public record ContentItem(String id, String title) {
}