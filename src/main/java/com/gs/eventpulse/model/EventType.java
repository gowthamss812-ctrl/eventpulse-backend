package com.gs.eventpulse.model;

/**
 * The fixed set of event types EventPulse tracks.
 * Using an enum instead of a plain String gives us compile-time safety —
 * an invalid event type is a compile error, not a silent runtime bug.
 */
public enum EventType {
    LOGIN_SUCCESS,
    LOGIN_FAILURE,
    PAGE_VIEW,
    VIDEO_PLAY,
    BANNER_CLICK,
    SCROLL_DEPTH
}
