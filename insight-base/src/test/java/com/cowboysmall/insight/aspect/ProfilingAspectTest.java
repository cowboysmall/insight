package com.cowboysmall.insight.aspect;

import com.cowboysmall.insight.TestContext;
import com.cowboysmall.insight.mock.MockLoggerService;
import com.cowboysmall.insight.object.SomeOtherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * jerry
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestContext.class)
public class ProfilingAspectTest {

    @Autowired
    private MockLoggerService loggerService;

    @Autowired
    private SomeOtherService someOtherService;


    //_________________________________________________________________________

    @Before
    public void setUp() {

        loggerService.clear();
    }


    //_________________________________________________________________________

    @Test
    public void testInsightProfilingAspect_Basic() {

        someOtherService.doSomething();

        assertEquals(1, loggerService.getMessageList().size());
        assertEquals(1, loggerService.getLevels().size());
        assertEquals(1, loggerService.getClasses().size());
        assertTrue(loggerService.getMessageList().get(0).contains("time taken to execute < doSomething > ="));
    }

    @Test
    public void testInsightProfilingAspect_BasicWithArgs() {

        someOtherService.doSomethingWithArgs("first", 2L);

        assertEquals(1, loggerService.getMessageList().size());
        assertEquals(1, loggerService.getLevels().size());
        assertEquals(1, loggerService.getClasses().size());
        assertTrue(loggerService.getMessageList().get(0).contains("time taken to execute < doSomethingWithArgs > ="));
    }

    @Test
    public void testInsightProfilingAspect_BasicWithReturn() {

        someOtherService.doSomethingWithReturn();

        assertEquals(1, loggerService.getMessageList().size());
        assertEquals(1, loggerService.getLevels().size());
        assertEquals(1, loggerService.getClasses().size());
        assertTrue(loggerService.getMessageList().get(0).contains("time taken to execute < doSomethingWithReturn > ="));
    }

    @Test
    public void testInsightProfilingAspect_BasicWithArgsAndReturn() {

        someOtherService.doSomethingWithArgsAndReturn("first", 2L, 3.0);

        assertEquals(1, loggerService.getMessageList().size());
        assertEquals(1, loggerService.getLevels().size());
        assertEquals(1, loggerService.getClasses().size());
        assertTrue(loggerService.getMessageList().get(0).contains("time taken to execute < doSomethingWithArgsAndReturn > ="));
    }
}
