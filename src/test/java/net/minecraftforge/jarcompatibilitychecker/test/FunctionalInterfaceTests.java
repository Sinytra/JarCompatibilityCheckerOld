/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.minecraftforge.jarcompatibilitychecker.test;

import net.minecraftforge.jarcompatibilitychecker.core.IncompatibilityMessages;
import org.junit.Test;

import java.nio.file.Path;

public class FunctionalInterfaceTests extends BaseCompatibilityTest {
    @Override
    protected Path getRoot() {
        return super.getRoot().resolve("FunctionalInterface");
    }

    @Test
    public void testSamChanged() {
        // A SAM with a changed descriptor in a functional interface is binary incompatible
        assertIncompatible(false, "SAMChanged", "A",
                new IncompatibilityData("a", "()V", IncompatibilityMessages.FUNCTIONAL_INTERFACE_SAM_CHANGED, "a(I)V"),
                new IncompatibilityData("a", "()V", IncompatibilityMessages.API_METHOD_REMOVED),
                new IncompatibilityData("a", "(I)V", IncompatibilityMessages.METHOD_MADE_ABSTRACT));
    }

    @Test
    public void testSamMadeDefault() {
        // A SAM made default in a functional interface is binary incompatible
        assertIncompatible(false, "SAMMadeDefault", "A", "a", "()V", IncompatibilityMessages.FUNCTIONAL_INTERFACE_SAM_MADE_DEFAULT);
    }

    @Test
    public void testSamRemoved() {
        // A removed SAM in a functional interface is binary incompatible
        assertIncompatible(false, "SAMRemoved", "A",
                new IncompatibilityData("a", "()V", IncompatibilityMessages.FUNCTIONAL_INTERFACE_SAM_REMOVED),
                new IncompatibilityData("a", "()V", IncompatibilityMessages.API_METHOD_REMOVED));
    }
}
