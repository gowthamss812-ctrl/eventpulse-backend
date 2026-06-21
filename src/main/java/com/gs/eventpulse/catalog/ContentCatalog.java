package com.gs.eventpulse.catalog;

import java.util.List;

/**
 * The mock content catalog — hardcoded reference data for the demo app.
 * Hardcoded deliberately: this is static demo content, not a frequently
 * changing business asset, so the simplicity of a Java constant outweighs
 * the flexibility a database/config file would add but we'd never use.
 */
public final class ContentCatalog {

    private ContentCatalog() {
        // utility class — no instances needed
    }

    public static final List<ContentItem> VIDEOS = List.of(
            new ContentItem("video-1", "The Mountain Trail"),
            new ContentItem("video-2", "City Lights Documentary"),
            new ContentItem("video-3", "Ocean Depths"),
            new ContentItem("video-4", "Desert Stories")
    );

    public static final List<ContentItem> OFFERS = List.of(
            new ContentItem("offer-1", "Summer Streaming Sale"),
            new ContentItem("offer-2", "New Releases This Week"),
            new ContentItem("offer-3", "Upgrade to Premium")
    );

    public static boolean isValidVideoId(String videoId) {
        return VIDEOS.stream().anyMatch(v -> v.id().equals(videoId));
    }

    public static boolean isValidOfferId(String offerId) {
        return OFFERS.stream().anyMatch(o -> o.id().equals(offerId));
    }
}