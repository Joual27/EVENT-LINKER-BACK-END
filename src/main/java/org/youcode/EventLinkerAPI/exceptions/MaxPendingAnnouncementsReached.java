package org.youcode.EventLinkerAPI.exceptions;

public class MaxPendingAnnouncementsReached extends RuntimeException {
    public MaxPendingAnnouncementsReached(String message) {
        super(message);
    }
}
