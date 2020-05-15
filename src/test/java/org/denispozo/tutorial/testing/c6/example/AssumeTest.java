package org.denispozo.tutorial.testing.c6.example;

import static org.junit.Assume.assumeTrue;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class AssumeTest {

    @Test
    public void shouldRunOnlyOnMacOS() {
        assumeTrue(isThisAMacOSMachine());
        log.info("Running on Mac OS!");
    }

    private boolean isThisAMacOSMachine() {
        return System.getProperty("os.name").startsWith("Mac OS");
    }

    @Test
    public void shouldRunOnlyOnLinux() {
        assumeTrue(isThisLinuxMachine());
        log.info("Running on Linux!");
    }

    private boolean isThisLinuxMachine() {
        return System.getProperty("os.name").startsWith("Linux");
    }
}
