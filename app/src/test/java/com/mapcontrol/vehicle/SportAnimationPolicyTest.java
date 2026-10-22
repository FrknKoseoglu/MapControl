package com.mapcontrol.vehicle;

import org.junit.Test;
import static org.junit.Assert.*;

public class SportAnimationPolicyTest {
    @Test public void everyConfirmedNonSportEntryWaitsThenRequestsExactlyOnce() {
        for (int from : new int[]{0, 1, 3, 4, 5, 7}) {
            SportAnimationPolicy policy = new SportAnimationPolicy();
            assertFalse(policy.observe(true, true, from, 0));
            assertFalse(policy.observe(true, true, 2, 100));
            assertFalse(policy.observe(true, true, 2, 699));
            assertTrue(policy.observe(true, true, 2, 700));
            assertFalse(policy.observe(true, true, 2, 701));
            assertFalse(policy.observe(true, true, 2, 100000));
        }
    }
    @Test public void initialSportSampleDoesNotDismissAnExistingWindow() {
        SportAnimationPolicy policy = new SportAnimationPolicy();
        assertFalse(policy.observe(true, true, 2, 0));
        assertFalse(policy.observe(true, true, 2, 100000));
    }
    @Test public void leavingSportBeforeSettleCancelsTheRequest() {
        SportAnimationPolicy policy = new SportAnimationPolicy();
        policy.observe(true, true, 1, 0);
        policy.observe(true, true, 2, 100);
        assertFalse(policy.observe(true, true, 1, 699));
        assertFalse(policy.observe(true, true, 1, 1000));
    }
    @Test public void disconnectCancelsPendingAndReconnectionInSportOnlyEstablishesBaseline() {
        SportAnimationPolicy policy = new SportAnimationPolicy();
        policy.observe(true, true, 1, 0);
        policy.observe(true, true, 2, 100);
        assertFalse(policy.observe(true, false, 2, 400));
        assertFalse(policy.observe(true, true, 2, 1000));
        assertFalse(policy.observe(true, true, 2, 5000));
    }
    @Test public void unknownValuesCancelPendingRatherThanActingAsAnotherMode() {
        for (int invalid : new int[]{-1, 6, 8, Integer.MIN_VALUE}) {
            SportAnimationPolicy policy = new SportAnimationPolicy();
            policy.observe(true, true, 1, 0);
            policy.observe(true, true, 2, 100);
            assertFalse(policy.observe(true, true, invalid, 400));
            assertFalse(policy.observe(true, true, 2, 1000));
            assertFalse(policy.observe(true, true, 2, 5000));
        }
    }
    @Test public void disablingCancelsAndReenablingInSportDoesNotClose() {
        SportAnimationPolicy policy = new SportAnimationPolicy();
        policy.observe(true, true, 1, 0);
        policy.observe(true, true, 2, 100);
        assertFalse(policy.observe(false, true, 2, 400));
        assertFalse(policy.observe(true, true, 2, 1000));
        assertFalse(policy.observe(true, true, 2, 5000));
    }
    @Test public void eachNewEntryHasItsOwnSettlePeriod() {
        SportAnimationPolicy policy = new SportAnimationPolicy();
        policy.observe(true, true, 1, 0);
        policy.observe(true, true, 2, 100);
        policy.observe(true, true, 1, 600);
        policy.observe(true, true, 2, 650);
        assertFalse(policy.observe(true, true, 2, 700));
        assertTrue(policy.observe(true, true, 2, 1250));
    }
    @Test public void anotherFullSportEntryCanRequestAfterAnEarlierRequestCompleted() {
        SportAnimationPolicy policy = new SportAnimationPolicy();
        policy.observe(true, true, 1, 0);
        policy.observe(true, true, 2, 100);
        assertTrue(policy.observe(true, true, 2, 700));
        policy.observe(true, true, 1, 1000);
        policy.observe(true, true, 2, 2000);
        assertTrue(policy.observe(true, true, 2, 2600));
    }
    @Test public void disabledNeverRequestsAcrossModeChanges() {
        SportAnimationPolicy policy = new SportAnimationPolicy();
        for (int i = 0; i < 30; i++) assertFalse(policy.observe(false, true, i % 3, i * 1000));
    }
}
