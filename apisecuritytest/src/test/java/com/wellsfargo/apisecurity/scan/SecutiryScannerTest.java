/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.wellsfargo.apisecurity.scan;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

class SecutiryScannerTest {
	
    @Test void someLibraryMethodReturnsTrue() throws IOException, InterruptedException {
    	SecutiryScanner classUnderTest = new SecutiryScanner();
        assertTrue(classUnderTest.scan(), "scan should return 'true'");
    }
}
